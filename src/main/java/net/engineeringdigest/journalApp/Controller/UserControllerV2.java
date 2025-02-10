package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repo.UserRepo;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//use for the controller
@RestController
//request mapping added the mapping to the whole class
@RequestMapping("/user")
public class UserControllerV2 {

   @Autowired
    private UserService userService;

   @Autowired
   private UserRepo userRepo;





    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  username =  authentication.getName();

        User userInDb = userService.findByUsername(username);
           userInDb.setUsername(user.getUsername());
           userInDb.setPassword(user.getPassword());
           userService.saveNewUser(userInDb);


       return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }


    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }


}
