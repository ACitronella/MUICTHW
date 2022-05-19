import React from 'react';
import { Link } from 'react-router-dom';
import styled from "styled-components";
const SearchBox = styled.div`
    background-color: black;
    padding-top: 150px;
    padding-bottom: 150px;
`;
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
const SearchForm = styled.form`
    text-align: center;
    padding-top: 50px;
`;
const SearchInput = styled.input`
    padding: 15px;
    font-size: 17px;
    border: 1px solid #2d2c2e;
    width: 35%;
    background-color:#2d2c2e;
    color: white;
`;
const SearchBtn = styled.button`
    color: white;
    font-size: 17px;
    background-color:midnightblue;
    padding: 15px;
    border: 1px solid midnightblue;
`;

async function SearchMovie(props) {
    let url = "http://localhost:3030/search/movies/all/";
    if(props.criteria !== 'all'){
        url = "http://localhost:3030/search/movies/" + props.criteria + "/" + props.query;
    }
    return fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'accept': 'application/json'
        }
    })
    .then((response) => response.json())
}

class Search extends React.Component{ 
    constructor(props){
        super(props);
        this.state = {
            criteria: 'all',
            query: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleChange(changeObject){
        this.setState(changeObject);
    }
    handleSubmit = async e => {
        e.preventDefault();
        await SearchMovie({
            criteria: this.state.criteria,
            query: this.state.query,
        })
        .then((response) => {
            this.setState({
                movies: response['data'],
            })
        })
        .catch((err) => {
            console.log(err);
        });
    }
    render() { 
        const token = sessionStorage.getItem('accessToken');
        if(!token) {
            window.location.href = "/signin"
        }
        return ( 
            <div>
                <SearchBox> 
                    <H1>Search Movies</H1>
                    <SearchForm onSubmit={this.handleSubmit}> 
                        <SearchInput name="search" type="text" placeholder="Search..." 
                            onChange = { (e) => 
                                this.handleChange({
                                    query: e.target.value
                                })
                            }
                        /><br/><br/>
                            <div className="d-flex justify-content-center mb-5">
                                <select className="form-select" style={{width: "300px"}} aria-label="Default select example"
                                    onChange = { (e) => 
                                        this.handleChange({
                                            criteria: e.target.value
                                        })
                                    }>
                                    <option value="all">Search a movie by...</option>
                                    <option value="all">No criteria</option>
                                    <option value="name">Name</option>
                                    <option value="tag">Genre</option>
                                    <option value="year">Released year</option>
                                </select>
                            </div>    
                        <SearchBtn type="submit" id="SearchButt">Search Movies</SearchBtn>
                    </SearchForm>
                </SearchBox>
                <ResultMovies movies = {this.state.movies}/>
            </div>
        ); 
    }
}

class ResultMovies extends React.Component{
    render(){
        return(
            <div style={{display:'flex', flexDirection:'row', flexWrap:'wrap', justifyContent:'center', backgroundColor:'black', padding: '50px'}}>
            {this.props.movies && this.props.movies.map((movie) => {
                return(
                    <center className="d-flex align-items-center" style={{backgroundColor:"black", width: "20rem"}} key={movie.Movie_ID}>
                        <div className="card bg-light m-2" style={{minHeight: "35rem"}}>
                            <div className="card-body">
                                <img src={movie.Movie_img_src} className="card-img-top"/>
                                <h5 className="card-title mt-2">{movie.Movie_Name} ({movie.Release_Date.substring(0, 4)})</h5>
                                <h6 className="card-subtitle mb-2 text-muted">{movie.Tag}</h6>
                                <Link to={`/moviedetail/${movie.Movie_ID}`} className="btn text-white" style={{backgroundColor: "midnightblue"}}>See More Details</Link>
                            </div>
                        </div><br/>
                    </center>
                )
            })}
            </div>
        )
    }
}
export default Search;