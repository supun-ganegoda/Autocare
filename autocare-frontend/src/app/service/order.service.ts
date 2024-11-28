import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Order} from '../model/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  baseUrl: string = "http://localhost:9000/api"
  constructor(private httpClient: HttpClient) { }

  placeOrder(order: Order): Observable<string>{
    return this.httpClient.post<string>(this.baseUrl+"/order",order).pipe(catchError(
      (err)=>{
        console.error(err);
        return throwError(()=>err)
      }
    ))
  }
}
