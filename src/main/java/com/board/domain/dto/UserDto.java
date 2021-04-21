package com.board.domain.dto;

import com.board.domain.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;

    public User toEntity(){
        User build = User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .build();
        return build;
    }

    @Builder
    public UserDto(String username,String password){
        this.username = username;
        this.password = password;
    }
}
