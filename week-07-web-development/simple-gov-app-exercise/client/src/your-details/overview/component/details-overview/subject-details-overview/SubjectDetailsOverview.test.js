import {render, screen} from "@testing-library/react";
import {when} from "jest-when";
import * as DetailsCompletionValidator from "../../../domain/DetailsCompletionValidator";
import SubjectDetailsOverview from "./SubjectDetailsOverview";
import {createMemoryHistory} from "history";
import {Router} from "react-router-dom";
import userEvent from "@testing-library/user-event";

jest.mock('../../../domain/DetailsCompletionValidator');

describe('on subject details overview rendered', () => {
    const history = createMemoryHistory();
    const subject = {
        firstName: "Subject First Name",
        lastName: "Subject Last Name",
        age: "50"
    };

    describe('and change link clicked', () => {
        it('should go to your details', async () => {
            await renderSubjectDetailsOverview();

            const button = await screen.findByText('Change', {selector: 'a'});
            userEvent.click(button);

            expect(history.location.pathname).toBe('/your-details');
        });
    });

    describe('and incomplete details provided', () => {
        it('should show warning', async () => {
            when(DetailsCompletionValidator.isCompletedPerson).calledWith(subject).mockReturnValue(false);

            await renderSubjectDetailsOverview();

            expect(screen.getByText('This section has not been completed yet')).toBeInTheDocument();
        });
    });

    describe('and complete subject details provided', () => {
        it('should show overview', async () => {
            when(DetailsCompletionValidator.isCompletedPerson).calledWith(subject).mockReturnValue(true);

            await renderSubjectDetailsOverview();

            expect(screen.getByText('Subject First Name Subject Last Name')).toBeInTheDocument();
            expect(screen.getByText('50')).toBeInTheDocument();
        });
    });

    const renderSubjectDetailsOverview = async () => {
        await render(
            <Router history={history}>
                <SubjectDetailsOverview subject={subject}/>
            </Router>
        );
    };
});