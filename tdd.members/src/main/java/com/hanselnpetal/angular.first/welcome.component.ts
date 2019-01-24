import { Component, OnInit, OnDestroy } from '@angular/core';
import { PoolingService } from '../pooling.service';

declare var require: NodeRequire;

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnDestroy {

  message: string = "hello";
  policies = [];
  songs = [];


  constructor(private poolingService:PoolingService) { 
    this.poolingService.subscribe(msg => { this.message = msg; });
    this.poolingService.start();
  }

  ngOnDestroy() {
  }

}
