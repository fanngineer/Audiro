import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from './pages/Home';
import GiftDetail from './pages/GiftDetail';
import ChatList from './pages/ChatList/ChatList'
import ChatRoom from './pages/ChatRoom/ChatRoom'

import './App.css';
import GiftList from "./pages/GiftList";
import GiftListOther from "./pages/GiftListOther";
import Login from './pages/Login'
import Musicmate from "./pages/Musicmate";
import Intro from './pages/Intro'

import UserInfo from "./pages/UserInfo";
import Main from "./pages/Main"

function App() {

  return (
    <div>
      {/* <Logo userId='연희동 아자르'/> */}
      <BrowserRouter>
      {/* <Nav/> */}
      <Routes>
          <Route exact path="/" element={<Intro/>}/>
          <Route path='/intro' element={<Intro/>}/>
          <Route path='/home' element={<Main/>}/>
          
          <Route path="/gifts" element={<GiftList/>}/>
          <Route path="/gifts/other/:nickname/:id" element={<GiftListOther/>}/>
          <Route path="/gifts/:giftid/:giftcnt/:mmcnt" element={<GiftDetail/>}/>
          
          <Route path="/messenger" element={<ChatList/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/musicmate' element={<Musicmate/>}/> 

          <Route path='/messenger/:channel_id/:other_nickname' element={<ChatRoom/>} />
          <Route path='/userinfo' element={<UserInfo/>}/>
          <Route path='/main' element={<Main/>}/>

      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;