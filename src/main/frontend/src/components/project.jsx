import React from "react";
import securityApi from "../api/securityApi";
export default class Project extends React.Component {
    constructor(props) {
        super(props);
    
        this.state = {
          currentUser: securityApi.getCurrentUser()
        };
      }
    
    render() {
       return <h1>Project</h1>
    }
}
