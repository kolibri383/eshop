package com.example.eshop.model

import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
        @SequenceGenerator(name = "category_seq", sequenceName = "SEQ_CATEGORY", initialValue = 1, allocationSize = 1)
        @Column(name = "id", nullable = false)
        var id: Long? = null,
        var title: String,
)