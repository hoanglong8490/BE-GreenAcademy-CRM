package org.green.hr.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadFile {

    private final String directory = System.getProperty("user.dir") + "/images/";

    private void makeDirectoryIfNotExists(String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public String uploadFile(MultipartFile multipartFile) {
        makeDirectoryIfNotExists(directory);
        Path fileNamePath = Paths.get(directory, multipartFile.getOriginalFilename());

        try {
            Files.write(fileNamePath, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory + "/" + multipartFile.getOriginalFilename();
    }
}
