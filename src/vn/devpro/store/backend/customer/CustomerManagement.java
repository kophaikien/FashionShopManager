package vn.devpro.store.backend.customer;

import java.util.ArrayList;
import java.util.Scanner;

import vn.devpro.exception.InputException;

public class CustomerManagement {
	public static Scanner sc = new Scanner(System.in);
	
	public static int autoId = 1;
	
	private static ArrayList<Customer> customers = new ArrayList<>();
	
		
	public static int getAutoId() {
		return autoId;
	}
	
	public static void increaseAutoId() {
		CustomerManagement.autoId++;
	}
	
	public static ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public static void setCustomers(ArrayList<Customer> customers) {
		CustomerManagement.customers = customers;
	}
	
	public static void init() {
		customers.add(new Customer(autoId++,"101","Chu Trung", "0967189648"));
		customers.add(new Customer(autoId++,"102","truyen", "0967189648"));
		customers.add(new Customer(autoId++,"103","Tuyen", "0967189648"));
	}
	public static void management() {
		do {		
			
			System.out.println("\n\t\tCHUONG TRINH CAP NHAT THONG TIN KHACH HANG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. XEM THONG TIN KHACH HANG ");
			System.out.println("\t2. THEM THONG TIN KHACH HANG");
			System.out.println("\t3. CAP NHAT THONG TIN KHACH HANG");
			System.out.println("\t4. XOA THONG TIN KHACH HANG");
			System.out.println("\t0. Thoat khoi chuong trinh");

			System.out.println("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				display();
				break;
			case 2:
				add();
				break;
			case 3:
				edit();
				break;
			case 4:
				delete();
				break;
			case 0:
				return ;
				
			default:
				System.out.println("Lua chon khong hop le");
		}
		} while(true);
	}
	private static void display() {
		// TODO Auto-generated method stub
		System.out.println("\n\t\tDANH SACH KHACH HANG");
		System.out.printf("%3s %12s %-60s%n","ID","MaKH","TenKH");
		for (Customer customer : customers) {
			customer.display();
		}
	}
	private static void add() {
		// TODO Auto-generated method stub
		try { 
		System.out.println("\n\t\tTHEM MOI KHACH HANG");
		System.out.print("\tNHAP TEN KHACH HANG ");
		String name = sc.nextLine();
		if (name.trim().length() <= 0) {
			System.out.println("ten khong duoc de trong");
			return ; 
		}
		String code = "";
		code = code + (100+autoId);
		
		System.out.println("Nhap vao so dien thoai");
		String phone = sc.nextLine(); 
		InputException.isValidPhone(phone);
		Customer provider = new Customer(autoId++,code,name, phone);
		customers.add(provider);
		System.out.println("them thanh cong !");
	}catch (InputException e) { 
		System.out.println(e.getMessage());
	}
	}
	private static void edit() {
		// TODO Auto-generated method stub
		System.out.println("\n\t\tSUA MOI KHACH HANG");
		System.out.print("\tNHAP MA KHACH HANG ");
		String code = sc.nextLine();
		// tim kiem xem trong danh sach cho KHACH HANG hay khong?
		int index = findProByCode(code);
			if (index == -1) {
				System.out.println("\tKHACH HANG khong ton tai !");
				return ;
			}
			customers.get(index).edit();
	}
	private static void delete() {
		// TODO Auto-generated method stub
		System.out.println("\n\t\tSUA MOI KHACH HANG");
		System.out.print("\tNHAP MA KHACH HANG ");
		String code = sc.nextLine();
		// tim kiem xem trong danh sach cho KHACH HANG hay khong?
		int index = findProByCode(code);
			if (index == -1) {
				System.out.println("\tKHACH HANG khong ton tai !");
				return ;
			}
		customers.remove(index);
		System.out.println("xoa thanh cong !");
	}
	
	
	// dung vong lap check xem co ma trong mang kh
	public static int findProByCode(String code) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getCode().trim().equals(code.trim())) {
				return i;
			}
		}
		return -1;
	}
	public static String getCustomerNameById(int id) {
		for (Customer customer : customers) {
			if(customer.getId() == id) {
				return customer.getName();
			}
		}
		return "";
	}
	public static String getCustomerPhoneById(int id) { 
		for (Customer customer : customers) {
			if(customer.getId() == id) {
				return customer.getPhone();
			}
		}
		return "";
	}
}
