package com.example.apichallenge.Mapper;

import com.example.apichallenge.DTO.UserDTO;
import com.example.apichallenge.Entity.User;

public class UserMapper {
    public static User toEntity(UserDTO dto) {
         User user=new User();
      //   user.setId(dto.getId());
         user.setName(dto.getName());
         user.setEmail(dto.getEmail());
         user.setPassword(dto.getPassword());
         return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto=new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
