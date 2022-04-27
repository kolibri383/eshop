package com.example.eshop.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
        @SequenceGenerator(name = "product_seq", sequenceName = "SEQ_PRODUCT", initialValue = 1, allocationSize = 1)
        @Column(name = "id", nullable = false)
        var id: Long? = null,
        var title: String,
        var price: BigDecimal,
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name = "products_categories",
                joinColumns = [JoinColumn(name = "product_id")],
                inverseJoinColumns = [JoinColumn(name = "category_id")])
        val categories: List<Category>,
)