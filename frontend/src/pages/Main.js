import React, {useEffect, useState, useParams} from 'react';
import styled, {keyframes} from 'styled-components';
import Logo from "../components/Logo";
import Earphone from "../assets/images/Earphone.jpg";
import Chart from "../assets/images/chart.PNG";
import Gift from "../assets/images/gift.PNG";
import Postcard from "../assets/images/postcard.PNG";
import Chatting from "../assets/images/chatting.PNG"
import Nav from "../components/Nav";
import jwt from 'jwt-decode';

const StyledContainer = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    margin-top: 30px;
`;

const moveImg = keyframes`
    0% {
        opacity: 1;
        top: 30px;
    }
    100% {
        opacity: 1;
        top: 0px;
    }
`

const StyledEarphone = styled.img`
    width: 150px;
    height: 150px;
    margin-top: 100px;
    animation: ${moveImg} 6s linear 1s infinite alternate;
`;


const StyledImg = styled.img`
    width: 200px;
    height: 250px;
    border: 2px solid #6522F2;
    border-radius: 10px;
    margin: 50px;
    animation: ${moveImg} 6s linear 1s infinite alternate;
`;

const StyledImgWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

const StyledImgText = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareR);
    margin-bottom: 80px;
`;
const StyledImgText2 = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareR);
    margin-bottom: 10px;
`;

const StyledImgTitle = styled.div`
    font-size: 17px;
    font-family: var(--font-nanumSquareB);
    margin-bottom: 10px;
    background-color: #6522F2;
    border-radius: 20px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 15px;
    padding-right: 15px;
`;

const Main = () =>{

    const [token, setToken] = useState();

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        console.log(window.location);
        console.log(window.location.search);
        const jwtToken = params.get("auth");
        const refreshToken = params.get("refresh");
        
        if(jwtToken!=null){
          setToken(jwtToken);
          localStorage.setItem('login-token', jwtToken);
          localStorage.setItem('refresh-token', refreshToken);
        }
        
        console.log(jwtToken);
        console.log("parsing");
    }, [])

    return (
        <div>
            <Logo/>
            <Nav/>
            <StyledContainer>
                <StyledImgWrapper>
                    <StyledImg src={Chart}></StyledImg>
                    <StyledImgTitle>인기차트</StyledImgTitle>
                    <StyledImgText>부스에서 가장 인기있는 노래를 들어보세요</StyledImgText>
                </StyledImgWrapper>
                <StyledImgWrapper>
                    <StyledImg src={Gift}></StyledImg>
                    <StyledImgTitle>노래마니또 & 채팅 </StyledImgTitle>
                    <StyledImgText2>다른 사람이 남긴 엽서를 통해 노래를 듣고,</StyledImgText2>
                    <StyledImgText>나와 음악 취향이 비슷한 사람과 채팅을 시작해요</StyledImgText>
                </StyledImgWrapper>
                <StyledImgWrapper>
                    <StyledImg src={Postcard}></StyledImg>
                    <StyledImgTitle>편지함</StyledImgTitle>
                    <StyledImgText>노래를 들으며 생각나는 사람에게 편지를 작성해요 </StyledImgText>
                </StyledImgWrapper>
            </StyledContainer>
        </div>
      )
};

export default Main;