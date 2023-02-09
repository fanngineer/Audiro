import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import MusicmateItem from '../components/musicmate/MusicmateItem';
import Logo from "../components/Logo";
import Nav from "../components/Nav";

import axios from 'axios';
import ProfileHeader from "../components/mygift/ProfileHeader";
import jwt from 'jwt-decode';

const StyledMMContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin: 20px;
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
            {data?.map(item => (
                        <MusicmateItem nickname={item.nickname} img={item.img} key={item.id} id={item.id}/>
                    ))}
            </StyledMMContainer>
        </div>
    );
};

export default Musicmate;