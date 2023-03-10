import React, { useState } from 'react'
import styled from 'styled-components';
import axios from 'axios'
const StyeldNicknameModal=styled.div`
    
`;

const NicknameModal = (props) => {

    const [nickname,setNickname]=useState('')
    const onChange=(e)=>{    
        setNickname(e.target.value)
        console.log(nickname)
    }
    
    const token=localStorage.getItem('login-token')

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
        <StyeldNicknameModal>
       
            <form onSubmit={submitHandler}>
                <input onChange={onChange} value={nickname}  type='text'/> 
                <button type='submit'>닉네임 변경</button>
            </form>
        </StyeldNicknameModal>
    );
};

export default NicknameModal;
