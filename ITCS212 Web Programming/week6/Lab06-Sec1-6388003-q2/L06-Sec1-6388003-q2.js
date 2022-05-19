const path = require("path");
const express = require("express");
const app = express();

app.get("/", function(req, res) {
    res.send("Hello World! in plain text");
});

app.get("/th", function(req, res) {
    res.sendFile(path.join(__dirname, "greeting_th.html")); 
});

app.get("/cn", function(req, res) {
    res.sendFile(path.join(__dirname, "greeting_cn.html")); 
});

app.use(function(req, res, next) {
    res.status(404).send("WHERE ARE YOU GOING?");
});

app.listen(3030);
console.log("Running at Port 3030");
