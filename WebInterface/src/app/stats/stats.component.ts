import {Component, OnInit} from '@angular/core';
import {StatsService} from "./stats.service";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  stats!: Map<string, number>;
  p!: number;

  protected readonly Object = Object;

  constructor(
    private statsService: StatsService,
  ) { }
  // TODO: bad practise, should cache

  ngOnInit(): void {
    this.p = 1;
    this.statsService.getRequestsPerName().subscribe((response => {
      this.stats = response;
    }))
  }
}
