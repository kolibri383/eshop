package com.example.eshop.model

import javax.persistence.*

@Entity
@Table(name = "carts")
data class Cart(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
        @SequenceGenerator(name = "cart_seq", sequenceName = "SEQ_CART", initialValue = 1, allocationSize = 1)
        @Column(name = "id", nullable = false)
        val id: Long,
        @OneToOne
        @JoinColumn(name = "user_id")
        val user: User,
        @ManyToMany
        @JoinTable(name = "cart_products",
                joinColumns = [JoinColumn(name = "cart_id")],
                inverseJoinColumns = [JoinColumn(name = "product_id")])
        val product: List<Product>,
)