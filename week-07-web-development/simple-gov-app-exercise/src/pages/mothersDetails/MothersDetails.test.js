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

            expectInputToHaveValue('First Name', '');
            expectInputToHaveValue('Last Name', '');
            expectInputToHaveValue('Maiden Name', '');
            expectInputToHaveValue('Age', '');
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

            expectInputToHaveValue('First Name', 'Jane');
            expectInputToHaveValue('Last Name', 'Joe');
            expectInputToHaveValue('Maiden Name', 'James');
            expectInputToHaveValue('Age', '30');
        });
    });

    describe('submit button pressed', () => {
        it('should persist the details', async () => {
            await render(<MothersDetails/>);
            inputText('First Name', 'John');
            inputText('Last Name', 'Joe');
            inputText('Maiden Name', 'James');
            inputText('Age', '20');

            screen.getByText('Submit', {selector: 'button'}).click();

            expect(axios.post).toBeCalledWith('http://localhost:3004/mother', {
                firstName: 'John',
                lastName: 'Joe',
                maidenName: 'James',
                age: '20',
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