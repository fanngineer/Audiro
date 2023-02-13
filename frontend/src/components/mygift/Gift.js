import React, {useState, useRef, useEffect} from "react";
import { Link } from "react-router-dom";
import styled from 'styled-components';
import axios from "axios";

const Gift = (props) => {
  
  return (
    <div>
        <Link to={`/gifts/${props.id}/${props.giftcnt}/${props.mmcnt}`}><img src={props.src} height={105} width={170}/></Link>
    </div>
  );
  
}


export default Gift;