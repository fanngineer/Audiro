import React from "react";
import { Link } from "react-router-dom";
import styled from 'styled-components';
import Logo from "../components/Logo";
import kakao from "../assets/images/kakao_logo.png"
import naver from "../assets/images/naver_logo.png"
import google from "../assets/images/google_logo.png"
import axios from "axios";

const StyledLoginContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 80px;
`;

const StyledLoginTitle = styled.div`
    font-size: 20px;
    font-family: var(--font-nanumSquareR);
    margin-bottom: 20px;
`;

const StyledLoginBtnWrapper = styled.div`
    background-color: #6522F2;
    padding: 40px;
    border-radius: 50px;
`;

const StyledLoginBtn = styled.a`
    color: black;
    background-color: ${props => props.background};
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 10px;
    padding-bottom: 10px;
    margin-bottom: 10px;
    border-radius: 20px;
    font-size: 15px;
    font-family: var(--font-nanumSquareR);
    display: flex;
    align-items: center;
    justify-content: center;
    border-style: none;
    text-decoration: none;
`;

const StyledLoginLogoKaKao = styled.div`
    margin-right: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
`;

const StyledLoginLogoGoogle = styled.div`
    margin-right: 17px;
    display: flex;
    align-items: center;
    justify-content: center;
`;

const StyledLoginLogoNaver = styled.div`
    margin-right: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
`;

const Login = () =>{
    function Kakaologin(){
        fetch('http://i8a402.p.ssafy.io:8080/oauth2/authorization/kakao',{
            method: 'GET',
            credentials: "include",
            
        })

        axios.get(
            'http://i8a402.p.ssafy.io:8080/oauth2/authorization/kakao', 
            { }, 
            { withCredentials: true }
        )
        .then(response => { 
            console.log(response); 
            console.log(response.data); 
        
        })
        .catch(err=>{
            console.log(err)
        })
    }

    function Naverlogin(){
        fetch('http://i8a402.p.ssafy.io:8080/oauth2/authorization/naver',{
            method: 'GET',
            credentials: "include",
            
        })

        axios.get(
            'http://i8a402.p.ssafy.io:8080/oauth2/authorization/naver', 
            { }, 
            { withCredentials: true }
        )
        .then(response => { 
            console.log(response); 
            console.log(response.data); 
        
        })
        .catch(err=>{
            console.log(err)
        })
    }
    return (
        <div>
            <Logo type="login"/>
            <StyledLoginContainer>
            <StyledLoginTitle>로그인</StyledLoginTitle>
            <StyledLoginBtnWrapper>
                <StyledLoginBtn background="#FFE812" href="http://i8a402.p.ssafy.io/oauth2/authorization/kakao?redirect_uri=http://i8a402.p.ssafy.io/home&spot_id=1"> 
                    <StyledLoginLogoKaKao><img src={kakao} height="20"/></StyledLoginLogoKaKao> 
                    카카오로 로그인하기
                </StyledLoginBtn>
                <StyledLoginBtn background="#FFFFFF" href="http://i8a402.p.ssafy.io/oauth2/authorization/google?redirect_uri=http://i8a402.p.ssafy.io/home&spot_id=1"> 
                    <StyledLoginLogoGoogle><img src={google} height="18"/></StyledLoginLogoGoogle>
                    구글로 로그인하기
                </StyledLoginBtn>
                <StyledLoginBtn onClick={Naverlogin} background="#FFFFFF"> 
                    <StyledLoginLogoNaver ><img src={naver} height="20"/></StyledLoginLogoNaver>
                    네이버로 로그인하기
                </StyledLoginBtn>
            </StyledLoginBtnWrapper>
            </StyledLoginContainer>
        </div>
      )
};

export default Login;