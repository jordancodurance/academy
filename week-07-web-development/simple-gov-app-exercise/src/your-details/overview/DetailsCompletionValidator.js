export function hasCompletedRequiredDetails(details) {
   return Object.keys(details).every(key => {
      const detail = details[key];
      return Object.keys(detail).length;
   });
}
