import fetch from 'node-fetch';
import authHeader from './auth-header';
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/project';

class projectApi {
   

    // getProjects(id) { 
    //     return axios.get(API_URL+"getProjects/" + id, 
    //     {headers: authHeader()})
    //     .then(res => {return res.data});
    // } 

    deleteProject(id) {
        return axios.delete(API_URL + "/deleteProject/" + id,
        {headers: authHeader()})
        .then(res=>
            console.log(res.data));
    }

    updateState(id) {
        return axios.put(API_URL + "/updateState/" + id,
        {headers: authHeader()})
        .then(res=>
            console.log(res));
    }
}

export default new projectApi();