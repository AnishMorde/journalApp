package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health")
    public String health(){
        return "health is ok";
    }

    @PostMapping("/create-user")
    public boolean createUser(@RequestBody User user){
        userService.saveNewUser(user);
        return true;
    }


}
