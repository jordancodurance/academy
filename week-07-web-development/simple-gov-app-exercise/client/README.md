# Local government service
You've been asked to take over a project for a local government organisation from another consultancy. As with all government services it must be styled to the [Government Design System standards](https://insidegovuk.blog.gov.uk/gov-uk-standards-and-guidelines/)

Luckily the consultancy is using an [open source library](https://github.com/govuk-react/govuk-react) that uses React components that comply with these standards so we don't have to create these components from scratch ourselves

A useful resource to view these components can be [found here using Storybook](https://govuk-react.github.io/govuk-react/?path=/story/welcome--page) so we can see what the components look like and decide if they would be useful to us 

## The task

The project is to gather details from users about themselves and their parents for an upcoming alpha release. They can review their answers and if they have filled everything in, submit them.

If they haven't completed all sections they should see an appropriate error messsage

```
src/
├─ pages/
│  ├─ home-page
│  ├─ my-details-page
│  ├─ fathers-details-page
│  ├─ mothers-details-page
│  ├─ overview-page
│  ├─ successful-submission-page

```

The overview page and successful submission page don't exist yet.

After reviewing the project you've discovered that the other consultancy didn't write tests and have used static, single use components to render the respective details pages.

The business have also asked you to implement validation on all the form inputs:

- If a field is empty it should show a message prompting the user to fill it in
- If a field that accepts a name has any [non simple latin charset](https://en.wikipedia.org/wiki/ISO_basic_Latin_alphabet) then it should show an appropriate error message
- If a field that acccepts an age has any non-numeric input then it should show an appropriate error message

When a user successfully submits a form they should be redirected back to the homepage


## Working on this project
This project relies upon a mock API called [json-server](https://github.com/typicode/json-server) which provides us with a schemaless API that we can save and retrieve data from like a conventional API. When we post data to it we can see the result in `db.json` file in the package root

We can run it using the command: `json-server --watch db.json --port 3004`

To help us interrogate this API there is a postman collection in the `/assets` folder of this project that will help us post and get data from it without having to use the UI - this will help us check our validation rules are working when we run the mock API

## Install dependencies
`npm install` or `npm i`

## Start local API
`$ json-server --watch db.json --port 3004`

## Start the app locally
`npm start`

## Run tests
`npm test` or `npm t`


