import React, {useEffect, useState} from "react";
import axios from 'axios';
import {Button, Fieldset, Input, Label, LabelText} from 'govuk-react';
import {BrandedPage} from "../shared/BrandedPage";
import {SubmittableForm} from "./shared/SubmittableForm";
import {FormField} from "./shared/FormField";

function MothersDetails() {
    const getInitialDetails = async () => {
        const result = await axios.get(`http://localhost:3004/mother`);

        if (!result) return {};

        return result.data;
    }

    const updateMothersDetails = (fields) => {
        axios.post('http://localhost:3004/mother', fields);
    }

    return (
        <BrandedPage>
            <h2>Your Mothers Details</h2>

            <SubmittableForm loadInitialState={getInitialDetails} onSubmit={updateMothersDetails}>
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