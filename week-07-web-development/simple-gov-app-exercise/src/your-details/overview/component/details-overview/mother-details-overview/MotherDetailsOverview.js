import {
    DetailsSummaryList,
    DetailsSummaryListAction,
    DetailsSummaryListKey,
    DetailsSummaryListRow,
    DetailsSummaryListValue
} from "../details-summary-list/DetailsSummaryList";
import {YOUR_MOTHER_ROUTE} from "../../../../YourDetailsRoutes";
import DetailsMissingWarning from "../details-missing-warning/DetailsMissingWarning";
import React from "react";
import {isCompletedPerson} from "../../../policy/DetailsCompletionValidator";
import {Link} from "react-router-dom";

function MotherDetailsOverview(props) {
    const {mother} = props;

    return (
        <DetailsSummaryList>
            <DetailsSummaryListRow>
                <DetailsSummaryListKey>
                    <h4>Your Mothers Details:</h4>
                </DetailsSummaryListKey>
                <DetailsSummaryListAction>
                    <Link to={YOUR_MOTHER_ROUTE}>Change</Link>
                </DetailsSummaryListAction>
            </DetailsSummaryListRow>

            {isCompletedPerson(mother)
                ? <>
                    <DetailsSummaryListRow>
                        <DetailsSummaryListKey>Name</DetailsSummaryListKey>
                        <DetailsSummaryListValue>{mother.firstName} {mother.lastName}</DetailsSummaryListValue>
                    </DetailsSummaryListRow>

                    <DetailsSummaryListRow>
                        <DetailsSummaryListKey>Maiden Name</DetailsSummaryListKey>
                        <DetailsSummaryListValue>{mother.maidenName}</DetailsSummaryListValue>
                    </DetailsSummaryListRow>

                    <DetailsSummaryListRow>
                        <DetailsSummaryListKey>Age</DetailsSummaryListKey>
                        <DetailsSummaryListValue>{mother.age}</DetailsSummaryListValue>
                    </DetailsSummaryListRow>
                </>
                : <DetailsMissingWarning/>
            }
        </DetailsSummaryList>
    )
}

export default MotherDetailsOverview;