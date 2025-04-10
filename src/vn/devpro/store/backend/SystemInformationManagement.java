package vn.devpro.store.backend;


import java.beans.Customizer;
import java.util.Scanner;

import vn.devpro.store.backend.product.ProductManagement;
import vn.devpro.store.backend.customer.CustomerManagement;
import vn.devpro.store.backend.category.CategoryManagement;



public class SystemInformationManagement {
	public static Scanner sc = new Scanner(System.in);
	public static void management() {
		do {
			System.out.println("\n\t\tCAP NHAP THONG TIN HE THONG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Cap nhap thong tin chung loai  ");
			System.out.println("\t2. Cap nhap thong tin san pham");
			System.out.println("\t3. Cap nhap thong tin khach hang");
		
			System.out.println("\t0. Quay lai");

			System.out.println("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				CategoryManagement.management();
				break;
			case 2:
				ProductManagement.management();
				break;
			case 3:
				CustomerManagement.management();
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
