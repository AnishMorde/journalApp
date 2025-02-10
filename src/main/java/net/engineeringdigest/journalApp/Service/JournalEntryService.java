package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repo.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    //dependency injection
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
      try {
          journalEntry.setDate(LocalDateTime.now());
          User user = userService.findByUsername(username);
          JournalEntry saved = journalEntryRepo.save(journalEntry);
          user.getJournalEntries().add(saved);
          userService.saveUser(user);
      } catch (Exception e) {
          throw new RuntimeException("Error while saving the entry");
      }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myid){
       return journalEntryRepo.findById(myid);
    }


    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
      try {
          User user = userService.findByUsername(username);
         removed =   user.getJournalEntries().removeIf(x -> x.getId().equals(id));
          if (removed){
              userService.saveUser(user);
              journalEntryRepo.deleteById(id);
          }
      } catch (Exception e) {
          throw new RuntimeException("An error occured while deleting the entry" , e);
      }

        return removed;

    }











}

//controller-->service-->repo
