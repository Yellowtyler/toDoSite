import React from "react";
import loginImg from "../../login.jpg";

export class Register extends React.Component {
  render() {
    return <div className="base-container" ref = {this.props.containerRef}>
            <div className="header">Register</div>
            <div className="content">
            <div className="image"> 
                <img src={loginImg} alt=""/>
            </div>
            <div className="form">
              <div className="form-group"> 
                <label htmlFor="username">Username</label>
                <input type="text" name="username" placeholder="username"></input>
              </div> 
              <div className="form-group"> 
                <label htmlFor="email">Email</label>
                <input type="email" name="email" placeholder="email"></input>
              </div> 
              <div className="form-group"> 
                <label htmlFor="password">Password</label>
                <input type="password" name="password" placeholder="password"></input>
              </div> 
              <div className="form-group"> 
                <label htmlFor="password">Repeat password</label>
                <input type="password" name="password" placeholder="password"></input>
              </div> 
            </div>
            </div>
            <div className="footer">
                <button type="button" className="btn">Register</button>
            </div>
           </div>
  }
}