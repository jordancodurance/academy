import React from 'react';
import ReactDOM from 'react-dom';
import { Homepage } from './homepage/Homepage';
import YourDetailsRoutes from './your-details/YourDetailsRoutes';
import './index.css'
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import {MAIN_ROUTE} from "./shared/Routes";

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <YourDetailsRoutes />
      <Switch>
        <Route exact path={MAIN_ROUTE}>
          <Homepage />
        </Route>
      </Switch>
    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);
