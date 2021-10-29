import { YourDetails } from './YourDetails'
import { Route, Switch } from "react-router-dom";
import {FathersDetails} from "./father/FathersDetails";
import {MothersDetails} from "./mother/MothersDetails";
import Overview from "./overview/Overview";

export const YOUR_DETAILS_ROUTE = "/your-details";
export const YOUR_FATHER_ROUTE = "/your-details/father";
export const YOUR_MOTHER_ROUTE = "/your-details/mother";
export const YOUR_DETAILS_OVERVIEW_ROUTE = "/your-details/overview";
export const YOUR_DETAILS_SUCCESSFUL_SUBMISSION = "/your-details/successful-submission";

const YourDetailsRoutes = () => {
    return (
        <Switch>
            <Route path={YOUR_DETAILS_ROUTE} exact>
                <YourDetails />
            </Route>

            <Route path={YOUR_FATHER_ROUTE} exact>
                <FathersDetails />
            </Route>

            <Route path={YOUR_MOTHER_ROUTE} exact>
                <MothersDetails />
            </Route>

            <Route path={YOUR_DETAILS_OVERVIEW_ROUTE} exact>
                <Overview />
            </Route>
        </Switch>
    );
}

export default YourDetailsRoutes;