package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/note")
public class NoteController {


    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }
   @PostMapping
    public String createEditNote(Authentication auth, Model model, @ModelAttribute Note note) {
        Integer userid = userService.getCurrentUserId(auth);
        note.setUserid(userid);
        try {
            noteService.createEditNote(note);
            model.addAttribute("isChangeSuccess", true);
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-notes-tab");
        return "result";
    }
    /* @PostMapping
    public String EditNote(Authentication auth, Model model, @ModelAttribute Note note) {
        Integer userid = userService.getCurrentUserId(auth);
        note.setUserId(userid);
        try {
            noteService.EditNote(note);
            model.addAttribute("isChangeSuccess", true);
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-notes-tab");
        return "result";
    }*/
    @PostMapping("/{noteid}")
    public String deleteFile(Authentication auth, @PathVariable Integer noteid, Model model) {
        Integer userid = userService.getCurrentUserId(auth);
        try {
            noteService.deleteNote(noteid, userid);
            model.addAttribute("isChangeSuccess", true);
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-notes-tab");
        //return it to result page
        return "result";
    }
}
