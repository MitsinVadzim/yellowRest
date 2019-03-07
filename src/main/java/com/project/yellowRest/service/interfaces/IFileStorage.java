package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.response.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileStorage {
    UploadFileResponse storeFile(MultipartFile file);
    List<UploadFileResponse> storeFile(MultipartFile[] files);
    Resource loadFileAsResource(String fileName);
}
