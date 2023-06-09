import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FileUploadResponseModel} from "../../assets/file.upload.response.model";

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  constructor(
    private http: HttpClient,
  ) { }

  public uploadFile(file: FormData) {
    return this.http.post<FileUploadResponseModel>('/humans/upload', file);
  }
}
