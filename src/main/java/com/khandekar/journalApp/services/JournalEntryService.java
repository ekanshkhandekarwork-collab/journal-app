package com.khandekar.journalApp.services;

import com.khandekar.journalApp.entities.JournalEntry;
import com.khandekar.journalApp.entities.User;
import com.khandekar.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String username) {
        User user = userService.getUserByUsername(username);
        if(user != null) {
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(savedEntry);
            userService.saveEntry(user);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
    public void deleteEntryById(ObjectId id, String username) {
        User user = userService.getUserByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
