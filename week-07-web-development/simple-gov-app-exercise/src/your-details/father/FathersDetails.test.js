import {FathersDetails} from "./FathersDetails";
import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as axios from "axios";
import userEvent from "@testing-library/user-event";

jest.mock('axios');

describe('on fathers details render', () => {
    describe('and no details fetched', () => {
        it('should not pre-fill the form', async () => {
            await render(<FathersDetails/>)

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

            await render(<FathersDetails/>);

            await expectInputToHaveValue('First Name', 'John');
            await expectInputToHaveValue('Last Name', 'Joe');
            await expectInputToHaveValue('Age', '20');
        });
    });

    describe('submit button pressed', () => {
        it('should persist the details', async () => {
            await render(<FathersDetails/>);
            await inputText('First Name', 'John');
            await inputText('Last Name', 'Joe');
            await inputText('Age', '20');

            screen.getByText('Submit', {selector: 'button'}).click();

            expect(axios.post).toBeCalledWith('http://localhost:3004/father', {
                firstName: 'John',
                lastName: 'Joe',
                age: '20'
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