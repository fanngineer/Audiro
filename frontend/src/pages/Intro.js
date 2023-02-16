import React from 'react';
import {Link} from 'react-router-dom'
import styled, {keyframes} from 'styled-components';
import wave from '../assets/images/background-wave.gif'
import LP from '../assets/images/Recode.png';

const StyledIntroContainer = styled.div`
    display: flex;
    flex-direction: column;
    margin: 20px;
    justify-content: center;
    align-items: center;
`;

const StyledIntroLogoContainer = styled.div`
    display: flex;
    flex-direction: column;
    margin: 20px;
    margin-top: 60px;
`;

const StyledIntroSub = styled.div`
    background-color: #6522F2;
    width: fit-content;
    padding-left: 15px;
    padding-right: 15px;
    padding-bottom: 2px;
    padding-top: 2px;
    border-radius: 10px;
    font-size: 15px;
    font-family: var(--font-nanumSquareL);
`;

const StyledIntroLogoWrapper = styled.div`
    position: relative;
`;

const StyledLogoCircleLeft = styled.div`
    position: absolute;
    top: 17px;
    left: 40px;
    z-index: -99;
    width: 35px;
    height: 35px;
    background-color: rgba(101, 34, 242, 0.56);
    border-radius: 100%;
`;

const StyledLogo = styled.div`
    font-size: 40px;
    font-family: var(--font-nanumSquareEB);
    text-align: center;
    margin-top: 5px;
    margin-bottom: 5px;
`;

const moveInLeft = keyframes`
    0% {
        opacity: 0;
        transform: translateX(-50px);
    }
    80% {
        transform: translateX(10px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
`

const StyledIntroSubLogo = styled.div`
    font-size: 15px;
    font-family: var(--font-nanumSquareR);
    animation-name: ${moveInLeft};
    animation-duration: 3s;
    text-align: end;
    margin-top: 10px;
`;

const StyledLogoCircleRight = styled.div`
    position:absolute;
    top: 5px;
    left: 120px;
    z-index: -99;
    width: 80px;
    height: 80px;
    background-color: rgba(101, 34, 242, 0.56);
    border-radius: 100%;
`;

const StyledIntroLoginButton = styled.div`
    margin-top: 20px;
    background-color: #6522F2;
    padding-left: 50px;
    padding-right: 50px;
    padding-top: 15px;
    padding-bottom: 15px;
    border-radius: 30px;
    font-size: 20px;
    font-family: var(--font-nanumSquareR);
    text-decoration: inherit;
    a:-webkit-any-link{
        text-decoration: none;
    }
`;

const StyledWaveContainer = styled.div`
    position: relative;
`;

const rotate = keyframes`
    100%{
        transform: rotate(360deg);
    }
`

const StyledIntroLP = styled.image`
   position: absolute;
   top: 125px;
   left: 190px;
   z-index: 999;
   animation: ${rotate} 6s linear infinite;
   transform-origin: 50% 50%;
`;

const Intro = () => {
    return (
        <StyledIntroContainer>

            <StyledIntroLogoContainer>
                <StyledIntroSub>취향 저격 음악 마니또</StyledIntroSub>
                <StyledIntroLogoWrapper>
                    <StyledLogoCircleLeft></StyledLogoCircleLeft>
                    <StyledLogo>어디:로</StyledLogo>
                    <StyledIntroSubLogo>새로운 사람과 음악으로 연결되는 공간</StyledIntroSubLogo>
                    <StyledLogoCircleRight></StyledLogoCircleRight>
                </StyledIntroLogoWrapper>
            </StyledIntroLogoContainer>
            <StyledWaveContainer>
                <img src={wave} width="500"/>
                <StyledIntroLP><img src={LP} width="120"/></StyledIntroLP> 
            </StyledWaveContainer>
            <StyledIntroLoginButton><Link to='/login'>로그인 하기</Link></StyledIntroLoginButton>
        </StyledIntroContainer>
    );
};

export default Intro;