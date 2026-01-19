import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  imports: [FormsModule, CommonModule, RouterModule]
})
export class LoginComponent {

  loginData = {
    email: '',
    password: ''
  };

  constructor(private auth: AuthService, private router: Router) {}

  login() {
  this.auth.login(this.loginData).subscribe({
    next: (res: any) => {
      if (res.token) {
        this.auth.saveToken(res.token);
        alert("Login successful!");
        this.router.navigate(['/dashboard']);
      } else {
        alert("Invalid Credentials");
      }
    },
    error: () => alert("Server Error")
  });
}
showPassword: boolean = false;

togglePassword() {
  this.showPassword = !this.showPassword;
}





}
