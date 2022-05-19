const express = require("express");
const mysql = require("mysql");
require("dotenv").config();
const port = process.env.port;
const app = express();

const sql_init_query = "CREATE TABLE IF NOT EXISTS personal_info(StudentID INT(11) PRIMARY KEY, Firstname VARCHAR(45), Lastname VARCHAR(45), DOB DATETIME, Mobilephone VARCHAR(10));";
const sql_init_insert_query = `
INSERT INTO personal_info VALUES
(1, "Robert", "Dolls", "1985/01/20", "0919998877"),
(2, "Peter", "Jones", "1980/06/10", "0834455667"),
(3, "Lily", "James", "1991/10/20", "0889988776");
`;

// I CANT USE THE F**KING MYSQL, SO I USE THE HEROKU'S FREE DATABASE FOR TESTING. HOPE YOU DON'T MIND
const student_db = mysql.createConnection(process.env.database_uri);
student_db.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
    student_db.query(sql_init_query, function (err, result) {
        if (err) throw err;
        student_db.query(sql_init_insert_query, function(err, result){
            // surpress error
        });
    });
});


app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.get("/readone/:id", function(req, res){
    let student_id = req.params.id;
    student_db.query("SELECT * FROM personal_info WHERE StudentID = ?;", student_id, function (error, results) {
        if(error) throw error; 
        return res.send({error: false, data: results, message: "RETURNING EVERYTHING FROM PERSONAL_INFO DB"});
    });
});

app.get("/readall", function(req, res){
    student_db.query("SELECT * FROM personal_info;", function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results, message: "RETURNING EVERYTHING FROM PERSONAL_INFO DB"});
    });
});

app.post("/insert", function(req, res){
    let student = req.body.student;
    console.log(student, typeof studenp);
    if(!student) return res.status(400).send({error: true, message: "PLEASE PROVIDE STUDENT TOBE INSERT"});
    student_db.query("INSERT INTO personal_info SET ?;", student, function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results.affectedRows, message: "INSERT COMPLETE"});
    });
});

app.put("/edit", function(req, res){
    let student = req.body.student;
    let student_id = req.body.student.StudentID;
    if(!student_id || !student) return res.status(400).send({error: true, message: "PLEASE PROVIDE STUDENT WITH STUDENTID"});
    student_db.query("UPDATE personal_info SET ? WHERE StudentID = ?;", [student, student_id], function (error, results) {
        if(error) throw error;
        return res.send({error: false, data: results.affectedRows, message: "EDIT SUCCESS"});
    });
});

app.delete("/remove", function (req, res) {
    let student_id = req.body.StudentID;
    if(!student_id) return res.status(400).send({error: true, message: "PLEASE PROVIDE STUDENTID"});
    student_db.query("DELETE FROM personal_info WHERE StudentID = ?;", student_id, function(error, results){
        if(error) throw error;
        return res.send({error: false, data: results, message: "DELETE COMPLETE"});
    });
});



app.listen(port, function (){
    console.log("Listening at port: " + port);
});


