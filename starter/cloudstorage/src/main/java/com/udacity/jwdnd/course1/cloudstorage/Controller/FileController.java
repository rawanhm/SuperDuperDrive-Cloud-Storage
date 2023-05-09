package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final UserService userService;


    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }
    @PostMapping
    public String uploadFile(Authentication auth, @RequestParam("fileUpload") MultipartFile file, Model model) {
        Integer userid = userService.getCurrentUserId(auth);
        try {
            if (fileService.isFile(file, userid)) {
                model.addAttribute("hasSpecificError", true);
                model.addAttribute("errorMsg", "File with same name already exists. File not uploaded.");
            } else {
                fileService.storeFile(file, userid);
                model.addAttribute("isChangeSuccess", true);
            }
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "");
        return "result";
    }
    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer fileId, Authentication auth) {
        Integer userid = userService.getCurrentUserId(auth);
        File file = fileService.getFile(fileId, userid);
        //download file in local machine :)
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(file.getFiledata()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFilename())
                .body(resource);
    }
    @PostMapping("/{fileId}")
    public String deleteFile(Authentication auth, @PathVariable Integer fileId, Model model) {
        Integer userid = userService.getCurrentUserId(auth);
        try {
            fileService.deleteFile(fileId, userid);
            model.addAttribute("isChangeSuccess", true);
        } catch (Exception e) {
            model.addAttribute("hasGenericError", true);
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "");
        //return it to result page
        return "result";
    }
}
