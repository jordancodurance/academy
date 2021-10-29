import React from 'react';
import ReactDOM from 'react-dom';
import { Homepage } from './homepage/Homepage';
import { YourDetailsRoutes } from './your-details/YourDetailsRoutes';
import './index.css'
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <YourDetailsRoutes />
      <Switch>
        <Route exact path="/">
          <Homepage />
        </Route>
      </Switch>
    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);
