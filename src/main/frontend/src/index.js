import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import Project from './components/projectPage';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import history from './history';
import { positions, Provider } from "react-alert";
import AlertMUITemplate from "react-alert-template-mui";

const options = {
  position: positions.MIDDLE
};

ReactDOM.render(
  // <React.StrictMode>
  //   <App />
    
  // </React.StrictMode>,
  <Provider template={AlertMUITemplate} {...options}>
  <Router  history={history}>
  <Switch>
   <Route exact path="/" component={App} />
   <Route exact path="/project" component={Project} />
 </Switch>
 </Router>
 </Provider>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
