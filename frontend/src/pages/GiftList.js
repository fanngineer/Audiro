import React, {useState, useRef, useEffect} from "react";

import Modal from "../components/modal/Modal";
import {BsHeadphones} from "react-icons/bs"

import styled from 'styled-components';
import Logo from "../components/Logo";
import Nav from "../components/Nav";
import Gift from "../components/mygift/Gift";

import axios from 'axios';
import ProfileHeader from "../components/mygift/ProfileHeader";
import { useLocation, useParams } from "react-router";

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
    overflow: auto;
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

const GiftList = (props) =>{
    
    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    let nickname = jwt(token)['nickName']; 
    console.log(nickname);
    let userId = jwt(token)['userId'];
    console.log(userId);

    const [dataList, setDataList] = useState([]);
    const [giftcnt, setGiftcnt] = useState(0);
    const [mmcnt, setMMCnt] = useState(0);

    //const [realNickName, setNickname] = useState();
    //setNickname(nickname);

    //const {other_nickname} = useParams();
    //const {id} = useParams();
    //if(other_nickname!=nickname) {
    //    if(nickname===undefined) {
    //      nickname=other_nickname;
    //      userId=id;
    //      console.log("dala")
    //    }
    //    else if(other_nickname===undefined) {
    //      console.log("other_nickname undefined")
    //    }
    //    else {
    //      nickname=other_nickname;
    //      userId=id;
    //      console.log("dala")
    //    }
    //}
    
    //if(userId===undefined) userId=jwt(token)['userId'];
    //console.log("userId")
    //console.log(userId)
    
    //console.log("�г��� Ȯ��")
    
    //console.log(other_nickname);
    //console.log(nickname);
    //console.log(other_nickname===undefined);
    //console.log(nickname===undefined);

    useEffect(() => {
        axios.get('http://i8a402.p.ssafy.io/api/gift', {params: {nickname: `${nickname}`}, headers: {Auth: `${token}`}})
            .then((res) => {
                 console.log(res)
                 setDataList(res.data);
                 setGiftcnt(res.data.length);
                })
            .catch((err)=>{
                 console.log (err)
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
            <ProfileHeader nickname={nickname} user_id={userId} giftcnt={giftcnt} mmcnt={mmcnt}/>

            {
            giftcnt==0? 
                <>
                    <StyledMMNone>⛔ 작성한 엽서가 없습니다 ⛔</StyledMMNone> 
                    <StyledMMNone>부스에 방문하여 엽서를 만들어보세요</StyledMMNone>
                </>
                :
            <StyledMyGiftListWrapper>
                <StyledMyGiftList>
                    {dataList?.map(item => (
                        <Gift nickname={nickname} giftcnt={giftcnt} mmcnt={mmcnt} key={item.id} id={item.id} src={item.giftImg}/>
                    ))}
                </StyledMyGiftList>
            </StyledMyGiftListWrapper>
            }
            <StyledMateModal>{modalOpen && <Modal setOpenModal={setModalOpen} />}</StyledMateModal>
        </div>
      )
};

GiftList.defaultProps = {
    nickname:"okiiii"
};
   
export default GiftList;