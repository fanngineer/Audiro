import {useRef, useState, useEffect} from "react";
import {useParams} from "react-router-dom";
import StompJs from "stompjs";

const BASE_URL = "ws://localhost:8080/ws-stomp"
const REQUEST_URL = "http://localhost:8080/channel/list"
const user_id = 1;
const user_nickname = "sohee"

const ChatRoom = () => {

    const [messageList, setMessageList] = useState([]);
    const [message, setMessage] = useState('');

    const {channel_id} = useParams();
    const client = useRef({});

    const connect = () => {
        client.current = new StompJs.Client({
            brokerURL: BASE_URL,
            onConnect: () => {
                console.log("소켓 연결 성공!");
                subscribe();
            },
        });
        client.current.activate();
    };

    const subscribe = () => {
        client.current.subscribe(`/sub/${channel_id}`, (data) => {
            const json_data = JSON.parse(data.content);
            setMessageList((_message_lsit)=>[
                ..._message_lsit, json_data
            ]);
        });
    };

    const publish = (message) => {
        if(!client.current.connected) return;

        client.current.publish({
            destination: `/pub/${channel_id}`,
            body: JSON.stringify({
                userId: user_id,
                userNickname: user_nickname,
                contentType: "MESSAGE",
                content: message
            }),
        });

        setMessage('');
    };

    const disconnect = () => {
        client.current.deactivate();
    };

    const handleChange = (event) => {
        setMessage(event.target.value);
    };

    const handleSubmit = (event, message) => {
        event.preventDefault();

        publish(message);
    };

    useEffect(() => {
        connect();

        return () => disconnect();
    }, []);

    return (
        <div>
            <div className={'chat-list'}>{chatList}</div>
            <form onSubmit={(event) => handleSubmit(event, chat)}>
                <div>
                    <input type={'text'} name={'chatInput'} onChange={handleChange} value={chat} />
                </div>
                <input type={'submit'} value={'의견 보내기'} />
            </form>
        </div>
    );
}