import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../Entities/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl: string;
  
  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:4000/users';
   }

   public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }

  public delete(id: number) {
    return this.http.delete(`${this.usersUrl}/${id}`, { responseType: 'text' }).subscribe();
  }

  public update(id: number, user: User) {
    return null;
  }
}
