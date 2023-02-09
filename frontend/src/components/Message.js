import React, {useState,useRef}from 'react';
import styled from 'styled-components';
import Headset from '../assets/images/Group.png'


const StyledMessage=styled.div`
    display: flex;
    background-color: '#F5336D';
    height:50px;
    margin:16px;
`;
const StyledHeadsetWrapper=styled.div`
    background-color:white;
    border-radius:100%;
    width:35px;
    height:35px;
    diplay:flex;
    align-items:center;
    margin:8px
`;

const StyledHeadsetImage=styled.img`
    background-color:white;
    width: 20px;
    height: 16px;
    border-radius:100%;
    margin: 8px;
`;

const StyledSender=styled.div`
    color: #6522F2;
    font-size: 12px;
`;

const StyledGotMessage=styled.div`
    background-color: #6522F2;
    border-radius: 0px 15px 15px 15px; 
    padding: 10px 20px 1px 20px;
    margin: 4px;
    font-size: 10px;
    height:21px;
`;

const StyledTime=styled.div`
    font-size: 10px;
    line-height:12px;
    font-family: 'Inter';
    font-style: normal;
    text-align:end;
    margin:4px;
`;


const Message = () => {
    return (
        <div>
            <StyledMessage>
            <StyledHeadsetWrapper><StyledHeadsetImage src={Headset}/></StyledHeadsetWrapper>
            <div>
                <StyledSender> 보낸사람 </StyledSender> 
            <StyledGotMessage ><div>받은 메시지</div></StyledGotMessage>
            <StyledTime>작성시간</StyledTime>
            </div>  
            </StyledMessage>
        </div>
    );
};

export default Message;