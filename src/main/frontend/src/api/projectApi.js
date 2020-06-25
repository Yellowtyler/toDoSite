import axios from 'axios';
import fetch from 'node-fetch';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/project/';

class projectApi {
    createProject(name, descr, date, state) {
        // return axios.post(API_URL+"createProject", 
        // {headers: authHeader(), 
        //     name, descr, date, state});
    }

    getProjects(id) { 
        // return axios.get(API_URL+"getProjects/" + id, 
        // {headers: authHeader()})
        // .then(res => {return res.data});
        fetch(API_URL + "getProjects/" + id, {
            method: 'GET',
            headers: authHeader()
        })
        .then(res => {return res.json();})
        .then(res =>
              console.log(res)
        )
    } 
}

export default new projectApi();