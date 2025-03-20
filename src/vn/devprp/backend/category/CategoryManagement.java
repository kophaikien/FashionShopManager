package vn.devprp.backend.category;
import java.util.ArrayList;
import java.util.Scanner;

import vn.devpro.exception.InputException;



public class CategoryManagement {
	public static int autoId = 1;
	public static ArrayList<Category> categories = new ArrayList<Category>();
    
	public static ArrayList<Category> getCategories() {
		return categories;
	}

	public static void setCategories(ArrayList<Category> categories) {
		CategoryManagement.categories = categories;
	}

	public static void init() {
		categories.add(new Category(autoId++, "101", "Ao"));
		categories.add(new Category(autoId++, "102", "Quan"));
		categories.add(new Category(autoId++, "103", "Ao khoac"));
		categories.add(new Category(autoId++, "104", "Phu kien"));
		categories.add(new Category(autoId++, "105", "Giay dep"));
		
		
	}

	static Scanner sc = new Scanner(System.in);

	public static void management() {
		do {
			System.out.println("\n\t\tCAP NHAP THONG TIN CHUNG LOAI");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Xem danh sach chung loai");
			System.out.println("\t2. Sua thong tin chung loai");
			System.out.println("\t3. Xoa thong tin chung loai ");
			System.out.println("\t4. sap xep danh sach chung loai theo ten");

			System.out.println("\t0. Quay lai");

			System.out.println("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				display();
				break;
			case 2:
				edit();
				break;
			case 3:
				delete();
				break;

			case 4:
				sort();
				break;
			case 5: 
				add(); 
			case 0:
				return;

			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);

	}

	private static void add() {
		// TODO Auto-generated method stub
		System.out.println("\n\t\tTHEM MOI CHUNG LOAI");
		System.out.print("\tNhap ten chung loai: ");
		String name = sc.nextLine(); 
		if (name.trim().length() <= 0) { 
			System.out.println("\tTen khong duoc de trong");
			return;
		}
		String code = ""; 
		code = code + (100 + autoId); 
		Category category = new Category(autoId, code, name); 
		categories.add(category); 
		System.out.println("\tThem moi chung loai thanh cong!");
	}

	private static void display() {
		System.out.println("\t\tDANH SACH CHUNG LOAI");
		System.out.printf("%3s %13s %-60s%n", "ID", "Ma chung loai", "Ten chung loai");
		for (Category category: categories) { 
			category.display();
		}
	}

	private static void edit() throws InputException{
		// TODO Auto-generated method stub
//		System.out.println();
		
		try { 
			System.out.println("\tNhap vao ma chung loai can sua: ");
			String code = sc.nextLine(); 
			InputException.checkNoEmpty(code);
			InputException.invalidInput(code, categories);
			int index = findCategoryByCode(code);
			categories.get(index).edit(); 
			System.out.println("Cap nhap chung loai thanh cong!");
		}catch (InputException e ) { 
			System.out.println(e.getMessage() );
		}
	}

	private static void delete() {
		// TODO Auto-generated method stub

		try { 
			System.out.println("\tNhap vao ma chung loai can xoa: ");
			String code = sc.nextLine(); 
			InputException.checkNoEmpty(code);
			InputException.invalidInput(code, categories);
			int index = findCategoryByCode(code);
			categories.remove(index); 
			System.out.println("Xoa chung loai thanh cong!");
		}catch (InputException e ) { 
			System.out.println(e.getMessage() );
		}
	}
	

	private static void sort() {
		// TODO Auto-generated method stub
		for (int i = 0; i < categories.size(); i ++) { 
			for (int j = i + 1; j < categories.size(); j++) { 
				if (categories.get(i).getName().compareTo(categories.get(j).getName() )> 0 ) { 
					Category temp = categories.get(i); 
					categories.set(i, categories.get(j)); 
					categories.set(j, temp); 
				}
			}
		}
	}
	public static int findCategoryByCode(String code) {
		for (int i = 0; i < categories.size(); i ++) { 
			if (categories.get(i).getCode().trim().equalsIgnoreCase(code.trim())){
				return i; 
			}
		}
		return -1; 
	}
	public static String getCategoryById(int id) {
		for (Category category : categories) { 
			if (category.getId() == id) { 
				return category.getName(); 
			}
		}
		return null; 
	}
	
}
