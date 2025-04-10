package vn.devpro.store;

import java.util.Scanner;

import vn.devpro.store.backend.order.OrderManagement;
import vn.devpro.store.backend.product.ProductManagement;
import vn.devpro.store.backend.SystemInformationManagement;
import vn.devpro.store.backend.customer.CustomerManagement;
import vn.devpro.store.frontend.CartManagement;
import vn.devpro.store.backend.category.CategoryManagement;



public class Store {
	public static Scanner sc = new Scanner(System.in); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CategoryManagement.init();
		ProductManagement.init();
		CustomerManagement.init();
		do {
			System.out.println("\n\t\tCHUONG TRINH QUAN LY CUA HANG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Danh cho nguoi moi quan ly ");
			System.out.println("\t2. Danh cho khach mua hang");		
			System.out.println("\t3. Danh cho nguoi quan ly don hang");		
			System.out.println("\t0. Thoat khoi chuong trinh");

			System.out.println("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				SystemInformationManagement.management();
				break;
			case 2:
				CartManagement.management();
				break;
			case 3:
				OrderManagement.management();
				break;

			case 4:
				
				break;
			case 5:
				
				break;
			case 0:
				return;

			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);
	}

}
