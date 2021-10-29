import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as DetailsCompletionValidator from "../../../policy/DetailsCompletionValidator";
import MotherDetailsOverview from "./MotherDetailsOverview";
import {createMemoryHistory} from "history";
import {Router} from "react-router-dom";
import userEvent from "@testing-library/user-event";

jest.mock('../../../policy/DetailsCompletionValidator');

describe('on mother details overview rendered', () => {
    const history = createMemoryHistory();
    const mother = {
        firstName: "Mother First Name",
        lastName: "Mother Last Name",
        maidenName: "Mother Maiden Name",
        age: "75"
    };

    describe('and change link clicked', () => {
        it('should go to your mothers details', async () => {
            await renderMotherDetailsOverview();

            const button = await screen.findByText('Change', {selector: 'a'});
            userEvent.click(button);

            expect(history.location.pathname).toBe('/your-details/mother');
        });
    });

    describe('and incomplete details provided', () => {
        it('should show warning', async () => {
            when(DetailsCompletionValidator.isCompletedPerson).calledWith(mother).mockReturnValue(false);

            await renderMotherDetailsOverview();

            expect(screen.getByText('This section has not been completed yet')).toBeInTheDocument();
        });
    });

    describe('and complete mother details provided', () => {
        it('should show overview', async () => {
            when(DetailsCompletionValidator.isCompletedPerson).calledWith(mother).mockReturnValue(true);

            await renderMotherDetailsOverview();

            expect(screen.getByText('Mother First Name Mother Last Name')).toBeInTheDocument();
            expect(screen.getByText('Mother Maiden Name')).toBeInTheDocument();
            expect(screen.getByText('75')).toBeInTheDocument();
        });
    });

    const renderMotherDetailsOverview = async () => {
        await render(
            <Router history={history}>
                <MotherDetailsOverview mother={mother}/>
            </Router>
        );
    };
});