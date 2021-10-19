import { MyDetails } from './MyDetails'
import { Route, Switch } from "react-router-dom";
import {FathersDetails} from "./FathersDetails";
import {MothersDetails} from "./MothersDetails";

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
        </Switch>
    );
}

export { MyDetailsRoutes }