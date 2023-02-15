import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const StyledChatContainer = styled.div`
    >*{
        text-decoration: none;
    }
`;

const StyledChatListWrapper = styled.div`
    display: flex;
    justify-content: space-between;
    padding: 15px;
    border-bottom: 1px solid white;
    width: 270px;
    > *{
        text-decoration: none;
    }
`;

const StyledChatList = styled.div`
    display: flex;
`;

const StyledChatProfile = styled.div`
    background-color: white;
    border-radius: 100%;
    width: 35px;
    height: 35px;
    margin-right: 15px;
`;

const StyledChatWrapper = styled.div`
    display: block;
    width: 150px;
`;

const StyledChatNickname = styled.div`
    font-size: 15px;
    font-family: var(--font-nanumSquareB);
    color: #6522F2;
`;

const StyledChatMsg = styled.div`
    font-size: 15px;
    font-family: var(--font-nanumSquareL);
    color: white;
    margin-top: 5px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
`;

const StyledChatDate = styled.div`
    font-size: 12px;
    font-family: var(--font-nanumSquareR);
    color: white;
    margin-top: 5px;
`;

const ChatThumbnail = (props) => {

    let last_time = props.last_time;
    let now = new Date();
    let now_year=now.getFullYear();
    let now_month=(now.getMonth()+1)>9?(now.getMonth()+1) : '0'+(now.getMonth()+1);
    let now_date=now.getDate() > 9? now.getDate() : '0'+now.getDate();
    let now_hour=now.getHours() > 9? now.getHours() : '0'+now.getHours();
    let now_minutes=now.getMinutes() > 9 ? now.getMinutes() : '0'+now.getDate();

    now_hour=now_hour>12? '오후 '+now_hour:'오전 '+now_hour;

    const str=last_time.split(" ")
    const now1=str[0].split("-");
    const now2=str[1].split(":")

    let temp=last_time;
    if(now1[0]==now_year){
        if(now1[1]==now_month){
            if(now1[2]==now_date){
                temp=now_hour+":"+now_minutes;
            }
            else if(now1[2]+1==now_date){
                temp="어제";
            }
        }
    }

    last_time=temp;

    return (
        // 해당 컴포넌트를 누르면 채팅방으로 이동함
        <StyledChatContainer>
            <Link to={`/messenger/${props.channel_id}`}>
                <StyledChatListWrapper>
                <StyledChatList>
                    <StyledChatProfile></StyledChatProfile>
                    {/* {"채널명 : " + props.channel_id} */}
                    <StyledChatWrapper>
                        <StyledChatNickname>{props.nickname}</StyledChatNickname>
                        <StyledChatMsg>{props.last_message}</StyledChatMsg>
                    </StyledChatWrapper>
                </StyledChatList>
                <StyledChatDate>{last_time}</StyledChatDate>
                </StyledChatListWrapper>
            </Link>
        </StyledChatContainer>
    )
}

export default ChatThumbnail;