import React from 'react';
import styled from "styled-components";

const H1 = styled.h1`
    display:flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    color: #FFFFFF;
    font-size: 40px;
    text-align: center;
    font-weight: bold;
`;

const EditBoxBG = styled.div`
    background-color: black;
    padding-top: 50px;
    padding-bottom: 50px;
`;

const EditForm = styled.form`
    padding: 50px;
    background-color: white;
    border-radius: 10px;
    margin-top: 20px;
    margin-bottom: 30px;
`;

const EditBoxArr = styled.div`
    display: flex;
    justify-content: center;
`;

const EditInput = styled.input`
    padding: 7px;
    font-size: 17px;
    border: 1px solid #d3d3d3;
    border-radius: 2px;
    background-color: white;
    color: black;
    margin: 5px 0 5px 0;
    width: 400px;
`;

const Labels = styled.label`
    color: black;
    font-size: 17px;
`;

class ManageUser extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            User_ID: "",
            First_Name: "",
            Last_Name: "",
            Email: "",
            User_Password: "",
            User_Role: "",
            Query: "",
            Criteria: ""
        };
        this.insert = this.insert.bind(this);
        this.update = this.update.bind(this);
        this.delete = this.delete.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(changeObject){
        this.setState(changeObject);
    }

    insert(e){
        e.preventDefault();
        let body = JSON.stringify(
            {
                "user": {
                    "User_ID": this.state.User_ID,
                    "First_Name": this.state.First_Name,
                    "Last_Name": this.state.Last_Name,
                    "Email": this.state.Email,
                    "User_Password": this.state.User_Password,
                    "User_Role": this.state.User_Role
                }
            }
        )
        fetch("http://localhost:3030/user/add", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
            body: body
        })
        .then((response) => response.json())
        .then((response) => {
            this.selectall();
        })
        .catch((err) => {console.log(err);});
    }

    update(e){
        e.preventDefault();
        let body = JSON.stringify(
            {
                "user": {
                    "User_ID": this.state.User_ID,
                    "First_Name": this.state.First_Name,
                    "Last_Name": this.state.Last_Name,
                    "Email": this.state.Email,
                    "User_Password": this.state.User_Password,
                    "User_Role": this.state.User_Role
                }
            }
        )
        fetch("http://localhost:3030/user/update", {
            method: "PUT",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
            body: body
        })
        .then((response) => response.json())
        .then((response) => {
            this.selectall();
        })
        .catch((err) => {console.log(err);});
    }

    delete(e){
        e.preventDefault();
        let body = JSON.stringify(
            { 
                "User_ID": this.state.User_ID
            }
        )
        fetch("http://localhost:3030/user/delete", {
            method: "DELETE",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
            body: body
        })
        .then((response) => response.json())
        .then((response) => {
            this.selectall();
        })
        .catch((err) => {console.log(err);});
    }

    select(e){
        e.preventDefault();
        let url = "http://localhost:3030/user/select/" + this.state.Criteria + "/" + this.state.Query;
        if(this.state.Criteria === "all"){
            url = "http://localhost:3030/user/selectall";
        }
        fetch(url, {
            method: "GET",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
        })
        .then((response) => response.json())
        .then((response) => {
            this.setState({
                results: response['data']
            })
        })
        .catch((err) => {console.log(err);});
    }

    componentDidMount(){
        this.selectall();
    }

    selectall(){
        fetch("http://localhost:3030/user/selectall", {
            method: "GET",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            }
        })
        .then((response) => response.json())
        .then((response) => {
            this.setState({
                users: response['data']
            })
        })
        .catch((err) => {console.log(err);});
    }

    render(){
        return (
            <div style={{backgroundColor: 'black'}}>
                <EditBoxBG>
                    <H1>Manage User</H1>
                    <EditBoxArr>
                        <EditForm>
                        <Labels>Search User:</Labels><br/>
                            <EditInput name="search" type="text" placeholder="Search..." 
                                value = {this.state.Query}
                                onChange = { (e) => 
                                    this.handleChange({
                                        Query: e.target.value
                                    })
                                }
                            /><br/>
                            <div className="d-flex justify-content-center">
                                <select className="form-select" style={{width: "300px"}} aria-label="Default select example"
                                    onChange = { (e) => 
                                        this.handleChange({
                                            Criteria: e.target.value
                                        })
                                    }>
                                    <option value="all">Search user by...</option>
                                    <option value="all">No criteria</option>
                                    <option value="id">User ID</option>
                                    <option value="firstname">Firstname</option>
                                    <option value="role">Role (Type 'admin' or 'normal user')</option>
                                </select>
                                <button type="button" className="btn btn-warning mx-3" onClick={(e) => this.select(e)}>Search</button>
                            </div><br/>
                            <Labels>User ID: </Labels><br/>
                            <EditInput type = "text" name="MovieID" placeholder='Type user ID here....'
                                value = {this.state.User_ID}
                                onChange={ (e) =>
                                    this.handleChange({
                                        User_ID: parseInt(e.target.value)
                                    })
                                }
                            /><br />
                            <Labels>First name: </Labels><br/>
                            <EditInput type = "text" name="FirstName" placeholder='Type first name here....'
                                value = {this.state.First_Name}
                                onChange={ (e) =>
                                    this.handleChange({
                                        First_Name: e.target.value
                                    })
                                }
                            /><br />
                            <Labels>Last name: </Labels><br/>
                            <EditInput type = "text" name="LastName" placeholder='Type last name here....'
                                value = {this.state.Last_Name}
                                onChange={ (e) =>
                                    this.handleChange({
                                        Last_Name: e.target.value
                                    })
                                }
                            /><br />
                            <Labels>Email: </Labels><br/>
                            <EditInput type = "email" name="Email" placeholder='Type email here....'
                                value = {this.state.Email}
                                onChange={ (e) =>
                                    this.handleChange({
                                        Email: e.target.value
                                    })
                                }
                            /><br />
                            <Labels>Password: </Labels><br/>
                            <EditInput type = "text" name="MovieTime" placeholder='Type user password here....'
                                value = {this.state.User_Password} 
                                onChange={ (e) =>
                                    this.handleChange({
                                        User_Password: e.target.value
                                    })
                                }
                            /><br />
                            <Labels>Role: </Labels><br/>
                            <select className="form-select" style={{width: "400px", backgroundColor: 'aliceblue'}}
                                onChange = { (e) => 
                                    this.handleChange({
                                        User_Role: e.target.value
                                    })
                                }>
                                <option value="normal user">Select User Role</option>
                                <option value="normal user">Normal User</option>
                                <option value="admin">Admin</option>
                            </select>
                            <center>
                                <button type="button" className="btn btn-success mt-4 mx-1" onClick={(e) => this.insert(e)}>Insert</button>
                                <button type="button" className="btn btn-primary mt-4 mx-1" onClick={(e) => this.update(e)}>Update</button>
                                <button type="button" className="btn btn-danger mt-4 mx-1" onClick={(e) => this.delete(e)}>Delete</button> 
                            </center>                   
                        </EditForm>
                    </EditBoxArr>
                </EditBoxBG>
                <center><h1 className='text-light mb-5'>Search results</h1></center>
                <Results results = {this.state.results}/><br/><br/>
                <center><h1 className='text-light mb-5'>All users</h1></center>
                <div style={{padding:'10px 20px 60px 20px'}}>
                    <Users users = {this.state.users}/>
                </div>
            </div>
        );
    }
}

