import React from "react"
import styled from 'styled-components';

const StyledChatContainer = styled.div`
    display: flex;
    margin-bottom: 20px;
`;

const StyledChatProfile = styled.div`
    background-color: white;
    border-radius: 100%;
    width: 35px;
    height: 35px;
    margin-right: 5px;
`;

const StyledChatWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
`;

const StyledChatNickname = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareEB);
    color: #6522F2;
`;

const StyledChatMsgYou = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareR);
    color: white;
    margin-top: 5px;
    /* background-color: #6522F2; */
    background-color: #6522F2;
    padding: 10px;
    border-radius: 0px 10px 10px 10px;
    margin-left: 30px;
    max-width: 180px;
`;

const StyledChatDate = styled.div`
    font-size: 10px;
    font-family: var(--font-nanumSquareL);
    color: #A7A1A1;
    margin-top: 5px;
    text-align: end;
    margin-left: 5px;
`;

const StyledChat = styled.div`
    display: flex;
    align-items: center;
`;

const StyledChat2 = styled.div`
    display: flex;
    align-items: end;
`;

const ChatMessage = (props) => {
    console.log(props.receiver_id);

    const StringMessage = (props) => {
        return (
            
            <StyledChatContainer>
                <StyledChatWrapper>
                    <StyledChat>
                        <StyledChatProfile></StyledChatProfile>
                        <StyledChatNickname>{props.nickname}</StyledChatNickname>
                    </StyledChat>
                    <StyledChat2>
                        <StyledChatMsgYou>{props.content}</StyledChatMsgYou>
                        {/* {props.user_id} */}
                        {/* {props.content_type} */}
                        <StyledChatDate>{props.send_time.split(" ")[1]+" "+props.send_time.split(" ")[2]}</StyledChatDate>
                    </StyledChat2>
                </StyledChatWrapper>  
            </StyledChatContainer>
        )
    }

    // 이미지인 경우엔 http~~~하는 이미지 주소를 보여주게끔,,,
    const ImageMessage = (props) => {
        return(
            <StyledChatContainer>
                <StyledChatProfile></StyledChatProfile>
                <StyledChatWrapper>
                    <StyledChatNickname>{props.nickname}</StyledChatNickname>
                    <img alt="이미지 메세지" src={props.content}/>
                    {/* {props.user_id} */}
                    {/* {props.content_type} */}
                    <StyledChatDate>{props.send_time}</StyledChatDate>
                </StyledChatWrapper>
            </StyledChatContainer>
        )
    }

    // 메세지인 경우와 이미지인 경우로 나누어서 표현
    if(props.content_type == 'MESSAGE'){
        return (
            <StringMessage
                user_id={props.user_id}
                nickname={props.nickname}
                content_type={props.content_type}
                content={props.content}
                send_time={props.send_time}
            />
        )
    }
    return (
        <ImageMessage
            user_id={props.user_id}
            nickname={props.nickname}
            content_type={props.content_type}
            content={props.content}
            send_time={props.send_time}
        />
    )
}

export default ChatMessage;