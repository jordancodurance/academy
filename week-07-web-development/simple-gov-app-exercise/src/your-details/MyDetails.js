import React, {useEffect, useState} from "react";
import axios from 'axios';
import {Footer, Page} from 'govuk-react';
import {FormField} from "./shared/FormField";
import {SubmittableForm} from "./shared/SubmittableForm";

function MyDetails() {
    const getInitialDetails = async () => {
        const result = await axios.get(`http://localhost:3004/subject`);

        if (!result) return {};

        return result.data;
    }

    const updateYourDetails = (fields) => {
        axios.post('http://localhost:3004/subject', fields);
    }

    return (
        <>
            <Page>
                <div className="wrapper">
                    <h2>Your Details</h2>

                    <SubmittableForm heading="Please enter your details" loadInitialState={getInitialDetails} onSubmit={updateYourDetails}>
                        {({fields, handleFormUpdated}) => (
                            <>
                                <FormField label="First Name" name="firstName" value={fields.firstName} valueSetter={handleFormUpdated} />

                                <FormField label="Last Name" name="lastName" value={fields.lastName} valueSetter={handleFormUpdated} />

                                <FormField label="Age" name="age" value={fields.age} valueSetter={handleFormUpdated} />
                            </>
                        )}
                    </SubmittableForm>
                </div>
            </Page>
            <Footer/>
        </>
    )
}

export {MyDetails}