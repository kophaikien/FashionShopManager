package vn.devpro.store.frontend;

import vn.devpro.store.backend.product.Product;
import vn.devpro.store.backend.product.ProductManagement;

public class CartProduct {
	private int id ; 
	private int quantity;
	
	public void display() {
		Product product = ProductManagement.getProductById(this.id);
		System.out.printf("%-30s %8d %,15.2f %,15.2f%n",
				product.getName(),this.quantity,product.getPrice(),this.totalPrice());
	}
	public double totalPrice() {
		Product product = ProductManagement.getProductById(this.id);
		return this.quantity*product.getPrice();
	}
	public void updateQuantity(int quantity) {
		this.quantity += quantity;	
		}
	public CartProduct() {
		super();
	}
	public CartProduct(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
