import { Component, OnInit, ViewChild } from '@angular/core';
import { Product } from 'src/app/Entities/product';
import { ProductService } from 'src/app/Services/product.service';
import { MatDialog } from '@angular/material/dialog';
import { AddProductComponent } from '../add-product/add-product.component';
import { EditProductComponent } from '../edit-product/edit-product.component';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})

export class ProductsComponent implements OnInit {

  products: Product[];
  
  @ViewChild(ProductsComponent) product: any;

  constructor(private matDialog: MatDialog, private productService: ProductService) { }

  ngOnInit(): void {
    this.refreshList();
  }

  addProduct(): void {
    const addDialog = this.matDialog.open(AddProductComponent);
  }

  deleteProduct(id: number){
    this.productService.delete(id);
    this.refreshList();
  }

refreshList():void  {
  this.productService.findAll().subscribe(data => {
    this.products = data;
  });
}

  updateProduct(product: Product) {
   const updateDialog = this.matDialog.open(EditProductComponent);     
  }
}
