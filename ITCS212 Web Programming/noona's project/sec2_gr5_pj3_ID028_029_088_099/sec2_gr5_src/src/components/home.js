import React from 'react' 
import {Link} from "react-router-dom"; 
import styled from "styled-components";
import img1 from '../pictures/movie_catalog.jpg';
import img2 from '../pictures/play-icon.jpg';
import img3 from '../pictures/woman-watching-movie-home.jpg';
import background from '../pictures/2382.jpg';
const MyHome = styled.div`
    background-color: black;
`;
const HomeArticle = styled.div`
    width: 95%;
    padding-top: 150px;
    padding-bottom: 150px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
`;
const ArticleIMG = styled.img`
    padding-left: 100px;
    padding-right: 100px;
    height: 300px;
`;
const TextLeft = styled.div`
    padding-left: 100px;
    padding-right: 100px;
    font-size: 60px;
    font-weight: bold;
    text-align: right;
    color: white;
    font-family: sans-serif;
`;
const TextRight = styled.div`
    padding-left: 100px;
    padding-right: 100px;
    font-size: 60px;
    font-weight: bold;
    text-align: left;
    color: white;
    font-family: sans-serif;
`;
const HomeBgPic = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    align-items: center;
`;
const bgIMG = {
    backgroundImage: `url(${background})`,
    backgroundRepeat: 'no-repeat',
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundAttachment: 'fixed',
    opacity: '0.9'
}
const Ready = styled.div`
    margin-top: 250px;
    font-size: 90px;
    font-weight: bold;
    background-color: transparent;
    color: white;
    text-shadow: 3px 3px 1px #2d2c2e;
`;
const MyLink = styled(Link)` 
    font-size: 30px;
    padding-top: 20px;
    padding-bottom: 20px;
    padding-left: 60px;
    padding-right: 60px;
    text-decoration: none;
    background-color: MidnightBlue;
    margin-top: 50px;
    margin-bottom: 250px;
    color: white;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
`;
const handleMouseEnter = e => {
    e.target.style.background = 'DarkBlue'
    e.target.style.color = 'white'
}
const handleMouseLeave = e => {
    e.target.style.background = 'MidnightBlue'
}
class Home extends React.Component{
    render() { 
        return ( 
            <MyHome> 
                <HomeArticle>
                    <ArticleIMG src={img1} />
                    <TextLeft>Stream<br />plenty of<br />movies.</TextLeft> 
                </HomeArticle>
                <HomeArticle>
                    <TextRight>Download<br />and play<br />offline.</TextRight>
                    <ArticleIMG src={img2} />
                </HomeArticle>
                <HomeArticle>
                    <ArticleIMG src={img3} />
                    <TextLeft>Watch<br />anywhere<br />anytime.</TextLeft>
                </HomeArticle>
                <HomeBgPic style={bgIMG}>
                    <Ready>Ready to watch?</Ready>
                    <MyLink 
                        onMouseEnter={handleMouseEnter} 
                        onMouseLeave={handleMouseLeave}
                        to="/signin">Get Start</MyLink>
                </HomeBgPic>
            </MyHome> 
        ); 
    }
} 
export default Home;