package com.barter.barter.data.entity;

import com.barter.barter.data.dto.user.UserDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_id;

    private String password;
    private String name;
    private String nickname;
    private String img;

    public UserDTO toDto(){
        return UserDTO.builder()
                .id(id)
                .user_id(user_id)
                .name(name)
                .nickname(nickname)
                .img(img)
                .build();
    }
}
