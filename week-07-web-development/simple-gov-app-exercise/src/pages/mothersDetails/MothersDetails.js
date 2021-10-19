import React, { useState, useEffect } from "react";
import axios from 'axios';
import { Input, Fieldset, Label, LabelText } from 'govuk-react';
import { Page, Footer } from 'govuk-react';

function MothersDetails() {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [age, setAge] = useState("");
    const [maidenName, setMaidenName] = useState("");

    useEffect(() => {
        const getMothersDetails = async () => {
            const res = await axios.get(`http://localhost:3004/mother`);

            if (res) {
                const { firstName, lastName, age, maidenName } = res.data;

                setFirstName(firstName);
                setLastName(lastName);
                setAge(age);
                setMaidenName(maidenName)
            }
        }

        getMothersDetails();
    }, []);

    const onSubmit = event => {
        event.preventDefault();

        axios.post('http://localhost:3004/mother', {
            firstName, lastName, age, maidenName
        });
    }

    return (
        <>
            <Page>
                <div className="wrapper">
                    <h2>Your Mothers Details</h2>
                        <form onSubmit={onSubmit}>
                            <Fieldset>
                                <Fieldset.Legend>Please enter her details</Fieldset.Legend>
                                <div className="form-group">
                                    <Label>
                                        <LabelText>
                                            First Name
                                        </LabelText>
                                        <Input name={"firstName"} defaultValue={firstName} onChange={e => setFirstName(e.target.value)} />
                                    </Label>
                                </div>
                                <div className="form-group">
                                    <Label>
                                        <LabelText>
                                            Last Name
                                        </LabelText>
                                        <Input name={"lastName"} defaultValue={lastName} onChange={e => setLastName(e.target.value)} />
                                    </Label>
                                </div>
                                <div className="form-group">
                                    <Label>
                                        <LabelText>
                                            Maiden Name
                                        </LabelText>
                                        <Input name={"lastName"} defaultValue={maidenName} onChange={e => setMaidenName(e.target.value)} />
                                    </Label>
                                </div>
                                <div className="form-group">
                                    <Label>
                                        <LabelText>
                                            Age
                                        </LabelText>
                                        <Input name={"age"} defaultValue={age} onChange={e => setAge(e.target.value)} />
                                    </Label>
                                </div>
                                <div className="form-group">
                                    <Input data-testid="submit-button" type="submit" />
                                </div>
                            </Fieldset>
                        </form>
                </div>
            </Page>
            <Footer />
        </>
    )
}

export { MothersDetails }