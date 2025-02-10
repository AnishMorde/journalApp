package net.engineeringdigest.journalApp.Repo;

import net.engineeringdigest.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {


    User findByUsername(String username);
    User deleteByUsername(String username);
}
