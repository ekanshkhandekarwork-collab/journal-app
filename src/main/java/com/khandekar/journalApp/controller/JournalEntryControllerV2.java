package com.khandekar.journalApp.controller;

import com.khandekar.journalApp.entities.JournalEntry;
import com.khandekar.journalApp.entities.User;
import com.khandekar.journalApp.services.JournalEntryService;
import com.khandekar.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if(journalEntries != null && !journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@PathVariable String username, @RequestBody JournalEntry newJournal) {
        try{
            journalEntryService.saveEntry(newJournal, username);
            return new ResponseEntity<>(newJournal, HttpStatus.CREATED);
        } catch(Exception e) {
            System.out.println("Error- " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(id);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable String username,@PathVariable ObjectId id) {
        journalEntryService.deleteEntryById(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id,
                                                          @RequestBody JournalEntry newEntry,
                                                          @PathVariable String username) {
        try{
            JournalEntry oldJournalEntry = journalEntryService.getEntryById(id).orElse(null);
            if(oldJournalEntry != null) {
                if(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                    oldJournalEntry.setTitle(newEntry.getTitle());
                }
                if(newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                    oldJournalEntry.setContent(newEntry.getContent());
                }
                journalEntryService.saveEntry(oldJournalEntry);
                return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
