import React from "react";
import axios from 'axios';
import {Fieldset} from 'govuk-react';
import {FormField} from "./shared/FormField";
import {SubmittableForm} from "./shared/SubmittableForm";
import {BrandedPage} from "../shared/BrandedPage";

function YourDetails() {
    const getInitialDetails = async () => {
        const result = await axios.get(`http://localhost:3004/subject`);

        if (!result) return {};

        return result.data;
    }

    const updateYourDetails = (fields) => {
        axios.post('http://localhost:3004/subject', fields);
    }

    return (
        <BrandedPage>
            <h2>Your Details</h2>

            <SubmittableForm loadInitialState={getInitialDetails} onSubmit={updateYourDetails}>
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