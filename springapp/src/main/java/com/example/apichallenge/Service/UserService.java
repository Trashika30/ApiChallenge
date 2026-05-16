package com.example.apichallenge.Service;

import com.example.apichallenge.DTO.UserDTO;
import com.example.apichallenge.Entity.User;
import com.example.apichallenge.Mapper.UserMapper;
import com.example.apichallenge.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    public UserDTO  addUser(UserDTO userdto)
    {
     User user= UserMapper.toEntity(userdto);
     User u=repo.save(user);
     return UserMapper.toDTO(u);
    }

    public UserDTO getUserByEmail(String email)
    {
        User u=repo.findByEmail(email);
        if(u==null)return null;
        UserDTO dto=UserMapper.toDTO(u);
        dto.setPassword(u.getPassword());
        return dto;
    }
}
