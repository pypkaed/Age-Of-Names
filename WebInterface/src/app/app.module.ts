import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RouterModule } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { NamesComponent } from './names/names.component';
import { FilesComponent } from './files/files.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { NamesListComponent } from './names-list/names-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NamesComponent,
    FilesComponent,
    TopBarComponent,
    NamesListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: 'search', component: NamesComponent},
      {path: 'upload', component: FilesComponent},
      {path: 'list', component: NamesListComponent},
    ]),
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
