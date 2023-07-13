import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit  {
  nameUser: string = "";
  urlImage: string = "";
  userList: any[] = [];
  passedData: any;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.fetchUserList();
  }

  fetchUserList() {
    this.http.get<any[]>('http://localhost:8080/user/all').subscribe(
      (response) => {
        const email = localStorage.getItem("email");
        this.userList = response.filter(user => user.email !== email);
        const user = response.find(user => user.email == email);
        this.nameUser = user.name;
        this.urlImage = user.urlImage;

        localStorage.setItem("name", this.nameUser);
        localStorage.setItem("url", this.urlImage);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
