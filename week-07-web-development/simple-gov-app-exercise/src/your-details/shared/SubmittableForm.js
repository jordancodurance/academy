import {Button, Fieldset} from "govuk-react";
import * as PropTypes from "prop-types";

function SubmittableForm(props) {
    const {onSubmit, heading, children} = props;

    const onFormSubmit = event => {
        event.preventDefault();

        onSubmit();
    }

    return (
        <form onSubmit={onFormSubmit}>
            <Fieldset>
                <Fieldset.Legend>{heading}</Fieldset.Legend>

                {children}

                <div className="form-group">
                    <Button type="submit">Submit</Button>
                </div>
            </Fieldset>
        </form>
    );
}

SubmittableForm.propTypes = {
    onSubmit: PropTypes.func,
    heading: PropTypes.string,
    children: PropTypes.node
};

export {SubmittableForm}