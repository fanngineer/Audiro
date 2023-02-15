import {useRef, useState, useEffect} from "react";
import {useParams} from "react-router-dom";
import * as StompJs from "@stomp/stompjs";
import ChatThumbnailList from './ChatThumbnailList'
import axios from "axios";
import Logo from "../../components/Logo";
import Nav from "../../components/Nav";
import jwt from 'jwt-decode';

// 웹 소켓 연결 endpoint
const BASE_URL = "ws://localhost:8082/ws-stomp";
// 채널 리스트를 요청하는 rest api path
const REQUEST_URL = "http://i8a402.p.ssafy.io/chat/channel/list";
// 임의로 넣어둔 사용자 ID
// let user_id = 2;
// 임의로 넣어둔 사용자 닉네임
// const user_nickname = "pickapicka";

const ChatList = () => {

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    const user_id = jwt(token)['userId']; 
    const user_nickname = jwt(token)['nickname']; 

    // 채팅 룸 리스트를 저장하는 state
    const [channelList, setChannelList] = useState([]);

    // websocket과 연결되는 클라이언트
    const client = useRef({});

    // 웹소켓 연결 + STOMP 사용
    const connect = () => {
        client.current = new StompJs.Client({
            brokerURL: BASE_URL,
            onConnect: () => {
                console.log("소켓 연결 성공!");
                if (channelList.length > 0 && channelList[0]) {
                    subscribe();
                }
            },
        });
        client.current.activate();
        // console.log("=====클라이언트", client)
    };

    // connect 후 리스트 목록 구독
    const subscribe = () => {
        console.log("subscribe 시작");
        console.log("subscribe 함수",typeof channelList, channelList)
        channelList.forEach((channel) => {
            // 리스트 forEach로 돌면서 구독
            console.log("포이치")
            client.current.subscribe(`/sub/${channel.channelId}`, async (data) => {
                // sub이 발생할 경우 useState이용해 목록을 업뎃 -> 렌더링,,이 다시 될 줄 알았는데 새로고침하면 안먹음
                // const subChannel = async () => {
                    console.log("sub 발생!! : " + JSON.parse(data.body).content);
                    const res = await axios.get(REQUEST_URL, {params: {userId: user_id}});
                    //     .then((res)=>{
                    //     setChannelList(res.data);
                    // })
                    setChannelList(res.data);
                    console.log(res.data);
                    console.log(channelList);
                    //     res.data);
                    // setMessageList((message_list) => [
                    //     ...message_list, json_data
                    // ]);
                // }
                // subChannel();
            });
        });
    };

    // 마운트에서 내려갈 때 ? 종료
    const disconnect = () => {
        if(!client.current.connected) return;
        console.log("소켓 통신 종료");
        client.current.deactivate();
    };

    // 마운트될 때 리스트를 가져옴
    useEffect(() => {
        axios.get(REQUEST_URL, {params: {userId: user_id}})
        .then((res)=>{
            setChannelList(res.data);
        });
        return () => {disconnect()}
    }, []);

    useEffect(() => {
        disconnect()
        connect()
    }, [channelList])

    // 소켓 연결 후 리스트 화면에 뿌리기
    return(
        <div>
            <Logo/>
            <Nav/>
            <ChatThumbnailList chatThumbnailList={channelList}/>
        </div>
    )
}
export default ChatList