export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
    
    if (user && user.token) {
        console.log(user);
        return {
        //'Accept': 'application/json',
        //'Content-Type': 'application/json',
        Authorization: 'Bearer ' + user.token
      };
    } else {
      return {};
    }
}