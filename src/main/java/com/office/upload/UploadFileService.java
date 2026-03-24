package com.office.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class UploadFileService {

    // application.properties 값 주입
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.upload-dir-separator}")
    private String uploadDirSeparator;

    public String upload(MultipartFile file) {
        System.out.println("[UploadFileService] upload()");

        boolean result = false;

        String fileOriName = file.getOriginalFilename();
        String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());

        UUID uuid = UUID.randomUUID();
        String uniqueFileName = uuid.toString().replaceAll("-", "");

        File dir = new File(uploadDir);
        if(!dir.exists())
            dir.mkdirs();

        File saveFile = new File(uploadDir + uploadDirSeparator + uniqueFileName + fileExtension);
        try {
            file.transferTo(saveFile);
            result = true;

        } catch (Exception e) {
            e.printStackTrace();

        }

        if (result) {
            System.out.println("[UploadFileService] FILE UPLOAD SUCCESS!!");
            System.out.println("[UploadFileService] FILE NAME: " + (uniqueFileName + fileExtension));
            return uniqueFileName + fileExtension;

        } else {
            System.out.println("[UploadFileService] FILE UPLOAD FAIL!!");
            return null;

        }

    }

}
