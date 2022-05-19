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
    width: 600px;
`;

const Labels = styled.label`
    color: black;
    font-size: 17px;
`;

class ManageMovie extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            MovieID: "",
            MovieName: "",
            MovieImg: "",
            MovieDate: "",
            MovieTime: "",
            Director: "",
            Rate: "",
            Genre: "",
            Description: "",
            Query: "",
            Criteria: "",
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
                "movie": {
                    "Movie_ID": this.state.MovieID,
                    "Movie_Name": this.state.MovieName,
                    "Movie_img_src": this.state.MovieImg,
                    "Release_Date": this.state.MovieDate,
                    "Run_Time": this.state.MovieTime,
                    "Director": this.state.Director,
                    "Rate": this.state.Rate,
                    "Tag": this.state.Genre,
                    "Movie_Description": this.state.Description
                }
            }
        )
        fetch("http://localhost:3030/movie/add", {
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
                "movie": {
                    "Movie_ID": this.state.MovieID,
                    "Movie_Name": this.state.MovieName,
                    "Movie_img_src": this.state.MovieImg,
                    "Release_Date": this.state.MovieDate,
                    "Run_Time": this.state.MovieTime,
                    "Director": this.state.Director,
                    "Rate": this.state.Rate,
                    "Tag": this.state.Genre,
                    "Movie_Description": this.state.Description
                }
            }
        )
        fetch("http://localhost:3030/movie/update", {
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
                "Movie_ID": this.state.MovieID
            }
        )
        fetch("http://localhost:3030/movie/delete", {
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
        let url = "http://localhost:3030/movie/select/" + this.state.Criteria + "/" + this.state.Query;
        if(this.state.Criteria === "all"){
            url = "http://localhost:3030/movie/selectall";
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
        fetch("http://localhost:3030/movie/selectall", {
            method: "GET",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
        })
        .then((response) => response.json())
        .then((response) => {
            this.setState({
                movies: response['data']
            })
        })
        .catch((err) => {console.log(err);});
    }

    render(){
        return (
            <div style={{backgroundColor: "black"}}>
                <EditBoxBG>
                    <H1>Manage Movie</H1>
                    <EditBoxArr>
                    <EditForm>
                        <Labels>Search Movie:</Labels><br/>
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
                                <option value="all">Search movie by...</option>
                                <option value="all">No criteria</option>
                                <option value="id">Movie ID</option>
                                <option value="name">Movie name</option>
                                <option value="tag">Genre</option>
                            </select>
                            <button type="button" className="btn btn-warning mx-3" onClick={(e) => this.select(e)}>Search</button>
                        </div>
                        <Labels>Movie ID: </Labels><br/>
                        <EditInput type = "text" name="MovieID" placeholder='Type movie ID here....'
                            value = {this.state.MovieID}
                            onChange = { (e) =>
                                this.handleChange({
                                    MovieID: parseInt(e.target.value)
                                })
                            }
                        /><br />
                        <Labels>Movie name: </Labels><br/>
                        <EditInput type = "text" name="MovieName" placeholder='Type movie name here....'
                            value = {this.state.MovieName}
                            onChange={ (e) =>
                                this.handleChange({
                                    MovieName: e.target.value
                                })
                            }
                        /><br />
                        <Labels>Image source: </Labels><br/>
                        <EditInput type = "text" name="MovieImg" placeholder='Type movie image here....'
                            value = {this.state.MovieImg}
                            onChange={ (e) =>
                                this.handleChange({
                                    MovieImg: e.target.value
                                })
                            }
                        /><br />
                        <Labels>Released date: </Labels><br/>
                        <EditInput type = "date" name="MovieDate" placeholder='Type movie released date here....'
                        value = {this.state.MovieDate}
                            onChange={ (e) =>
                                this.handleChange({
                                    MovieDate: e.target.value
                                })
                            }
                        /><br />
                        <Labels>Run time: </Labels><br/>
                        <EditInput type = "text" name="MovieTime" placeholder='Type run time in the format hh:mm:ss'
                            value = {this.state.MovieTime} 
                            onChange={ (e) =>
                                this.handleChange({
                                    MovieTime: e.target.value
                                })
                            }
                        /><br />
                        <Labels>Director: </Labels><br/>
                        <EditInput type = "text" name="Director" placeholder='Type movie director here....'
                            value = {this.state.Director}
                            onChange={ (e) =>
                                this.handleChange({
                                    Director: e.target.value
                                })
                            }
                        /><br />
                        <Labels>Rate: </Labels><br/>
                        <EditInput type = "text" name="Rate" placeholder='Type movie rate here....'
                            value = {this.state.Rate}
                            onChange={ (e) =>
                                this.handleChange({
                                    Rate: e.target.value
                                })
                            }
                        /><br />
                        <Labels>Genre: </Labels><br/>
                        <EditInput type = "text" name="Genre" placeholder='Type movie tag here....'
                            value = {this.state.Genre}
                            onChange={ (e) =>
                                this.handleChange({
                                    Genre: e.target.value
                                })
                            }
                        /><br />
                        <Labels>Description: </Labels><br/>
                        <EditInput type = "text" name="Description" placeholder='Type movie description here....'
                            value = {this.state.Description}
                            onChange={ (e) =>
                                this.handleChange({
                                    Description: e.target.value
                                })
                            }
                        /><br />
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
                <center><h1 className='text-light mb-5'>All movies</h1></center>
                <Movies movies = {this.state.movies}/>
            </div>
        );
    }
}

class Results extends React.Component{
    render(){
        return(
            <div style={{display:'flex',flexDirection:'row',flexWrap:'wrap',justifyContent:'center'}}>
            {this.props.results && this.props.results.map((result) => {
                return(
                    <div className="card text-light bg-primary mb-4 mx-4" style={{width: "24rem"}} key={result.Movie_ID}>
                        <div className="card-header">ID: {result.Movie_ID}</div>
                        <div className="card-body">
                            <h5 className="card-title h4">{result.Movie_Name}</h5>
                            <p className="card-text m-0"><b>Image source: </b>{result.Movie_img_src}</p>
                            <p className="card-text m-0"><b>Released date: </b>{result.Release_Date.substring(0,10)}</p>
                            <p className="card-text m-0"><b>Run time: </b>{result.Run_Time}</p>
                            <p className="card-text m-0"><b>Director: </b>{result.Director}.</p>
                            <p className="card-text m-0"><b>Movie rate: </b>{result.Rate}</p>
                            <p className="card-text m-0"><b>Genre: </b>{result.Tag}</p>
                            <p className="card-text m-0"><b>Description: </b>{result.Movie_Description}</p>
                        </div>
                    </div>
                )
            })}
            </div>
        )
    }
}

class Movies extends React.Component{
    render(){
        return(
            <div style={{display:'flex',flexDirection:'row',flexWrap:'wrap',justifyContent:'center'}}>
            {this.props.movies && this.props.movies.map((movie) => {
                return(
                    <div className="card text-dark bg-light mb-4 mx-4" style={{width: "24rem"}} key={movie.Movie_ID}>
                        <div className="card-header text-light bg-primary">ID: {movie.Movie_ID}</div>
                        <div className="card-body">
                            <h5 className="card-title text-primary h4">{movie.Movie_Name}</h5>
                            <p className="card-text m-0"><b>Image source: </b>{movie.Movie_img_src}</p>
                            <p className="card-text m-0"><b>Released date: </b>{movie.Release_Date.substring(0,10)}</p>
                            <p className="card-text m-0"><b>Run time: </b>{movie.Run_Time}</p>
                            <p className="card-text m-0"><b>Director: </b>{movie.Director}.</p>
                            <p className="card-text m-0"><b>Movie rate: </b>{movie.Rate}</p>
                            <p className="card-text m-0"><b>Genre: </b>{movie.Tag}</p>
                            <p className="card-text m-0"><b>Description: </b>{movie.Movie_Description}</p>
                        </div>
                    </div>
                )
            })}
            </div>
        )
    }
}

export default ManageMovie;