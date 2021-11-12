Cypress.Commands.add('typeInputValue', (label, value) => {
    cy.contains(label).parent().within(() => {
        cy.get('input').type(value);
    });
});

Cypress.Commands.add('clickButton', (text) => {
    cy.contains(text).click();
});

