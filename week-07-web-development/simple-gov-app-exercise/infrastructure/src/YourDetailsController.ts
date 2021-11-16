import {Context} from 'aws-lambda';
import * as AWS from 'aws-sdk';

const database = new AWS.DynamoDB.DocumentClient();

class Details {
    firstName: string;
    lastName: string;
    age: number;
    maidenName?: string;
}

export class YourDetailsController {

    async update(event: any, context: Context) {
        const id = event.path.replace('/', '');
        const newDetails = JSON.parse(event.body);
        const persistedDetails = {
            id,
            ...newDetails
        };
        await database
            .put({
                TableName: process.env.DYNAMODB_TABLE!!,
                Item: persistedDetails,
            })
            .promise();

        return {
            statusCode: 201
        };
    }

    async get(event: any, context: Context) {
        const id = event.path.replace('/', '');
        const result = await database
            .get({
                TableName: process.env.DYNAMODB_TABLE!!,
                Key: {id},
            })
            .promise();

        const details = result.Item as Details;

        return {
            statusCode: 200,
            body: JSON.stringify(details)
        }
    }
}