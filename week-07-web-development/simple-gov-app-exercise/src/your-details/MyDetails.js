import React, {useEffect, useState} from "react";
import axios from 'axios';
import {Footer, Page} from 'govuk-react';
import {FormField} from "./shared/FormField";
import {SubmittableForm} from "./shared/SubmittableForm";

function MyDetails() {
    const [details, setDetails] = useState({
        firstName: "",
        lastName: "",
        age: ""
    })

    useEffect(() => {
        const getMyDetails = async () => {
            const res = await axios.get(`http://localhost:3004/subject`);

            if (res) {
                setDetails(res.data);
            }
        }

        getMyDetails();
    }, []);

    const updateYourDetails = (fields) => {
        axios.post('http://localhost:3004/subject', fields);
    }

    return (
        <>
            <Page>
                <div className="wrapper">
                    <h2>Your Details</h2>

                    <SubmittableForm heading="Please enter your details" initialState={details} onSubmit={updateYourDetails}>
                        {({handleFormUpdated}) => (
                            <>
                                <FormField label="First Name" name="firstName" value={details.firstName} valueSetter={handleFormUpdated} />

                                <FormField label="Last Name" name="lastName" value={details.lastName} valueSetter={handleFormUpdated} />

                                <FormField label="Age" name="age" value={details.age} valueSetter={handleFormUpdated} />
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