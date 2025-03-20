package vn.devprp.backend.category;

import java.util.Scanner;

public class Category {
	private int id; 
	private String code; 
	private String name;
	public Category(int id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	public Category() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public void display() {
		System.out.printf("%3d %12s %-60s%n", this.id, this.code, this.name);
	}
	public  void edit() {
		Scanner sc = new Scanner(System.in);
		
		try { 
		System.out.println("\t\tSUA THONG TIN CHUNG LOAI");
		System.out.print("\tNhap ten chung loai: ");
		String name = sc.nextLine(); 
		if (name.trim().length() <= 0) { 
			throw new IllegalArgumentException("Ten khong duoc de trong"); 
		
		}
		this.name = name; 
		System.out.println("\tSua ten chung loai thanh cong");
		}catch (IllegalArgumentException e){
			System.out.println(e.getMessage()); 
		}
}
}
