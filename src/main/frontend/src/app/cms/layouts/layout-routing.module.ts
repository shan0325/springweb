import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CmsLoginComponent } from '../login/login.component';

const LAYOUT_ROUTES: Routes = [
    { path: 'cms', component: CmsLoginComponent},
    { path: 'cms/login', component: CmsLoginComponent},
];

@NgModule({
    imports: [ RouterModule.forRoot(LAYOUT_ROUTES, { useHash: true }) ],
    exports: [ RouterModule ]
})
export class CmsLayoutRoutingModule {}