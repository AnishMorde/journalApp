package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//use for the controller
@RestController
//request mapping added the mapping to the whole class
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;



    @GetMapping
   public ResponseEntity<?> getAllJournalEntriesOfUser(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              String username = authentication.getName();
              User user = userService.findByUsername(username);
              List<JournalEntry> all = user.getJournalEntries();

              if(all!=null && !all.isEmpty()){
                  return  new ResponseEntity<>(all , HttpStatus.OK);
              }

       return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }


   //so the actual url for this post mapping is 8080/journal/post
   @PostMapping
   public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
       try {

           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String username = authentication.getName();
           journalEntryService.saveEntry(myEntry , username );
           return new ResponseEntity<>( myEntry ,HttpStatus.CREATED);

       } catch (Exception e) {
           return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
       }

   }

   //get the journal from the id pf the journal
   @GetMapping("id/{myId}")
   public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = authentication.getName();
       User user = userService.findByUsername(username);
       List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
       if(!collect.isEmpty()){
           Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

           if(journalEntry.isPresent()){
               return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK );
           }
       }

           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }


   @DeleteMapping("id/{myid}")
   public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myid ){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = authentication.getName();
      boolean removed =  journalEntryService.deleteById(myid , username);
      if(removed){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);


   }



   @PutMapping("id/{myid}")
   public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myid , @RequestBody JournalEntry newEntry ){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username = authentication.getName();
       User user = userService.findByUsername(username);
       List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
       if(!collect.isEmpty()){
           Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
           if(journalEntry.isPresent()){
               JournalEntry old =  journalEntry.get();
                   old.setTittle(newEntry.getTittle() != null && !newEntry.getTittle().equals("")? newEntry.getTittle() : old.getTittle());
                   old.setContent(newEntry.getContent()!= null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                   journalEntryService.saveEntry(old);
                   return  new ResponseEntity<>(old ,HttpStatus.OK);

           }
       }

       return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }



}
