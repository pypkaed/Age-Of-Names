import { NgModule } from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { NgxPaginationModule } from 'ngx-pagination';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { NamesComponent } from './names/names.component';
import { UploadComponent } from './upload/upload.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { StatsComponent } from './stats/stats.component';
import { FilteringComponent } from './filtering/filtering.component';

@NgModule({
  declarations: [
    AppComponent,
    NamesComponent,
    UploadComponent,
    TopBarComponent,
    WelcomePageComponent,
    StatsComponent,
    FilteringComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    RouterModule.forRoot([
      {path: '', component: WelcomePageComponent},
      {path: 'search', component: NamesComponent},
      {path: 'upload', component: UploadComponent},
      {path: 'filtering', component: FilteringComponent},
      {path: 'stats', component: StatsComponent},
    ]),
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
