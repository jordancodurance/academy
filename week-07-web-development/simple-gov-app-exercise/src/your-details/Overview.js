import React, {useEffect, useState} from "react";
import {Link, useHistory} from "react-router-dom";
import {BrandedPage} from "../shared/BrandedPage";
import Button from "@govuk-react/button";
import axios from 'axios';
import {hasCompletedRequiredDetails} from "./domain/DetailsCompletionValidator";
import {ErrorText, WarningText} from "govuk-react";

function Overview() {
    const history = useHistory();
    const [error, setError] = useState(undefined);
    const [details, setDetails] = useState(undefined);

    useEffect(() => {
        retrieveCompletedDetails().then(details => {
            console.log(details);
            if (!hasCompletedRequiredDetails(details))
                setError({
                    type: "warning",
                    message: "Form cannot be submitted until all required fields are filled"
                });

            setDetails(details);
        });
    }, [])

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