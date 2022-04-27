package com.example.eshop.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "orders_details")
data class OrderDetails(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_seq")
        @SequenceGenerator(name = "order_details_seq", sequenceName = "SEQ_DETAILS_ORDER", initialValue = 1, allocationSize = 1)
        @Column(name = "id", nullable = false)
        val id: Long,
        @ManyToOne
        @JoinColumn(name = "order_id")
        val order: Order,
        @ManyToOne
        @JoinColumn(name = "product_id")
        val product: Product,
        var amount: BigDecimal,
        var price: BigDecimal

) {
}
