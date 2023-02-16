import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import MusicmateItem from '../components/musicmate/MusicmateItem';
import Logo from "../components/Logo";
import Nav from "../components/Nav";
import { Link } from "react-router-dom";

import axios from 'axios';
import ProfileHeader from "../components/mygift/ProfileHeader";
import jwt from 'jwt-decode';

const StyledMMContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin: 20px;
    overflow: auto;
`;

const StyledMMNone = styled.div`
    text-align: center;
    color: white;
    font-size: 14px;
    font-family: var(--font-nanumSquareR);
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 20px;
    padding-right: 20px;
`;

const Musicmate = () => {

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    const userId = jwt(token)['userId']; 
    console.log(userId);

    const [data, setData] = useState([]);
    useEffect(() => {
        axios.get('http://i8a402.p.ssafy.io/api/musicmate', {params: {userId: `${userId}`}, headers: {Auth: `${token}`}})
            .then((res) => {
                 setData(res.data);
                 console.log(res);
                 console.log(res.data);
                })
    }, []);


    return (
        <div>
            <Logo/>
            <Nav/>
            <StyledMMContainer>
            {
            data.length==0? 
                <>
                    <StyledMMNone>⛔ 뮤직메이트가 없습니다 ⛔</StyledMMNone> 
                    <StyledMMNone>부스에 방문하여 뮤직메이트를 만들어보세요</StyledMMNone>
                </>
                :
                data?.map(item => (
                    <MusicmateItem nickname={item.nickname} img={item.img} key={item.id} id={item.id}/>
                ))
            }
            </StyledMMContainer>
        </div>
    );
};

export default Musicmate;