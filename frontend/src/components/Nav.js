import React, {useState} from "react";
import { Link } from "react-router-dom";
import '../styles/Nav.css'
import styled from 'styled-components';
import jwt from 'jwt-decode';

const Nav=()=>{

    const token = localStorage.getItem('login-token');
    console.log(jwt(token));
    let nickname = jwt(token)['nickName']; 

    const items=[
        {
            title: '홈',
            link: '/home'
        },
        {
            title: '나의 엽서',
            link: '/gifts'
        },
        {
            title: '편지함',
            link: '/messenger'
        },
        {
            title: '뮤직 메이트',
            link: '/musicmate'
        },
        {
            title: '마이 페이지',
            link: '/userinfo'
        }
    ];

    const [select, setSelect] = useState('홈');
    const onClicked = (type)=>{
        setSelect(type);
    }

    const cur=window.location.href
    if (cur=='http://localhost:3000/intro'){
        return null;
    }

    return(
        <nav className="nav-container">
            {items.map((item, index) => (
                <div 
                    key={index}
                    onClick={() => {
                        onClicked(item.title);
                    }}
                >
                    <Link to={item.link}>
                        {item.title}
                    </Link>
                </div>
            ))}
        </nav>
    );
};

export default Nav;