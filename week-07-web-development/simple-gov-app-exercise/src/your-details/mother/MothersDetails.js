import React, {useState} from "react";
import {ErrorText, Fieldset} from 'govuk-react';
import {BrandedPage} from "../../shared/BrandedPage";
import {SubmittableForm} from "../shared/component/SubmittableForm";
import {FormField} from "../shared/component/FormField";
import {getMother, updateMother} from "../shared/api/YourDetailsApiClient";
import {useHistory} from "react-router-dom";
import {determineNextRoute} from "../shared/domain/YourDetailsNavigationProvider";

function MothersDetails() {
    const history = useHistory();
    const [error, setError] = useState(undefined);

    const updateMothersDetails = (fields) => {
        updateMother(fields)
            .then(() => {
                const nextRoute = determineNextRoute(history.location.pathname);
                history.push(nextRoute);
            })
            .catch(() => setError("Unable to submit mothers details due to a network error"))
    }

    return (
        <BrandedPage>
            <h2>Your Mothers Details</h2>

            {error &&
            <ErrorText>{error}</ErrorText>
            }

            <SubmittableForm loadInitialState={getMother} onSubmit={updateMothersDetails}>
                {({fields, handleFormUpdated}) => (
                    <>
                        <Fieldset.Legend>Please enter your mothers details</Fieldset.Legend>

                        <FormField label="First Name" name="firstName" value={fields.firstName}
                                   valueSetter={handleFormUpdated}/>

                        <FormField label="Last Name" name="lastName" value={fields.lastName}
                                   valueSetter={handleFormUpdated}/>

                        <FormField label="Maiden Name" name="maidenName" value={fields.maidenName}
                                   valueSetter={handleFormUpdated}/>

                        <FormField label="Age" name="age" value={fields.age} valueSetter={handleFormUpdated}/>

                    </>
                )}
            </SubmittableForm>
        </BrandedPage>
    );
}

export {MothersDetails}