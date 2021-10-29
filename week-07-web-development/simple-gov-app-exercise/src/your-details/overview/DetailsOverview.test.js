import {render, screen} from "@testing-library/react";
import DetailsOverview from "./DetailsOverview";
import * as DetailsCompletionValidator from "./DetailsCompletionValidator";
import {when} from "jest-when";

jest.mock('./DetailsCompletionValidator.js');

describe('on details overview rendered', () => {
    const details = {
        subject: {
            firstName: "Subject First Name",
            lastName: "Subject Last Name",
            age: "50"
        },
        father: {
            firstName: "Father First Name",
            lastName: "Father Last Name",
            age: "82"
        },
        mother: {
            firstName: "Mother First Name",
            lastName: "Mother Last Name",
            maidenName: "Mother Maiden Name",
            age: "75"
        }
    };

    describe('and incomplete subject details provided', () => {
        it('should show warning', async () => {
            withDetailsNotBeingCompleted(details.subject);

            await renderDetailsOverview();

            expectWarningMessageToBeShown("Your Details");
        });
    });

    describe('and complete subject details provided', () => {
        it('should show overview', async () => {
            withDetailsBeingCompleted(details.subject);

            await renderDetailsOverview();

            expect(screen.getByText('Your Details:')).toBeInTheDocument();
            expect(screen.getByText('Name - Subject First Name Subject Last Name')).toBeInTheDocument();
            expect(screen.getByText('Age - 50')).toBeInTheDocument();
        });
    });

    describe('and incomplete father details provided', () => {
        it('should show warning', async () => {
            withDetailsNotBeingCompleted(details.father);

            await renderDetailsOverview();

            expectWarningMessageToBeShown("Your Fathers Details");
        });
    });

    describe('and complete father details provided', () => {
        it('should show overview', async () => {
            withDetailsBeingCompleted(details.father);

            await renderDetailsOverview();

            expect(screen.getByText('Your Fathers Details:')).toBeInTheDocument();
            expect(screen.getByText('Name - Father First Name Father Last Name')).toBeInTheDocument();
            expect(screen.getByText('Age - 82')).toBeInTheDocument();
        });
    });

    describe('and incomplete mother details provided', () => {
        it('should show warning', async () => {
            withDetailsNotBeingCompleted(details.mother);

            await renderDetailsOverview();

            expectWarningMessageToBeShown("Your Mothers Details");
        });
    });

    describe('and complete mother details provided', () => {
        it('should show overview', async () => {
            withDetailsBeingCompleted(details.mother);

            await renderDetailsOverview();

            expect(screen.getByText('Your Mothers Details:')).toBeInTheDocument();
            expect(screen.getByText('Name - Mother First Name Mother Maiden Name Mother Last Name')).toBeInTheDocument();
            expect(screen.getByText('Age - 75')).toBeInTheDocument();
        });
    });

    const renderDetailsOverview = async () => {
        await render(
            <DetailsOverview details={details}/>
        );
    };

    const withDetailsNotBeingCompleted = detail => {
        when(DetailsCompletionValidator.isCompletedPerson).calledWith(detail).mockReturnValue(false);
    };

    const withDetailsBeingCompleted = detail => {
        when(DetailsCompletionValidator.isCompletedPerson).calledWith(detail).mockReturnValue(true);
    };

    const expectWarningMessageToBeShown = detailName => {
        expect(screen.getByText(`${detailName} have not been completed yet`)).toBeInTheDocument();
    };
});
