describe('on adding complete personal details', () => {
    it('should navigate through the forms to capture a persons details', () => {
        cy.intercept('GET', 'http://localhost:3004/subject').as('getSavedSubject');
        cy.intercept('GET', 'http://localhost:3004/father').as('getSavedFather');
        cy.intercept('GET', 'http://localhost:3004/mother').as('getSavedMother');

        cy.visit('/');
        cy.clickButton('Your details page');

        submitPersonalDetails();
        submitFathersDetails();
        submitMothersDetails();
        completeMyDetails();

        cy.url().should('include', '/your-details/successful-submission');
    });


    const submitPersonalDetails = () => {
        cy.wait('@getSavedSubject');

        cy.typeInputValue('First Name', 'My First Name');
        cy.typeInputValue('Last Name', 'My First Name');
        cy.typeInputValue('Age', '20');

        cy.intercept('POST', 'http://localhost:3004/subject').as('saveSubject');
        cy.clickButton('Submit');
        cy.wait('@saveSubject');
    };

    const submitFathersDetails = () => {
        cy.wait('@getSavedFather');

        cy.typeInputValue('First Name', 'Father First Name');
        cy.typeInputValue('Last Name', 'Father Last Name');
        cy.typeInputValue('Age', '63');

        cy.intercept('POST', 'http://localhost:3004/father').as('saveFather');
        cy.clickButton('Submit');
        cy.wait('@saveFather');
    };

    const submitMothersDetails = () => {
        cy.wait('@getSavedMother');

        cy.typeInputValue('First Name', 'Mother First Name');
        cy.typeInputValue('Last Name', 'Mother Last Name');
        cy.typeInputValue('Maiden Name', 'Mother Maiden Name');
        cy.typeInputValue('Age', '58');

        cy.intercept('POST', 'http://localhost:3004/mother').as('saveMother');
        cy.clickButton('Submit');
        cy.wait('@saveMother');
    };

    const completeMyDetails = () => {
        cy.wait(['@getSavedSubject', '@getSavedFather', '@getSavedMother'])
        cy.intercept('POST', 'http://localhost:3004/your-details/complete', {data: {result: "success"}});

        cy.clickButton('Submit Your Details');
    };
});