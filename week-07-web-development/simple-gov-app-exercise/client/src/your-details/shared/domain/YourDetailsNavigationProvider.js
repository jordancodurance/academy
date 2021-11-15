import {
    YOUR_DETAILS_OVERVIEW_ROUTE,
    YOUR_DETAILS_ROUTE,
    YOUR_FATHER_ROUTE,
    YOUR_MOTHER_ROUTE
} from "../../YourDetailsRoutes";

export const determineNextRoute = currentRoute => {
    if (currentRoute === YOUR_DETAILS_ROUTE) return YOUR_FATHER_ROUTE;
    if (currentRoute === YOUR_FATHER_ROUTE) return YOUR_MOTHER_ROUTE;

    return YOUR_DETAILS_OVERVIEW_ROUTE;
};