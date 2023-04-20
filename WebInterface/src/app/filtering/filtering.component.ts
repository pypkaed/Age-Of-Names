import { Component } from '@angular/core';
import {FilteringService} from "./filtering.service";
import {HumanModel} from "../../assets/human.model";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-filtering',
  templateUrl: './filtering.component.html',
  styleUrls: ['./filtering.component.css']
})
export class FilteringComponent {
  p: number = 1;
  selectFilter!: string;
  humans!: HumanModel[];
  filterValueForm = this.formBuilder.group({
    value: '',
  })

  constructor(
    private filteringService: FilteringService,
    private formBuilder: FormBuilder,
  ) { }


  onSubmit() {
    if (this.filterValueForm.value.value) {
      this.filteringService.applyFilterValue(
        this.selectFilter,
        this.filterValueForm.value.value)
        .subscribe((response => {
          this.humans = response;
        }),
          (error) => {
          console.log(error);
          });
    }
    else {
      this.filteringService.applyFilter(this.selectFilter)
        .subscribe((response => {
            this.humans = response;
          }),
          (error) => {
            console.error(error);
          });
    }
  }
}
