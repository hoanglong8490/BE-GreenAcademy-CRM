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

    // Đường dẫn lưu trữ ảnh trong thư mục uploads (ngoài src)
    private static final String UPLOAD_DIR = "uploads/hr_img/";
    private static final String UPLOAD_DIR_FILE = "uploads/hr_file/";

    private void makeDirectoryIfNotExists() {
        File dir = new File(UPLOAD_DIR);
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
        // Trả về đường dẫn của ảnh (trong thư mục uploads, có thể truy cập từ URL)
        return "/hr_img/" + fileName;
    }

    public String uploadFileContract(MultipartFile multipartFile) {
        makeDirectoryIfNotExists();
        // Tạo tên file duy nhất bằng UUID
        String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR_FILE + fileName);

        try {
            Files.write(filePath, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Trả về đường dẫn của ảnh (trong thư mục uploads, có thể truy cập từ URL)
        return "/hr_file/" + fileName;
    }
}
