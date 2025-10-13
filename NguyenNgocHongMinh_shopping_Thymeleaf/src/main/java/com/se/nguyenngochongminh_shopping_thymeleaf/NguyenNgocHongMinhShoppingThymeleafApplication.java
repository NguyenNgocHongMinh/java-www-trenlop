package com.se.nguyenngochongminh_shopping_thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NguyenNgocHongMinhShoppingThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(NguyenNgocHongMinhShoppingThymeleafApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void printUrl() {
		System.out.println("Ứng dụng đã khởi động: http://localhost:8085/home");
	}
}
