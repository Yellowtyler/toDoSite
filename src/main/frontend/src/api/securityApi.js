import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

class securityApi {
   login(username, password) {
       return axios.post(API_URL + "signin", 
       {username, password})
       .then(response => {
         if (response.data.token) {
            localStorage.setItem('user', JSON.stringify(response.data));
       }
      return response.data;
      });
   }

   register(username, email, password) { 
       return axios.post(API_URL + "signup", {username,
       email, password})
   }

   logout() { 
     localStorage.removeItem("user");
   }

   getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new securityApi();