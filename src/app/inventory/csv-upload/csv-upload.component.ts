import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-csv-upload',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './csv-upload.html',
  styleUrls: ['./csv-upload.css']
})
export class CsvUploadComponent {

  selectedFile: File | null = null;
  successMessage: string = "";
  errorMessage: string = "";

  upload() {
    if (!this.selectedFile) {
      this.errorMessage = "Please select a CSV file.";
      this.successMessage = "";
      return;
    }

    // Dummy success message for now
    this.successMessage = "CSV uploaded successfully!";
    this.errorMessage = "";
  }
}
