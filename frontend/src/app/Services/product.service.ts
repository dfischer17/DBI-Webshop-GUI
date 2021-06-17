import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../Entities/product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
     this.usersUrl = 'http://localhost:4000/products';
   }

   public findAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.usersUrl);
  }

  public save(product: Product) {
    return this.http.post<Product>(this.usersUrl, product);
  }

  public delete(id: number) {
    return this.http.delete(`${this.usersUrl}/${id}`, { responseType: 'text' }).subscribe();
  }

  public update(product: Product) {
    return this.http.put<Product>(`${this.usersUrl}/${product.id}`, product);
  }

}
