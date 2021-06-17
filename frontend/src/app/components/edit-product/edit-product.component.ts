import { Component, Input, OnInit } from '@angular/core';
import { Product } from 'src/app/Entities/product';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit { 

  constructor() { }

  ngOnInit(): void {
  }

}
