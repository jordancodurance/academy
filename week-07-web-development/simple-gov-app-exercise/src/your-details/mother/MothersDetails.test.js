import {MothersDetails} from "./MothersDetails";
import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as axios from "axios";
import userEvent from "@testing-library/user-event";
import * as YourDetailsNavigationProvider from "../shared/policy/YourDetailsNavigationProvider";
import {Route, Router} from "react-router-dom";
import {createMemoryHistory} from "history";

jest.mock('axios');
jest.mock('../shared/policy/YourDetailsNavigationProvider');

describe('on mothers details render', () => {
    const history = createMemoryHistory();

    describe('and no details fetched', () => {
        it('should not pre-fill the form', async () => {
            await renderMothersDetails();

            await expectInputToHaveValue('First Name', '');
            await expectInputToHaveValue('Last Name', '');
            await expectInputToHaveValue('Maiden Name', '');
            await expectInputToHaveValue('Age', '');
        });
    });

    describe('and mothers details fetched', () => {
        it('should pre-fill the form', async () => {
            when(axios.get).calledWith('http://localhost:3004/mother').mockResolvedValueOnce({
                data: {
                    firstName: "Jane",
                    lastName: "Joe",
                    maidenName: "James",
                    age: 30
                }
            });

            await renderMothersDetails();

            await expectInputToHaveValue('First Name', 'Jane');
            await expectInputToHaveValue('Last Name', 'Joe');
            await expectInputToHaveValue('Maiden Name', 'James');
            await expectInputToHaveValue('Age', '30');
        });
    });

    describe('and submit button pressed', () => {
        const expectedMother = {
            firstName: 'Joanna',
            lastName: 'Joe',
            maidenName: 'James',
            age: '51',
        };

        beforeEach(async () => {
            await renderMothersDetails();
            await inputText('First Name', 'Joanna');
            await inputText('Last Name', 'Joe');
            await inputText('Maiden Name', 'James');
            await inputText('Age', '51');
        });

        describe('and unable to submit mothers details', () => {
            it('should show error', async () => {
                when(axios.post).calledWith('http://localhost:3004/mother', expectedMother).mockRejectedValue({});

                await clickSubmitButton();

                expect(await screen.findByText('Unable to submit mothers details due to a network error')).toBeInTheDocument();
            });
        });

        describe('and able to submit mothers details', () => {
            it('should go to next route', async () => {
                when(axios.post).calledWith('http://localhost:3004/mother', expectedMother).mockResolvedValue({});
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

    const renderMothersDetails = async () => {
        await render(
            <Router history={history}>
                <Route>
                    <MothersDetails/>
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