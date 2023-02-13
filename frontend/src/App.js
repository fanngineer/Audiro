import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from './pages/Home';
import GiftDetail from './pages/GiftDetail';
import MessageList from './pages/MessageList';
import './App.css';
import GiftList from "./pages/GiftList";
import Login from './pages/Login'
import Musicmate from "./pages/Musicmate";
import Intro from './pages/Intro'
import Test from "./pages/Test";
import Others from "./pages/Others";
import Messages from "./pages/Messages";

import UserInfo from "./pages/UserInfo";

function App() {

  return (
    <div>
      {/* <Logo userId='연희동 아자르'/> */}
      <BrowserRouter>
      {/* <Nav/> */}
      <Routes>
          <Route exact path="/" element={<Intro/>}/>
          <Route path='/intro' element={<Intro/>}/>
          <Route path='/home' element={<Home/>}/>

          <Route path="/gifts" element={<GiftList/>}/>
          <Route path="/gifts/:giftid/:giftcnt/:mmcnt" element={<GiftDetail/>}/>
          
          <Route path="/messenger" element={<MessageList/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/musicmate' element={<Musicmate/>}/> 

          <Route path='/others/:nickname' element={<Others/>}/>
          <Route path='/messenger/:opponent' element={<Messages/>} />
          <Route path='/test/' element={<Test/>}/>
          <Route path='/userinfo' element={<UserInfo/>}/>
          <Route path='/test' element={<Test/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;