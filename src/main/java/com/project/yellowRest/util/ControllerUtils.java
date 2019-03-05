package com.project.yellowRest.util;

import com.project.yellowRest.entity.RecordEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ControllerUtils {

    public static void saveFile(RecordEntity record, MultipartFile file, String uploadPath) throws IOException {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            record.setFilename(resultFileName);
        }
    }
}