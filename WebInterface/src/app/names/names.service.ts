import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {HumanModel} from "../../assets/human.model";

@Injectable({
  providedIn: 'root'
})
export class NamesService {
  constructor(
    private http: HttpClient,
  ) { }

  public getName(name: string) {
    return this.http.get<HumanModel>('/humans/search?name=' + name);
  }

  public getAllNames() {
    return this.http.get<HumanModel[]>('/humans/all')
  }
}
