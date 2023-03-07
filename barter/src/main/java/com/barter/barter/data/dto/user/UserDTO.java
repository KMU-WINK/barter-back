package com.barter.barter.data.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {

    private Long id;
    private String user_id;
    private String password;
    private String name;
    private String nickname;
    private String img;
}
