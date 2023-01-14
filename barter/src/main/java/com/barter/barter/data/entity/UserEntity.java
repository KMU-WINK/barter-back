package com.barter.barter.data.entity;

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
    String id;

    String password;
    String name;
    String nickname;
    String img;
}
