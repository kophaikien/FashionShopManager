package vn.devpro.store.backend.customer;

import java.util.Scanner;

public class Customer {
	private int id;
	private String code;
	private String name;
	private String phone; 
	public Customer() {
		super();
	}
	public Customer(int id, String code, String name, String phone) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.phone = phone; 
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
		System.out.printf("%3d %12s %-60s %13s %n",this.id,this.code,this.name, this.phone);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void edit() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\tNHAP TEN NHA KHACH HANG ");
		String name = sc.nextLine();
		if (name.trim().length() <= 0) {
			System.out.println("ten khong duoc de trong");
			return ; 
		}
		this.name = name;
		System.out.println("sua thanh cong ! ");
	}
	
}
