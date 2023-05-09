package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/credential")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String createEditCredential(Authentication auth, Model model, @ModelAttribute Credential credential) {
        Integer userid = userService.getCurrentUserId(auth);
        credential.setUserid(userid);
        try {
            credentialService.createOrEditCredential(credential);
            model.addAttribute("isChangeSuccess", true);
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-credentials-tab");
        return "result";
    }

    /*  @PostMapping
    public String EditCredential(Authentication auth, Model model, @ModelAttribute Credential credential) {
        Integer userid = userService.getCurrentUserId(auth);
        credential.setUserid(userid);
        try {
            credentialService.EditCredential(credential);
            model.addAttribute("isChangeSuccess", true);
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-credentials-tab");
        return "result";
    }*/
    @PostMapping("/{credentialid}")
    public String deleteCredential(Authentication auth, @PathVariable Integer credentialid, Model model) {
        Integer userid = userService.getCurrentUserId(auth);
        try {
            credentialService.deleteCredential(credentialid, userid);
            model.addAttribute("isChangeSuccess", true);
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-credentials-tab");
        //return it to result page
        return "result";
    }

}
