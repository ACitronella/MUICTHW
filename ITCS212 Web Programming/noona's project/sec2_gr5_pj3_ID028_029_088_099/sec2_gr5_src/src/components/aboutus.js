import React from 'react';
import styled from "styled-components";
import phop from '../pictures/028pongpop.jpg';
import neo from '../pictures/029kittapon.jpg';
import noona from '../pictures/088Paveena.jpg';
import can from '../pictures/099kanin.jpg';
const MyAboutUs = styled.div`
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    padding: 40px 0px 40px 0px;
    align-items: center;
    color: white;
    background-color: black;
`;
const OurTeam = styled.p`
    font-weight: bold;
    font-size: 60px;
    text-align: center;
    padding: 30px;
`;
const Twomembers = styled.div`
    display:flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: space-evenly;
`;
const Member = styled.div`
    margin: 80px 80px 80px 80px;
    justify-content: center;
    display: flex;
    flex-direction: column;
    align-items: left;
`;
const MemIMG = styled.img`
    height: 400px;
    width: 400px;
`;
const Figcaption = styled.div`
    padding: 30px 20px 20px 0px;
`;
class AboutUs extends React.Component{ 
    render() { 
        return ( 
            <MyAboutUs>
                <OurTeam>Our Team</OurTeam>
                <Twomembers>
                    <Member id="pongpop">
                        <MemIMG src={phop}/>
                        <Figcaption>
                            <h1>Pongpop Lapvikai<br/>
                                (Phop)</h1><br/>
                            <h5>Student ID: 6388028 Section: 2<br/>
                            E-mail: pongpop.lap@student.mahidol.ac.th</h5>
                        </Figcaption>
                    </Member>
                    
                    <Member id="kittapon">
                        <MemIMG src={neo}/>
                        <Figcaption>
                            <h1>Kittapon Lerdpayub<br/>
                                (Neo)</h1><br/>
                            <h5>Student ID: 6388029 Section: 2<br/>
                            E-mail: kittapon.ler@student.mahidol.ac.th</h5>
                        </Figcaption>
                    </Member>
                </Twomembers>

                <Twomembers>
                    <Member id="paveena">
                        <MemIMG src={noona}/>
                        <Figcaption>
                            <h1>Paveena Kumnerdpun<br/>
                                (Noona)</h1><br/>
                            <h5>Student ID: 6388088 Section: 2<br/>
                            E-mail: paveena.kum@student.mahidol.ac.th</h5>
                        </Figcaption>
                    </Member>

                    <Member id="kanin">
                        <MemIMG src={can}/>
                        <Figcaption>
                            <h1>Kanin Prakaikowit<br/>
                                (Can)</h1><br/>
                            <h5>Student ID: 6388099 Section: 2<br/>
                            E-mail: kanin.pra@student.mahidol.ac.th</h5>
                        </Figcaption>
                    </Member>
                </Twomembers>
            </MyAboutUs> 
        ); 
    }
} 
export default AboutUs;