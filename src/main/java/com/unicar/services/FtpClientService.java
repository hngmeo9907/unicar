//package com.unicar.services;
//
//import lombok.extern.log4j.Log4j;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.net.PrintCommandListener;
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.net.ftp.FTPReply;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.*;
//
//@Service
//@Slf4j
//public class FtpClientService {
//
//    // Creating FTP Client instance
//    FTPClient ftp;
//
//    // Constructor to connect to the FTP Server
//    @PostConstruct
//    public void FtpClient() throws Exception {
//
//        FTPClient ftp = new FTPClient();
//        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//        int reply;
//        ftp.connect("ssh root@103.173.155.25", 9020);
//        log.info("FTP URL is: {}", ftp.getDefaultPort());
//        reply = ftp.getReplyCode();
//        if (!FTPReply.isPositiveCompletion(reply)) {
//            ftp.disconnect();
//            throw new Exception("Exception in connecting to FTP Server");
//        }
//        ftp.login("root", "uck6hj7kK4dkVh");
//        ftp.setFileType(FTP.BINARY_FILE_TYPE);
//        ftp.enterLocalPassiveMode();
//    }
//
//    // Method to upload the File on the FTP Server
//    public void uploadFTPFile(String localFileFullName, String fileName, String hostDir)
//            throws Exception {
//        try {
//            InputStream input = new FileInputStream(new File(localFileFullName));
//
//            this.ftp.storeFile(hostDir + fileName, input);
//        } catch (Exception e) {
//
//        }
//    }
//
//    // Download the FTP File from the FTP Server
//    public void downloadFTPFile(String source, String destination) {
//        try (FileOutputStream fos = new FileOutputStream(destination)) {
//            this.ftp.retrieveFile(source, fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // list the files in a specified directory on the FTP
//    public boolean listFTPFiles(String directory, String fileName) throws IOException {
//        // lists files and directories in the current working directory
//        boolean verificationFilename = false;
//        FTPFile[] files = ftp.listFiles(directory);
//        for (FTPFile file : files) {
//            String details = file.getName();
//            System.out.println(details);
//            if (details.equals(fileName)) {
//                System.out.println("Correct Filename");
//            } else {
//                log.info("Verification Failed: The filename {} is not updated at the CDN end.", details);
//            }
//        }
//        return verificationFilename;
//    }
//
//    // Disconnect the connection to FTP
//    public void disconnect() {
//        if (this.ftp.isConnected()) {
//            try {
//                this.ftp.logout();
//                this.ftp.disconnect();
//            } catch (IOException f) {
//                // do nothing as file is already saved to server
//            }
//        }
//    }
//}
