package com.unicar.controller;

import com.unicar.dto.UploadResponse;
import com.unicar.services.FileStorageService;
import com.unicar.ssh.SSHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Autowired
    private SSHService sshService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file
    ) {
        String fileName = fileStorageService.storeFile(file);

        UploadResponse uploadResponse = new UploadResponse(fileName);

        return ResponseEntity.ok().body(uploadResponse);
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "add-media";
    }

    @PostMapping("/upload/v2")
    public String uploadFileV2(@RequestParam(name = "file", required = false) MultipartFile file,
                               @RequestParam(name = "type", required = false) Integer type,
                               Model model)
            throws Exception {
        boolean upload = sshService.store(file, type);
        if (upload) {
            model.addAttribute("success", "File Uploaded Successfully!!!");
        }
        return "add-media";
    }
}
