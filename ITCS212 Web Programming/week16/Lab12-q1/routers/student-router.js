const express = require('express');
const student = express.Router()
const studentJSON = require("../models/student-model").studentJSON;
student.get("/", function(req, res){
    res.render("student", {"studentJSON": studentJSON, "EACH_ROW": 3});
});

module.exports = student;