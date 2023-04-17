import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from "@angular/forms";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
        { path: '', component: AppComponent },
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
