package com.se.nguyenngochongminh_shopping_thymeleaf.entities;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "customer_since")
    private Calendar customerSince;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(Integer id, String name, Calendar customerSince, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.customerSince = customerSince;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(Calendar customerSince) {
        this.customerSince = customerSince;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}