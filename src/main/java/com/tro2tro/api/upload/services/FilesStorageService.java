package com.tro2tro.api.upload.services;


import java.nio.file.Path;
import java.util.stream.Stream;

import com.tro2tro.api.payload.request.FileUploadInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
