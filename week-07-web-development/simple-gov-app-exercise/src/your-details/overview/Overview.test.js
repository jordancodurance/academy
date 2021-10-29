import {render, screen} from "@testing-library/react";
import Overview from "./Overview";
import {createMemoryHistory} from "history";
import {Router} from "react-router";
import {when} from "jest-when";
import axios from "axios";
import * as DetailsCompletionValidator from "./DetailsCompletionValidator";
import userEvent from "@testing-library/user-event";
import {Route} from "react-router-dom";

jest.mock('axios');
jest.mock('./DetailsCompletionValidator.js');

describe('on overview page rendered', () => {
    const history = createMemoryHistory();

    describe('and back button is pressed', () => {
        it('takes user back to homepage', async () => {
            withInvalidDetailsRetrieved();
            await renderOverview();

            const button = await screen.findByText('Go back', {selector: 'a'});
            userEvent.click(button);

            expectPathNameToBe('/');
        });
    })

    describe('and incomplete details are retrieved', () => {
        beforeEach(async () => {
            withInvalidDetailsRetrieved();

            await renderOverview();
        });

        it('it should disable the submit button', async () => {
            expect(await screen.findByText('Submit Your Details', {selector: 'button'})).toHaveAttribute('disabled');
        });

        it('it should display a warning', async () => {
            expect(await screen.findByText('Form cannot be submitted until all required fields are filled')).toBeInTheDocument();
        });
    })

    describe('and submit button is pressed', () => {
        beforeEach(async () => {
            withValidDetailsRetrieved();

            await renderOverview();
        });

        describe('and attempt to complete was unsuccessful', () => {
            it('should show error feedback', async () => {
                when(axios.post).calledWith('http://localhost:3004/your-details/complete').mockRejectedValueOnce({});

                await clickButton('Submit Your Details');

                expect(await screen.findByText('Unable to submit your details due to a network error')).toBeInTheDocument();
            });
        });

        describe('and attempt to complete was successful', () => {
            it('takes user to successful submission page', async () => {
                when(axios.post).calledWith('http://localhost:3004/your-details/complete').mockResolvedValueOnce({});

                await clickButton('Submit Your Details');

                expectPathNameToBe('/successful-submission');
            });
        });
    });

    const renderOverview = async () => {
        await render(
            <Router history={history}>
                <Route path="/overview">
                    <Overview/>
                </Route>
            </Router>
        );
        history.push("/overview");
    };

    const withInvalidDetailsRetrieved = () => {
        const expectedDetails = buildExpectedRetrievedDetails();
        withExpectedDetailsRetrieved(expectedDetails);
        when(DetailsCompletionValidator.hasCompletedRequiredDetails).calledWith(expectedDetails).mockReturnValue(false);
    };

    const withValidDetailsRetrieved = () => {
        const expectedDetails = buildExpectedRetrievedDetails();
        withExpectedDetailsRetrieved(expectedDetails);
        when(DetailsCompletionValidator.hasCompletedRequiredDetails).calledWith(expectedDetails).mockReturnValue(true);
    };

    const buildExpectedRetrievedDetails = () => ({
        subject: {
            firstName: "Subject First name",
            lastName: "Subject Last Name",
            age: "50"
        },
        father: {
            firstName: "Father First name",
            lastName: "Father Last Name",
            age: "82"
        },
        mother: {
            firstName: "Mother First name",
            lastName: "Mother Last Name",
            maidenName: "Mother Maiden Name",
            age: "75"
        }
    });

    const withExpectedDetailsRetrieved = details => {
        when(axios.get).calledWith('http://localhost:3004/subject').mockResolvedValue({
            data: details.subject
        });
        when(axios.get).calledWith('http://localhost:3004/father').mockResolvedValue({
            data: details.father
        });
        when(axios.get).calledWith('http://localhost:3004/mother').mockResolvedValue({
            data: details.mother
        });
    };

    const expectPathNameToBe = path => expect(history.location.pathname).toBe(path);

    const clickButton = async text => {
        const button = await screen.findByText(text, {selector: 'button'});
        userEvent.click(button);
    };
});
