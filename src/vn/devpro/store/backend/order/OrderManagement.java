package vn.devpro.store.backend.order;

import java.util.ArrayList;

import java.util.Scanner;

import vn.devpro.store.backend.product.Product;
import vn.devpro.store.backend.product.ProductManagement;
import vn.devpro.exception.InputException;
import vn.devpro.store.backend.customer.CustomerManagement;
import vn.devpro.store.frontend.Cart;
import vn.devpro.store.frontend.CartManagement;
import vn.devpro.store.frontend.CartProduct;

public class OrderManagement {
	public static Scanner sc = new Scanner(System.in);
	public static int autoId = 1;
	private static ArrayList<Cart> order1 = CartManagement.getOrder(); 
	public static void management() {
		do {

			System.out.println("\n\t\tCHUONG TRINH CAP NHAT THONG TIN GIO HANG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. HIEN THI DANH SACH HOA DON DON HANG ");
			System.out.println("\t2. XOA 1 DON HANG KHOI DANH SACH ");
			System.out.println("\t3. HIEN THI TONG DOANH THU CO DUOC TU CAC HOA DON ");
			System.out.println("\t4. HIEN THI TONG SO TIEN THU DUOC THEO KHACH HANG");
			System.out.println("\t5. HIEN THI TONG SO TIEN THU DUOC THEO SAN PHAM DA BAN");
			System.out.println("\t0. Thoat khoi chuong trinh");

			System.out.println("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				display();
				break;
			case 2:
				delete(); 
				break;
			case 3:
				totalRevenue(); 
				break;
			case 4:
				totalByCus(); 
				break;
			case 5:
//				totalByPro(); 
				break;
			

			case 0:
				return;

			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);
	}

	private static void delete() {
		// TODO Auto-generated method stub
		try {
		System.out.println("\t\tXOA HOA DON");
		System.out.println("Nhap vao ma hoa don can xoa");
		String code = sc.nextLine(); 
		InputException.checkNoEmpty(code);
		
		int index = findOderByCode(code);
		if (index == 1) { 
			order1.remove(index);
			System.out.println("Xoa thanh cong don hang " + code);
		}else { 
			System.out.println("khong ton tai hoa don nay");
		}
		
		}
		 catch (InputException e) { 
				System.out.println(e.getMessage());
		 }
	}

	private static void display() {
		

		System.out.println("\n\tDON HANG");
		for (int i = 0; i < CartManagement.getOrder().size(); i++) {
		
			System.out.println("Khach hang : "
					+ CustomerManagement.getCustomerNameById(CartManagement.getOrder().get(i).getCustomerId()));
			System.out.println("So dien thoai: "
					+ CustomerManagement.getCustomerPhoneById(CartManagement.getOrder().get(i).getCustomerId()));
			System.out.println("CODE: " + order1.get(i).getCode());
			System.out.printf("%-30s %8s %15s %15s%n", "Ten san pham", "So luong", "Don gia", "Thanh tien");
			double totalCartPrice = 0;
			for (CartProduct cartProduct : order1.get(i).getCartProducts()) {
				cartProduct.display();
				totalCartPrice += cartProduct.totalPrice();
			}
			System.out.println("-----------------------------");
		}
	}
	private static int findOderByCode(String code) { 
		for (int i = 0; i < order1.size(); i++) { 
			if (order1.get(i).getCode().trim().equalsIgnoreCase(code.trim())) {
				return i; 
			}
		}
		return -1; 
	}
	private static void totalRevenue() { 
		double totalCartPrice = 0;
		for (int i = 0; i < CartManagement.getOrder().size(); i++) {
			System.out.printf("%-30s %8s %15s %15s%n", "Ten san pham", "So luong", "Don gia", "Thanh tien");
			for (CartProduct cartProduct : order1.get(i).getCartProducts()) {
				cartProduct.display();
				totalCartPrice += cartProduct.totalPrice();
			}
			System.out.println("-----------------------------");
		}
		System.out.printf("Tong doanh thu cua tat ca dong hang la: %,15.2f %n",totalCartPrice); 
	}
	private static void totalByCus() { 
		try {
			System.out.println("\t\tDOANH THU THEO KHACH HANG");
			System.out.println("Nhap vao ma khach hang can tinh");
			String code = sc.nextLine(); 
			InputException.checkNoEmpty(code);
			
			double total = 0; 
			boolean found = false; 
			for (int i = 0; i < CartManagement.getOrder().size(); i++) {
				for (CartProduct cartProduct : order1.get(i).getCartProducts()) {
					if (order1.get(i).getCode().trim().equals(code.trim())) { 
						cartProduct.display();
						total += cartProduct.totalPrice(); 
						found = true; 
				}
			}
			} 
			if (found== false) { 
				System.out.println("khong ton tai hoa don nay");
			}
			System.out.printf("Doanh thu theo khach hang ma : %5s là %,15.2f%n",code,total );
			}
			 catch (InputException e) { 
					System.out.println(e.getMessage());
			 }
		
		
	}
	// ý 5 chưa làm được 
}
