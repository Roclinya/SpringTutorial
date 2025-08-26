package com.tutorial.SpringTutorial.entity;

import jakarta.persistence.*;

@Entity
//@Table(name = "stock",schema = "GSMUSER")
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "amount")
    private int amount;

    @Version
    @Column(name = "version")
    private int version;

    // getters and setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}