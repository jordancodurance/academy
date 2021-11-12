describe('on adding complete personal details', () => {
    it('should navigate through the forms to capture a persons details', () => {
        cy.visit('/my-details');

        cy.intercept('GET', 'http://localhost:3004/subject').as('getSavedSubject');
        cy.wait('@getSavedSubject');

        cy.typeInputValue('Age', '17');

        cy.contains('Age').parent().within(() => {
            cy.contains('You can\'t be under 18 to use this application');
        });
    });
});