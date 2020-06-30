import React,{useState, useEffect} from 'react';
import Button from '@material-ui/core/Button';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import TextField from '@material-ui/core/TextField';
import DateFnsUtils from "@date-io/date-fns";
import authHeader from '../api/auth-header';
import {DatePicker, DateTimePicker, MuiPickersUtilsProvider} from '@material-ui/pickers';
import axios from 'axios';
import securityApi from '../api/securityApi';
import { useAlert } from "react-alert";
const CreateProjectForm = (props) => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState(new Date());
  const [user, setUser] = useState(securityApi.getCurrentUser());
  const today = new Date();
  const {projectsCount} = props;
  const alert = useAlert();

  const createProject = (e) => {
    e.preventDefault();
    console.log(projectsCount);
    if(projectsCount >= 7) {
        alert.show("Error! You can have maximum 7 active projects! Complete one of them and then create a new one.");
    }
    else {
    const project = {name: name, descr: description, date: date.toLocaleString(), username: user.username};
    axios.post('http://localhost:8080/api/project/createProject', project,
    {headers: authHeader()})
    .then(res => res.data);
    window.location.reload();
    }
  };

  return (
      <div className = "form-container">
        <form onSubmit={createProject}>
            <div>
              <TextField 
              id="standard-basic" 
              label="Name" 
              value={name} 
              onChange={e=>setName(e.target.value)}
              />
            </div>
            <div>
              <TextField 
              id="standard-basic" 
              label="Description" 
              value={description}
              onChange={e=>setDescription(e.target.value)}
              />
            </div>
            <div>  
              <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <DateTimePicker value={date} onChange={setDate} minDate={today} ampm={false}/>
              </MuiPickersUtilsProvider>
            </div>  
            <div>
              <Button type="submit">Create</Button> 
            </div>
        </form>
      
      </div>
  );



};
export default CreateProjectForm;