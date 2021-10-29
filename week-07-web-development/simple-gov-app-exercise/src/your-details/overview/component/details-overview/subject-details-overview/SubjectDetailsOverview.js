import {
    DetailsSummaryList,
    DetailsSummaryListAction,
    DetailsSummaryListKey,
    DetailsSummaryListRow,
    DetailsSummaryListValue
} from "../details-summary-list/DetailsSummaryList";
import {YOUR_DETAILS_ROUTE} from "../../../../YourDetailsRoutes";
import DetailsMissingWarning from "../details-missing-warning/DetailsMissingWarning";
import React from "react";
import {isCompletedPerson} from "../../../policy/DetailsCompletionValidator";
import {Link} from "react-router-dom";

function SubjectDetailsOverview(props) {
    const {subject} = props;

    return (
        <DetailsSummaryList>
            <DetailsSummaryListRow>
                <DetailsSummaryListKey>
                    <h4>Your Details:</h4>
                </DetailsSummaryListKey>
                <DetailsSummaryListAction>
                    <Link to={YOUR_DETAILS_ROUTE}>Change</Link>
                </DetailsSummaryListAction>
            </DetailsSummaryListRow>

            {isCompletedPerson(subject)
                ? <>
                    <DetailsSummaryListRow>
                        <DetailsSummaryListKey>Name</DetailsSummaryListKey>
                        <DetailsSummaryListValue>{subject.firstName} {subject.lastName}</DetailsSummaryListValue>
                    </DetailsSummaryListRow>

                    <DetailsSummaryListRow>
                        <DetailsSummaryListKey>Age</DetailsSummaryListKey>
                        <DetailsSummaryListValue>{subject.age}</DetailsSummaryListValue>
                    </DetailsSummaryListRow>
                </>
                : <DetailsMissingWarning/>
            }
        </DetailsSummaryList>
    )
}

export default SubjectDetailsOverview;