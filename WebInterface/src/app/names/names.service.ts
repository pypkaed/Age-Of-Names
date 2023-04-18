import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {HumanModel} from "../human.model";

@Injectable({
  providedIn: 'root'
})
export class NamesService {
  constructor(
    private http: HttpClient,
  ) { }

  public getName(name: string) {
    return this.http.get<HumanModel>('/humans?name=' + name);
  }
}
