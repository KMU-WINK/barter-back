package com.barter.barter.data.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {
    private String id;
    private String password;
}
