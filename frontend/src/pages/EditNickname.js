import React, {useEffect, useState} from "react";
import styled from 'styled-components';
import axios from "axios";
import Logo from "../components/Logo";
import Nav from "../components/Nav";

import jwt from 'jwt-decode';


const StyleInput = styled.input`
    border-top: none;
    border-left: none;
    border-right: none;
    border-bottom: none;
    background: transparent;
    color: white;
    font-size: 16px;
    font-family: var(--font-nanumSquareL);
    text-align: end;
    :focus{
        outline: none !important;
        border-bottom: 1px solid white;
    }
`;

const EditNickname = (props) =>{

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    
    const [nickname,setNickname]=useState('')
    
    const onChange=(e)=>{    
        setNickname(e.target.value)
        console.log(nickname)
    }
    
     const submitHandler=(e)=>
    {
        e.preventDefault()
        
        const data = {
          "newNickName" : nickname
        }
        
        axios.post('http://i8a402.p.ssafy.io:80/api/user/change-nickname', {}
        ,
        {
            headers: {
              "Auth": token
            },
            params: {newNickName: `${nickname}`}
        })
        .then ((res)=>{
            console.log("결과 받기")
            console.log(token);
            console.log(res);
    
            console.log((res['headers'])['refresh'])
            const jwtToken=(res['headers'])['refresh'];
            localStorage.setItem('login-token', jwtToken);
            console.log('success');
            console.log(localStorage.getItem('login-token'))
        })
        .catch((e)=>{
            console.log(e);
        });
    }    

    return (
            <form onSubmit={submitHandler}>
                <StyleInput onChange={onChange} value={nickname} type='text'/> 
            </form>
      )
};

export default EditNickname;