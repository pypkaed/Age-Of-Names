import { Component } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { NamesService } from "./names.service";
import { HumanModel } from "../../assets/human.model";
import {HttpErrorResponse} from "@angular/common/http";
import {ERRORS} from "../../assets/errors";

@Component({
  selector: 'app-names',
  templateUrl: './names.component.html',
  styleUrls: ['./names.component.css']
})
export class NamesComponent {
  human!: HumanModel;
  error!: string | null;

  nameForm = this.formBuilder.group({
    name: '',
  })

  constructor(
    private namesService: NamesService,
    private formBuilder: FormBuilder,
  ) { }

  onSubmit() {
    this.namesService.getName(<string> this.nameForm.value.name)
      .subscribe((response: HumanModel) => {
        this.human = response;
        this.error = null;
      },
        (error: HttpErrorResponse) => {
          if (error.status === ERRORS.BAD_REQUEST) {
            this.error = error.error;
          }
          else {
            console.error(error);
          }
        });

    this.nameForm.reset();
  }

}
