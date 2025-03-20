package vn.devpro.store.backend.product;

import java.awt.im.InputContext;
import java.util.ArrayList;
import java.util.Scanner;

import vn.devpro.exception.InputException;

import vn.devprp.backend.category.Category;
import vn.devprp.backend.category.CategoryManagement;



public class ProductManagement {
	private static int autoId = 1; 
	private static ArrayList<Product> products = new ArrayList<Product>(); 
	private static Scanner sc = new Scanner(System.in);
	
	public static ArrayList<Product> getProducts() {
		return products;
	}
	public static void setProducts(ArrayList<Product> products) {
		ProductManagement.products = products;
	}
	public static void init() {
		products.add(new Product(autoId++, 1, "101", "Ao thun co tron",  450000));
		products.add(new Product(autoId++, 3, "102", "Ao khoac bomber",  950000));
		products.add(new Product(autoId++, 2, "103", "Quan jeans",  250000));
		products.add(new Product(autoId++, 5, "104", "Giay Nike",  4500000));
		products.add(new Product(autoId++, 1, "105", "Ao so mi dai tay",  1530000));
		products.add(new Product(autoId++, 1, "106", "Ao so mi coc tay",  1530000));
		products.add(new Product(autoId++, 2, "107", "Quan short",  1530000));
		products.add(new Product(autoId++, 3, "108", "Ao khoac denim",  1530000));
		products.add(new Product(autoId++, 4, "109", "Kinh dam",  1530000));
		products.add(new Product(autoId++, 5, "110", "Dep chai co",  1530000));
		products.add(new Product(autoId++, 4, "111", "Vong co",  1530000));
	}
	public static void management() {
		do {
			System.out.println("\n\t\tCAP NHAP THONG TIN SAN PHAM");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Xem thong tin san pham ");
			System.out.println("\t2. Them thong tin san pham");
			System.out.println("\t3. Sua thong tin san pham");
			System.out.println("\t4. Xóa thong tin san pham");
			System.out.println("\t5. Sap xep thong tin san pham");
			System.out.println("\t6. Tim kiem san pham theo chung loai");
			System.out.println("\t7. Tim kiem san pham theo tu khoa");
			System.out.println("\t0. Quay lai");

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
			case 5: 
				searchByCate();
			case 6: 
				searchByKey(); 
				
			case 0:
				return;

			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);
	}
	
	public static void display() {
		// TODO Auto-generated method stub
		System.out.println("\n\t\tDANH SACH SAN PHAM DANG BAN");
		System.out.printf("%3s %-25s %10s %-30s %-15s%n", "ID", "CHUNG LOAI", "Ma Sp","Ten san pham", "Don gia");
		for (Product product: products) { 
			product.display();
		}
	}
	
	private static void add() {
		try {
			System.out.println("\n\t\tTHEM MOI SAN PHAM");
			
			System.out.println("\tChon chung loai");
			String cate = sc.nextLine(); 
			InputException.invalidInput(cate, CategoryManagement.getCategories());
			
			System.out.print("\tNhap ten san pham: ");
			String name = sc.nextLine(); 
			InputException.checkNoEmpty(name);
			
			String code = ""; 
			code = code + (100 + autoId); 
			
			
			System.out.print("\tNhap gia tien cua san pham: ");
			double price = Double.parseDouble(sc.nextLine()); 
			InputException.priceInvalid(price);
			
			int index = CategoryManagement.findCategoryByCode(cate); 
			
			Product product= new Product(autoId++,CategoryManagement.getCategories().get(index).getId(), code, name, price); 
			products.add(product); 
			System.out.println("\tThem moi chung loai thanh cong!");
		}catch(InputException e) { 
			System.out.println(e.getMessage());
		}catch( NumberFormatException e	) { 
			System.out.println(e.getMessage());
		}
		
		
		
	}
	private static void edit() {
		System.out.println("\n\t\tSUA THONG TIN SAN PHAM");
		System.out.print("\tNhap ma san pham: ");
		String code = sc.nextLine();
		InputException.checkNoEmpty(code);
		InputException.invalidInput(code, products);
		//tim xem trong danh sach co ncc nay khogn
		int index = findProductByCode(code); 
		if (index == -1) {
			System.out.println("\tSan pham khong ton tai");
			return;
		}
		products.get(index).edit(); 
		
	}
	private static void delete() {
		// TODO Auto-generated method stub
		try { 
			System.out.println("\tNhap vao ma san pham can xoa: ");
			String code = sc.nextLine(); 
			InputException.checkNoEmpty(code);
			InputException.invalidInput(code, products);
			int index = findProductByCode(code);
			products.remove(index); 
			System.out.println("Xoa chung loai thanh cong!");
		}catch (InputException e ) { 
			System.out.println(e.getMessage() );
		}
	}
	private static void sort() {
		// TODO Auto-generated method stub
		for (int i = 0; i < products.size(); i ++) { 
			for (int j = i + 1; j < products.size(); j++) { 
				if (products.get(i).getName().compareTo(products.get(j).getName() )> 0 ) { 
					Product temp = products.get(i); 
					products.set(i, products.get(j)); 
					products.set(j, temp); 
				}
			}
		}
	}
	private static void searchByCate() {
		// TODO Auto-generated method stub
		System.out.println("\t\tTim kiem san pham theo chung loai");
		System.out.print("\tNhap vao chung loai can tim: ");
		String cate = sc.nextLine(); 
		boolean found = false; 
		if (cate.equals("") ) { 
			display();
		}
		else { 
			for (int index = 0; index < products.size(); index++) { 
				String cate_n = CategoryManagement.getCategoryById(products.get(index).getCategory_id());  
				if (cate.trim().equalsIgnoreCase(cate_n.trim())) { 
					products.get(index).display(); 
					found = true; 
				}
			}
		}
		if (found == false) { 
			System.out.println("Khong co chung loai can tim");
		}
	}
	private static void searchByKey() {
		System.out.println("\t\tTim kiem san pham theo ten");
		System.out.print("\tNhap vao ten san pham can tim: ");
		String name = sc.nextLine();
		
		if (name.equals("") ) { 
			display();
		}
		 boolean found = false;
	        for (Product product : products) {
	            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
	                product.display();
	                found = true;
	            }
	        }

	        if (!found) {
	            System.out.println("Không tìm thấy sản phẩm nào.");
	        }
	    }
		
	
	
	public static int findProductByCode(String code) {
		for (int i = 0; i < products.size(); i++) { 
			if (products.get(i).getCode().trim().equalsIgnoreCase(code.trim())){ 
				return i; 
			}
		}
		return -1; 
	}
	public static Product getProductById(int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return new Product();
	}
}
