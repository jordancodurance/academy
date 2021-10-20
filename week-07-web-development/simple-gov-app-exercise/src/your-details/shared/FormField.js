import {Input, Label, LabelText} from "govuk-react";
import React from "react";

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

export {FormField}