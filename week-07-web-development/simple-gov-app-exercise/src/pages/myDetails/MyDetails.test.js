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

            expectInputToHaveValue('First Name', '');
            expectInputToHaveValue('Last Name', '');
            expectInputToHaveValue('Age', '');
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

            expectInputToHaveValue('First Name', 'John');
            expectInputToHaveValue('Last Name', 'Joe');
            expectInputToHaveValue('Age', '20');
        });
    });

    describe('submit button pressed', () => {
        it('should persist the details', async () => {
            await render(<MyDetails/>);
            inputText('First Name', 'John');
            inputText('Last Name', 'Joe');
            inputText('Age', '20');

            screen.getByText('Submit', {selector: 'button'}).click();

            expect(axios.post).toBeCalledWith('http://localhost:3004/subject', {
                firstName: 'John',
                lastName: 'Joe',
                age: '20'
            });
        });
    });

    const expectInputToHaveValue = (labelText, value) => {
        expect(findInput(labelText)).toHaveValue(value);
    };

    const findInput = labelText => screen.getByLabelText(labelText);

    const inputText = (labelText, value) => {
        const input = findInput(labelText);
        userEvent.type(input, value);
    }
});