import React, {useEffect, useState} from "react";
import axios from 'axios';
import {Button, Fieldset, Input, Label, LabelText} from 'govuk-react';
import {BrandedPage} from "../shared/BrandedPage";

function FathersDetails() {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [age, setAge] = useState("");

    useEffect(() => {
        const getFathersDetails = async () => {
            const res = await axios.get(`http://localhost:3004/father`);

            if (res) {
                const {firstName, lastName, age} = res.data;

                setFirstName(firstName);
                setLastName(lastName);
                setAge(age);
            }
        }

        getFathersDetails();
    }, []);

    const onSubmit = event => {
        event.preventDefault();

        axios.post('http://localhost:3004/father', {
            firstName, lastName, age
        });
    }

    return (
        <BrandedPage>
            <h2>Your Fathers Details</h2>
            <form onSubmit={onSubmit}>
                <Fieldset>
                    <Fieldset.Legend>Please enter his details</Fieldset.Legend>
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
        </BrandedPage>
    );
}

export {FathersDetails}