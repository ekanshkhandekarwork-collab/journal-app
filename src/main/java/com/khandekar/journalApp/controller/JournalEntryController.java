package com.khandekar.journalApp.controller;

import com.khandekar.journalApp.entities.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll() {
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry newJournal) {
//        journalEntries.put(newJournal.getId(), newJournal);
//        return true;
//    }
//
//    @GetMapping("/id/{id}")
//    public JournalEntry getJournalById(@PathVariable Long id) {
//        return journalEntries.get(id);
//    }
//
//    @DeleteMapping("/id/{id}")
//    public JournalEntry deleteJournalById(@PathVariable Long id) {
//        return journalEntries.remove(id);
//    }
//
//    @PutMapping("/id/{id}")
//    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {
//        return journalEntries.put(id, journalEntry);
//    }
}
