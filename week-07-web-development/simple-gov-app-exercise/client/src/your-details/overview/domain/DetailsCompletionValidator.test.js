import {hasAllCompletedRequiredDetails} from "./DetailsCompletionValidator";

describe('on determining if details are complete', () => {
    const subject = {
        firstName: "Subject First name",
        lastName: "Subject Last Name",
        age: "50"
    };
    const father = {
        firstName: "Father First name",
        lastName: "Father Last Name",
        age: "82"
    };
    const mother = {
        firstName: "Mother First name",
        lastName: "Mother Last Name",
        maidenName: "Mother Maiden Name",
        age: "75"
    };

    it.each([
        {subject: {}, father: {}, mother: {}},
        {subject: subject, father: {}, mother: {}},
        {subject: subject, father: father, mother: {}},
        {subject: subject, father: {}, mother: mother},
        {subject: {}, father: father, mother: {}},
        {subject: subject, father: father, mother: {}},
        {subject: {}, father: father, mother: mother},
        {subject: {}, father: {}, mother: mother},
        {subject: subject, father: {}, mother: mother},
        {subject: {}, father: father, mother: mother}
    ])
    ('should not be complete with any required sections incomplete', (details) => {
        expect(hasAllCompletedRequiredDetails(details)).toBe(false);
    });

    it('should be complete with all required sections complete', () => {
        const details = {subject: subject, father: father, mother: mother};

        expect(hasAllCompletedRequiredDetails(details)).toBe(true);
    });
});