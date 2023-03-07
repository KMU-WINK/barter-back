package com.barter.barter.data.dto.trade;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuggestTradeDTO {
    private Long pushId;
    private Long acceptId;
}
