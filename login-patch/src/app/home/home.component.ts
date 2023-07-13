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
  filteredUserList: any[] = [];
  passedData: any;
  searchTerm: string = "";

  constructor(private http: HttpClient) {
    this.searchTerm = '';
  }

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
        this.filteredUserList = response.filter(user => user.email !== email);

        localStorage.setItem("name", this.nameUser);
        localStorage.setItem("url", this.urlImage);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  filterUserList() {

    if(this.searchTerm.trim() == "") {
      this.userList = this.filteredUserList;
      return;
    }

    this.userList = this.filteredUserList.filter(user =>
      user.name.toLowerCase().includes(this.searchTerm.toLowerCase() || user.email.toLowerCase().includes(this.searchTerm.toLowerCase()))
    );
  }
}
