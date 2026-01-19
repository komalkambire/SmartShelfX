import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../models/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = 'http://localhost:8080/api/transactions';

  constructor(private http: HttpClient) {}

  recordTransaction(txn: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(`${this.baseUrl}`, txn);
  }

  getAllTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.baseUrl);
  }
}
