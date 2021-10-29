import { YourDetails } from './YourDetails'
import { Route, Switch } from "react-router-dom";
import {FathersDetails} from "./father/FathersDetails";
import {MothersDetails} from "./mother/MothersDetails";
import Overview from "./overview/Overview";

const YourDetailsRoutes = () => {
    return (
        <Switch>
            <Route path="/your-details">
                <YourDetails />
            </Route>

            <Route path="/fathers-details">
                <FathersDetails />
            </Route>

            <Route path="/mothers-details">
                <MothersDetails />
            </Route>

            <Route path="/overview">
                <Overview />
            </Route>
        </Switch>
    );
}

export { YourDetailsRoutes }