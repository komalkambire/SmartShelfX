import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  // LOGIN API
  login(data: any) {
    return this.http.post(`${this.baseUrl}/login`, data);
  }

  // REGISTER API
  register(body: any) {
    return this.http.post(`${this.baseUrl}/register`, body, {
      responseType: 'text'
    });
  }

  // SAVE TOKEN
  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  // GET TOKEN
  getToken() {
    return localStorage.getItem('token');
  }

  // LOGOUT
  logout() {
    localStorage.removeItem('token');
  }
}
