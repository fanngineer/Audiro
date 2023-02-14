import React ,{useState,useEffect}from 'react';
import styled from 'styled-components';
import {useParams} from 'react-router-dom'
import axios from 'axios'
import jwt from 'jwt-decode';
import GiftDetailForOthers from '../components/mygift/Gift';
import Logo from '../components/Logo'
import Nav from '../components/Nav'


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

    width:248px;
    height:73px;
    font-weight: bold;
    font-family: var(--font-nanumSquareR);
`

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
const StyledNotMate=styled.div`
    display: flex;
    flex-direction: column;
    justify-items: space-around;
    align-items: center;
`;

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
    let mates=[]
    useEffect(() => {
        axios.get( 'http://i8a402.p.ssafy.io/api/gift', {params: {nickname: `${nickname}`}, headers: {Auth: `${token}`}} )
            .then((res) => {
                 console.log(res)
                 setDataList(res.data);
                 setGiftcnt(res.data.length);
                })
            .catch((err)=>{
                 console.log (err)
            })

        axios.get('http://i8a402.p.ssafy.io/api/musicmate',{params:{userId:`${userId}`},headers:{Auth:`${token}`}})
            .then ((res)=>{
                console.log(res.data)
                for (const dat of res.data){
                    mates.push(dat['nickname'])
                }
                console.log(mates)
                console.log(mates.includes(nickname))
                
            })
            .catch((err)=>{
                console.log(err) 
            })
    }   );

    const beMate=()=>{

    }
    if (mates.includes(nickname)) {

        return(     
        <>
        <Logo/>
        <Nav/>
        <OthersBackground>
            {nickname}님 페이지입니다<br/>
            <StyledMyGiftListWrapper>
                <StyledMyGiftList>
                    {dataList?.map(item => (
                        <GiftDetailForOthers nickname={nickname}  key={item.id} id={item.id} src={item.giftImg}/>
                    ))}
                </StyledMyGiftList>
            </StyledMyGiftListWrapper>
        </OthersBackground> 
        </>    
      )
    }
    
    else{
        return (
            <div>
            <Logo/>
            <Nav/>
            <StyledNotMate>
                {nickname}님 페이지입니다 <br/>
                게시물을 보시려면 음악 메이트 신청하세요.
                
                <OthersMateButton onClick={beMate}>음악 메이트 신청하기</OthersMateButton>
            </StyledNotMate>
            
            </div>
            
            )   
    }
}
export default Others;