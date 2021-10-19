import React, {useEffect, useState} from "react";
import axios from 'axios';
import {Button, Fieldset, Footer, Input, Label, LabelText, Page} from 'govuk-react';

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

    const onSubmit = event => {
        event.preventDefault();

        axios.post('http://localhost:3004/subject', {
            firstName, lastName, age
        });
    }

    return (
        <>
            <Page>
                <div className="wrapper">
                    <h2>Your Details</h2>
                    <form onSubmit={onSubmit}>
                        <Fieldset>
                            <Fieldset.Legend>Please enter your details</Fieldset.Legend>

                            <FormField label="First Name" name="firstName" value={firstName}
                                       valueSetter={setFirstName}/>

                            <FormField label="Last Name" name="lastName" value={lastName} valueSetter={setLastName}/>

                            <FormField label="Age" name="age" value={age} valueSetter={setAge}/>
                            <div className="form-group">
                                <Button data-testid="submit-button" type="submit">Submit</Button>
                            </div>
                        </Fieldset>
                    </form>
                </div>
            </Page>
            <Footer/>
        </>
    )
}

function FormField(props) {
    const {label, name, value, valueSetter} = props;

    return (
        <div className="form-group">
            <Label>
                <LabelText>
                    {label}
                </LabelText>
                <Input name={name} defaultValue={value} onChange={e => valueSetter(e.target.value)}/>
            </Label>
        </div>
    );
}

export {MyDetails}