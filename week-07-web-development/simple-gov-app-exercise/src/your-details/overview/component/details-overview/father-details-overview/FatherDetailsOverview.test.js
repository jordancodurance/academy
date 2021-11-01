import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as DetailsCompletionValidator from "../../../domain/DetailsCompletionValidator";
import FatherDetailsOverview from "./FatherDetailsOverview";
import {createMemoryHistory} from "history";
import {Router} from "react-router-dom";
import userEvent from "@testing-library/user-event";

jest.mock('../../../domain/DetailsCompletionValidator');

describe('on father details overview rendered', () => {
    const history = createMemoryHistory();
    const father = {
        firstName: "Father First Name",
        lastName: "Father Last Name",
        age: "82"
    };

    describe('and change link clicked', () => {
        it('should go to your fathers details', async () => {
            await renderFatherDetailsOverview();

            const button = await screen.findByText('Change', {selector: 'a'});
            userEvent.click(button);

            expect(history.location.pathname).toBe('/your-details/father');
        });
    });

    describe('and incomplete details provided', () => {
        it('should show warning', async () => {
            when(DetailsCompletionValidator.isCompletedPerson).calledWith(father).mockReturnValue(false);

            await renderFatherDetailsOverview();

            expect(screen.getByText('This section has not been completed yet')).toBeInTheDocument();
        });
    });

    describe('and complete father details provided', () => {
        it('should show overview', async () => {
            when(DetailsCompletionValidator.isCompletedPerson).calledWith(father).mockReturnValue(true);

            await renderFatherDetailsOverview();

            expect(screen.getByText('Father First Name Father Last Name')).toBeInTheDocument();
            expect(screen.getByText('82')).toBeInTheDocument();
        });
    });

    const renderFatherDetailsOverview = async () => {
        await render(
            <Router history={history}>
                <FatherDetailsOverview father={father}/>
            </Router>
        );
    };
});