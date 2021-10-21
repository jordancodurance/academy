import React, {useEffect, useState} from "react";
import {Link, useHistory} from "react-router-dom";
import {BrandedPage} from "../shared/BrandedPage";
import Button from "@govuk-react/button";
import axios from 'axios';
import {hasCompletedRequiredDetails} from "./shared/DetailsPolicyValidator";
import {ErrorText, WarningText} from "govuk-react";

function Overview() {
    const history = useHistory();
    const [error, setError] = useState(undefined);
    const [details, setDetails] = useState(undefined);

    useEffect(() => {
        const retrieveCompletedDetails = async () => {
            const subject = await axios.get('http://localhost:3004/subject');
            const father = await axios.get('http://localhost:3004/father');
            const mother = await axios.get('http://localhost:3004/mother');

            return {
                subject: subject.data,
                father: father.data,
                mother: mother.data
            };
        };

        retrieveCompletedDetails().then(details => {
            if (!hasCompletedRequiredDetails(details))
                setError({
                    type: "warning",
                    message: "incomplete details"
                });

            setDetails(details);
        });
    }, [])

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
        return <div>Loading...</div>
    }

    return (
        <BrandedPage>
            {error && error.type !== "warning" &&
            <ErrorText>{error.message}</ErrorText>
            }
            {error && error.type === "warning" &&
            <WarningText>{error.message}</WarningText>
            }
            <Link to="/">Go back</Link>
            <Button onClick={attemptSubmit} disabled={error && error.type === "warning"}>Submit Your Details</Button>
        </BrandedPage>
    )
}

export default Overview;