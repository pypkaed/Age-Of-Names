import { Component } from '@angular/core';
import { AppService } from "./app.service";
import { FormBuilder } from "@angular/forms";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WebInterface';
  nameForm = this.formBuilder.group({
    name: '',
  })
  constructor(
    private appService: AppService,
    private formBuilder: FormBuilder,
  ) { }

  onSubmit() {
    this.appService.getName(<string>this.nameForm.value);
    this.nameForm.reset();
  }
}
