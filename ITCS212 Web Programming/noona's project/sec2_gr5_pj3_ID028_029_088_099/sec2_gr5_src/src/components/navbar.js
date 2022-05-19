import React from "react"; 
import {Link} from "react-router-dom"; 
import styled from "styled-components";
const TopMenu = styled.nav` 
    display: flex; 
    flex-direction: row; 
    justify-content: right; 
    margin: 0px; 
    padding: 20px 10px 20px 10px;
    background-color: #12124B; 
`;
const MyUl = styled.ul` 
    list-style-type: none; 
    display: flex; 
`;
const MyLi = styled.li` 
    display: block;
    padding: 0px 25px 0px 25px;
`;
const MyLink= styled(Link)` 
    font-size: 17pt; 
    color: white; 
    text-decoration: none; 
`;
class Nav extends React.Component{ 
    render() { 
        return ( 
        <TopMenu> 
            <MyUl>
                <MyLi><MyLink to="/">Home</MyLink></MyLi>
                <MyLi><MyLink to="/search">Search</MyLink></MyLi>
                <MyLi><MyLink to="/manage">Manage</MyLink></MyLi>
                <MyLi><MyLink to="/aboutus">About Us</MyLink></MyLi>
                <MyLi><MyLink to="/signin">Sign In</MyLink></MyLi>
            </MyUl>
        </TopMenu> 
        ); 
    } 
} 
export default Nav;