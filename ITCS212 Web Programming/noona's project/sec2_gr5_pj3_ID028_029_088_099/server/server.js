//const path = require('path');
const express = require('express');
const app = express();
const cors = require('cors');
const bcrypt = require('bcrypt'); //hash password
const saltRounds = 10; //number to generate hash password
app.use(express.json());
app.use(express.urlencoded({extended: true}));
const jwt = require('jsonwebtoken');
const dotenv = require('dotenv');
dotenv.config();
app.use(cors());

const mysql = require('mysql2');
var dbConn = mysql.createConnection({
    host : process.env.Host,
    user : process.env.DB_user,
    password : process.env.DB_pass,
    database : process.env.DB_name,
})
const PORT = process.env.Port;

app.get("/", (req, res) => {
    res.json({ message: "Hello from server!" });
});

// --------------------------------- authentication service -----------------------------------------

// handle authentication
// testing signin as an admin
// method: post
// URL: http://localhost:3030/signin/auth
// body: raw JSON
/*
{
    "email": "paveena@student.mahidol.ac.th",
    "password": "mko098"
}
*/
// testing signin as a normal user
// method: post
// URL: http://localhost:3030/signin/auth
// body: raw JSON
/*
{
    "email": "sh.pro@gmail.com",
    "password": "8uh2ws"
}
*/
app.post('/signin/auth', function(req, res){
    dbConn.execute(
        "SELECT * FROM user WHERE Email = ?", [req.body.email],
        function(err, user, field) {
            if (err) throw error;
            if (user.length == 0) { 
                return res.status(400).json({error: true, message: 'Sign In Failed.'});
            }
            // compare password with decoded password in database
            bcrypt.compare(req.body.password, user[0].User_Password, function(err, isSignIn){
                if (err) return res.status(400).send({ error: true, message: 'error'});
                if(isSignIn){ // if authentic
                    // generate token for checking authorization
                    let token = jwt.sign(
                        {email : user[0].Email, role : user[0].User_Role},
                        process.env.Secret,
                        {expiresIn: '300'}
                    );
                    res.status(200).json({ error: false, message: 'Sign in success.', accessToken: token, role: user[0].User_Role});
                }
                else{
                    return res.status(400).json({ error: true, message: 'Sign In Failed. Email or Password may be incorrect'});
                }
            });
        }
    );
});

//----------------------------------search service for normal user ----------------------------------------

// test on postman
//search with no criteria
//show all results, and result can link to movie details
//URL: http://localhost:3030/search/movies/all/
app.get('/search/movies/all/',function (req, res){ //No criteria (return all results)
    dbConn.query('SELECT * FROM movie', function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: 'Movie retrieved.'});
    });
});

// test on postman
// testing search movie with criteria: movie name
// method: get
// URL: http://localhost:3030/search/movies/name/avengers
// can change 'avengers' to be, for example, ronin, impact, etc.
app.get('/search/movies/name/:name', function(req, res){
    let movie_name = req.params.name;
    if(!movie_name){
        return res.status(400).send({ error: true, message: 'Please provide movie name'});
    }
    movie_name = '%' + movie_name + '%'; // to be able to serch only some part of movie name
    dbConn.query('SELECT * FROM movie WHERE Movie_Name LIKE ?', movie_name, function (error, results) {
            if(error) throw error;
            return res.send({error: false, data: results, message: 'Movie retrieved.'});
    });
});

// test on postman
// testing search movie with criteria: movie tag
// method: get
// URL: http://localhost:3030/search/movies/tag/horror
// can change 'horror' to be, for example, action, comedy, horror, sci-fi.
app.get('/search/movies/tag/:tag', function(req, res){
    let movie_tag = req.params.tag;
    if(!movie_tag){
        return res.status(400).send({ error: true, message: 'Please provide movie tag'});
    }
    movie_tag = '%' + movie_tag + '%';
    dbConn.query('SELECT * FROM movie WHERE Tag LIKE ?', movie_tag, function (error, results) {
            if(error) throw error;
            return res.send({error: false, data: results, message: 'Movie retrieved.'});
    });
});

// test on postman
// testing search movie with criteria : movie released yeer
// method: get
// URL: http://localhost:3030/search/movies/year/2019
// change ':year' to be, for example, 2012, 2019, 2018
app.get('/search/movies/year/:year', function(req, res){
    let movie_year = req.params.year;
    if(!movie_year){
        return res.status(400).send({ error: true, message: 'Please provide released year of movie'});
    }
    dbConn.query('SELECT * FROM movie WHERE YEAR(Release_Date) = ?', movie_year, function (error, results) {
            if(error) throw error;
            return res.send({error: false, data: results, message: 'Movie retrieved.'});
    });
});

