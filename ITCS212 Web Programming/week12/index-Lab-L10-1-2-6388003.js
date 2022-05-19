import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// import App from './App';
import reportWebVitals from './reportWebVitals';

class InfoForm extends React.Component {
    
    constructor(props) {
        console.log(props);
        super(props);
        this.form_type = props.type;
        this.state = {
            firstname: "",
            lastname: "",
            age: 0
        };
        if(this.form_type === "special"){
            this.state.nationality = "thai";
            this.state.disability = "no";
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleChange(e) {
        const target = e.target;
        const value = target.value;
        const elementname = target.name;

        this.setState({
            [elementname]: value
        });
    }
    handleSubmit(e) {
        e.preventDefault();
        let alert_message = `hello, ${this.state.firstname} ${this.state.lastname}`;
        if(this.form_type === "special"){
            alert_message = alert_message + `\nYour nationality is ${this.state.nationality}`;
        }
        else{
            alert_message = alert_message + `\nYour age is ${this.state.age}`;
        } 
        console.log(this.state);
        alert(alert_message);
    }
    render() {
        if(this.form_type === "special"){
            return (
                <form onSubmit={this.handleSubmit}>
                    <label>First Name:</label>
                    <input
                    type="text"
                    name="firstname"
                    value={this.state.firstname}
                    onChange={this.handleChange}/>
                    <br/>

                    <label>Last Name:</label>
                    <input
                    type="text"
                    name="lastname"
                    value={this.state.lastname}
                    onChange={this.handleChange}/>
                    <br/>
                    
                    <label>Age:</label>
                    <input
                    type="text"
                    name="age"
                    value={this.state.age}
                    onChange={this.handleChange}/> 
                    <br/>

                    <label>Pick your nationality:</label>
                    <select name="nationality" value={this.state.nationality} onChange={this.handleChange}>
                        <option value="thai">Thai</option>
                        <option value="japanese">Japanese</option>
                        <option value="british">British</option>
                    </select>
                    <br/>

                    <label>Disability:</label>
                    <select name="disability" value={this.state.disability} onChange={this.handleChange}>
                        <option value="no">no</option>
                        <option value="yes">yes</option>
                    </select>
                    <br/>

                    <input
                    type="submit"
                    value="submit"/>
                </form>
            );
        }
        return (
            <form onSubmit={this.handleSubmit}>
                <label>First Name:</label>
                <input
                type="text"
                name="firstname"
                value={this.state.firstname}
                onChange={this.handleChange}/>
                <br/>

                <label>Last Name:</label>
                <input
                type="text"
                name="lastname"
                value={this.state.lastname}
                onChange={this.handleChange}/>
                <br/>
                
                <label>Age: </label>
                <input
                type="text"
                name="age"
                value={this.state.age}
                onChange={this.handleChange}/> 
                <br/>

                <input
                type="submit"
                value="submit"/>
            </form>
        );
    }
}



ReactDOM.render(<InfoForm type="special" />, document.getElementById("root"));

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();