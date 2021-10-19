import { MothersDetails } from './MothersDetails'
import { Route, Switch } from "react-router-dom";

const MothersDetailsRoutes = () => {
    return (
        <Switch>
            <Route path="/mothers-details">
                <MothersDetails />
            </Route>
        </Switch>
    );
}

export { MothersDetailsRoutes }