const express = require("express");
const path = require("path");
const app = express();

const router = express.Router();
app.use("/", router);

router.use(express.json());
router.use(express.urlencoded({extended: true}));

router.get("/", function(req, res){
    console.log("Accessed Contact Us");
    res.sendFile(path.join(__dirname, "contact_us.html"));
});

router.post("/submit-form", function(req, res){
    const name = req.body.name;    
    const email = req.body.email;
    const message = req.body.messages;
    console.log("Form submitted by " + name);
     
    res.send("Greeting <span style='background-color:lightblue;'>" + name + "</span> The following message has been received: <span style='background-color:orange;'>" + message + "</span>. We will contact you via <span style='background-color:lightgreen;'>" + email + "</span> later.");
});

app.listen(3030);
