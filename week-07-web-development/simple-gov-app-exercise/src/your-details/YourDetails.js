import React from "react";
import {Fieldset} from 'govuk-react';
import {FormField} from "./shared/FormField";
import {SubmittableForm} from "./shared/SubmittableForm";
import {BrandedPage} from "../shared/BrandedPage";
import {getSubject, updateSubject} from "./shared/YourDetailsApiClient";

function YourDetails() {
    return (
        <BrandedPage>
            <h2>Your Details</h2>

            <SubmittableForm loadInitialState={getSubject} onSubmit={updateSubject}>
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