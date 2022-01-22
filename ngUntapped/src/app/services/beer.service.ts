import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BeerService {

    private baseUrl = 'http://localhost:8083/';
    private url = 'api/beers';


  constructor(

    private http: HttpClient

  ) { }
  
}
