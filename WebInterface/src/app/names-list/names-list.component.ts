import {Component, OnInit} from '@angular/core';
import {HumanModel} from "../../assets/human.model";
import {NamesService} from "../names/names.service";

@Component({
  selector: 'app-names-list',
  templateUrl: './names-list.component.html',
  styleUrls: ['./names-list.component.css']
})
export class NamesListComponent implements OnInit {
  humans!: HumanModel[];
  p!: string | number;

  constructor(
    private namesService: NamesService,
  ) { }

  // TODO: bad practise, should cache
  ngOnInit(): void {
    this.p = 1;
    this.namesService.getAllNames().subscribe((response: HumanModel[]) => {
      this.humans = response;
    });
  }
}
