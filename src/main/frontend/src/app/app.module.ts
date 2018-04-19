import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { CmsLayoutRoutingModule } from './cms/layouts/layout-routing.module';
import { HomeLayoutRoutingModule } from './home/layouts/layout-routing.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { CmsLoginComponent } from './cms/login/login.component';
import { CmsHeaderComponent } from './cms/layouts/header/header.component';

import { HomeLoginComponent } from './home/login/login.component';
import { HomeHeaderComponent } from './home/layouts/header/header.component';


@NgModule({
  declarations: [
    AppComponent,
    CmsLoginComponent,
    CmsHeaderComponent,
    HomeLoginComponent,
    HomeHeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    CmsLayoutRoutingModule,
    HomeLayoutRoutingModule,
    NgbModule.forRoot()
  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy} 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