// ----------------------------- user manager service for admin ---------------------------------------
// test in postman
// testing insert a new user
// method: post
// URL: http://localhost:3030/user/add
// body: raw JSON
/*
{
    "user": {
            "User_ID": 111111,
            "First_Name": "BlahBlah",
            "Last_Name": "Yeager",
            "Email": "eren.yea@gmail.com",
            "User_Password": "7uhnte",
            "User_Role": "normal user"
    }
}
*/
app.post('/user/add', function (req, res){
    let user = req.body.user;
    if(!user){
        return res.status(400).send({ error: true, message: 'Please provide user information'});
    }
    // encoded password and store information into database
    bcrypt.hash(user.User_Password, saltRounds, function(err, hash){
        dbConn.execute("INSERT INTO user (User_ID, First_Name, Last_Name, Email, User_Password, User_Role) VALUES (?, ?, ?, ?, ?, ?)",
        [user.User_ID, user.First_Name, user.Last_Name, user.Email, hash, user.User_Role],
        function (error, results) {
            if(error){ res.status(400).send({ error: true, message: 'error'});}
            return res.send({error: false, message: 'New user has been created successfully.'});
        });
    });
});


// testing update a new information
// update firstname
// method: put
// URL: http://localhost:3030/user/update
// body: raw JSON
/*
{
    "user": {
            "User_ID": 111111,
            "First_Name": "Eran",
            "Last_Name": "Yeager",
            "Email": "eren.yea@gmail.com",
            "User_Password": "7uhnte",
            "User_Role": "normal"
    }
}
*/
app.put('/user/update', function (req, res){
    let user_id = req.body.user.User_ID;
    let user = req.body.user;

    if(!user_id || !user){
        return res.status(400).send({ error: user, message: 'Please provide user information'});
    }
    bcrypt.hash(user.User_Password, saltRounds, function(err, hash){
        user.User_Password = hash;
        dbConn.query('UPDATE user SET ? WHERE User_ID = ?', [user, user_id], function (error, results) {
            if(error) throw error;
            return res.send({error: false, data: results.affectedRows, message: 'This user has been updated successfully.'});
        });
    });
});


// testing delete user
// method: delete
// URL: http://localhost:3030/user/delete
// body: raw JSON
/*
{
    "User_ID": 111111
}
*/
app.delete('/user/delete', function(req, res){
    let user_id = req.body.User_ID;

    if(!user_id){
        return res.status(400).send({ error: true, message: 'Please provide User_ID'});
    }
    dbConn.query('DELETE FROM user WHERE User_ID = ?', [user_id], function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results.affectedRows, message: 'This user has been deleted successfully.'});
    });
});


// testing select user by ID
// method: get
// URL: http://localhost:3030/user/select/id/100001
app.get('/user/select/id/:id', function(req, res){
    let user_id = req.params.id;
    if(!user_id){
        return res.status(400).send({ error: true, message: 'Please provide User_ID'});
    }
    dbConn.query('SELECT * FROM user WHERE User_ID = ?', user_id, function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: 'User retrieved.'});
    });
});

// testing select user by firstname
// method: get
// URL: http://localhost:3030/user/select/firstname/kanin
app.get('/user/select/firstname/:firstname', function(req, res){
    let user_firstname = req.params.firstname;
    if(!user_firstname){
        return res.status(400).send({ error: true, message: 'Please provide First_Name'});
    }
    user_firstname = '%' + user_firstname + '%';
    dbConn.query('SELECT * FROM user WHERE First_Name LIKE ?', user_firstname, function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: 'User retrieved.'});
    });
});

// testing select user by role
// method: get
// URL: http://localhost:3030/user/select/role/admin
// or
// URL: http://localhost:3030/user/select/role/normal user
app.get('/user/select/role/:role', function(req, res){
    let user_role = req.params.role;
    if(!user_role){
        return res.status(400).send({ error: true, message: 'Please provide User_Role'});
    }
    dbConn.query('SELECT * FROM user WHERE User_Role = ?', user_role, function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: 'User retrieved.'});
    });
});

// testing select all user
// method: get
// URL: http://localhost:3030/user/selectall
app.get('/user/selectall', function (req, res){
    dbConn.query('SELECT * FROM user', function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: 'User list.'});
    });
});

// ----------------------------- movie manager service for admin ---------------------------------------

