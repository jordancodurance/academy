import React, {useEffect, useState} from "react";
import {Link, useHistory} from "react-router-dom";
import {BrandedPage} from "../../shared/BrandedPage";
import Button from "@govuk-react/button";
import {hasAllCompletedRequiredDetails} from "./policy/DetailsCompletionValidator";
import {ErrorText, H3, LoadingBox, WarningText} from "govuk-react";
import {completeYourDetails, getFather, getMother, getSubject} from "../shared/api/YourDetailsApiClient";
import DetailsOverview from "./component/details-overview/DetailsOverview";
import {MAIN_ROUTE} from "../../shared/Routes";
import {YOUR_DETAILS_SUCCESSFUL_SUBMISSION} from "../YourDetailsRoutes";

function Overview() {
    const history = useHistory();
    const [error, setError] = useState(undefined);
    const [details, setDetails] = useState(undefined);

    useEffect(() => {
        retrieveCompletedDetails().then(details => {
            if (!hasAllCompletedRequiredDetails(details))
                setError({
                    type: "warning",
                    message: "Form cannot be submitted until all required fields are filled"
                });

            setDetails(details);
        });
    }, [])

    const retrieveCompletedDetails = async () => {
        const subject = await getSubject();
        const father = await getFather();
        const mother = await getMother();

        return {
            subject,
            father,
            mother
        };
    };

    async function attemptSubmit() {
        await completeYourDetails()
            .then(() => history.push(YOUR_DETAILS_SUCCESSFUL_SUBMISSION))
            .catch(() => setError({
                type: "network-error",
                message: "Unable to submit your details due to a network error"
            }))
    }

    return (
        <BrandedPage>
            <Link to={MAIN_ROUTE}>Go back</Link>

            {error && error.type !== "warning" &&
            <ErrorText>{error.message}</ErrorText>
            }

            {error && error.type === "warning" &&
            <WarningText>{error.message}</WarningText>
            }

            {!details
                ? <LoadingBox loading><H3>Loading Your Details status</H3></LoadingBox>
                : <DetailsOverview details={details}/>
            }

            <Button onClick={attemptSubmit} disabled={error && error.type === "warning"}>Submit Your Details</Button>
        </BrandedPage>
    )
}

export default Overview;