import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { TestService } from '../test.service';
import { PoolingService } from '../pooling.service';

declare var require: NodeRequire;

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnDestroy {

  message: string = "hello";
  subscription: Subscription;
  policies = [];
  songs = [];

  constructor(
    private messageService:TestService,
    private poolingService:PoolingService
    ) { 

    this.subscription = this.messageService.getMessage().subscribe(msg => { this.message = msg; });
    this.poolingService.start();

  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
