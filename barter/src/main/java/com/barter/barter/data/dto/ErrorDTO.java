package com.barter.barter.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorDTO {
    private String error;
    private String message;
}
