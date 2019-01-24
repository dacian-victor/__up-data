import { Injectable, OnDestroy } from '@angular/core';
import { TestService } from './test.service';
import { Observable, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PoolingService implements OnDestroy {

    private interval: NodeJS.Timer;
    private subscriptions = new Subscription()

    constructor(private messageService:TestService) { 

    }

    start(): void {
        this.interval = setInterval(()=> {
            let curentDate = new Date()
            this.messageService.sendMessage(curentDate.toLocaleTimeString());
        }, 1000)
    }

    subscribe(arg0: (msg: any) => void): void {
        let subscription = this.messageService.getMessage().subscribe(arg0);
        this.subscriptions.add(subscription);
    }

    ngOnDestroy(): void {
        if (this.interval) {
            clearInterval(this.interval);
        }
        this.subscriptions.unsubscribe();
    }
}