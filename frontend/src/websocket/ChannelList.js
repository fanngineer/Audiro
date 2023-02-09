import React, {useState, userRedf, useEffect, createContext} from "react";
import {stompClient} from "socket";
import StompClient from "./StompClient";

const MyContext = createContext();
const stompClient = StompClient;

const BASE_URL = 'http://localhost:8080/channel/list'

const data = [
    {
        "channelId" : 1234,
        "nickname" : "sohee1",
        "lastMessage" : "ㅎㅎㅎㅎㅎ"
    },
    {
        "channelId" : 1235,
        "nickname" : "sohee2",
        "lastMessage" : "ㅋㅋㅋㅋㅋ"
    },
    {
        "channelId" : 1236,
        "nickname" : "sohee3",
        "lastMessage" : "ㅠㅠㅠㅠㅠ"
    },
]

const ChannelList = () => {
    console.log("channel_list")

    useEffect(()=>{
        stompClient.stompConnect;
    })

    return(
        <div>
        </div>
    )
};

export default ChannelList;