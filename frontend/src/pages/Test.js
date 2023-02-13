import React,{useState} from 'react';
import styled from 'styled-components';

const StyledTitle = styled.div`
  display: ${props => props.isHidden ||'none'};
`;

const Test = () => {
    const [isHidden, setisHidden] = useState('none')
    const handleToggle = () => {
        setisHidden("block")
    }
    return(
    <>
    <button onClick={handleToggle} >Click me</button>

    // see the prop name here, isHidden, this is better than toggle as a prop
    <StyledTitle isHidden={isHidden}>Text</StyledTitle>
    </>)
};
export default Test;
