import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FileUploadResponseModel} from "./file.upload.response.model";

@Injectable({
  providedIn: 'root'
})
export class FilesService {
  constructor(
    private http: HttpClient,
  ) { }

  public uploadFile(file: FormData) {
    return this.http.post<FileUploadResponseModel>('/humans/upload', file);
  }
}
