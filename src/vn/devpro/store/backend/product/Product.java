package vn.devpro.store.backend.product;


import java.awt.im.InputContext;
import java.util.Scanner;

import vn.devpro.exception.InputException;
import vn.devprp.backend.category.CategoryManagement;


public class Product {
	private int id; 
	private int category_id; 
	private String code; 
	private String name; 
	private double price;
	public Product(int id, int category_id, String code, String name, double price) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.code = code;
		this.name = name;
		this.price = price;
	}
	public Product() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	} 
	
	public void display() {
		System.out.printf("%3d %-25s %10s %-30s %,15.2f%n",
				this.id,CategoryManagement.getCategoryById(category_id), this.code, this.name, this.price);
	}
	public void edit() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n\t\tSUA THONG TIN SANPHAM");
			System.out.println("Hay chon mot thong tin can sua");
			System.out.println("\t1. Sua ten san pham");
			System.out.println("\t2. Sua dongia san pham");
			System.out.println("\t0. Quay lai");
			System.out.println("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				try {
				System.out.print("\tNhap ten moi: ");
				this. name = sc.nextLine(); 
				InputException.checkNoEmpty(name);
				System.out.println("\tSua ten hang hoa thanh cong");
				}catch (InputException e) { 
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
				System.out.print("\tNhap don gia moi: ");
				this. price = Double.parseDouble(sc.nextLine()); 
				InputException.priceInvalid(price);
				System.out.println("\t Sua don gia thanh cong");
				}catch (InputException e) { 
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				
				break;

			case 0:
				return;

			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);
		
	}
}
