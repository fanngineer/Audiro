import React, {useState, useRef, useEffect} from "react";
import {Link} from "react-router-dom";
import styled from 'styled-components';
import axios from 'axios'
import Messages from "./Messages";
import MessageIntro from "../components/Message/MessageIntro"
import Logo from '../components/Logo'
import Nav from '../components/Nav'

const StyledMessageListContainer = styled.div`
  margin: 15px;
`;

const StyledMessageListWrapper = styled.div`
  background-color: #1E0E40;
  >*{
    text-decoration: none;
  }
`;

const StyledMessageListTitle = styled.div`
    font-size: 17px;
    font-family: var(--font-nanumSquareEB);
    margin-bottom: 25px;
    margin-left: 10px;
    color: white;
    margin-top: 20px;
`;

const MessageList=() => {
  const example=[
    {nickname:'신촌너구리',text:"일과 끝나고 노래 들었",time:"오후 2:00",unread:2},
    {nickname:'전체 개발자',text:"코딩할 때 듣기 너무 좋은 노래네요",time:"오후 2:00",unread:1}
  ];

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

            <StyledMessageListContainer>
                <StyledMessageListTitle>연희동 아자르님의 편지함 🎁 </StyledMessageListTitle>
                  <StyledMessageListWrapper>
                    {example.map(item => <Link to= {`/messenger/${item.nickname}`} element={Messages}><MessageIntro {...item}/></Link>)}
                    {dataList.map(item=> <Link to={`/messenger/${item.nickname}`} element={Messages}> <MessageIntro {...item}/> </Link> )}
                  </StyledMessageListWrapper>
            </StyledMessageListContainer>
        </div>
    )
  }

export default MessageList;