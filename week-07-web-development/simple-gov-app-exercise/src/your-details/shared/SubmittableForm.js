import React, {useEffect, useState} from "react";
import {Button, Fieldset} from "govuk-react";
import * as PropTypes from "prop-types";

function SubmittableForm(props) {
    const {onSubmit, heading, initialState, children} = props;
    const [fields, setFields] = useState(initialState);

    const onFormSubmit = event => {
        event.preventDefault();

        onSubmit(fields);
    }

    function updateForm(event) {
        const {name, value} = event.target;
        setFields({
            ...fields,
            [name]: value
        });
    }


    return (
        <form onSubmit={onFormSubmit}>
            <Fieldset>
                <Fieldset.Legend>{heading}</Fieldset.Legend>

                {
                    children({
                        handleFormUpdated: updateForm
                    })
                }

                <div className="form-group">
                    <Button type="submit">Submit</Button>
                </div>
            </Fieldset>
        </form>
    );
}

export {SubmittableForm}