class Results extends React.Component{
    render(){
        return(
            <div style={{display:'flex', flexDirection:'row', flexWrap:'wrap', justifyContent:'center'}}>
            {this.props.results && this.props.results.map((result) => {
                return(
                    <div className="card text-dark bg-light mb-3 mx-4" style={{maxWidth: "18rem"}} key={result.User_ID}>
                    <div className="card-header">ID{result.User_ID}</div>
                    <div className="card-body">
                        <h5 className="card-title">{result.First_Name} {result.Last_Name}</h5>
                        <p className="card-text">Email: {result.Email}</p>
                        <p className="card-text">Encoded password: {result.User_Password}</p>
                        <p className="card-text">Role: {result.User_Role}</p>
                    </div>
                    </div>
                )
            })}
            </div>
        )
    }
}

class Users extends React.Component{
    render(){
        return(
            <table className="table table-striped m-0" style={{backgroundColor: 'white'}}>
                <thead>
                    <tr style={{backgroundColor: 'midnightblue', color: 'white'}}>
                        <th>User ID</th>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th>Encoded Password</th>
                        <th>Role</th>
                    </tr>
                </thead>
                {this.props.users && this.props.users.map((user) => {
                    return(
                        <tbody key={user.User_ID}>
                            <tr>
                                <td>{user.User_ID}</td>
                                <td>{user.First_Name}</td>
                                <td>{user.Last_Name}</td>
                                <td>{user.Email}</td>
                                <td>{user.User_Password}</td>
                                <td>{user.User_Role}</td>
                            </tr>
                        </tbody> 
                    );
                })}
            </table>        )
    }
}

export default ManageUser;