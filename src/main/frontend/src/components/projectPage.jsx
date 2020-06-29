import React,{useState, useEffect} from 'react';
import authHeader from '../api/auth-header';
import securityApi from '../api/securityApi';
import axios from 'axios';
import Button from '@material-ui/core/Button';
import history from '../history';
import ProjectForm from './projectForm';
import { Link } from "react-router-dom"; 
import './projectPage.scss';
import 'bootstrap/dist/css/bootstrap.min.css';
const Project = (props) => {
  const [user, setUser] = useState(securityApi.getCurrentUser());
  const [projects, setProjects] = useState([]);
  const [projectId, setProjectId] = useState(-1);

  useEffect(() => {
    if(user===null) {
      console.log(user);
      history.push("/");
      window.location.reload();
    } 
    else {
      if(user.roles[0].includes('ROLE_ADMIN') || user.roles[0].includes('ROLE_USER')) {
        axios
        .get('http://localhost:8080/api/project/getProjects/' + user.id, {headers: authHeader()})
        .then(res => {
            console.log(res);
            setProjects(res.data);
        })
      } 
      else {
        window.open('/error');
      }
    }
  }, []);

  const openProject = (projectId) => () => {
    setProjectId(projectId);
  };

  const logout = (e) => {
    e.preventDefault();
    securityApi.logout();
    history.push("/");
    window.location.reload();
  };
  
  return (
    <div>
      <h1>Projects</h1>
      <ul class="nav navbar-nav navbar-right">
        <li><Button  className="mr-sm-2" onClick={logout}>Logout</Button></li>
      </ul>
      <div className="container-left">
        <ul>
          {projects.map(item => (
            <li>
            <Button onClick={openProject(item.id)} >{item.name}</Button>
            </li>
          ))}
          <li>
            <Button onClick={openProject(-1)}>Add new project!</Button>
          </li>
        </ul>
       
      </div>
      <div className="container-right">
          <ProjectForm projectId = {projectId}/>
        </div>
    </div>
  );
}
export default Project;
