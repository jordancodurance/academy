import {Context, Handler} from 'aws-lambda';
import {YourDetailsController} from "./YourDetailsController";

const yourDetailsController = new YourDetailsController();

export const update: Handler = (event: any, context: Context) => {
    return yourDetailsController.update(event, context);
};

export const get: Handler = (event: any, context: Context) => {
  return yourDetailsController.get(event, context);
};