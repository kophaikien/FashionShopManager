package vn.devpro.store.frontend;

import java.util.ArrayList;
import java.util.Scanner;

import vn.devpro.store.backend.product.ProductManagement;
import vn.devpro.exception.InputException;
import vn.devpro.store.backend.customer.Customer;
import vn.devpro.store.backend.customer.CustomerManagement;


public class CartManagement {
	public static Scanner sc = new Scanner(System.in);
	
	public static int autoId = 1;
	 
	public static Cart cart = new Cart();
	
	public static ArrayList<Cart> order = new ArrayList<Cart>();
	
	
	
	public static Cart getCart() {
		return cart;
	}

	public static void setCart(Cart cart) {
		CartManagement.cart = cart;
	}
	
	

	

	public static ArrayList<Cart> getOrder() {
		return order;
	}

	public static void setOrder(ArrayList<Cart> order) {
		CartManagement.order = order;
	}

	public static void management() {
		do {		
			
			System.out.println("\n\t\tCHUONG TRINH CAP NHAT THONG TIN GIO HANG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. XEM THONG TIN SAN PHAM ");
			System.out.println("\t2. XEM THONG TIN GIO HANG");
			System.out.println("\t3. THEM HANG VAO GIO HANG");
			System.out.println("\t4. SUA THONG TIN GIO HANG");
			System.out.println("\t5. XOA THONG TIN GIO HANG");
			System.out.println("\t6. XOA TOAN BO GIO HANG");
			System.out.println("\t7. THANH TOAN GIO HANG");
			System.out.println("\t0. Thoat khoi chuong trinh");

			System.out.println("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				ProductManagement.display();
				break;
			case 2:
				cart.display();
				break;
			case 3:
				add();
				break;
			case 4:
				edit();
				break;
			case 5:
				remove();
				break;
			case 6:
				removeAll();
				break;
			case 7:
				checkOut();
				break;
			case 0:
				return ;
				
			default:
				System.out.println("Lua chon khong hop le");
		}
		} while(true);
	}
	private static void add() {
		System.out.println("\n\t\tTHEM SAN PHAM VAO GIO HANG");
		System.out.println("\tMoi nhap ma san pham : ");
		String code = sc.nextLine();
		//tim san pham trong danh sach san pham
		int index = ProductManagement.findProductByCode(code);
		if (index == -1) {
			System.out.println("Ma san pham khong dung xin nhap lai !");
			return ; 
		}
		
		System.out.println("\tMoi nhap so luong can mua : ");
		int quantity = Integer.parseInt(sc.nextLine());
		if (quantity < 1) {
		System.out.println("\tSo luong khong hop le !");
			return ; 
		}
		//tim san pham trong gio hang
		int index1 = cart.findProductById(ProductManagement.getProducts().get(index).getId());
		if (index1 > -1 ) {// san pham da co trong gio hang
			cart.getCartProducts().get(index1).updateQuantity(quantity);	
		}else {
			cart.getCartProducts().add(new CartProduct(ProductManagement.getProducts().get(index).getId(), quantity));
		}
		System.out.println("\tDa them san pham vao gio hang");
	}
	private static void edit() {
		System.out.println("\n\t\tSUA THONG TIN SAN PHAM TRONG GIO HANG");
		System.out.println("\tMoi nhap ma san pham : ");
		String code = sc.nextLine();
		//tim san pham trong danh sach san pham
		int index = ProductManagement.findProductByCode(code);
		if (index == -1) {
			System.out.println("Ma san pham khong dung xin nhap lai !");
			return ; 
		}
		// lay id cua san pham 
		int productId = ProductManagement.getProducts().get(index).getId();
		// kiem tra san pham trong gio hang
		int index1 = cart.findProductById(productId);
		if (index1 == -1) {
			System.out.println("\tSan pham nay khong co trong gio hang");
			return ; 
		}
		System.out.println("\tMoi nhap vao so luong moi");
		int quantity = Integer.parseInt(sc.nextLine());
		if(quantity < 1) {
			System.out.println("\tSo luong khong hop le");
		}
		cart.getCartProducts().get(index1).setQuantity(quantity);
		System.out.println("\tSua thanh cong san pham !");
	}
	private static void remove() {
		System.out.println("\n\t\tXOA THONG TIN SAN PHAM TRONG GIO HANG");
		System.out.println("\tMoi nhap ma san pham : ");
		String code = sc.nextLine();
		//tim san pham trong danh sach san pham
		int index = ProductManagement.findProductByCode(code);
		if (index == -1) {
			System.out.println("Ma san pham khong dung xin nhap lai !");
			return ; 
		}
		// lay id cua san pham 
		int productId = ProductManagement.getProducts().get(index).getId();
		// kiem tra san pham trong gio hang
		int index1 = cart.findProductById(productId);
		if (index1 == -1) {
			System.out.println("\tSan pham nay khong co trong gio hang");
			return ; 
		}
		cart.getCartProducts().remove(index1);
		System.out.println("\tXoa san pham trong gio hang thanh cong");
	}
	private static void removeAll() {
		cart = new Cart();
		System.out.println("\tXoa gio hang thanh cong");
	}
	private static void checkOut() {
		// TODO Auto-generated method stub
		// Cap nhat thong tin ve khach hang
		try { 
		Customer customer = new Customer();
		System.out.println("\tNhap vao ma khach hang: ");
		String customerCode = sc.nextLine();
		int index = CustomerManagement.findProByCode(customerCode);
		if (index == -1 ) {//thong tin khach hang chua co trong he thong
			System.out.println("\tNhap ten khach hang : ");
			String name = sc.nextLine();
			if(name == null || name.trim().length() <= 0 ) {
				System.out.println("\tten khach hang khong duoc de trong ");
				return ; 
			}
			customerCode = "" + (100+CustomerManagement.getAutoId());
			
			System.out.println("\tNhap vao so dien thoai");
			String phone = sc.nextLine(); 
			InputException.isValidPhone(phone);
			customer = new Customer(CustomerManagement.getAutoId(),customerCode,name, phone);
			CustomerManagement.increaseAutoId();
			CustomerManagement.getCustomers().add(customer);
		}else {//khac hang da co trong he thong
			customer = CustomerManagement.getCustomers().get(index);
		}
		cart.setCustomerId(customer.getId());
		cart.autoCode();
		//hien thi hoa don
		cart.display();
		//luu gio hang 
		order.add(cart);
		// xoa gio hang 
		cart = new Cart();
		System.out.println("\tDat hang thanh cong!");
	} catch (InputException e) { 
		System.out.println(e.getMessage());
	}

}
	
}
