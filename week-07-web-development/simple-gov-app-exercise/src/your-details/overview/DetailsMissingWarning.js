import {WarningText} from "govuk-react";
import React from "react";

function DetailsMissingWarning(props) {
    const {name} = props;

    return (
        <WarningText>{name} have not been completed yet</WarningText>
    );
}

export default DetailsMissingWarning;