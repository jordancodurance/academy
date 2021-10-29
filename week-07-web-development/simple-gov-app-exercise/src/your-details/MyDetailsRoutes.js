import { MyDetails } from './MyDetails'
import { Route, Switch } from "react-router-dom";
import {FathersDetails} from "./FathersDetails";
import {MothersDetails} from "./MothersDetails";
import Overview from "./Overview";

const MyDetailsRoutes = () => {
    return (
        <Switch>
            <Route path="/my-details">
                <MyDetails />
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

export { MyDetailsRoutes }