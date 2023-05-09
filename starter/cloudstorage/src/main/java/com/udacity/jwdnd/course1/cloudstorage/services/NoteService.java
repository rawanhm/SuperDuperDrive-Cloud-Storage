package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void createEditNote(Note note) {
        // checking if the note id is not null for updating  notes
        if (note.getNoteid() != null) {
            noteMapper.update(note);
        } else {
            noteMapper.insert(note);
        }
    }
//    public void EditNote(Note note) {
//        // checking if the note id is not null
//        if (note.getNoteId() != null) {
//            noteMapper.update(note);
//        }
//    }
    public List<Note> getAllNotes(Integer userid) {
        return noteMapper.getAllNotes(userid);
    }
    public void deleteNote(Integer noteid, Integer userid) {
        noteMapper.delete(noteid, userid);
    }
}
