import React, {useState, useRef, useEffect} from "react";
import { Link } from "react-router-dom";
import styled from 'styled-components';
import axios from "axios";

const Gift = (props) => {
  
  return (
    <div>
        <Link to={{pathname:`/gifts/${props.id}/${props.giftcnt}/${props.mmcnt}`,
                   state:{nickname:props.nickname,id:props.id}
      }}><img src={props.src} height={105} width={170}/></Link>
    </div>
  );
  
}


export default Gift;