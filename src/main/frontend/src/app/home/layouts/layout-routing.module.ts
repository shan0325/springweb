import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeLoginComponent } from '../login/login.component';

const LAYOUT_ROUTES: Routes = [
    { path: 'home/login', component: HomeLoginComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(LAYOUT_ROUTES, { useHash: true }) ],
    exports: [ RouterModule ]
})
export class HomeLayoutRoutingModule {}