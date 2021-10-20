import {MyDetails} from "./MyDetails";
import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as axios from "axios";
import userEvent from "@testing-library/user-event";

jest.mock('axios');

describe('on my details render', () => {
    describe('and no details fetched', () => {
        it('should not pre-fill the form', async () => {
            await render(<MyDetails/>)

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

            await render(<MyDetails/>);
            
            await expectInputToHaveValue('First Name', 'John');
            await expectInputToHaveValue('Last Name', 'Joe');
            await expectInputToHaveValue('Age', '20');
        });
    });

    describe('submit button pressed', () => {
        it('should persist the details', async () => {
            await render(<MyDetails/>);
            await inputText('First Name', 'John');
            await inputText('Last Name', 'Joe');
            await inputText('Age', '20');

            screen.getByText('Submit', {selector: 'button'}).click();

            expect(axios.post).toBeCalledWith('http://localhost:3004/subject', {
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