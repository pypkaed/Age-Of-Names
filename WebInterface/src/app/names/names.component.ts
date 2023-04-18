import { Component } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { NamesService } from "./names.service";
import { HumanModel } from "../../assets/human.model";

@Component({
  selector: 'app-names',
  templateUrl: './names.component.html',
  styleUrls: ['./names.component.css']
})
export class NamesComponent {
  human!: HumanModel;

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
      });

    this.nameForm.reset();
  }

}
