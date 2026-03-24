package com.office.upload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

    private final UploadFileService uploadFileService;

    public HomeController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @GetMapping({"", "/"})
    public String home() {
        System.out.println("[HomeController] home()");

        return "home";

    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile file, Model model) {
        System.out.println("[HomeController] uploadFile()");

        String nextPage = "home";

        String savedFileName = uploadFileService.upload(file);
        if (savedFileName != null) {
            model.addAttribute("imageUrl", "/uploads/" + savedFileName);
        }

        return nextPage;

    }

}
