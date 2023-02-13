import React, {useState, useEffect} from "react";
import styled from 'styled-components';

import axios from "axios";
import jwt from 'jwt-decode';

const StyledLogoGreet=styled.div`
    display: flex;
    justify-content: end;
    margin-right: 15px;
    align-items: center;
`;

const StyledLogoHeadset=styled.img`
    background-color: white;
    border-radius: 100%;
    width: 20px;
    height: 20px;
`;

const StyledLogoProfile = styled.div`
    text-align: end;
    color: white;
    margin-right: 5px;
    font-size: 15px;
    font-family: var(--font-nanumSquareR);
    margin-left: 5px;
`;

const LogoUserInfo=(props)=>{   

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    const nickname = jwt(token)['nickName']; 
    console.log(nickname);
    const userId = jwt(token)['userId']; 
    console.log(userId);

    const [userImg, setuserImg] = useState();
    useEffect(() => {
        axios.get(`http://i8a402.p.ssafy.io/api/user/${userId}`, {headers: {Auth: `${token}`}})
            .then((res) => {
                 setuserImg(res.data.img);
                })
    }, []);

    // if (props.type=='login'){
    //     return null;
    // }

    return(
        
        <StyledLogoGreet>
            <StyledLogoHeadset src={userImg}/>
            <StyledLogoProfile>{nickname}님</StyledLogoProfile>
        </StyledLogoGreet>
    );
};

export default LogoUserInfo;