import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl: string = "http://localhost:9000/api"
  constructor(private httpClient:HttpClient) { }

  getProducts(): Observable<Array<Product>>{
    return this.httpClient.get<Array<Product>>(this.baseUrl+"/product").pipe(catchError((err)=>{
      console.error(err);
      return throwError(()=>err);
    }))
  }

  createProduct(product: Product):Observable<Product>{
    return this.httpClient.post<Product>(this.baseUrl+"/product",product).pipe(catchError(
      (err)=>{
        console.error(err);
        return throwError(()=>err);
      }
    ))
  }
}
