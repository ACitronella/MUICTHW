import React from 'react';
import { Link } from 'react-router-dom';
import styled from "styled-components";
const MyManage = styled.div`
    background-color: black;
    padding: 250px 200px 250px 200px;
    display: flex;
    flex-direction: column;
    justify-content: center;
`;
class Manage extends React.Component{ 
    render() { 
        const token = sessionStorage.getItem('accessToken');
        const role = sessionStorage.getItem('role');
        if(!token) {
            window.location.href = "/signin"
        }
        else{
            if(role === 'normal user'){
                return(
                    <center style={
                            {
                                backgroundColor: "black",
                                color: "white",
                                paddingTop: "250px",
                                paddingBottom: "250px",
                                fontSize: "50px"
                            }
                        }>Sorry. Your are not an admin T_T
                    </center>
                )
            }
        }
        return ( 
            <MyManage>
                <Link to="/manage/movie">
                    <button type="button" className="btn btn-primary btn-lg mb-5 w-100"><h2>Manage Movie</h2></button>
                </Link>
                <Link to="/manage/user">
                    <button type="button" className="btn btn-primary btn-lg w-100"><h2>Manage User</h2></button>
                </Link>
            </MyManage>
        ); 
    }
} 
export default Manage;