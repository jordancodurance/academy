import React, {useEffect, useState} from "react";
import axios from 'axios';
import {Footer, Page} from 'govuk-react';
import {FormField} from "./shared/FormField";
import {SubmittableForm} from "./shared/SubmittableForm";

function MyDetails() {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [age, setAge] = useState("");

    useEffect(() => {
        const getMyDetails = async () => {
            const res = await axios.get(`http://localhost:3004/subject`);

            if (res) {
                const {firstName, lastName, age} = res.data;

                setFirstName(firstName);
                setLastName(lastName);
                setAge(age);
            }
        }

        getMyDetails();
    }, []);

    const updateYourDetails = () => {
        axios.post('http://localhost:3004/subject', {
            firstName, lastName, age
        });
    }

    return (
        <>
            <Page>
                <div className="wrapper">
                    <h2>Your Details</h2>

                    <SubmittableForm heading="Please enter your details" onSubmit={updateYourDetails}>
                        <FormField label="First Name" name="firstName" value={firstName} valueSetter={setFirstName}/>

                        <FormField label="Last Name" name="lastName" value={lastName} valueSetter={setLastName}/>

                        <FormField label="Age" name="age" value={age} valueSetter={setAge}/>
                    </SubmittableForm>
                </div>
            </Page>
            <Footer/>
        </>
    )
}

export {MyDetails}