import {GrFormClose} from 'react-icons/gr'
import React, { PropsWithChildren } from 'react';
import styled from 'styled-components';
import { useNavigate, useParams } from 'react-router-dom';
import axios from "axios";

const StyledModalBG = styled.div`
    position: absolute;
    height: 100vh;   
    width: 100vw;
    top: 40%; 
`;

const StyledModalContainer = styled.div`
    border-radius: 10px;
    background-color: #F5336D;
    padding-left: 25px;
    padding-right: 25px;
    padding-top: 10px;
    padding-bottom: 10px;
    margin-left: 50px;
    margin-right: 50px;
`;

const StyledModalText = styled.div`
    text-align: center;
    font-size: 13px;
    font-family: var(--font-nanumSquareL);
    margin-bottom: 5px;
`;

const StyledModalBtnWrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 10px;
    >*{
      color: #F5336D;
      background-color: white;
      font-size: 14px;
      font-family: var(--font-nanumSquareR);
      font-weight: bold;
      border: none;
      border-radius: 10px;
      padding-left: 15px;
      padding-right: 15px;
      padding-top: 5px;
      padding-bottom: 5px;
    }
`;

function DeleteModal({setOpenModal}) {
    const closeDeleteModal = () => {
        setOpenModal(false);
    };
    const naviagte=useNavigate()
    const {giftid} = useParams();
    const token = localStorage.getItem('login-token');
    const deleteGift=()=>{
    
      axios.delete("http://i8a402.p.ssafy.io/api/gift", {params: {giftId: giftid}, headers: {Auth: `${token}`}})
        .then(response => {
          console.log(response.data);
          naviagte(-1);
        });
    }
    return (
        <StyledModalBG>
            <StyledModalContainer>
            <StyledModalText>피드를 삭제하시면, </StyledModalText>
            <StyledModalText>해당 공간에 남겨진 엽서도 삭제됩니다. </StyledModalText>
            <StyledModalText>정말 삭제하시겠습니까?</StyledModalText>
            <StyledModalBtnWrapper>
                <button onClick={closeDeleteModal}> 취소 </button>
                <button onClick={deleteGift}> 삭제 </button>
            </StyledModalBtnWrapper>
            </StyledModalContainer>
        </StyledModalBG>
      );
}


export default DeleteModal;