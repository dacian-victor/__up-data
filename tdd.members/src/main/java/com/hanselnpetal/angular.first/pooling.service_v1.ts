import { Injectable, OnDestroy } from '@angular/core';
import { TestService } from './test.service';

@Injectable({
  providedIn: 'root',
})
export class PoolingService implements OnDestroy {

    interval: NodeJS.Timer;

    constructor(private messageService:TestService) { 

    }

    start(): void {
        this.interval = setInterval(()=> {
            let curentDate = new Date()
            this.messageService.sendMessage(curentDate.toLocaleTimeString());
        }, 1000)
    }
    
    ngOnDestroy(): void {
        if (this.interval) {
            clearInterval(this.interval);
        }
    }
}