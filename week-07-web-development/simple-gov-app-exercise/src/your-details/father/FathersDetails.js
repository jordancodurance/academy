import React, {useState} from "react";
import {ErrorText, Fieldset} from 'govuk-react';
import {BrandedPage} from "../../shared/BrandedPage";
import {SubmittableForm} from "../shared/component/SubmittableForm";
import {FormField} from "../shared/component/FormField";
import {getFather, updateFather} from "../shared/api/YourDetailsApiClient";
import {useHistory} from "react-router-dom";
import {determineNextRoute} from "../shared/policy/YourDetailsNavigationProvider";

function FathersDetails() {
    const history = useHistory();
    const [error, setError] = useState(undefined);

    const updateFathersDetails = (fields) => {
        updateFather(fields)
            .then(() => {
                const nextRoute = determineNextRoute(history.location.pathname);
                history.push(nextRoute);
            })
            .catch(() => setError("Unable to submit fathers details due to a network error"))
    }

    return (
        <BrandedPage>
            <h2>Your Fathers Details</h2>

            {error &&
            <ErrorText>{error}</ErrorText>
            }

            <SubmittableForm loadInitialState={getFather} onSubmit={updateFathersDetails}>
                {({fields, handleFormUpdated}) => (
                    <>
                        <Fieldset.Legend>Please enter your fathers details</Fieldset.Legend>

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

export {FathersDetails}