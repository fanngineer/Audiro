import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from './pages/Home';
import MyGift from './pages/MyGift';
import Messenger from './pages/Messenger';
import Nav from './components/Nav';
import './App.css';

function App() {
  return (
    <div>
      <BrowserRouter>
      <Nav/>
      <Routes>
          <Route exact path="/" element={<Home/>}/>
          <Route path="/mygift" element={<MyGift/>}/>
          <Route path="/messenger" element={<Messenger/>}/>
        </Routes>
      </BrowserRouter>
      
    </div>
  );
}

export default App;