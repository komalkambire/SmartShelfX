import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class DashboardComponent implements OnInit {

  userRole = localStorage.getItem("role");

  stats = {
    totalItems: 120,
    lowStock: 8,
    autoRestock: 4,
    vendors: 12
  };

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.loadChart();
  }

  loadChart() {
    new Chart("stockChart", {
      type: 'line',
      data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{
          label: 'Stock Levels',
          data: [120, 150, 140, 170, 160, 200],
          borderWidth: 2
        }]
      }
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
