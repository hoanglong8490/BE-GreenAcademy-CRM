package org.green.hr.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class UploadFile {

    // Đường dẫn lưu trữ ảnh trong thư mục resources/static/images
    private static final String UPLOAD_DIR = "src/main/resources/static/hr_img/";

    private void makeDirectoryIfNotExists() {
        File dir = new File(UploadFile.UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public String uploadFile(MultipartFile multipartFile) {
        makeDirectoryIfNotExists();
        // Tạo tên file duy nhất bằng UUID
        String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        try {
            Files.write(filePath, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Trả về đường dẫn của ảnh (trong thư mục static, có thể truy cập từ URL)
        return "/images/" + fileName;
    }
}
