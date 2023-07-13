import { Component } from '@angular/core';
import { APIService } from '../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
    name: string = "";
    password: string = "";
    confirmPassword: String = "";
    email: string = "";
    urlImage: string = "";
    gender: String = "";

    constructor(private apiService: APIService, private router: Router) { }

    create() {
      if(this.email == "") {
        alert("Inform the email");
        return;
      }

      if(this.name == "") {
        alert("Inform the name");
        return;
      }

      if(this.name == "") {
        alert("Inform the url image");
        return;
      }

      if(this.gender == "") {
        alert("Select the genre");
        return;
      }

      if(this.password == "") {
        alert("Inform the password");
        return;
      }

      if(this.confirmPassword == "") {
        alert("Inform the confirm password");
        return;
      }

      if(this.password != this.confirmPassword) {
        alert("The passwords are different");
        return;
      }

      this.apiService.createAccount(this.email, this.password, this.urlImage, this.name, this.gender).subscribe(
        response => {
          console.log(response);
          if (response.Success) {
            this.router.navigate(['/']);
          }else{
            alert(response.Message);
          }
        },
        error => {
          alert("");
        }
      );
    }
}
