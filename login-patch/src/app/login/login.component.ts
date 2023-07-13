import { Component } from '@angular/core';
import { APIService } from '../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = "";
  password: string = "";

  constructor(private apiService: APIService, private router: Router) { }

  fazerLogin(): void {
    this.apiService.fazerLogin(this.email, this.password).subscribe(
      response => {
        console.log(response);
        if (response.Success) {
          this.router.navigate(['/home']);
        }
      },
      error => {
        alert("User not found");
      }
    );
  }
}


