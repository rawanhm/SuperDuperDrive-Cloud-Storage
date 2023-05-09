package com.udacity.jwdnd.course1.cloudstorage.Controller;


import com.udacity.jwdnd.course1.cloudstorage.Model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final FileService fileService;
    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(FileService fileService, UserService userService,
                          NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String homeView(Authentication authentication, Model model) {
        String currentUsername = authentication.getName();
        User user = userService.getUser(currentUsername);
        model.addAttribute("files", fileService.getFileNames(user.getUserid()));
        model.addAttribute("notes", noteService.getAllNotes(user.getUserid()));
        model.addAttribute("credentials", credentialService.getAllCredentials(user.getUserid()));
        model.addAttribute("file",new File());
        model.addAttribute("note", new Note());
        model.addAttribute("credential", new Credential());
        return "home";
    }
}
