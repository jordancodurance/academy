import {
    DetailsSummaryList,
    DetailsSummaryListAction,
    DetailsSummaryListKey,
    DetailsSummaryListRow,
    DetailsSummaryListValue
} from "../details-summary-list/DetailsSummaryList";
import {YOUR_FATHER_ROUTE} from "../../../../YourDetailsRoutes";
import DetailsMissingWarning from "../details-missing-warning/DetailsMissingWarning";
import React from "react";
import {isCompletedPerson} from "../../../policy/DetailsCompletionValidator";
import {Link} from "react-router-dom";

function FatherDetailsOverview(props) {
    const {father} = props;

    return (
        <DetailsSummaryList>
            <DetailsSummaryListRow>
                <DetailsSummaryListKey>
                    <h4>Your Fathers Details:</h4>
                </DetailsSummaryListKey>
                <DetailsSummaryListAction>
                    <Link to={YOUR_FATHER_ROUTE}>Change</Link>
                </DetailsSummaryListAction>
            </DetailsSummaryListRow>

            {isCompletedPerson(father)
                ? <>
                    <DetailsSummaryListRow>
                        <DetailsSummaryListKey>Name</DetailsSummaryListKey>
                        <DetailsSummaryListValue>{father.firstName} {father.lastName}</DetailsSummaryListValue>
                    </DetailsSummaryListRow>

                    <DetailsSummaryListRow>
                        <DetailsSummaryListKey>Age</DetailsSummaryListKey>
                        <DetailsSummaryListValue>{father.age}</DetailsSummaryListValue>
                    </DetailsSummaryListRow>
                </>
                : <DetailsMissingWarning/>
            }
        </DetailsSummaryList>
    )
}

export default FatherDetailsOverview;