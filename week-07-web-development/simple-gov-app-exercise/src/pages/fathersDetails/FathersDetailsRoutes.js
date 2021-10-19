import { FathersDetails } from './FathersDetails'
import { Route, Switch } from "react-router-dom";

const FathersDetailsRoutes = () => {
    return (
        <Switch>
            <Route path="/fathers-details">
                <FathersDetails />
            </Route>
        </Switch>
    );
}

export { FathersDetailsRoutes }