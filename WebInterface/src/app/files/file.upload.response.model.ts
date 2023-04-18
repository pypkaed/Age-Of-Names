import {HumanModel} from "../human.model";

export class FileUploadResponseModel {
  "humanDtos": HumanModel[];
  "errors": string[];
}
