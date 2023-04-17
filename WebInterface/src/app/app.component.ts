import {Component} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import { AppService } from "./app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
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
