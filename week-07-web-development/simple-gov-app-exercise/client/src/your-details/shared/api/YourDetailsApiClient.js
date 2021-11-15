import axios from "axios";

export const getSubject = async () => await getDetails("subject")

export const updateSubject = (details) => updateDetails("subject", details)

export const getFather = async () => await getDetails("father")

export const updateFather = (details) => updateDetails("father", details)

export const getMother = async () => await getDetails("mother")

export const updateMother = (details) => updateDetails("mother", details)

export const completeYourDetails = () => axios.post('http://localhost:3004/your-details/complete')

async function getDetails(endpoint) {
    const result = await axios.get(`http://localhost:3004/${endpoint}`);

    if (!result) return {};

    return result.data;
}

const updateDetails = (endpoint, details) => axios.post(`http://localhost:3004/${endpoint}`, details);
