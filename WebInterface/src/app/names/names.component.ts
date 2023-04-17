import {Component, OnDestroy} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import { AppService } from "../app.service";

@Component({
  selector: 'app-names',
  templateUrl: './names.component.html',
  styleUrls: ['./names.component.css']
})
export class NamesComponent {
  nameForm = this.formBuilder.group({
    name: '',
  })

  constructor(
    private appService: AppService,
    private formBuilder: FormBuilder,
  ) { }

  onSubmit() {
    this.appService.getName(<string> this.nameForm.value)
    this.nameForm.reset();
  }

}
