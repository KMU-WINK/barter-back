package com.barter.barter.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserPostDTO {
    private String id;
    private String password;
    private String name;
    private String nickname;
}
