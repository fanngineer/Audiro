import React, { useState } from 'react'
import styled from 'styled-components';
import axios from 'axios'
const StyeldNicknameModal=styled.div`
    
`;

const NicknameModal = (props) => {

    const [newNickname,setNewNickname]=useState('')
    const onChange=(e)=>{    
        setNewNickname(e.target.value)
        setNewNickname(e.target.value)
        console.log(newNickname)
        console.log(e.target.value)
        }
    
    const token=localStorage.getItem('login-token')
    const submitHandler=(e)=>
    {
        e.preventDefault()}    

    const ChangeNickname=(e)=> {
        console.log(newNickname)
        axios({
            method:'POST',
            url:'http://i8a402.p.ssafy.io:80/api/user/change-nickname',
            headers: {Auth: `${token}`},
            data:{
                newNickname:`${newNickname}`
            },
        })
        .then ((res)=>{
            console.log(res.header.refresh)
            const jwtToken=res.header.resfresh
            localStorage.setItem('login-token', jwtToken);
            console.log('success')

        })
        .catch((err=>{
            console.log(err.data)
        }))
    }

    return (
        <StyeldNicknameModal>
       
            <form onSubmit={submitHandler}>
                <input onChange={onChange} value={newNickname}  type='text'/> 
                <button type='button' onClick={ChangeNickname}>닉네임 변경</button>
            </form>
        </StyeldNicknameModal>
    );
};

export default NicknameModal;