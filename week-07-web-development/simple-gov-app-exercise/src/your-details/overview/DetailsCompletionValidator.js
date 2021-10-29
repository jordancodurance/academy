export const hasAllCompletedRequiredDetails = details => Object.keys(details).every(key => {
   const detail = details[key];
   return isCompletedPerson(detail);
});

export const isCompletedPerson = detail => Object.keys(detail).length;
