package com.barter.barter.data.entity;

import com.barter.barter.data.State;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "trade")
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="push_product")
    private ProductEntity pushProduct;

    @ManyToOne
    @JoinColumn(name = "accept_product")
    private ProductEntity acceptProduct;
    @Enumerated(EnumType.STRING)
    private State tradeState;
}
