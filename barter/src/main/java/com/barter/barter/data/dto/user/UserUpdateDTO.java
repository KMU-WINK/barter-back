package com.barter.barter.data.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserUpdateDTO {
    private String id;
    private String password;
    private String nickname;
    private String img;
}
