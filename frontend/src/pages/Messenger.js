import React, {useState, useRef, useEffect} from "react";
import {Link} from "react-router-dom";
import '../styles/Messenger.css'
import Messages from "./Messages";
import Chatroom2 from '../components/Chatroom2'
import Logo from '../components/Logo'
import Nav from '../components/Nav'

const Messenger=() => {
  const example=[
    {nickname:'신촌너구리',text:"일과 끝나고 노래 들었",time:"오후 2:00",unread:2},
    {nickname:'전체 개발자',text:"코딩할 때 듣기 너무 좋은 노래네요",time:"오후 2:00",unread:1}
  ]
  const [dataList, setDataList] = useState([]);
    useEffect(() => {
        axios.get('http://i8a402.p.ssafy.io:80/api/channel/list', {params: {nickname: '?'}})
            .then((res) => 
                setDataList(res.data))
            .catch((err)=>
            console.log(err))
    }, []);
    return (

        <div>
            <Logo/>
            <Nav/>
            <h1>Messenger</h1>
            
            {example.map(item => <Link to= {`/messenger/${item.nickname}`} element={Messages}><Chatroom2 {...item}/></Link>)}
            {dataList.map(item=> <Link to={`/messenger/${item.nickname}`} element={Messages}> <Chatroom2 {...item}/> </Link> )}
        </div>
       
    )
  }

export default Messenger;