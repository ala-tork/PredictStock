package com.project.predictstock.Controllers;

import com.project.predictstock.DTO.UpdateUserDto;
import com.project.predictstock.Entities.User;
import com.project.predictstock.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<String> register(
            @PathVariable String id
    ){
        return  ResponseEntity.ok(userService.DeleteUser(id));
    }



    @PutMapping("/Update/{idUser}")
    public  ResponseEntity<User> update (@RequestBody UpdateUserDto newUser, @PathVariable String idUser) {
        try {
            User updatedUser = userService.UpdateUser(newUser, idUser);

            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/GetUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        try {
            User user = userService.GetUserById(id);
            if(user!= null){ return  ResponseEntity.ok(user);}
            else return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
