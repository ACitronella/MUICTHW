import React from 'react';
import {useParams} from "react-router-dom"
import styled from "styled-components";

const MyMovieDetail = styled.div`
    background-color: black;
    color: aliceblue;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 80px;
`;

const MovImg = styled.img`
    border-radius: 10px;
    width: 350px;
`;

const Info = styled.p`
    font-size: 25px;
    padding-bottom: 10px;
    color:LightSlateGray
`;

const Desc = styled.p`
    font-size: 25px;
    font-family: Georgia, serif;
    padding-top: 10px;
`;

function MovieDetail(){
    const [movie, setMovie] = React.useState([]);
    let {id} = useParams();
    const url = "http://localhost:3030/movie/select/id/" + id;

    React.useEffect(function effectFunction() {
        fetch(url)
        .then(response => response.json())
        .then(response => { 
            setMovie(response.data[0]); 
        });
    }, []);
    
    return(
        <MyMovieDetail key={movie.Movie_ID}>
            <div>
                <MovImg src={movie.Movie_img_src}/>
            </div>
            <div style={{paddingLeft: "100px"}}>
                <p style={{fontSize: "60px"}}>{movie.Movie_Name}</p>
                <Info>Released date {movie.Release_Date} ✦ Rate {movie.Rate} ✦ {movie.Run_Time} hours</Info>
                <Desc>{movie.Movie_Description}</Desc>
                <Desc>Director: {movie.Director}</Desc>
                <Desc>Genre: {movie.Tag}</Desc>
                <button type="button" className="btn btn-primary btn-lg px-5">Play</button>
                <button type="button" className="btn btn-primary btn-lg px-5 m-5">Download</button>
            </div>
        </MyMovieDetail>
    );
}
export default MovieDetail;