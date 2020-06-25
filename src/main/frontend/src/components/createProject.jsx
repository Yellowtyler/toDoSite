import React,{useState, useEffect} from 'react';
import Button from '@material-ui/core/Button';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import TextField from '@material-ui/core/TextField';
import DateFnsUtils from "@date-io/date-fns";
import {DatePicker, MuiPickersUtilsProvider} from '@material-ui/pickers';

const CreateProjectForm = (props) => {
  
  return (
      <div className = "form-container">
          <List>
              <ListItem>
              <TextField></TextField>
              </ListItem>
              <ListItem>
              <TextField></TextField>
              </ListItem>
              <ListItem>
              <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <DatePicker/>
              </MuiPickersUtilsProvider>
              </ListItem>
              <ListItem>
              <Button>Create</Button>
              </ListItem>
          </List>
      </div>
  );



};
export default CreateProjectForm;