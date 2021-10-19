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
                            <div className="form-group">
                                <Label>
                                    <LabelText>
                                        First Name
                                    </LabelText>
                                    <Input name={"firstName"} defaultValue={firstName}
                                           onChange={e => setFirstName(e.target.value)}/>
                                </Label>
                            </div>
                            <div className="form-group">
                                <Label>
                                    <LabelText>
                                        Last Name
                                    </LabelText>
                                    <Input name={"lastName"} defaultValue={lastName}
                                           onChange={e => setLastName(e.target.value)}/>
                                </Label>
                            </div>
                            <div className="form-group">
                                <Label>
                                    <LabelText>
                                        Age
                                    </LabelText>
                                    <Input name={"age"} defaultValue={age} onChange={e => setAge(e.target.value)}/>
                                </Label>
                            </div>
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

export {MyDetails}