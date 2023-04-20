import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HumanModel} from "../../assets/human.model";

@Injectable({
  providedIn: 'root'
})
export class FilteringService {
  constructor(
    private http: HttpClient,
  ) { }

  applyFilter(filter: string) {
    return this.http.get<HumanModel[]>("/humans/filter?filter=" + filter);
  }
  applyFilterValue(filter: string, value: string) {
    return this.http.get<HumanModel[]>("/humans/filter?filter=" + filter + "&value=" + value);
  }
}
