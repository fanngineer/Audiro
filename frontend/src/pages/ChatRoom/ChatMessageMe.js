import React from "react"
import styled from 'styled-components';

const StyledChatContainer = styled.div`
    display: flex;
    margin-bottom: 20px;
    justify-content: flex-end;
`;

const StyledChatProfile = styled.div`
    background-color: white;
    border-radius: 100%;
    width: 35px;
    height: 35px;
    margin-left: 5px;
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
    /* background-color: ; */
    border: 2px solid #6522F2;
    padding: 10px;
    border-radius: 10px 0px 10px 10px;
    margin-left: 5px;
    max-width: 180px;
`;

const StyledChatDate = styled.div`
    font-size: 10px;
    font-family: var(--font-nanumSquareL);
    color: #A7A1A1;
    text-align: end;
    margin-left: 5px;
`;

const StyledChat = styled.div`
    display: flex;
    align-items: center;
    justify-content: end;
`;

const StyledChat2 = styled.div`
    display: flex;
    align-items: end;
    justify-content: end;
`;

const StyledChatImgContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border-radius: 0px 20px 20px 20px;
    border: 2px solid #F5336D;
    margin-left: 30px;
`;

const StyledChatTitle = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareR);
    color: white;
    background-color: #F5336D;
    padding: 10px;
    border-radius: 0px 10px 10px 10px;
`;

const StyledChatImg = styled.div`
    width: 150px;
    height: 90px;
    background-color: white;
    margin-top: 20px;
    margin-bottom: 20px;
`;

const StyledChatImgText = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareR);
    color: white;
    margin-top: 5px;
    background-color: #F5336D;
    padding: 7px;
    border-radius: 10px 10px 10px 10px;
    margin-bottom: 10px;
    padding-left: 5px;
    padding-right: 5px;
`;

const ChatMessageMe = (props) => {
    console.log(props.receiver_id);

    const StringMessage = (props) => {
        return (
            <StyledChatContainer>
                <StyledChatWrapper>
                    <StyledChat>
                        {/* <StyledChatNickname>{props.nickname}</StyledChatNickname> */}
                        {/* <StyledChatProfile></StyledChatProfile> */}
                    </StyledChat>
                    <StyledChat2>
                        <StyledChatDate>{props.send_time.split(" ")[1]+" "+props.send_time.split(" ")[2]}</StyledChatDate>
                        <StyledChatMsgYou>{props.content}</StyledChatMsgYou>
                        {/* {props.user_id} */}
                        {/* {props.content_type} */}
                    </StyledChat2>
                </StyledChatWrapper>  
            </StyledChatContainer>
        )
    }

    // 이미지인 경우엔 http~~~하는 이미지 주소를 보여주게끔,,,
    const ImageMessage = (props) => {
        return(
            <StyledChatContainer>
                {/* <StyledChatProfile src={userImg}></StyledChatProfile> */}
                <StyledChatWrapper>
                    {/* <StyledChatNickname>{props.nickname}</StyledChatNickname> */}
                    
                    <StyledChatImgContainer>
                        {/* <img alt="이미지 메세지" src={props.content}/> */}
                        <StyledChatTitle>내 엽서에 대한 답장이 도착했어요!</StyledChatTitle>
                        <StyledChatImg></StyledChatImg>
                        <StyledChatImgText>음악메이트 신청하기</StyledChatImgText>
                    </StyledChatImgContainer>
                    
                    {/* {props.user_id} */}
                    {/* {props.content_type} */}
                    <StyledChatDate>{props.send_time.split(" ")[1]+" "+props.send_time.split(" ")[2]}</StyledChatDate>
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

export default ChatMessageMe;