import React, {useEffect, useState} from "react";
import styled from 'styled-components';
import axios from "axios";
import Logo from "../components/Logo";
import Nav from "../components/Nav";

import jwt from 'jwt-decode';
import {useNavigate} from 'react-router-dom'

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

const EditMsg = (props) =>{
    const Navigate=useNavigate()

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
        
        axios.post('http://i8a402.p.ssafy.io:80/api/user/change-msg', {}
        ,
        {
            headers: {
              "Auth": token
            },
            params: {newMsg: `${nickname}`}
        })
        .then ((res)=>{
            console.log("결과 받기")
            console.log(token);
            console.log(res);
    
            console.log((res['body']))
         
            Navigate('/home')
            
            
            
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

export default EditMsg;