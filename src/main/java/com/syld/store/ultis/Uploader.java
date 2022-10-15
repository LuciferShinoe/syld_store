package com.syld.store.ultis;

import antlr.StringUtils;
import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public class Uploader {

    String upload_dir = "src/main/webapp/assets/uploads/";

    public String upload(MultipartFile file, String identity) {
        try {
            this.upload_dir += identity.toLowerCase();
//            get file type
            String extension = "";

            int i = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf('.');
            if (i > 0) {
                extension = file.getOriginalFilename().substring(i + 1);
            }
//            end
            String filename = UUID.randomUUID().toString() + "." + extension;
            Path filenameAndPath = Paths.get(upload_dir, filename);
            if (!Files.exists(Paths.get(this.upload_dir))) {
                Files.createDirectories(Paths.get(this.upload_dir));
            }
            Files.write(filenameAndPath, file.getBytes());
            return "/assets/uploads/"+identity.toLowerCase()+"/" + filename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void remove(String filePath){
        Path path = Paths.get(this.upload_dir.substring(0,this.upload_dir.indexOf("/assets")) + filePath);
        if (Files.exists(path)){
            File file = new File(filePath);
            file.delete();
        }
    }
}