// test in postman
// testing insert new movie
// method: post
// URL: http://localhost:3030/movie/add
// body: raw JSON
/*
{
    "movie": {
        "Movie_ID": 138989,
        "Movie_Name": "Turning Red Panda",
        "Movie_img_src": "https://m.media-amazon.com/images/M/MV5BNjY0MGEzZmQtZWMxNi00MWVhLWI4NWEtYjQ0MDkyYTJhMDU0XkEyXkFqcGdeQXVyODc0OTEyNDU@._V1_FMjpg_UX1000_.jpg",
        "Release_Date": "2012-03-05",
        "Run_Time": "01:40:00",
        "Director": "Domee Shi",
        "Rate": "PG",
        "Tag": "animation",
        "Movie_Description": "Mei Lee is a confident, dorky 13-year-old torn between staying her mother's dutiful daughter and the chaos of adolescence. And as if changes to her interests, relationships and body weren't enough, whenever she gets too excited, she poofs into a giant red panda"
    }
}
*/
app.post('/movie/add', function (req, res){
    let movie = req.body.movie;

    if(!movie){
        return res.status(400).send({ error: true, message: 'Please provide movie information'});
    }
    dbConn.query("INSERT INTO movie SET ?", movie, function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results.affectedRows, message: 'New movie has been created successfully.'});
    });
})


// testing udpate movie information
// update Movie_Name and Release_Date
// method: put
// URL: http://localhost:3030/movie/update
// body: raw JSON
/*
{
    "movie": {
        "Movie_ID": 138989,
        "Movie_Name": "Turning Red",
        "Movie_img_src": "https://m.media-amazon.com/images/M/MV5BNjY0MGEzZmQtZWMxNi00MWVhLWI4NWEtYjQ0MDkyYTJhMDU0XkEyXkFqcGdeQXVyODc0OTEyNDU@._V1_FMjpg_UX1000_.jpg",
        "Release_Date": "2022-03-10",
        "Run_Time": "01:40:00",
        "Director": "Domee Shi",
        "Rate": "PG",
        "Tag": "animation",
        "Movie_Description": "Mei Lee is a confident, dorky 13-year-old torn between staying her mother's dutiful daughter and the chaos of adolescence. And as if changes to her interests, relationships and body weren't enough, whenever she gets too excited, she poofs into a giant red panda."
    }
}
*/
app.put('/movie/update', function (req, res){
    let movie_id = req.body.movie.Movie_ID;
    let movie = req.body.movie;

    if(!movie_id || !movie){
        return res.status(400).send({ error: movie, message: 'Please provide movie information'});
    }
    dbConn.query('UPDATE movie SET ? WHERE Movie_ID = ?', [movie, movie_id], function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results.affectedRows, message: 'This movie has been updated successfully.'});
    });
});


// testing delete movie
// method: delete
// URL: http://localhost:3030/movie/delete
// body: raw JSON
/*
{
    "Movie_ID": 138989
}
*/
app.delete('/movie/delete', function(req, res){
    let movie_id = req.body.Movie_ID;

    if(!movie_id){
        return res.status(400).send({ error: true, message: 'Please provide Movie_ID'});
    }
    dbConn.query('DELETE FROM movie WHERE Movie_ID = ?', [movie_id], function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results.affectedRows, message: 'This movie has been deleted successfully.'});
    });
});


// testing select movie by ID
// method: get
// URL: http://localhost:3030/movie/select/id/130001
app.get('/movie/select/id/:id',function(req, res){
    let movie_id = req.params.id;

    if(!movie_id){
        return res.status(400).send({ error: true, message: 'Please provide Movie_ID'});
    }
    dbConn.query('SELECT * FROM movie WHERE Movie_ID = ?', movie_id, function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: 'Movie retrieved.'});
    });
});

// testing select movie by name
// method: get
// URL: http://localhost:3030/movie/select/name/avengers
app.get('/movie/select/name/:name',function(req, res){
    let movie_name = req.params.name;
    if(!movie_name){
        return res.status(400).send({ error: true, message: 'Please provide movie name'});
    }
    movie_name = '%' + movie_name + '%'; // to be able to serch only some part of movie name
    dbConn.query('SELECT * FROM movie WHERE Movie_Name LIKE ?', movie_name, function (error, results) {
            if(error) throw error;
            return res.send({error: false, data: results, message: 'Movie retrieved.'});
    });
});

// testing select movie by genre
// method: get
// URL: http://localhost:3030/movie/select/tag/action
app.get('/movie/select/tag/:tag',function(req, res){
    let movie_tag = req.params.tag;
    if(!movie_tag){
        return res.status(400).send({ error: true, message: 'Please provide movie tag'});
    }
    movie_tag = '%' + movie_tag + '%';
    dbConn.query('SELECT * FROM movie WHERE Tag LIKE ?', movie_tag, function (error, results) {
            if(error) throw error;
            return res.send({error: false, data: results, message: 'Movie retrieved.'});
    });
});

// testing select all movies
// method: get
// URL: http://localhost:3030/movie/selectall
app.get('/movie/selectall', function (req, res){
    dbConn.query('SELECT * FROM movie', function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: 'Movie list.'});
    });
});

app.listen(PORT);
console.log('Running at Port ' + PORT);