import React, {useEffect, useState} from "react";
import {Link, useHistory} from "react-router-dom";
import {BrandedPage} from "../../shared/BrandedPage";
import Button from "@govuk-react/button";
import axios from 'axios';
import {hasCompletedRequiredDetails} from "./DetailsCompletionValidator";
import {ErrorText, WarningText} from "govuk-react";
import {getFather, getMother, getSubject} from "../shared/YourDetailsApi";

function Overview() {
    const history = useHistory();
    const [error, setError] = useState(undefined);
    const [details, setDetails] = useState(undefined);

    useEffect(() => {
        retrieveCompletedDetails().then(details => {
            if (!hasCompletedRequiredDetails(details))
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
        await axios
            .post('http://localhost:3004/your-details/complete')
            .then(() => history.push('/successful-submission'))
            .catch(() => setError({
                type: "network-error",
                message: "Unable to submit your details due to a network error"
            }))
    }

    if (!details) {
        return (
            <BrandedPage>
                <div>Loading...</div>
            </BrandedPage>
        );
    }

    return (
        <BrandedPage>
            <Link to="/">Go back</Link>

            {error && error.type !== "warning" &&
            <ErrorText>{error.message}</ErrorText>
            }

            {error && error.type === "warning" &&
            <WarningText>{error.message}</WarningText>
            }

            <Button onClick={attemptSubmit} disabled={error && error.type === "warning"}>Submit Your Details</Button>
        </BrandedPage>
    )
}

export default Overview;