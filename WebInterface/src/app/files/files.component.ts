import { Component } from '@angular/core';
import { FilesService } from "./files.service";
import { FileUploadResponseModel } from "./file.upload.response.model";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent {
  uploadResponse!: FileUploadResponseModel;
  file!: File;
  uploadFormData!: FormData;

  constructor(
    private filesService: FilesService,
  ) { }


  onFileSelected(event: any) {
    this.file = <File> event.target.files[0];
  }

  onSubmit() {
    this.uploadFormData = new FormData();
    this.uploadFormData.append('file', this.file)
    this.filesService.uploadFile(this.uploadFormData)
        .subscribe((response: FileUploadResponseModel) => {
          this.uploadResponse = response;
        },
          (error: HttpErrorResponse) => {
          if (error.status === 400) {
            this.uploadResponse = error.error;
          }
          else {
            console.error(error);
          }
          })
  }
}
