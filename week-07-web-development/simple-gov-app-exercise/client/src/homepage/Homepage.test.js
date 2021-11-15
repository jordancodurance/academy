import {createMemoryHistory} from "history";
import {Router} from "react-router";
import {Homepage} from "./Homepage";
import {render, screen} from "@testing-library/react";

describe('on homepage rendered', () => {
    const history = createMemoryHistory();

    beforeEach(async () => {
        await render(
            <Router history={history}>
                <Homepage/>
            </Router>
        );
    });

    describe('on your details link clicked', () => {
        it('should go to your details page', () => {
            clickLink('Your details page');

            expectPathNameToBe('/your-details');
        });
    });

    describe('on father details link clicked', () => {
        it('should go to father details page', () => {
            clickLink('Your fathers details page');

            expectPathNameToBe('/your-details/father');
        });
    });

    describe('on mother details link clicked', () => {
        it('should go to mother details page', () => {
            clickLink('Your mothers details page');

            expectPathNameToBe('/your-details/mother');
        });
    });

    describe('on overview link clicked', () => {
        it('should go to your details overview page', () => {
            clickLink('View and finalise all of your details');

            expectPathNameToBe('/your-details/overview');
        });
    });

    const clickLink = text => screen.getByText(text, {selector: 'a'}).click();

    const expectPathNameToBe = path => expect(history.location.pathname).toBe(path);
});

