package com.example.apichallenge.Controller;


import com.example.apichallenge.ApiResponse.ApiResponse;
import com.example.apichallenge.DTO.UserDTO;
import com.example.apichallenge.JWT.JwtFilter;
import com.example.apichallenge.JWT.JwtUtil;
import com.example.apichallenge.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    UserService ser;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userdto){
       String email=userdto.getEmail();
       String password=userdto.getPassword();
       if(ser.getUserByEmail(email)!=null){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("User with this email already exists",HttpStatus.CONFLICT,null));
       }
       userdto.setPassword(encoder.encode(password));
       UserDTO dto=ser.addUser(userdto);
       return ResponseEntity.status(HttpStatus.CREATED).body(new  ApiResponse("User registered successfully",HttpStatus.CREATED,dto));

    }

    @PostMapping("/login/{email}/{pwd}")
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String pwd){
        UserDTO dto=ser.getUserByEmail(email);
        if(dto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Invalid email id",HttpStatus.NOT_FOUND,null));
        }
       //encoded email and checks with stored one
        if(!encoder.matches(pwd,dto.getPassword())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Invalid Credentials",HttpStatus.NOT_FOUND,null));
        }

        String token=jwtUtil.generateToken(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Token Generated",HttpStatus.OK,token));

    }

}
