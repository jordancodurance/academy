import {YourDetails} from "./YourDetails";
import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as axios from "axios";
import userEvent from "@testing-library/user-event";
import {Route, Router} from "react-router-dom";
import {createMemoryHistory} from "history";
import * as YourDetailsNavigationProvider from "./shared/policy/YourDetailsNavigationProvider";

jest.mock('axios');
jest.mock('./shared/policy/YourDetailsNavigationProvider');

describe('on my details render', () => {
    const history = createMemoryHistory();

    describe('and no details fetched', () => {
        it('should not pre-fill the form', async () => {
            await renderYourDetails();

            await expectInputToHaveValue('First Name', '');
            await expectInputToHaveValue('Last Name', '');
            await expectInputToHaveValue('Age', '');
        });
    });

    describe('and details fetched', () => {
        it('should pre-fill the form', async () => {
            when(axios.get).calledWith('http://localhost:3004/subject').mockResolvedValueOnce({
                data: {
                    firstName: "John",
                    lastName: "Joe",
                    age: 20
                }
            });

            await renderYourDetails();
            
            await expectInputToHaveValue('First Name', 'John');
            await expectInputToHaveValue('Last Name', 'Joe');
            await expectInputToHaveValue('Age', '20');
        });
    });

    describe('and submit button pressed', () => {
        const expectedSubject = {
            firstName: 'John',
            lastName: 'Joe',
            age: '20'
        };

        beforeEach(async () => {
            await renderYourDetails();
            await inputText('First Name', 'John');
            await inputText('Last Name', 'Joe');
            await inputText('Age', '20');
        });

        describe('and unable to submit your details', () => {
            it('should show error', async () => {
                when(axios.post).calledWith('http://localhost:3004/subject', expectedSubject).mockRejectedValue({});

                await clickSubmitButton();

                expect(await screen.findByText('Unable to submit your details due to a network error')).toBeInTheDocument();
            });
        });

        describe('and able to submit your details', () => {
            it('should go to next route', async () => {
                when(axios.post).calledWith('http://localhost:3004/subject', expectedSubject).mockResolvedValue({});
                when(YourDetailsNavigationProvider.determineNextRoute).calledWith("/").mockReturnValue("/next-route");

                await clickSubmitButton();

                expect(history.location.pathname).toBe("/next-route");
            });
        });

        const clickSubmitButton = async () => {
            const button = await screen.findByText('Submit', {selector: 'button'});
            userEvent.click(button);
        };
    });

    const renderYourDetails = async () => {
        await render(
            <Router history={history}>
                <Route>
                    <YourDetails/>
                </Route>
            </Router>
        );
    };

    const expectInputToHaveValue = async (labelText, value) => {
        const input = await findInput(labelText);
        expect(input).toHaveValue(value);
    };

    const findInput = async labelText => screen.findByLabelText(labelText);

    const inputText = async (labelText, value) => {
        const input = await findInput(labelText);
        userEvent.type(input, value);
    }
});