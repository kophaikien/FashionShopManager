package vn.devpro.exception;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.devpro.store.backend.product.Product;
import vn.devpro.store.backend.category.Category;

public class InputException extends RuntimeException {

	public InputException(String message) {
		super(message);
	}
	public 	static void checkNoEmpty(String code) {
		if (code.trim().isEmpty()) { 
			throw new InputException("khong duoc de trong" );
	}
	}
	
	public static void invalidInput(String code, ArrayList<?> al) {
		boolean exists = false;
        for (Object item : al) {
        	if (item instanceof Category) {
        		Category cate = (Category)item; 
        		if (cate.getCode().equalsIgnoreCase(code.trim())) {
                exists = true;
                break;
            }
        	}else if (item instanceof Product) {  // Kiểm tra kiểu Product
                Product product = (Product) item;  // Ép kiểu về Product
                if (product.getCode().trim().equalsIgnoreCase(code.trim())) {
                    exists = true;
                    break;
                }
            }
        	}
        
        if (!exists) {
            throw new InputException("không tồn tại!");
        }
	}
	public static void priceInvalid	(double price) {
		if (price <= 0) { 
			throw new InputException("Gia tien phai lon hon 0"); 
		}
		
	}
	public static void isValidPhone(String phone) {
		try {
			String regex = "^0(3|5|7|8|9)\\d{8}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(phone);
	        if (matcher.matches() == false) { 
	        	throw new InputException("So dien thoai khong hop le"); 
	    }
		}catch (InputException e) {
	    	System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	}

