import {Component, inject} from '@angular/core';
import {NgIf} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {ProductService} from '../../service/product.service';
import {Product} from '../../model/product';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  addProductForm: FormGroup;
  private readonly productService = inject(ProductService);
  productCreated = false;

  constructor(private fb: FormBuilder) {
    this.addProductForm = this.fb.group({
      skuCode: ['', [Validators.required]],
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      price: [0, [Validators.required]]
    })
  }

  onSubmit(): void {
    if (this.addProductForm.valid) {
      const product: Product = {
        skuCode: this.addProductForm.get('skuCode')?.value,
        name: this.addProductForm.get('name')?.value,
        description: this.addProductForm.get('description')?.value,
        price: this.addProductForm.get('price')?.value
      }
      this.productService.createProduct(product).subscribe(product => {
        this.productCreated = true;
        this.addProductForm.reset();
      })
    } else {
      console.log('Form is not valid');
    }
  }

  get skuCode(){ return this.addProductForm.get('skuCode')}
  get name(){ return this.addProductForm.get("name")}
  get description(){ return this.addProductForm.get("description")}
  get price(){ return this.addProductForm.get("price")}
}
