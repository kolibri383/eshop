package com.example.eshop.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
        @SequenceGenerator(name = "order_seq", sequenceName = "SEQ_ORDER", initialValue = 1, allocationSize = 1)
        @Column(name = "id", nullable = false)
        val id: Long,
        @CreationTimestamp
        val created: LocalDateTime,
        @UpdateTimestamp
        var updated: LocalDateTime,
        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,
        var total_sum: BigDecimal,
        var adress: String,
        @OneToMany(cascade = [CascadeType.ALL])
        val details: List<OrderDetails>,
        @Enumerated(EnumType.STRING)
        var status: OrderStatus
)