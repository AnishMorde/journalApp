package net.engineeringdigest.journalApp.Entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Getter
@Setter
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    //username and the passoword should not null
    @NonNull
   private String username;
    @NonNull
   private String password;

    //We create the refference of the journal entries in the user
    @DBRef
    private List<JournalEntry>  journalEntries = new ArrayList<>();
    private  List<String> roles;




}
