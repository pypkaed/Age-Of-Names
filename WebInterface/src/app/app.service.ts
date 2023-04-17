import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  rootURL = '/humans';

  constructor(
    private http: HttpClient,
  ) { }

  getName(name: string) {
    return this.http.get(this.rootURL)
  }
}
