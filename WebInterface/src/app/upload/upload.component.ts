import { Component } from '@angular/core';
import { UploadService } from "./upload.service";
import { FileUploadResponseModel } from "../../assets/file.upload.response.model";
import { HttpErrorResponse } from "@angular/common/http";
import { ERRORS } from "../../assets/errors";

@Component({
  selector: 'app-files',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  uploadResponse!: FileUploadResponseModel;
  file!: File;
  uploadFormData!: FormData;
  // errorsLog!: any;

  constructor(
    private filesService: UploadService,
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
          if (error.status === ERRORS.UNPROCESSABLE_ENTITY) {
            this.uploadResponse = error.error;
          }
          else {
            console.error(error);
          }
        })
  }

  toggleLog() {
    this.uploadResponse = {
      ...this.uploadResponse,
      expanded: !this.uploadResponse.expanded
    }
  }
}
