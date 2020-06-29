import React,{useState, useEffect} from 'react';
import Button from '@material-ui/core/Button';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import TextField from '@material-ui/core/TextField';
import DateFnsUtils from "@date-io/date-fns";
import authHeader from '../api/auth-header';
import {DatePicker, MuiPickersUtilsProvider} from '@material-ui/pickers';
import axios from 'axios';
import securityApi from '../api/securityApi';
const CreateProjectForm = (props) => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState(null);
  const [active, setActive] = useState(true);
  const [user, setUser] = useState(securityApi.getCurrentUser());

  const createProject = (e) => {
    e.preventDefault();
    const project = {name: name, descr: description, date: date, active: active, user: user.id};
    axios.post('http://localhost:8080/api/project/createProject', 
    {headers: authHeader(), project})
    .then(res => res.data);
  };

  return (
      <div className = "form-container">
        <form onSubmit={createProject}>
              <TextField 
              id="standard-basic" 
              label="Name" 
              value={name} 
              onChange={e=>setName(e.target.value)}
              />
              
              <TextField 
              id="standard-basic" 
              label="Description" 
              value={description}
              onChange={e=>setDescription(e.target.value)}
              />
             
              <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <DatePicker/>
              </MuiPickersUtilsProvider>
            
              <Button type="submit">Create</Button> 
        </form>
      </div>
  );



};
export default CreateProjectForm;