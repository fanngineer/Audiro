import { createContext, useState } from "react";
import SockJS from "sockjs-client";
import StompJs from "stompjs";

const BASE_URL = "ws://localhost:8080/ws-stomp"
const REQUEST_URL = "http://localhost:8080/channel/list"
const user_id = 1;
const user_nickname = "sohee"

const sock = new SockJs(BASE_URL);
//client 객체 생성 및 서버주소 입력

const stomp = StompJs.over(sock);
//stomp로 감싸기

const sample = [
    {
        "channelId" : "ch1",
        "nickname" : "sohee1",
        "lastMessage" : "ㅎㅎㅎㅎㅎ"
    },
    {
        "channelId" : "ch2",
        "nickname" : "sohee2",
        "lastMessage" : "ㅋㅋㅋㅋㅋ"
    },
    {
        "channelId" : "ch3",
        "nickname" : "sohee3",
        "lastMessage" : "ㅠㅠㅠㅠㅠ"
    },
]

const stompConnect = () => {
    try {
      stomp.connect(token, () => {
        sample.map((s) => {
            stomp.subscribe(
                `/sub/${s.channelId}`, (message) => {
                    showMessage(message);
                }
            )
        });
      });
    } catch (err) {
      console.log(err);
    }
  };

//웹소켓 connect-subscribe 부분

const showMessage = (message) => {
    console.log(response)
}

const stompDisConnect = () => {
    try {
      stomp.disconnect(() => {
        stomp.unsubscribe("sub-0");
      }, token);
    } catch (err) {
      
    }
  };
//웹소켓 disconnect-unsubscribe 부분
// 웹소켓을 disconnect을 따로 해주지 않으면 계속 연결되어 있어서 사용하지 않을때는 꼭 연결을 끊어주어야한다. 

const SendMessage = (channel_id, msg) => {
    stomp.debug = null;
    const data = {
      userId: `user:${user_id}`,
      userNickname: user_nickname,
      contentType: "MESSAGE",
      content: msg
    };
    stomp.send(`/pub/${channel_id}`, token, JSON.stringify(data));
  };
//웹소켓 데이터 전송 부분

export default StompClient;