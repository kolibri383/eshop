package com.example.eshop.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
        @SequenceGenerator(name = "user_seq", sequenceName = "SEQ_USER", initialValue = 1, allocationSize = 1)
        @Column(name = "id", nullable = false)
        var id: Long? = null,
        var name: String,
        var password: String,
        var email: String?,
        var archive: Boolean = false,
        @Enumerated(EnumType.STRING)
        var role: Role,
        @OneToOne(cascade = [CascadeType.REMOVE])
        var cart: Cart?,
)
