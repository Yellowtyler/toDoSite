import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/project/';

class projectApi {
    createProject(name, descr, date, state) {
        return axios.post(API_URL+"createProject", 
        {headers: authHeader(), 
            name, descr, date, state});
    }

    getProjects(id) {
        return axios.get(API_URL+"getProjects/" + id, 
        {headers: authHeader()});
    }
  
}

export default new projectApi();