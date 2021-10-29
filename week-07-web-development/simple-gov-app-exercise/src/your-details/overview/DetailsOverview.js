import React from "react";
import {isCompletedPerson} from "./DetailsCompletionValidator";
import DetailsMissingWarning from "./DetailsMissingWarning";
import {ListItem, UnorderedList} from "govuk-react";

function DetailsOverview(props) {
    const {details} = props;

    const isCompleteDetail = details => isCompletedPerson(details)

    return (
        <>
            {!isCompleteDetail(details.subject)
                ? <DetailsMissingWarning name="Your Details"/>
                : <>
                    <h4>Your Details:</h4>
                    <UnorderedList>
                        <ListItem>Name - {details.subject.firstName} {details.subject.lastName}</ListItem>
                        <ListItem>Age - {details.subject.age}</ListItem>
                    </UnorderedList>
                </>
            }

            {!isCompleteDetail(details.father)
                ? <DetailsMissingWarning name="Your Fathers Details"/>
                : <>
                    <h4>Your Fathers Details:</h4>
                    <UnorderedList>
                        <ListItem>Name - {details.father.firstName} {details.father.lastName}</ListItem>
                        <ListItem>Age - {details.father.age}</ListItem>
                    </UnorderedList>
                </>
            }

            {!isCompleteDetail(details.mother)
                ? <DetailsMissingWarning name="Your Mothers Details"/>
                : <>
                    <h4>Your Mothers Details:</h4>
                    <UnorderedList>
                        <ListItem>Name - {details.mother.firstName} {details.mother.maidenName} {details.mother.lastName}</ListItem>
                        <ListItem>Age - {details.mother.age}</ListItem>
                    </UnorderedList>
                </>
            }
        </>
    );
}

export default DetailsOverview;