import React from "react";
import loginImg from "../../login.jpg";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import securityApi from "../../api/securityApi";
//import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import history from "../../history";


const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

export class Login extends React.Component {
  constructor(props) {
    super(props);
    this.handleLogin = this.handleLogin.bind(this);
  }
  state = {
    username: '',
    password: '',
    message: '',
  };
  OnChangeUsername(e) {
    this.setState({
      username: e.target.value
    });
  }

  OnChangePassword(e) {
    this.setState({
      password: e.target.value
    });
  }

  handleLogin(e) {  
    e.preventDefault();

    this.setState({
      message: ''
    });

    this.form.validateAll();
    console.log(this.state.username);
    if (this.checkBtn.context._errors.length === 0) {
     // console.log(this.state.username);
      securityApi.login(this.state.username,
        this.state.password).then(
          () => {
            history.push("/project");
            window.location.reload();
          },
          error => {
            const resMessage =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
  
            this.setState({
              message: resMessage
            });
          }
        );
      } 
  }
  
  render() {
    return <div className="base-container" ref = {this.props.containerRef}>
            <div className="header">Login</div>
            <div className="content">
            <div className="image"> 
                <img src={loginImg} alt=""/>
            </div>
            <Form className="form" onSubmit={this.handleLogin}
            ref={c => {
              this.form = c;
            }}>
              <div className="form-group"> 
                <label htmlFor="username">Username</label>
                <Input 
                type="text" 
                className="form-control" 
                name="username" 
                placeholder="Username"
                value={this.state.username}
                onChange={this.OnChangeUsername.bind(this)}
                validations={[required]}
                />
                <p>{this.state.username}</p>
              </div> 
              <div className="form-group"> 
                <label htmlFor="password">Password</label>
                <Input 
                type="password" 
                className="form-control" 
                name="password" 
                placeholder="Password"
                value={this.state.password}
                onChange={this.OnChangePassword.bind(this)}
                validations={[required]}

                />
              </div>

              <div className="form-group">
                <button type="submit" className="btn">Login</button>
              </div>

                {this.state.message && (
                <div className="form-group">
                  <div className="alert alert-danger" role="alert">
                    {this.state.message}
                  </div>
                </div>
                )}
              <CheckButton
                style={{ display: "none" }}
                ref={c => {
                  this.checkBtn = c;
                }}
              />
            </Form>
            </div>
           </div>
  }
}