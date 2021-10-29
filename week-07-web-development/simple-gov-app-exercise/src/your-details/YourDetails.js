import React, {useState} from "react";
import {ErrorText, Fieldset} from 'govuk-react';
import {FormField} from "./shared/FormField";
import {SubmittableForm} from "./shared/SubmittableForm";
import {BrandedPage} from "../shared/BrandedPage";
import {getSubject, updateSubject} from "./shared/YourDetailsApiClient";
import {useHistory} from "react-router-dom";
import {determineNextRoute} from "./shared/policy/YourDetailsNavigationProvider";

function YourDetails() {
    const history = useHistory();
    const [error, setError] = useState(undefined);

    const updateYourDetails = (fields) => {
        updateSubject(fields)
            .then(() => {
                const nextRoute = determineNextRoute(history.location.pathname);
                history.push(nextRoute);
            })
            .catch(() => setError("Unable to submit your details due to a network error"))
    }

    return (
        <BrandedPage>
            <h2>Your Details</h2>

            {error &&
                <ErrorText>{error}</ErrorText>
            }

            <SubmittableForm loadInitialState={getSubject} onSubmit={updateYourDetails}>
                {({fields, handleFormUpdated}) => (
                    <>
                        <Fieldset.Legend>Please enter your details</Fieldset.Legend>

                        <FormField label="First Name" name="firstName" value={fields.firstName}
                                   valueSetter={handleFormUpdated}/>

                        <FormField label="Last Name" name="lastName" value={fields.lastName}
                                   valueSetter={handleFormUpdated}/>

                        <FormField label="Age" name="age" value={fields.age} valueSetter={handleFormUpdated}/>
                    </>
                )}
            </SubmittableForm>
        </BrandedPage>
    );
}

export {YourDetails}