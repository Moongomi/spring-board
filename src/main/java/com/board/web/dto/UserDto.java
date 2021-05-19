package com.board.web.dto;

import com.board.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
