import React, {useState, useRef, useEffect} from "react";

import Modal from "../modal/Modal";
import {BsHeadphones} from "react-icons/bs"
import {useParams} from 'react-router-dom'
import DeleteModal from "../modal/DeleteModal";
import styled from 'styled-components';
import { Link } from "react-router-dom";

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

const ProfileHeader = (props) => {
  const [modalOpen, setModalOpen] = useState(false);
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);
  const outside = useRef();
  const {giftid}=useParams();
  console.log([deleteModalOpen, setDeleteModalOpen])

  return (
    <div>
      <StyledHeader>
        <StyledMyGiftTitle>반가워요, {props.nickname}님 👋 </StyledMyGiftTitle>

        <StyledMyGiftHeaderWrapper>
          <StyledMyGiftProfile><BsHeadphones fill='black' size="30"/></StyledMyGiftProfile>
            <Link to="/gifts" style={{ textDecoration: 'none' }}>
                <div>
                <StyledMyGiftListNumber>20</StyledMyGiftListNumber>
                <StyledMyGiftListTitle>나의 엽서</StyledMyGiftListTitle>
                </div>
            </Link>
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
      {modalOpen && <Modal className="gift-modal" setOpenModal={setModalOpen} />}
      </div>
  );
  
}


export default ProfileHeader;