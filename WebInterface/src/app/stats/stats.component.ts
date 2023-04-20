import {Component, OnInit} from '@angular/core';
import {StatsService} from "./stats.service";
import {HumanModel} from "../../assets/human.model";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  stats!: Map<string, number>;

  constructor(
    private statsService: StatsService,
  ) { }

  ngOnInit(): void {
    this.statsService.getRequestsPerName().subscribe((response => {
      this.stats = response;
    }));
  }
}
