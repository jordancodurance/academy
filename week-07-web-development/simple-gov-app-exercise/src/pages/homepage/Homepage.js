import React from "react";
import { InsetText, LeadParagraph } from 'govuk-react'
import { Link } from "react-router-dom";
import { Page, Footer } from 'govuk-react';

function Homepage() {
  return (
      <>
          <Page>
            <div className="wrapper">
                  <h2>Welcome to your local government service</h2>
                  <LeadParagraph>Please complete every selection specified below and submit when ready</LeadParagraph>
                  <InsetText>Processing of your details can take upto 8 weeks</InsetText>
                  <p>Once you've completed each section below you will be given the chance to review your responses before submitting them</p>
                  <p>You will be asked basic information about yourself and your parents. You can review and amend your answers before submitting them.</p>
                  <div>
                    <h3>Your details:</h3>
                    <Link data-testid="my-details-link" to="/my-details">Your details page</Link>
                  </div>
                  <div>
                    <h3>Your fathers details:</h3>
                    <Link data-testid="fathers-details-link" to="/fathers-details">Your fathers details page</Link>
                  </div>
                  <div>
                    <h3>Your mothers details:</h3>
                    <Link data-testid="fathers-details-link" to="/mothers-details">Your mothers details page</Link>
                  </div>
                </div>
            </Page>
          <Footer />
      </>
  );
}

export { Homepage };
