package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repo.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    public UserRepo userRepo;


    @ParameterizedTest
    @CsvSource({
            "anish",
            "anish123"

    })
    public void testFindByUsername(String name){
       User user =  userRepo.findByUsername(name);
        assertEquals(4 , 2+2);
        assertTrue(user.getUsername().equals(name));
        assertTrue(10>3);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "23,40,63",
            "4,5,9"
    })
    public void test(int a , int b , int expected){
        assertEquals(expected , a+b);
    }
}
