import {FathersDetails} from "./FathersDetails";
import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as axios from "axios";
import userEvent from "@testing-library/user-event";
import {Route, Router} from "react-router-dom";
import {createMemoryHistory} from "history";
import * as YourDetailsNavigationProvider from "../shared/domain/YourDetailsNavigationProvider";

jest.mock('axios');
jest.mock('../shared/domain/YourDetailsNavigationProvider');

describe('on fathers details render', () => {
    const history = createMemoryHistory();

    describe('and no details fetched', () => {
        it('should not pre-fill the form', async () => {
            await renderFathersDetails();

            await expectInputToHaveValue('First Name', '');
            await expectInputToHaveValue('Last Name', '');
            await expectInputToHaveValue('Age', '');
        });
    });

    describe('and fathers details fetched', () => {
        it('should pre-fill the form', async () => {
            when(axios.get).calledWith('http://localhost:3004/father').mockResolvedValueOnce({
                data: {
                    firstName: "John",
                    lastName: "Joe",
                    age: 20
                }
            });

            await renderFathersDetails();

            await expectInputToHaveValue('First Name', 'John');
            await expectInputToHaveValue('Last Name', 'Joe');
            await expectInputToHaveValue('Age', '20');
        });
    });

    describe('and submit button pressed', () => {
        const expectedFather = {
            firstName: 'John Sr.',
            lastName: 'Joe',
            age: '56'
        };

        beforeEach(async () => {
            await renderFathersDetails();
            await inputText('First Name', 'John Sr.');
            await inputText('Last Name', 'Joe');
            await inputText('Age', '56');
        });

        describe('and unable to submit fathers details', () => {
            it('should show error', async () => {
                when(axios.post).calledWith('http://localhost:3004/father', expectedFather).mockRejectedValue({});

                await clickSubmitButton();

                expect(await screen.findByText('Unable to submit fathers details due to a network error')).toBeInTheDocument();
            });
        });

        describe('and able to submit fathers details', () => {
            it('should go to next route', async () => {
                when(axios.post).calledWith('http://localhost:3004/father', expectedFather).mockResolvedValue({});
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

    const renderFathersDetails = async () => {
        await render(
            <Router history={history}>
                <Route>
                    <FathersDetails/>
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