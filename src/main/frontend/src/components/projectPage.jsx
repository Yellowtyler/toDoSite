import React,{useState, useEffect} from 'react';
import authHeader from '../api/auth-header';
import securityApi from '../api/securityApi';
import projectApi from '../api/projectApi';
import axios from 'axios';
import Button from '@material-ui/core/Button';
import history from '../history';
import ProjectForm from './projectForm';
import styles from './projectPage.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useAlert } from "react-alert";
import trashIcon from '../trash.png';
const Project = (props) => {
  const [user, setUser] = useState(securityApi.getCurrentUser());
  const [projects, setProjects] = useState([]);
  const [projectId, setProjectId] = useState(-1);
  const [deleteActive, setDeleteActive] = useState(false);
  const alert = useAlert();

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

  const deleteProject = (event, projectId) => {
    event.preventDefault();
    alert.show("Are you sure you want to delete this project?", {
      title: "Delete project",
      actions: [ 
        {
         copy: "Delete",
         onClick: () => {
           projectApi.deleteProject(projectId);
           window.location.reload();
        }
        }
      ]});
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
      <div className={styles.containerLeft}>
        <ul>
          {projects.map(item => (
            <li>
            <Button onClick={openProject(item.id)} >{item.name}</Button>
            {deleteActive && <Button onClick={e => deleteProject(e, item.id)}>x</Button>}
            </li>
          ))}
         
            <Button onClick={openProject(-1)}>Add new project</Button>
          
        </ul> 
        <div>
          <Button>Show all projects!</Button>
          <Button onClick={()=>setDeleteActive(!deleteActive)} className={styles.deleteBtn}>
            <img src={trashIcon} alt="" style={styles.img}/>
          </Button>
        </div>
      </div>
      <div className={styles.containerRight}>
          <ProjectForm projectId={projectId} projectsCount={projects.length} />
        </div>
    </div>
  );
}
export default Project;
