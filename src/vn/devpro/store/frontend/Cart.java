package vn.devpro.store.frontend;

import java.util.ArrayList;

import vn.devpro.store.backend.customer.CustomerManagement;

public class Cart {
	public static int autoId = 1; 
	private int id;
	private int customerId;
	private String code;	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private ArrayList<CartProduct> cartProducts = new ArrayList<>();
	
	public void display() {
		System.out.println("\n\tGIO HANG CUA BAN");
		System.out.println("Khach hang : "+ CustomerManagement.getCustomerNameById(this.customerId));
		System.out.println("So dien thoai: " + CustomerManagement.getCustomerPhoneById(customerId));
		System.out.printf("%-30s %8s %15s %15s%n","Ten san pham","So luong","Don gia","Thanh tien");
		double totalCartPrice = 0;
		for (CartProduct cartProduct : cartProducts) {
			cartProduct.display();
			totalCartPrice += cartProduct.totalPrice();
		}
	}
	public Cart() {
		super();
	}
	public Cart(int id, int customerId, ArrayList<CartProduct> cartProducts) {
		super();
		this.id = id;
		this.customerId = customerId;
		
		this.cartProducts = cartProducts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public ArrayList<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(ArrayList<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public int findProductById(int id) {
		for (int i = 0; i < cartProducts.size(); i++) {
			if (cartProducts.get(i).getId() == id) {
				return i ;
			}
		}
		return -1;
	}
	public  void autoCode() {
		this.code = ""; 
		this.code += (100 + autoId++);
	}
}
