import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {HumanModel} from "../../assets/human.model";

@Injectable({
  providedIn: 'root'
})
export class StatsService {
  constructor(
    private http: HttpClient,
  ) { }

  getRequestsPerName() {
    return this.http.get<Map<string, number>>("/humans/stats");
  }
}
