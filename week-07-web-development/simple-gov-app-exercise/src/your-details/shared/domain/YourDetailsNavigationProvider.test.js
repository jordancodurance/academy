import {determineNextRoute} from "./YourDetailsNavigationProvider";

describe('on determining the next route for your details journey', () => {
    it.each([
        ["/your-details", "/your-details/father"],
        ["/your-details/father", "/your-details/mother"],
        ["/your-details/mother", "/your-details/overview"]
    ])
    ('should provide next configured route', (currentRoute, expectedNextRoute) => {
        expect(determineNextRoute(currentRoute)).toBe(expectedNextRoute);
    });
});