import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'space_front';
  

  constructor (private router: Router){}

  ngOnInit(): void{
    this.router.navigate(['/ecranAcceuil'])
  }
}
