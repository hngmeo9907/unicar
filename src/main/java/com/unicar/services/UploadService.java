package com.unicar.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class UploadService {
    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        //Extension
        String fileExtensionName = fileName.substring(fileName.lastIndexOf('.')+1);
        //Use UUID to prevent duplicate file names and overwrite other people's files
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        //new file
        File fileDir = new File(path);
        //Determine whether the file exists, and create a new one if it does not exist
        if(!fileDir.exists()){
            //Make the file modifiable, because after Tomcat publishes the service, the permissions of the file may not be modifiable
            fileDir.setWritable(true);
            //dirs is used to solve the problem that if a folder is not created in the uploaded path, it will automatically create a folder
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //So far, the file has been uploaded to the server successfully

            //The next step is to upload the file to the FTP server and connect with the FTP file server
//            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //File uploaded to FTP

            //After uploading, delete the file under upload
            targetFile.delete();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  targetFile.getName();

    }
}
