import React, {useEffect, useRef} from 'react';
import ChatMessage from './ChatMessage';
import ChatMessageMe from './ChatMessageMe';
import styled from 'styled-components';
import { useState } from 'react';

const StyledChatContainer = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 30px;
    margin-right: 30px;
    overflow: auto;
    height: 500px;
`;

const StyledChatRoomTitle = styled.div`
    font-size: 17px;
    font-family: var(--font-nanumSquareEB);
    margin-bottom: 25px;
    color: white;
    margin-top: 30px;
    margin-left: 20px;
    margin-right: 20px;
    padding: 10px;
    background-color: rgba(65, 22, 162, 0.5);
`;

const StyledChatDateWrapper = styled.span`
    font-size: 12px;
    font-family: var(--font-nanumSquareR);
    background-color: rgb(65, 22, 162);
    text-align: center;
    padding: 5px;
    margin-right: 80px;
    margin-left: 80px;
    color: #A7A1A1;
    margin-top: 10px;
    margin-bottom: 10px;
    border-radius: 30px;
`;

const ChatMessageList = (props) => {
    // 맵으로 돌면서 컴포넌트 생성
    // props로 정보 전달
    console.log(props.userId);

    const scrollRef = useRef();
    const scrollToBottom = () => {
        scrollRef.current?.scrollIntoView({ behavior: "smooth" });
    }
    useEffect(()=>{
        scrollRef.current.scrollTop=scrollRef.current.scrollHeight;
    });

    return(
        <>            
            <StyledChatRoomTitle>gaok님과의 편지</StyledChatRoomTitle>
            <StyledChatContainer ref={scrollRef}>
                
                {
                    props.messageList && props.messageList.map((msg, index) => {
                    console.log(props.messageList[0].sendTime.split(" ")[0])
                    return(
                        <> 
                            {index!=0 && 
                                props.messageList[index-1].sendTime.split(" ")[0]!==props.messageList[index].sendTime.split(" ")[0] && 
                                <StyledChatDateWrapper>{props.messageList[index].sendTime.split(" ")[0]}</StyledChatDateWrapper>
                            }
                            {msg.userId == 1 ?
                            <ChatMessageMe
                                key={index}
                                user_id={msg.userId}
                                nickname={msg.userNickname}
                                content_type={msg.contentType}
                                content={msg.content}
                                send_time={msg.sendTime}
                            />
                        :   <ChatMessage
                                key={index}
                                user_id={msg.userId}
                                nickname={msg.userNickname}
                                content_type={msg.contentType}
                                content={msg.content}
                                send_time={msg.sendTime}
                            />
                            }
                        </>
                    )
                    
                    })}
            </StyledChatContainer>
            {/* <div ref={scrollRef}/> */}
        </>
    )
}

export default ChatMessageList;