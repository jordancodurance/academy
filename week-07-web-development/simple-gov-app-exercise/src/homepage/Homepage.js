import React from "react";
import {InsetText, LeadParagraph} from 'govuk-react'
import {Link} from "react-router-dom";
import {BrandedPage} from "../shared/BrandedPage";
import {HomepageLink} from "./HomepageLink";

function Homepage() {
    return (
        <BrandedPage>
            <h2>Welcome to your local government service</h2>

            <LeadParagraph>Please complete every selection specified below and submit when ready</LeadParagraph>
            <InsetText>Processing of your details can take upto 8 weeks</InsetText>

            <p>Once you've completed each section below you will be given the chance to review your responses before
                submitting them</p>

            <p>You will be asked basic information about yourself and your parents. You can review and amend your
                answers before submitting them.</p>

            <HomepageLink header="Your details:" name="Your details page" route="/my-details"/>
            <HomepageLink header="Your fathers details:" name="Your fathers details page" route="/fathers-details"/>
            <HomepageLink header="Your mothers details:" name="Your mothers details page" route="/mothers-details"/>
        </BrandedPage>
    );
}

export {Homepage};
