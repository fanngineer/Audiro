import React, {useState, useRef, useEffect} from "react";
import Logo from "../components/Logo";
import Nav from "../components/Nav";

import Modal from "../components/modal/Modal";
import {BsHeadphones} from "react-icons/bs"
import {HiMusicNote} from "react-icons/hi";
import {FaHeart} from "react-icons/fa"
import fun from '../assets/images/fun.png';
import love from '../assets/images/love.png';
import sad from '../assets/images/sad.png';
import wow from '../assets/images/wow.png';
import {useParams} from 'react-router-dom'
import song from '../assets/audio/Ditto.mp3';
import DeleteModal from "../components/modal/DeleteModal";
import styled from 'styled-components';

import Youtube from 'react-youtube';
import YouTube from "react-youtube";
import ProfileHeader from "../components/mygift/ProfileHeader";

import axios from "axios";

import jwt from 'jwt-decode';

const StyledGiftDetailContainer = styled.div`
    margin: 20px;
`;

const StyledGiftDetailBtnWrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: flex-end;  
`;

const StyledDetailBtn = styled.div`
    border: 1px solid #6522f2;
    border-radius: 20px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 15px;
    padding-right: 15px;
    font-size: 13px;
    font-family: var(--font-nanumSquareR);
    margin-right: 10px;
    margin-bottom: 10px;
`;

const StyledDetailImg = styled.div`
    display: flex;
    height: 200px;
    background-color: white;
`;

const StyledDetailBottomWrapper = styled.div`
    display: flex;
    align-items: center;
    margin-top: 10px;
    justify-content: space-between;
`;

const StyledSongWrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
`;

const StyledHeartWrapper = styled.div`
    border: 1px solid #6522f2;
    border-radius: 20px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 15px;
    padding-right: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
`;

const StyledSongDetail = styled.div`
    font-size: 13px;
    font-family: var(--font-nanumSquareR);
    margin-right: 5px;
    margin-left: 5px;
`;

const StyledHeartDetail = styled.div`
    font-size: 12px;
    font-family: var(--font-nanumSquareR);
    margin-right: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
`;

const StyleVideo = styled.div`
  //display:none;
`;

const StyledReactionWrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content:space-around;
    margin-top: 40px;
    margin-left: 30px;
    margin-right: 30px;
`;

const StyledDetailReaction = styled.div`
    height: 35px;
    width: 35px;
    border: 7px solid rgba(101, 34, 242, 0.51);
    border-radius: 100%;
`;

const StyledReactionNumber = styled.div`
    font-size: 13px;
    font-family: var(--font-nanumSquareR);
    margin-top: 10px;
    text-align: center;
`;

const GiftDetail = (props) => {
  const token = localStorage.getItem('login-token');
  console.log(jwt(token));
  const nickname = jwt(token)['nickName']; 
    
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);

  const {giftid} = useParams();
  // console.log([deleteModalOpen, setDeleteModalOpen])

  const [dataDetail, setDataDetail] = useState({});
  const [dataEmoji, setDataEmoji] = useState({});
  useEffect(() => {
      axios.get('http://i8a402.p.ssafy.io/api/gift/detail', {params: {giftId: giftid}, headers: {Auth: `${token}`}})
          .then((res) => {
              setDataDetail(res.data)
              setDataEmoji(res.data["emoji"])})
              // console.log(res.data["emoji"]))
  }, []);

  return (
    <div>
      <Logo/>
      <Nav/>
      <ProfileHeader nickname={nickname}/>
      <StyledGiftDetailContainer>
        <StyledGiftDetailBtnWrapper>
          <StyledDetailBtn>비공개</StyledDetailBtn>
          <StyledDetailBtn>삭제하기</StyledDetailBtn>
        </StyledGiftDetailBtnWrapper>
        <StyledDetailImg><img src={dataDetail.giftImg} width="350"/></StyledDetailImg>

        <StyledDetailBottomWrapper>
          <StyledSongWrapper>
            <HiMusicNote fill="#6522f2" size="16"/>
            <StyledSongDetail>{dataDetail.singer}</StyledSongDetail>
            <StyledSongDetail>-</StyledSongDetail>
            <StyledSongDetail>{dataDetail.song}</StyledSongDetail>
          </StyledSongWrapper>
          <StyledHeartWrapper>
            <StyledHeartDetail><FaHeart fill="red"/></StyledHeartDetail>
            <StyledHeartDetail>21340</StyledHeartDetail>
          </StyledHeartWrapper>
        </StyledDetailBottomWrapper>

        <StyledReactionWrapper>
          <StyledDetailReaction>
            <img src={love} height="35px" width="35px"/>
            <StyledReactionNumber>{dataEmoji.emo1}</StyledReactionNumber>
          </StyledDetailReaction>
          <StyledDetailReaction>
            <img src={sad} height="35px" width="35px"/>
            <StyledReactionNumber>{dataEmoji.emo2}</StyledReactionNumber>
          </StyledDetailReaction>
          <StyledDetailReaction>
            <img src={wow} height="35px" width="35px"/>
            <StyledReactionNumber>{dataEmoji.emo3}</StyledReactionNumber>
          </StyledDetailReaction>
          <StyledDetailReaction>
            <img src={fun} height="35px" width="35px"/>
            <StyledReactionNumber>{dataEmoji.emo4}</StyledReactionNumber>
          </StyledDetailReaction>
        </StyledReactionWrapper>
        
      </StyledGiftDetailContainer>  
      {deleteModalOpen&& <DeleteModal setOpenModal={setDeleteModalOpen}/>}
    </div>
  );
  
}


export default GiftDetail;