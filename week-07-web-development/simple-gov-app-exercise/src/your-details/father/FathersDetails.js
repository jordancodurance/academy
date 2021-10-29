import React from "react";
import axios from 'axios';
import {Fieldset} from 'govuk-react';
import {BrandedPage} from "../../shared/BrandedPage";
import {SubmittableForm} from "../shared/SubmittableForm";
import {FormField} from "../shared/FormField";

function FathersDetails() {
    const getInitialDetails = async () => {
        const result = await axios.get(`http://localhost:3004/father`);

        if (!result) return {};

        return result.data;
    }

    const updateFathersDetails = (fields) => {
        axios.post('http://localhost:3004/father', fields);
    }

    return (
        <BrandedPage>
            <h2>Your Fathers Details</h2>

            <SubmittableForm loadInitialState={getInitialDetails} onSubmit={updateFathersDetails}>
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