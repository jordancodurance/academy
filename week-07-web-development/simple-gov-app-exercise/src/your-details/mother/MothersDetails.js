import React from "react";
import {Fieldset} from 'govuk-react';
import {BrandedPage} from "../../shared/BrandedPage";
import {SubmittableForm} from "../shared/SubmittableForm";
import {FormField} from "../shared/FormField";
import {getMother, updateMother} from "../shared/YourDetailsApi";

function MothersDetails() {
    return (
        <BrandedPage>
            <h2>Your Mothers Details</h2>

            <SubmittableForm loadInitialState={getMother} onSubmit={updateMother}>
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