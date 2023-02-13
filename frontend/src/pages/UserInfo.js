import React, {useEffect, useState} from "react";
import styled from 'styled-components';
import axios from "axios";
import Logo from "../components/Logo";
import Nav from "../components/Nav";
import EditNickname from "./EditNickname";

import jwt from 'jwt-decode';

const StyleUserInfoInput = styled.input`
    font-size: 16px;
    font-family: var(--font-nanumSquareL);
    color: white;
`;

const StyleUserInfoContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 30px;
    background-color: rgba(65, 22, 162, 0.5);
    margin-left: 20px;
    margin-right: 20px;
    padding-bottom: 20px;
`;

const StyleUserInfoImg = styled.img`
    border-radius: 100%;
    background-color: white;
    width: 90px;
    height: 90px;
    margin-bottom: 50px;
`;

const StyledUserInfoWrapper = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 300px;
    margin-bottom: 10px;
`;

const StyleUserInfoTitle = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareB);
    color: white;
`;

const StyleTitle = styled.div`
    font-size: 18px;
    font-family: var(--font-nanumSquareB);
    color: white;
    margin-bottom: 20px;
    margin-top: 20px;
`;

const StyleUserInfoText = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareL);
    color: white;
`;

const UserInfo = () =>{

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    const nickname = jwt(token)['nickName']; 
    console.log(nickname);
    const userId = jwt(token)['userId']; 
    console.log(userId);

    const [name, setName] = useState();
    const [email, setEmail] = useState();
    const [profile, setProfile] = useState();
    const [isEdit, setEdit] = useState(false);

    useEffect(() => {
        axios.get(`http://i8a402.p.ssafy.io/api/user/${userId}`, {headers: {Auth: `${token}`}})
            .then((res) => {
                 console.log(res);
                 setName(res.data["name"]);
                 setEmail(res.data["email"]);
                 setProfile(res.data['img']);
            })
    }, []);
    
    const onClicked = () => {
      console.log("hahahahaha");
      setEdit(true);
    };

    return (
        <div>
            <Logo/>
            <Nav/>
            <StyleUserInfoContainer>
                <StyleTitle>내 정보 수정</StyleTitle>
                <StyleUserInfoImg src={profile}></StyleUserInfoImg>
                <StyledUserInfoWrapper>
                    <StyleUserInfoTitle>이름</StyleUserInfoTitle>
                    <StyleUserInfoText>{name}</StyleUserInfoText>
                </StyledUserInfoWrapper>
                <StyledUserInfoWrapper>
                    <StyleUserInfoTitle>이메일</StyleUserInfoTitle>
                    <StyleUserInfoText>{email}</StyleUserInfoText>
                </StyledUserInfoWrapper>
                <StyledUserInfoWrapper>
                    <StyleUserInfoTitle>닉네임</StyleUserInfoTitle>
                    <StyleUserInfoText onClick={onClicked}>{isEdit? <EditNickname/> : nickname}</StyleUserInfoText>
                </StyledUserInfoWrapper>
            </StyleUserInfoContainer>
        </div>
      )
};

export default UserInfo;