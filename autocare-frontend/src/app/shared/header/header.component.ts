import {Component, inject, OnInit} from '@angular/core';
import {OidcSecurityService} from 'angular-auth-oidc-client';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
  private readonly oidcSecurityService = inject(OidcSecurityService);
  isAuthenticated: boolean = false;
  username: string = "";

  ngOnInit(): void {
    this.oidcSecurityService.checkAuth().subscribe({next:({ isAuthenticated, userData }) => {
      this.isAuthenticated = isAuthenticated;
      this.username = userData?.preferred_username ?? 'Guest'
    },
    error:(err:any)=>{console.error(err)}});
  }
  login():void{
    this.oidcSecurityService.authorize();
  }

  logout():void{
    this.oidcSecurityService.logoff().subscribe((res)=>{console.log(res)})
  }

}
