import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  curSite: string;

  constructor(
    private router: Router,
  ) {
    this.curSite = 'home';
  }

  ngOnInit() {
    //현재 URL 구하기
    //F5를 눌러 새로고침 시 현재 컴포넌트가 초기화 된 후 한번 실행
    const currentUrl = this.router.url.split('/');
    console.log("this.router.url = " + this.router.url);
    if(currentUrl[1]) {
      this.curSite = currentUrl[1];
    }

    //routerLink 선택하여 이동할 때 마다 실행
    this.router.events.subscribe((event) => {
      if(event instanceof NavigationEnd) {
        this.curSite = event.url.split('/')[1];
        console.log("event.url = " + event.url);
      }
    });
  }
}
