import React from "react";
import SubjectDetailsOverview from "./subject-details-overview/SubjectDetailsOverview";
import FatherDetailsOverview from "./father-details-overview/FatherDetailsOverview";
import MotherDetailsOverview from "./mother-details-overview/MotherDetailsOverview";

function DetailsOverview(props) {
    const {details} = props;

    return (
        <>
            <SubjectDetailsOverview subject={details.subject}/>

            <FatherDetailsOverview father={details.father}/>

            <MotherDetailsOverview mother={details.mother}/>
        </>
    );
}

export default DetailsOverview;