import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
  imports: [FormsModule, CommonModule]
})
export class RegisterComponent {

  user = {
    fullName: '',
    email: '',
    password: ''
  };

  selectedRole = 'USER';  // or VENDOR, ADMIN, anything you need

  constructor(private auth: AuthService, private router: Router) {}

  
  register() {
  const payload = {
    fullName: this.user.fullName,
    email: this.user.email,
    password: this.user.password,
    role: this.selectedRole

    
  };

  this.auth.register(payload).subscribe({
    next: () => {
      alert("Registration successful!");
      this.router.navigate(['/login']);
    },
    error: (err) => {
      const message = err.error || "Something went wrong!";
      alert(message);
    }
  });
}


registerData = {
  fullName: '',
  email: '',
  password: '',
  confirmPassword: '',
  role: ''
};



  

}
