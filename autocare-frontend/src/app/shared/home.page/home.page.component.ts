import {Component, inject, OnInit} from '@angular/core';
import {Product} from '../../model/product';
import {Router} from '@angular/router';
import {OidcSecurityService} from 'angular-auth-oidc-client';
import {ProductService} from '../../service/product.service';
import {OrderService} from '../../service/order.service';
import {Order, UserDetails} from '../../model/order';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.page.component.html',
  styleUrl: './home.page.component.css',
})
export class HomePageComponent implements OnInit {
  private readonly oidcSecurityService = inject(OidcSecurityService);
  private readonly productService = inject(ProductService);
  private readonly orderService = inject(OrderService);
  private readonly router = inject(Router);
  isAuthenticated: boolean = false;
  products: Array<Product> = [];
  quantityIsNull: boolean = false;
  orderSuccess: boolean = false;
  orderFailed: boolean = false;

  ngOnInit(): void {
    this.oidcSecurityService.checkAuth().subscribe({
      next: ({isAuthenticated, userData}) => {
        this.isAuthenticated = isAuthenticated;
        this.productService.getProducts().subscribe((res) => {
          this.products = res;
        });
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  goToCreateProductPage() {
    this.router.navigateByUrl('/add-product');
  }

  orderProduct(productName: string, productPrice: number, qty: string) {
    this.orderSuccess = false;
    this.orderFailed = false;
    this.quantityIsNull = false;
    if (qty !== '') {
      this.oidcSecurityService.userData$.subscribe(result => {
        const userDetails: UserDetails = {
          email: result.userData.email,
          firstName: result.userData.firstName,
          lastName: result.userData.lastName
        }
        const order: Order = {
          skuCode: productName,
          price: productPrice,
          quantity: parseInt(qty),
          userDetails: userDetails
        };
        this.orderService.placeOrder(order).subscribe({
          next: (res) => {
            this.orderSuccess = true;
            console.log(res);
          },
          error: () => {
            this.orderFailed = true;
          },
        });
      });
    } else {
      this.orderSuccess = false;
      this.orderFailed = true;
      this.quantityIsNull = true;
    }
  }
}
