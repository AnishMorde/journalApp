package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//use for the controller
@RestController
//request mapping added the mapping to the whole class
@RequestMapping("/journal")
public class JournalEntryController {

   private Map<Long , JournalEntry> journalEntries = new HashMap<>();


   //so the actual url for this get request is localhost:8080/journal/get
   @GetMapping
   public List<JournalEntry> getAll(){
       return new ArrayList<>( journalEntries.values());
   }


   //so the actual url for this post mapping is 8080/journal/post
   @PostMapping
   public String creatEntry(@RequestBody JournalEntry myEntry){
       journalEntries.put(myEntry.getId(), myEntry);
       return "succesfully added info";
   }

   //get the journal from the id pf the journal
   @GetMapping("id/{myId}")
   public JournalEntry getJournalEntryById(@PathVariable Long myId){
     return  journalEntries.get(myId);
   }

   @DeleteMapping("id/{myid}")
   public JournalEntry deleteJournalEntry(@PathVariable Long myid){
      return  journalEntries.remove(myid);

   }

   @PutMapping("id/{id}")
   public JournalEntry updateJournalById(@PathVariable Long id , @RequestBody JournalEntry myEntry){
      return journalEntries.put(id,myEntry);

   }



}
