import { Component } from '@angular/core';

@Component({
    selector: 'cms-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class CmsNavbarComponent {
    isCollapsed: boolean;
    constructor() {
        this.isCollapsed = true;
    }
}