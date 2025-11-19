package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 70, columnDefinition = "NVARCHAR(70)")
    private String title;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;

    public Category() {
    }

    public Category(Long id, String title, List<Item> items) {
        this.id = id;
        this.title = title;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
