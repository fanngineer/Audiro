import React ,{useState,useEffect}from 'react';
import styled from 'styled-components';
import {useParams} from 'react-router-dom'
import axios from 'axios'
import jwt from 'jwt-decode';

const OthersBackground=styled.div`
    position: absolute;
    height: 100vh;   
    width: 100vw;
    top: 20%; 
    backdrop-filter: brightness(50%);
    // 블러야 투명도야 lighten? darken? opacity: 0.5;
    display:flex;
    justify-content:center;
    align-itmes:center;
`

const OthersMateButton=styled.button`
    background-color: #6522f2;
    border-radius:20px;
    position: absolute;
    top:40%;
    width:248px;
    height:73px;
    font-weight: bold;
    font-family: var(--font-nanumSquareR);
`

const Others = () => {
    const {nickname}=useParams();
    console.log(nickname)
    const token = localStorage.getItem('login-token');
    const [dataList, setDataList] = useState([]);
    const [giftcnt, setGiftcnt] = useState(0);

    const me = jwt(token)['nickName']; 
    console.log(me);
    const userId = jwt(token)['userId'];
    console.log(userId);

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

 

    
    return (
        
        <OthersBackground>
            <div>
                
            {nickname} 다른 사람 페이지입니다<br/>
            
            </div>

            <OthersMateButton>음악 메이트 신청하기</OthersMateButton>
              
        </OthersBackground>
    );
};

export default Others;