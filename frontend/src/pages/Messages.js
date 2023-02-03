import React from 'react';
import styled from 'styled-components';
import Headset from '../assets/images/Group.png'
import Send from '../assets/images/send.png'
const StyledMessageBox=styled.div`
    display:flex;
    flex-direction:column;
    background-color:black;
    justify-content:center;
    // align-items:center;

`
const StyledMyMessage=styled.div`
    display:flex;
    justify-content:end;
    height:50px;
    margin-right:16px;
`

const StyledMessage=styled.div`
    display: flex;
    background-color: '#F5336D';
    heigth:50px;
    margin-left:16px;
`
const StyledHeadsetImage=styled.img`
    background-color:white;
    width: 42px;
    heigth: 42px;
    border-radius:100%;
 
`
const StyledInput=styled.input`
    placeholder: "새로운 사람과의 대화를 시작합니다";
    type:text;
    width: 90vw;
    background-color: #1E0E40;
`
const StyledOpponent=styled.div`
    background-color: #1E0E40;
    height: 50px;
    margin: 24px ;
    display: flex;
    align-items: center;
`

const StyledInputSet=styled.div`
    background-color; #1E0E40;
    display:flex;

`
const StyledSendImage=styled.img`
    width:20px;
    heigth:20px;
    
`
const StyledSentMessage=styled.div`
    border: 1px solid #6522F2;
    border-radius: 15px 0px 15px 15px;
`

const StyledGotMessage=styled.div`
    background-color: #6522F2;
    border-radius: 0px 15px 15px 15px; 
`
const Messages = () => {
    return (
        <>
        <StyledMessageBox>
        <StyledOpponent>대화상대님과의 편지</StyledOpponent>    
        
        <StyledMyMessage>
            <StyledSentMessage >내가 보낸 메시지</StyledSentMessage><StyledHeadsetImage src={Headset}/>
        </StyledMyMessage>
        <StyledMessage>
            <StyledHeadsetImage src={Headset}/>
            <div>
                보낸사람    
            <StyledGotMessage >받은 메시지</StyledGotMessage>
            </div>
            
        </StyledMessage>

        
        </StyledMessageBox>
        <hr/>
        <StyledInputSet><form><StyledInput /></form> <StyledSendImage src={Send}/></StyledInputSet>
        </>
    );
};

export default Messages;