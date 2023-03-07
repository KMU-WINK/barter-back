package com.barter.barter.data.dto.trade;

import com.barter.barter.data.State;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TradeInfoDTO {
    private Long id;
    private Long pushProductId;
    private String pushUserId;
    private Long acceptProductId;
    private String acceptUserId;
    private State tradeState;
}
