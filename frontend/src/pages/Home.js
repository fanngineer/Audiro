import React, {useEffect, useState, useParams} from 'react';
import Logo from '../components/Logo';
import Nav from '../components/Nav';

import jwt from 'jwt-decode';
const Home = () => {

    const [token, setToken] = useState();

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        console.log(window.location);
        console.log(window.location.search);
        const jwtToken = params.get("auth");
        const refreshToken = params.get("refresh");
        const spotId = params.get("spot_id");
        console.log(spotId);
        
        if(jwtToken!=null){
          setToken(jwtToken);
          localStorage.setItem('login-token', jwtToken);
          localStorage.setItem('refresh-token', refreshToken);
        }
        
        console.log(jwtToken);
        console.log("parsing");
    }, [])
    
    //setToken(localStorage.getItem('login-token'));
    return (
        <div>
            <Logo/>
            <Nav/>
            홈 서비스 소개
            {token}
        </div>
    );
};

export default Home;