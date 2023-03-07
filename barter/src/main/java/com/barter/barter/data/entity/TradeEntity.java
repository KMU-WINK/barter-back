package com.barter.barter.data.entity;

import com.barter.barter.data.State;
import com.barter.barter.data.dto.trade.TradeInfoDTO;
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

    public TradeInfoDTO toDto(){
        return TradeInfoDTO.builder()
                .id(id)
                .pushProductId(pushProduct.getId())
                .pushUserId(pushProduct.getUser().getUser_id())
                .acceptProductId(acceptProduct.getId())
                .acceptUserId(acceptProduct.getUser().getUser_id())
                .tradeState(tradeState)
                .build();
    }
}
