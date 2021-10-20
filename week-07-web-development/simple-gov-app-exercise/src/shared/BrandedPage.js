import React from "react";
import {Footer, Page} from "govuk-react";

function BrandedPage(props) {
    const {children} = props;

    return (
        <>
            <Page>
                <div className="wrapper">
                    {children}
                </div>
            </Page>

            <Footer/>
        </>
    );
}

export {BrandedPage}