import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class APIService {
  private apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  fazerLogin(email: string, password: string): Observable<any> {
    const payload = {
      email: email,
      password: password
    };
    return this.http.post(`${this.apiUrl}user/login`, payload);
  }
}
