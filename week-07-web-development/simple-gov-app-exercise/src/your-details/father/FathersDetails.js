import React from "react";
import {Fieldset} from 'govuk-react';
import {BrandedPage} from "../../shared/BrandedPage";
import {SubmittableForm} from "../shared/SubmittableForm";
import {FormField} from "../shared/FormField";
import {getFather, updateFather} from "../shared/YourDetailsApi";

function FathersDetails() {
    return (
        <BrandedPage>
            <h2>Your Fathers Details</h2>

            <SubmittableForm loadInitialState={getFather} onSubmit={updateFather}>
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