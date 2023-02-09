import React, {useState, useRef, useEffect} from "react";

import Modal from "../modal/Modal";
import {BsHeadphones} from "react-icons/bs"
import {useParams} from 'react-router-dom'
import DeleteModal from "../modal/DeleteModal";
import styled from 'styled-components';
import { Link } from "react-router-dom";
import NicknameModal from "../modal/NicknameModal";

import axios from "axios";
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
    display: flex;
`;

const StyledMyGiftHeaderWrapper = styled.div`
    display: flex;
    justify-content: space-around;
    margin-bottom: 60px;
    margin-left: 10px;
    margin-right: 10px;
`;

const StyledMyGiftProfile = styled.div`
    height: 50px;
    width: 50px;
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

const StyledProfileImg=styled.img`
    background-color: white;
    border-radius: 100%;
    width: 50px;
    height: 50px;
`;

const ProfileHeader = (props) => {
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);
  const outside = useRef();
  const {giftid}=useParams();
  console.log([deleteModalOpen, setDeleteModalOpen])
  const [NicknameOpen, setNicknameOpen] = useState(false);

  const token = localStorage.getItem('login-token');
  console.log(jwt(token));
  const userId = jwt(token)['userId']; 
  console.log(userId);

  const [userImg, setuserImg] = useState();
  useEffect(() => {
    axios.get(`http://i8a402.p.ssafy.io/api/user/${userId}`, {headers: {Auth: `${token}`}})
      .then((res) => {
        setuserImg(res.data.img);
      })
  }, []);


  return (
    <div>
      <StyledHeader>
        <StyledMyGiftTitle>반가워요, <div onClick={()=>{setNicknameOpen(true)}}>{props.nickname}님</div> 👋 </StyledMyGiftTitle>

        <StyledMyGiftHeaderWrapper>
          <StyledMyGiftProfile>
          <StyledProfileImg src={userImg}/>
          </StyledMyGiftProfile>
            <Link to="/gifts" style={{ textDecoration: 'none' }}>
                <div>
                <StyledMyGiftListNumber>{props.giftcnt}</StyledMyGiftListNumber>
                <StyledMyGiftListTitle>나의 엽서</StyledMyGiftListTitle>
                </div>
            </Link>
            <div>
              <StyledMyGiftListNumber>1</StyledMyGiftListNumber>
              <StyledMyGiftListTitle>방문한 지점</StyledMyGiftListTitle>
            </div>
            <Link to="/musicmate" style={{ textDecoration: 'none' }}>
              <StyledMyGiftListNumber>{props.mmcnt}</StyledMyGiftListNumber>
              <StyledMyGiftListTitle>음악 메이트</StyledMyGiftListTitle>
            </Link>
        </StyledMyGiftHeaderWrapper>
      </StyledHeader>
      {modalOpen && <Modal className="gift-modal" setOpenModal={setModalOpen} />}
      {NicknameOpen && <NicknameModal/> }
      </div>
  );
  
}


export default ProfileHeader;