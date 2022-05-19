import React from "react"; 
import styled from "styled-components";
const MyFooter = styled.footer`
    background-color: #12124B;
    padding-top: 20px;
    padding-bottom: 20px;
    display: flex;
    flex-direction: column;
    justify-content: left;
`;
const Text = styled.div`
    color: white;
    font-size: 16px;
    background-color: transparent;
    padding-left: 40px;
    padding-top: 5px;
`;
class Footer extends React.Component{ 
    render() { 
        return ( 
            <MyFooter>
                <Text>FakeCopyright © 2022 MovFlix Limited.</Text>
                <Text>MovFlix Thailand</Text>
            </MyFooter> 
        ); 
    }
} 
export default Footer;