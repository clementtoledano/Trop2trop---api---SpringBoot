package com.tro2tro.api.controller;


import com.tro2tro.api.dao.ImageRepository;
import com.tro2tro.api.entity.Image;
import com.tro2tro.api.payload.response.MessageResponse;
import com.tro2tro.api.upload.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class FilesController {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);

            try {
                Image tempImage = new Image();
                tempImage.setName(file.getOriginalFilename());
                tempImage.setUrl(file.getOriginalFilename());
                tempImage.setActive(true);
                Image tmpImg = this.imageRepository.save(tempImage);
                message = "{\"id\":" + tmpImg.getId() + ", \"name\":\"" + file.getOriginalFilename() + "\"}";
                return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));

            } catch (Exception e) {
                message = "Could not upload the file: fc " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
            }
        } catch (Exception e) {
            message = "Could not upload the file: fc " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }

    // @GetMapping("/files")
    // public ResponseEntity<List<FileUploadInfo>> getListFiles() {
    //     List<FileUploadInfo> fileInfos = storageService.loadAll().map(path -> {
    //         String filename = path.getFileName().toString();
    //         String url = MvcUriComponentsBuilder
    //                 .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
    //
    //         return new FileUploadInfo(filename, url);
    //     }).collect(Collectors.toList());
    //
    //     return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    // }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource fileInfos = storageService.load(filename);
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
}
