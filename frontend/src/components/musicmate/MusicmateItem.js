import React from 'react';
import styled from 'styled-components';

import { Link } from "react-router-dom";
import axios from "axios";

const StyledMMWrapper = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: rgba(65, 22, 162, 0.5);
    margin-bottom: 10px;
    width: 300px;
    height: 50px;
    padding-left: 20px;
`;

const StyledMMInfoWrapper=styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
`;

const StyledMMImg=styled.img`
    border-radius: 100%;
    background-color: white;
    width: 30px;
    height: 30px;
    margin-right: 15px;
`;

const StyledMMNinkname=styled.div`
    text-align: end;
    color: white;
    font-size: 15px;
    font-family: var(--font-nanumSquareR);
`;

const StyledMMBlockBtn=styled.div`
    text-align: end;
    color: white;
    font-size: 14px;
    font-family: var(--font-nanumSquareR);
    margin-right: 20px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    padding-right: 10px;
    border-radius: 20px;
    border: 2px solid #F5336D;
`;

const MusicmateItem = (props) => {
    const token = localStorage.getItem('login-token');
    const musicmateDelete = () => {
        axios.delete("http://i8a402.p.ssafy.io/api/musicmate", {params: {mateId: props.id}, headers: {Auth: `${token}`}})
        .then(response => {
          console.log(response.data);
          window.location.reload();
        });
    }

    return (
            <StyledMMWrapper>
                <Link to={`/gifts/other/${props.nickname}/${props.id}`} style={{ textDecoration: 'none' }}>
                    <StyledMMInfoWrapper>
                        <StyledMMImg src={props.img}></StyledMMImg>
                        <StyledMMNinkname>{props.nickname}</StyledMMNinkname>
                    </StyledMMInfoWrapper>
                </Link>
                <StyledMMBlockBtn onClick={musicmateDelete}>삭제하기</StyledMMBlockBtn>
            </StyledMMWrapper>
    );
};

export default MusicmateItem;