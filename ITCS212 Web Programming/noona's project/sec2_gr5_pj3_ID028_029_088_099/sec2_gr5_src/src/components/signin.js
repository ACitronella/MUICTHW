import React from 'react';
import {NotificationContainer, NotificationManager} from 'react-notifications';
import styled from "styled-components";
const MySignin = styled.div`
    background-color: black;
    display:flex;
    justify-content: center;
    align-items: center;
    padding: 100px;
`;
const SigninForm = styled.form`
    background-color: white;
    padding: 80px 80px 80px 80px;
    border-radius: 10px;
    margin: 50px;
`;
const H1 = styled.h1`
    background-color: white;
    color:#141216;
    text-align: center;
    padding-bottom: 30px;
`;
const SignInput = styled.input`
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    background-color: white;
`;
const SigninBtn = styled.button`
    width: 100%;
    padding: 5px;
    color: white;
    background-color: midnightblue;
    border-radius: 5px;
    border-color: transparent;
    margin-top: 30px;
`;
const handleMouseEnter = e => {
    e.target.style.background = 'DarkBlue'
}
const handleMouseLeave = e => {
    e.target.style.background = 'MidnightBlue'
}

async function SigninUser(props) {
    return fetch("http://localhost:3030/signin/auth", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(props)
    })
    .then((response) => response.json())
}

class SignIn extends React.Component{ 
    constructor(props){
        super(props);
        this.state = {
            email: '',
            password: ''
        };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.signout = this.signout.bind(this);
    }

    handleChange(changeObject){
        this.setState(changeObject);
    }

    handleSubmit = async e => {
        e.preventDefault();
        const response = await SigninUser({
            email: this.state.email,
            password: this.state.password,
        });
        console.log(response);
        if('accessToken' in response){
            sessionStorage.setItem('accessToken', response['accessToken']);
            sessionStorage.setItem('role', response['role']);
            window.location.href = "/"
        }
        else{
            NotificationManager.error('Please sign in again...','Sign in failed.', 3000);
        }
    }
    signout(e){
        sessionStorage.removeItem('accessToken','role');
        NotificationManager.info('Have a good day!','Sign out successfully.', 3000);
    }
    render(){
        return ( 
            <MySignin>
                <SigninForm onSubmit={this.handleSubmit}>
                    <H1>Sign In</H1>
                    <label>Email</label>
                    <SignInput type="email" id="Email" name="email" className="signinput" placeholder="Type your email" required
                        onChange = { (e) => 
                            this.handleChange({
                                email: e.target.value
                            })
                        }
                    />
                    <label>Password</label>
                    <SignInput type="password" id="Password" name="password" className="signinput" placeholder="Type your password" required pattern=".{6,}" title="at least 6 characters"
                        onChange = { (e) => 
                            this.handleChange({
                                password: e.target.value
                            })
                        }
                    />
                    <SigninBtn type="submit" className="signinbutt" id="signinbutt"
                        onMouseEnter={handleMouseEnter} 
                        onMouseLeave={handleMouseLeave}
                    >Sign In</SigninBtn><br/>
                    <button type="button" className="btn btn-danger mt-2 w-100" onClick={(e) => this.signout(e)}>Sign out</button>
                </SigninForm> 
                <NotificationContainer/>
            </MySignin>
        ); 
    }
}
export default SignIn;