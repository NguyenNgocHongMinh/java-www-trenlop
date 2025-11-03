package com.se.nguyenngochongminh_shopping_thymeleaf.config;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.*;
import com.se.nguyenngochongminh_shopping_thymeleaf.reposities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Calendar;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository,
            OrderRepository orderRepository,
            OrderLineRepository orderLineRepository,
            CommentRepository commentRepository) {

        return args -> {
            if (categoryRepository.count() > 0) {
                System.out.println("Database already initialized. Skipping data initialization.");
                return;
            }

            System.out.println("Initializing database with sample data...");

            // 1. Tạo Categories
            Category electronics = new Category();
            electronics.setName("Electronics");
            categoryRepository.save(electronics);

            Category clothing = new Category();
            clothing.setName("Clothing");
            categoryRepository.save(clothing);

            Category books = new Category();
            books.setName("Books");
            categoryRepository.save(books);

            Category food = new Category();
            food.setName("Food & Beverage");
            categoryRepository.save(food);

            System.out.println("Created 4 categories");

            // 2. Tạo Products
            Product laptop = new Product();
            laptop.setName("Laptop Dell XPS 13");
            laptop.setPrice(new BigDecimal("25000000"));
            laptop.setInStock(true);
            laptop.setCategory(electronics);
            productRepository.save(laptop);

            Product phone = new Product();
            phone.setName("iPhone 15 Pro");
            phone.setPrice(new BigDecimal("30000000"));
            phone.setInStock(true);
            phone.setCategory(electronics);
            productRepository.save(phone);

            Product headphone = new Product();
            headphone.setName("Sony WH-1000XM5");
            headphone.setPrice(new BigDecimal("8000000"));
            headphone.setInStock(true);
            headphone.setCategory(electronics);
            productRepository.save(headphone);

            Product tshirt = new Product();
            tshirt.setName("T-Shirt Nike");
            tshirt.setPrice(new BigDecimal("500000"));
            tshirt.setInStock(true);
            tshirt.setCategory(clothing);
            productRepository.save(tshirt);

            Product jeans = new Product();
            jeans.setName("Jeans Levi's 501");
            jeans.setPrice(new BigDecimal("1200000"));
            jeans.setInStock(true);
            jeans.setCategory(clothing);
            productRepository.save(jeans);

            Product jacket = new Product();
            jacket.setName("Jacket Adidas");
            jacket.setPrice(new BigDecimal("2000000"));
            jacket.setInStock(false);
            jacket.setCategory(clothing);
            productRepository.save(jacket);

            Product book1 = new Product();
            book1.setName("Clean Code");
            book1.setPrice(new BigDecimal("350000"));
            book1.setInStock(true);
            book1.setCategory(books);
            productRepository.save(book1);

            Product book2 = new Product();
            book2.setName("Design Patterns");
            book2.setPrice(new BigDecimal("450000"));
            book2.setInStock(true);
            book2.setCategory(books);
            productRepository.save(book2);

            Product coffee = new Product();
            coffee.setName("Trung Nguyên Coffee");
            coffee.setPrice(new BigDecimal("150000"));
            coffee.setInStock(true);
            coffee.setCategory(food);
            productRepository.save(coffee);

            Product tea = new Product();
            tea.setName("Lipton Green Tea");
            tea.setPrice(new BigDecimal("80000"));
            tea.setInStock(true);
            tea.setCategory(food);
            productRepository.save(tea);

            System.out.println("Created 10 products");

            // 3. Tạo Comments cho Products
            Comment comment1 = new Comment();
            comment1.setText("Sản phẩm rất tốt, chất lượng cao!");
            comment1.setProduct(laptop);
            commentRepository.save(comment1);

            Comment comment2 = new Comment();
            comment2.setText("Giá hơi cao nhưng xứng đáng!");
            comment2.setProduct(laptop);
            commentRepository.save(comment2);

            Comment comment3 = new Comment();
            comment3.setText("Màn hình đẹp, pin trâu!");
            comment3.setProduct(phone);
            commentRepository.save(comment3);

            Comment comment4 = new Comment();
            comment4.setText("Chất lượng âm thanh tuyệt vời!");
            comment4.setProduct(headphone);
            commentRepository.save(comment4);

            Comment comment5 = new Comment();
            comment5.setText("Vải mát, mặc rất thoải mái!");
            comment5.setProduct(tshirt);
            commentRepository.save(comment5);

            Comment comment6 = new Comment();
            comment6.setText("Sách hay, nên đọc!");
            comment6.setProduct(book1);
            commentRepository.save(comment6);

            Comment comment7 = new Comment();
            comment7.setText("Cà phê thơm ngon, đậm đà!");
            comment7.setProduct(coffee);
            commentRepository.save(comment7);

            System.out.println("Created 7 comments");

            // 4. Tạo Customers
            Customer customer1 = new Customer();
            customer1.setName("Nguyễn Văn An");
            Calendar cal1 = Calendar.getInstance();
            cal1.set(2023, Calendar.JANUARY, 15);
            customer1.setCustomerSince(cal1);
            customerRepository.save(customer1);

            Customer customer2 = new Customer();
            customer2.setName("Trần Thị Bình");
            Calendar cal2 = Calendar.getInstance();
            cal2.set(2023, Calendar.MARCH, 20);
            customer2.setCustomerSince(cal2);
            customerRepository.save(customer2);

            Customer customer3 = new Customer();
            customer3.setName("Lê Hoàng Cường");
            Calendar cal3 = Calendar.getInstance();
            cal3.set(2023, Calendar.MAY, 10);
            customer3.setCustomerSince(cal3);
            customerRepository.save(customer3);

            Customer customer4 = new Customer();
            customer4.setName("Phạm Thị Dung");
            Calendar cal4 = Calendar.getInstance();
            cal4.set(2023, Calendar.JULY, 5);
            customer4.setCustomerSince(cal4);
            customerRepository.save(customer4);

            System.out.println("✓ Created 4 customers");

            // 5. Tạo Orders và OrderLines
            // Order 1 - Customer 1
            Order order1 = new Order();
            Calendar orderDate1 = Calendar.getInstance();
            orderDate1.set(2024, Calendar.JANUARY, 10);
            order1.setDate(orderDate1);
            order1.setCustomer(customer1);
            orderRepository.save(order1);

            OrderLine orderLine1_1 = new OrderLine();
            orderLine1_1.setAmount(1);
            orderLine1_1.setPurchasePrice(laptop.getPrice());
            orderLine1_1.setProduct(laptop);
            orderLine1_1.setOrder(order1);
            orderLineRepository.save(orderLine1_1);

            OrderLine orderLine1_2 = new OrderLine();
            orderLine1_2.setAmount(2);
            orderLine1_2.setPurchasePrice(headphone.getPrice());
            orderLine1_2.setProduct(headphone);
            orderLine1_2.setOrder(order1);
            orderLineRepository.save(orderLine1_2);

            // Order 2 - Customer 2
            Order order2 = new Order();
            Calendar orderDate2 = Calendar.getInstance();
            orderDate2.set(2024, Calendar.FEBRUARY, 15);
            order2.setDate(orderDate2);
            order2.setCustomer(customer2);
            orderRepository.save(order2);

            OrderLine orderLine2_1 = new OrderLine();
            orderLine2_1.setAmount(1);
            orderLine2_1.setPurchasePrice(phone.getPrice());
            orderLine2_1.setProduct(phone);
            orderLine2_1.setOrder(order2);
            orderLineRepository.save(orderLine2_1);

            OrderLine orderLine2_2 = new OrderLine();
            orderLine2_2.setAmount(3);
            orderLine2_2.setPurchasePrice(tshirt.getPrice());
            orderLine2_2.setProduct(tshirt);
            orderLine2_2.setOrder(order2);
            orderLineRepository.save(orderLine2_2);

            // Order 3 - Customer 3
            Order order3 = new Order();
            Calendar orderDate3 = Calendar.getInstance();
            orderDate3.set(2024, Calendar.MARCH, 20);
            order3.setDate(orderDate3);
            order3.setCustomer(customer3);
            orderRepository.save(order3);

            OrderLine orderLine3_1 = new OrderLine();
            orderLine3_1.setAmount(2);
            orderLine3_1.setPurchasePrice(book1.getPrice());
            orderLine3_1.setProduct(book1);
            orderLine3_1.setOrder(order3);
            orderLineRepository.save(orderLine3_1);

            OrderLine orderLine3_2 = new OrderLine();
            orderLine3_2.setAmount(1);
            orderLine3_2.setPurchasePrice(book2.getPrice());
            orderLine3_2.setProduct(book2);
            orderLine3_2.setOrder(order3);
            orderLineRepository.save(orderLine3_2);

            OrderLine orderLine3_3 = new OrderLine();
            orderLine3_3.setAmount(5);
            orderLine3_3.setPurchasePrice(coffee.getPrice());
            orderLine3_3.setProduct(coffee);
            orderLine3_3.setOrder(order3);
            orderLineRepository.save(orderLine3_3);

            // Order 4 - Customer 4
            Order order4 = new Order();
            Calendar orderDate4 = Calendar.getInstance();
            orderDate4.set(2024, Calendar.APRIL, 25);
            order4.setDate(orderDate4);
            order4.setCustomer(customer4);
            orderRepository.save(order4);

            OrderLine orderLine4_1 = new OrderLine();
            orderLine4_1.setAmount(1);
            orderLine4_1.setPurchasePrice(jeans.getPrice());
            orderLine4_1.setProduct(jeans);
            orderLine4_1.setOrder(order4);
            orderLineRepository.save(orderLine4_1);

            OrderLine orderLine4_2 = new OrderLine();
            orderLine4_2.setAmount(10);
            orderLine4_2.setPurchasePrice(tea.getPrice());
            orderLine4_2.setProduct(tea);
            orderLine4_2.setOrder(order4);
            orderLineRepository.save(orderLine4_2);

            System.out.println("Created 4 orders with 11 order lines");

            System.out.println("========================================");
            System.out.println("Database initialization completed!");
            System.out.println("========================================");
            System.out.println("Summary:");
            System.out.println("  - Categories: 4");
            System.out.println("  - Products: 10");
            System.out.println("  - Comments: 7");
            System.out.println("  - Customers: 4");
            System.out.println("  - Orders: 4");
            System.out.println("  - OrderLines: 11");
            System.out.println("========================================");
        };
    }
}
