package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if(all!=null && !all.isEmpty() ){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(all , HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public boolean createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
        return true;
    }


}
