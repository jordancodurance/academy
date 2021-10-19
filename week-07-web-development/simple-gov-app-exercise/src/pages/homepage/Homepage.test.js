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

    describe('on your details button clicked', () => {
        it('should go to your details page', () => {
            clickLink('Your details page');

            expectPathNameToBe('/my-details');
        });
    });

    describe('on father details button clicked', () => {
        it('should go to father details page', () => {
            clickLink('Your fathers details page');

            expectPathNameToBe('/fathers-details');
        });
    });

    describe('on mother details button clicked', () => {
        it('should go to mother details page', () => {
            clickLink('Your mothers details page');

            expectPathNameToBe('/mothers-details');
        });
    });

    const clickLink = text => screen.getByText(text, {selector: 'a'}).click();

    const expectPathNameToBe = path => expect(history.location.pathname).toBe(path);
});

