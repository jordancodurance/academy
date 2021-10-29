import {MothersDetails} from "./MothersDetails";
import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as axios from "axios";
import userEvent from "@testing-library/user-event";

jest.mock('axios');

describe('on mothers details render', () => {
    describe('and no details fetched', () => {
        it('should not pre-fill the form', async () => {
            await render(<MothersDetails/>)

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

            await render(<MothersDetails/>);

            await expectInputToHaveValue('First Name', 'Jane');
            await expectInputToHaveValue('Last Name', 'Joe');
            await expectInputToHaveValue('Maiden Name', 'James');
            await expectInputToHaveValue('Age', '30');
        });
    });

    describe('submit button pressed', () => {
        it('should persist the details', async () => {
            await render(<MothersDetails/>);
            await inputText('First Name', 'John');
            await inputText('Last Name', 'Joe');
            await inputText('Maiden Name', 'James');
            await inputText('Age', '20');

            screen.getByText('Submit', {selector: 'button'}).click();

            expect(axios.post).toBeCalledWith('http://localhost:3004/mother', {
                firstName: 'John',
                lastName: 'Joe',
                maidenName: 'James',
                age: '20',
            });
        });
    });

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