import React from "react";
import loginImg from "../../login.jpg";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import securityApi from "../../api/securityApi";
import { isEmail } from "validator";
import history from "../../history";
//import "bootstrap/dist/css/bootstrap.min.css";
//TODO добавить bootstrap

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const checkEmail = value => {
   if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
   }
}

const checkUser = value => {
   if(value.length < 3 || value.length > 30) {
    return (
      <div className="alert alert-danger" role="alert">
        Username must be between 3 and 30 characters.
      </div>
    );
   }
}

const checkPassword = value => {
  if(value.length < 6 || value.length > 30) {
    return (
      <div className="alert alert-danger" role="alert">
        Password must be between 6 and 30 characters.
      </div>
    );
  }
  var upper = /[A-Z]/;
  var lower = /[a-z]/;
  var nums = /[0-9]/;
  if(!value.match(upper)||!value.match(lower)||!value.match(nums)) {
    return (
      <div className="alert alert-danger" role="alert">
        Password must have numbers, uppercase letters and lowercase letters.
      </div>
    );
  }
}

export class Register extends React.Component {
  constructor(props) {
    super(props);
    this.handleRegister=this.handleRegister.bind(this);
  }
  state = {
    username : '',
    email: '',
    password: '',
    repeat: '',
    message: ''
  };


  handleRegister(e) {
     e.preventDefault();
     if(this.isPasswordMatched()) {
      this.form.validateAll();
      if (this.checkBtn.context._errors.length === 0) {
        securityApi.register(this.state.username,
          this.state.email,
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
  }

  isPasswordMatched() {
    if(this.state.password !== this.state.repeat) {
      this.setState({
        message: "Password and repeat don't match!"
      });
      return false;
    } else {
      this.setState({
        message: ""
      });
    }
    return true;
  }

  handleChangePassword(e) {
    const { name, value } = e.target
    this.setState({
        [name] : value 
      }, () => {
        if (name === 'password' || name === 'repeat') {
          this.isPasswordMatched();
        }
      }
    );
  }

  render() {
    return <div className="base-container" ref = {this.props.containerRef}>
            <div className="header">Register</div>
            <div className="content">
            <div className="image"> 
                <img src={loginImg} alt=""/>
            </div>
            <Form className="form" onSubmit={this.handleRegister} 
            ref={c => {this.form = c;}}
            >
              <div className="form-group"> 
                <label htmlFor="username">Username</label>
                <Input type="text" 
                name="username" 
                placeholder="Username"
                value={this.state.username}
                onChange={e=>this.setState({username: e.target.value})}
                validations={[required, checkUser]}
                />
              </div> 
              <div className="form-group"> 
                <label htmlFor="email">Email</label>
                <Input 
                type="email" 
                name="email" 
                placeholder="Email"
                value={this.state.email}
                onChange={e=>this.setState({email: e.target.value})}
                validations={[required, checkEmail]}
                />
              </div> 
              <div className="form-group"> 
                <label htmlFor="password">Password</label>
                <Input 
                type="password" 
                name="password" 
                placeholder="Password"
                value={this.state.password}
                onChange={e=>this.handleChangePassword(e)}
                validations={[required, checkPassword]}
                />
              </div> 
              <div className="form-group"> 
                <label htmlFor="password">Repeat password</label>
                <Input 
                type="password" 
                name="repeat" 
                placeholder="Repeat password"
                value={this.state.repeat}
                onChange={e=>this.handleChangePassword(e)}
                validations={[required]}
                />
              </div>
              <div className="form-group">
                <button type="submit" className="btn">Register</button>
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