import React, {useState, useRef, useEffect} from "react";

import Modal from "../components/modal/Modal";
import {BsHeadphones} from "react-icons/bs"

import styled from 'styled-components';
import Logo from "../components/Logo";
import Nav from "../components/Nav";
import Gift from "../components/mygift/Gift";

import axios from 'axios';
import ProfileHeader from "../components/mygift/ProfileHeader";
import { useLocation } from "react-router";

import jwt from 'jwt-decode';

const StyledHeader = styled.div`
    margin-top: 20px;
    margin-left: 10px;
    margin-right: 10px;
    position: relative;
`;

const StyledMyGiftTitle = styled.div`
    font-size: 17px;
    font-family: var(--font-nanumSquareEB);
    margin-bottom: 25px;
    margin-left: 10px;
    color: white;
`;

const StyledMyGiftHeaderWrapper = styled.div`
    display: flex;
    justify-content: space-around;
    margin-bottom: 60px;
    margin-left: 10px;
    margin-right: 10px;
`;

const StyledMyGiftProfile = styled.div`
    height: 40px;
    width: 40px;
    border-radius: 100%;
    background-color: white;
    display: flex;
    justify-content:center;
    align-items: center;
`;

const StyledMyGiftListNumber = styled.div`
    text-align: center;
    margin-bottom: 10px;
    font-size: 18px;
    font-family: var(--font-nanumSquareEB);
    color: white;
`;

const StyledMyGiftListTitle = styled.div`
    text-align: center;
    font-size: 13px;
    font-family: var(--font-nanumSquareR);
    color: white;
`;

const StyledMyGiftListWrapper=styled.div`
    display: flex;
    justify-content: center;
    align-items: center; 
`;

const StyledMyGiftList = styled.div`
    margin-left: 10px;
    margin-right: 10px;
    display: grid;
    grid-template-columns: 1fr 1fr;
    column-gap: 5px;
    row-gap: 5px;
`;


const StyledMateModal = styled.div`
    position: absolute;
    top: 50%;
`



const GiftList = (props) =>{
    
    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    const nickname = jwt(token)['nickName']; 
    console.log(nickname);
    const userId = jwt(token)['userId'];
    console.log(userId);

    const [dataList, setDataList] = useState([]);
    const [giftcnt, setGiftcnt] = useState(0);
    const [mmcnt, setMMCnt] = useState(0);

    useEffect(() => {
        axios.get('http://i8a402.p.ssafy.io/api/gift', {params: {nickname: `${nickname}`}, headers: {Auth: `${token}`}})
            .then((res) => {
                 setDataList(res.data);
                 setGiftcnt(res.data.length);
                })
    }, []);

    useEffect(() => {
        axios.get('http://i8a402.p.ssafy.io/api/musicmate', {params: {userId: `${userId}`}, headers: {Auth: `${token}`}})
            .then((res) => {
                 setMMCnt(res.data.length);
                 console.log(res);
                 console.log(res.data);
                })
    }, []);

    const [modalOpen, setModalOpen] = useState(false);
    
    return (
        <div>
            <Logo/>
            <Nav/>
            <ProfileHeader nickname={nickname} giftcnt={giftcnt} mmcnt={mmcnt}/>

            <StyledMyGiftListWrapper>
                <StyledMyGiftList>
                    {dataList?.map(item => (
                        <Gift nickname={nickname} giftcnt={giftcnt} mmcnt={mmcnt} key={item.id} id={item.id} src={item.giftImg}/>
                    ))}
                </StyledMyGiftList>
            </StyledMyGiftListWrapper>
            <StyledMateModal>{modalOpen && <Modal setOpenModal={setModalOpen} />}</StyledMateModal>
        </div>
      )
};

GiftList.defaultProps = {
    nickname:"okiiii"
};
   
export default GiftList;