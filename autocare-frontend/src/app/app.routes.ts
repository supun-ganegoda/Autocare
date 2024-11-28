import { Routes } from '@angular/router';
import {HomePageComponent} from './shared/home.page/home.page.component';
import {ProductComponent} from './shared/product/product.component';

export const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'add-product', component: ProductComponent}
];
