import {useRef, useState, useEffect} from "react";
import {useParams} from "react-router-dom";
import * as StompJs from "@stomp/stompjs";
import ChatMessageList from './ChatMessageList';
import axios from "axios";
import Nav from "../../components/Nav";
import styled from 'styled-components';
import {FiSend} from "react-icons/fi";
import { useCallback } from "react";
import { useSelector } from 'react-redux';
import jwt from 'jwt-decode';

// 웹 소켓 연결할 endpoint
const BASE_URL = "ws://localhost:8082/ws-stomp";

// 지난 메세지 내역을 요청하기 위한 rest api path
const REQUEST_URL = "http://i8a402.p.ssafy.io/chat/message";

// 임의로 넣어둔 userID
// const user_id = 2;

// 임의로 넣어둔 유저 닉네임
// const user_nickname = "pickapicka";

const StyledInputWrapper = styled.div`
    /* display: flex; */
    position: fixed;
    bottom: 0;
    margin-left: 20px;
    margin-right: 20px;
    margin-bottom: 40px;
    >*{
        display: flex;
        align-items: center;
    }
`;

const StyledInput = styled.input`
    border-top: none;
    border-left: none;
    border-right: none;
    border-bottom: none;
    color: white;
    font-size: 16px;
    font-family: var(--font-nanumSquareR);
    background-color: rgba(65, 22, 162, 0.5);
    padding: 10px;
    height: 20px;
    width: 260px;
    :focus{
        outline: none !important;
        /* border-bottom: 1px solid white; */
    }
`;

const StyledBtn = styled.div`
    background-color: rgba(65, 22, 162, 0.5);
    height: 20px;
    padding: 10px;
`;

const ChatRoom = () => {

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    const user_id = jwt(token)['userId']; 
    const user_nickname = jwt(token)['nickname']; 

    // 메세지들을 관리하는 state
    const [messageList, setMessageList] = useState([]);
    // 새로 작성하는 메세지를 관리하는 state -> 이 메세지를 전송하고, 전송 후엔 사라짐
    const [message, setMessage] = useState('');

    // path에서 채팅방 아이디 받아옴
    const {channel_id} = useParams();

    // 소켓 연결 클라이언트 관리
    const client = useRef({});

    // websocket 연결, STOMP 사용
    const connect = () => {
        console.log("id: "+channel_id);
        client.current = new StompJs.Client({
            brokerURL: BASE_URL,
            onConnect: () => {
                console.log("소켓 연결 성공!");
                // 연결에 성공하면 subscribe 함수를 콜함
                subscribe();
            },
        });
        // 웹 소켓 연결 활성화
        client.current.activate();
    };

    // 이 채팅방 채널을 구독함
    const subscribe = () => {
        console.log("subscribe 시작");
        client.current.subscribe(`/sub/${channel_id}`, (data) => {
            // sub된 메세지가 있을 경우 메세지 리스트에 추가함
            const json_data = JSON.parse(data.body);
            console.log(json_data);
            console.log("전송된 데이터 : " + json_data.content);
            setMessageList((message_list) => [
                ...message_list, json_data
            ]);
        });
    };

    // 메세지 전송 -> 서버에 pub
    const publish = (message) => {
        // 연결 안돼있는 경우 return
        if(!client.current.connected) return;

        console.log(message);

        // 채팅방 채널에 유저 아이디, 닉네임, 메세지 내용 전송 
        // contentType은 이미지 or 메세지인데 
        // 이미지 전송은 부스에서 엽서를 보낼 때에만 가능하니까 MESSAGE가 default
        client.current.publish({
            destination: `/pub/channel/${channel_id}`,
            body: JSON.stringify({
                userId: user_id,
                userNickname: user_nickname,
                contentType: "MESSAGE",
                content: message
            }),
        });

        // 전송 후엔 메세지 입력 칸 비워주기
        setMessage('');
    };

    // 웹소켓 해제시 콜
    const disconnect = () => {
        client.current.deactivate();
    };

    const handleChange = (event) => {
        setMessage(event.target.value);
    };

    // 전송 버튼 누를 경우
    const handleSubmit = (event, message) => {
        event.preventDefault();

        publish(message);
    };

    // 마운트될 때 웹 소켓 연결하고 메세지 목록 불러오기
    useEffect(() => {
        connect();

        axios.get(REQUEST_URL, {params: {channelId: channel_id}})
            .then((res)=>{
                setMessageList(res.data);
                console.log(res.data);
            })
            .catch(error => {
                console.log(error.response);
            })

        return () => disconnect();
    }, []);

    return (
        <div>
            <Nav/>
            {/* 메세지 리스트 컴포넌트 생성 */}
            <ChatMessageList messageList={messageList}/>
            <StyledInputWrapper>
                <form onSubmit={(event) => handleSubmit(event, message)}>
                    <StyledInput type={'text'} name={'chatInput'} onChange={handleChange} value={message} placeholder="새로운 사람과의 대화를 시작합니다."/>
                    <StyledBtn><FiSend type={'submit'}></FiSend></StyledBtn>
                </form>
            </StyledInputWrapper>
        </div>
    );
}
export default ChatRoom;