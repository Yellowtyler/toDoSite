import React,{useState, useEffect} from 'react';
import authHeader from '../api/auth-header';
import securityApi from '../api/securityApi';
import axios from 'axios';
import Button from '@material-ui/core/Button';
import history from '../history';
import CreateProjectForm from './createProject';

const ProjectForm = (props) => {
    const {projectId} = props;

    return (
      <div>
        {projectId === -1? 
         (<CreateProjectForm/>) :
         (<h1>PROJECTFORM</h1>)
        }
      </div>  
    );
};
export default ProjectForm;