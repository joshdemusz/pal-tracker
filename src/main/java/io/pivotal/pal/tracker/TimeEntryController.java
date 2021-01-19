package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    public TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        ResponseEntity re = timeEntryRepository.delete(timeEntryId);
//        re.
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry>  update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(timeEntryId, timeEntry);
        if(updatedTimeEntry == null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedTimeEntry);
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry>  create(@RequestBody TimeEntry timeEntry) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntryRepository.create(timeEntry));
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry>  read(@PathVariable long timeEntryId) {
        TimeEntry readTimeEntry = timeEntryRepository.find(timeEntryId);
        if(readTimeEntry == null) {
            return new ResponseEntity<>(readTimeEntry, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(readTimeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());
    }
}