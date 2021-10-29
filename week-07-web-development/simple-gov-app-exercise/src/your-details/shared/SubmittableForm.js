import React, {useEffect, useState} from "react";
import {Button, Fieldset, LoadingBox} from "govuk-react";

function SubmittableForm(props) {
    const {onSubmit, loadInitialState, children} = props;
    const [fields, setFields] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        setInitialFields();
    }, []);

    const setInitialFields = () => {
        loadInitialState().then((response) => {
            setFields(response);
            setIsLoading(false);
        });
    };

    const onFormSubmit = event => {
        event.preventDefault();

        onSubmit(fields);
    }

    const updateForm = event => {
        const {name, value} = event.target;
        setFields({
            ...fields,
            [name]: value
        });
    };

    return (
        <LoadingBox loading={isLoading}>
            <form onSubmit={onFormSubmit}>
                <Fieldset>
                    {
                        children({
                            fields,
                            handleFormUpdated: updateForm
                        })
                    }

                    <div className="form-group">
                        <Button type="submit">Submit</Button>
                    </div>
                </Fieldset>
            </form>
        </LoadingBox>
    );
}

export {SubmittableForm}