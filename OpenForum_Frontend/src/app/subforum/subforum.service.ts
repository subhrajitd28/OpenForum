import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SubforumModel } from './subforum-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubforumService {
  constructor(private http: HttpClient) { }

  getAllSubforums(): Observable<Array<SubforumModel>> {
    return this.http.get<Array<SubforumModel>>('http://localhost:8080/api/subforum');
  }

  createSubforum(subforumModel: SubforumModel): Observable<SubforumModel> {
    return this.http.post<SubforumModel>('http://localhost:8080/api/subforum',
      subforumModel);
  }
}
