import React, {useState, useRef, useEffect} from "react";

import Modal from "../components/modal/Modal";
import {BsHeadphones} from "react-icons/bs"

import styled from 'styled-components';
import Logo from "../components/Logo";
import Nav from "../components/Nav";
import Gift from "../components/mygift/Gift";

import axios from 'axios';

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

const GiftList = () =>{

    const [dataList, setDataList] = useState([]);
    useEffect(() => {
        axios.get('http://localhost:8080/gift', {params: {nickname: 'gaok'}})
            .then((res) => 
                setDataList(res.data))
    }, []);

    const [modalOpen, setModalOpen] = useState(false);

    return (
        <div>
            <Logo/>
            <Nav/>
            <StyledHeader>
                <StyledMyGiftTitle>반가워요, 연희동 아자르님 👋 </StyledMyGiftTitle>

                <StyledMyGiftHeaderWrapper>
                    <StyledMyGiftProfile><BsHeadphones fill='black' size="30"/></StyledMyGiftProfile>
                    <div>
                        <StyledMyGiftListNumber>20</StyledMyGiftListNumber>
                        <StyledMyGiftListTitle>나의 엽서</StyledMyGiftListTitle>
                    </div>
                    <div>
                        <StyledMyGiftListNumber>20</StyledMyGiftListNumber>
                        <StyledMyGiftListTitle>방문한 지점</StyledMyGiftListTitle>
                    </div>
                    <div onClick={()=>{ setModalOpen(true) }}>
                        <StyledMyGiftListNumber>20</StyledMyGiftListNumber>
                        <StyledMyGiftListTitle>음악 메이트</StyledMyGiftListTitle>
                    </div>
                </StyledMyGiftHeaderWrapper>
            </StyledHeader>

            <StyledMyGiftListWrapper>
                <StyledMyGiftList>
                    {dataList?.map(item => (
                        <Gift key={item.id} id={item.id} src={item.giftImg}/>
                    ))}
                </StyledMyGiftList>
            </StyledMyGiftListWrapper>
            <StyledMateModal>{modalOpen && <Modal setOpenModal={setModalOpen} />}</StyledMateModal>
        </div>
      )
};
   
export default GiftList;