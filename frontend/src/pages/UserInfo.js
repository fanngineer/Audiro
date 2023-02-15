import React, {useEffect, useState} from "react";
import styled from 'styled-components';
import axios from "axios";
import Logo from "../components/Logo";
import Nav from "../components/Nav";
import EditNickname from "./EditNickname";
import EditMsg from "./EditMsg";
import jwt from 'jwt-decode';
import {useNavigate} from 'react-router-dom'
const StyleUserInfoInput = styled.input`
    font-size: 16px;
    font-family: var(--font-nanumSquareL);
    color: white;
`;

const StyleUserInfoContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 30px;
    background-color: rgba(65, 22, 162, 0.5);
    margin-left: 20px;
    margin-right: 20px;
    padding-bottom: 20px;
`;

const StyleUserInfoImg = styled.img`
    border-radius: 100%;
    background-color: white;
    width: 90px;
    height: 90px;
    margin-bottom: 50px;
`;

const StyledUserInfoWrapper = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 300px;
    margin-bottom: 10px;
`;

const StyleUserInfoTitle = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareB);
    color: white;
`;

const StyleTitle = styled.div`
    font-size: 18px;
    font-family: var(--font-nanumSquareB);
    color: white;
    margin-bottom: 20px;
    margin-top: 20px;
`;

const StyleUserInfoText = styled.div`
    font-size: 16px;
    font-family: var(--font-nanumSquareL);
    color: white;
`;

const StyledLogout = styled.button`
    background-color:#6522F2;
    border: none;
    width:100px;
    heigth:60px;
    border-radius:5px;
    
`;

const UserInfo = () =>{
    const Navigate=useNavigate()

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    const nickname = jwt(token)['nickName']; 
    console.log(nickname);
    const userId = jwt(token)['userId']; 
    console.log(userId);


    const [name, setName] = useState();
    const [email, setEmail] = useState();
    const [profile, setProfile] = useState();
    const [isEdit, setEdit] = useState(false);
    let [msg,setMsg]=useState('상태메세지 입니다.');
    const [editingMsg,setEditingMsg]=useState(false);

    const[selectedFiles,setSelectedFiles]=useState()

 
    useEffect(() => {
        axios.get(`http://i8a402.p.ssafy.io/api/user/${userId}`, {headers: {Auth: `${token}`}},{withCredentials:true})
            .then((res) => {
                 console.log(res);
                 setName(res.data["name"]);
                 setEmail(res.data["email"]);
                 setProfile(res.data['img']);
                 console.log('이미지까지 완료')
                 setMsg(res.data['msg'])
            })
            
        }, []);
    if (!msg) {msg='상태 메세지입니다.'}
    const onClicked = () => {
      console.log("hahahahaha");
      setEdit(true);
    };
    const changeMsg=()=>{
        setEditingMsg(true)
    };
    const logout=() =>{
        localStorage.removeItem('login-token')
        localStorage.removeItem('refresh-token')
        Navigate('/login')
        console.log('로그아웃완료')
    };

    const inputHandler=(e)=>{
        e.preventDefault()
        const file=e.target.files;
        console.log(file)
        setSelectedFiles(file)
        console.log(selectedFiles[0]['name'])
        console.log('done1')
    };
    const changeImg=(e)=>{
        e.preventDefault()
        const formData = new FormData();
  
        formData.append("newImg",selectedFiles[0],selectedFiles[0]['name']);
        console.log(formData.has('newImg'))
        console.log('done2')
        
        axios.post('http://i8a402.p.ssafy.io/api/user/change-img',
                    formData,
                    {headers: {
                          "Auth": token,
                          "content-type": "multipart/form-data"},
  
                    }
        )
        .then((res)=>{
            console.log(res)
        })
        .catch((err)=>{
            console.log(err)
        })
        
    }

    return (
        <div>
            <Logo/>
            <Nav/>
            <StyleUserInfoContainer>
                <StyleTitle>내 정보 수정</StyleTitle>
                <StyleUserInfoImg src={profile}></StyleUserInfoImg>

                    
                   <input type="file" accept='image/*' onChange={inputHandler}/>
                   <input type="button" value="전송" color='black' onClick={changeImg}/>
                  


                <StyledUserInfoWrapper>
                    <StyleUserInfoTitle>이름</StyleUserInfoTitle>
                    <StyleUserInfoText>{name}</StyleUserInfoText>
                </StyledUserInfoWrapper>
                <StyledUserInfoWrapper>
                    <StyleUserInfoTitle>이메일</StyleUserInfoTitle>
                    <StyleUserInfoText>{email}</StyleUserInfoText>
                </StyledUserInfoWrapper>
                <StyledUserInfoWrapper>
                    <StyleUserInfoTitle>닉네임</StyleUserInfoTitle>
                    <StyleUserInfoText onClick={onClicked}>{isEdit? <EditNickname/> : nickname}</StyleUserInfoText>
                </StyledUserInfoWrapper>
                <StyledUserInfoWrapper>
                    <StyleUserInfoTitle>상태메세지</StyleUserInfoTitle>
                    <StyleUserInfoText onClick={changeMsg}>{editingMsg? <EditMsg/> : msg}</StyleUserInfoText>
                </StyledUserInfoWrapper>
                <StyledLogout background-color="black" onClick={logout}>로그아웃</StyledLogout>
                
             
            </StyleUserInfoContainer>
            
        </div>
      )
};

export default UserInfo;