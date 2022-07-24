package com.unicar.services;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Slf4j
public class FTPUtils {
    private final static String ftpIp = "103.173.155.25";
    private final static Integer ftpPort = 9020;
    private final static String ftpUser = "root";
    private final static String ftpPass = "uck6hj7kK4dkVh";

    public FTPUtils(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pass = pwd;
    }


    /**
     * Methods of uploading files exposed to the outside world
     *
     * @param fileList
     * @return
     */
    public static boolean uploadFile(List<MultipartFile> fileList) throws IOException, JSchException {
        FTPUtils ftpUtil = new FTPUtils(ftpIp, ftpPort, ftpUser, ftpPass);
        log.info("Start connection FTP The server");
        //Throw the exception to the service layer and do not handle it here
        boolean result = ftpUtil.uploadFile("/var/www/html/imgs-unicar/", fileList);
        log.info("Start connection FTP Server, end upload, upload results{}", result);
        return result;
    }

//    private boolean uploadFile(String remotePath, List<MultipartFile> fileList) throws IOException {
//        boolean uploaded = true;
//        InputStream fis = null;
//        //Connect to FTP server
////        if (connectServer(this.getIp(), this.getUser(), this.getPwd())) {
//        if (connectServer(ftpIp, ftpPort, ftpUser, ftpPass)) {
//            try {
//                ftpClient.changeWorkingDirectory(remotePath);
//                ftpClient.setBufferSize(1024);
//                ftpClient.setControlEncoding("UTF-8");
//                //Set to binary format to prevent garbled code
//                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                //Passive mode storage
//                ftpClient.enterLocalPassiveMode();
//                //Traverse file store
//                for (MultipartFile fileItem : fileList) {
//                    //Convert files to streams
//                    fis = fileItem.getInputStream();
//                    //Call storeFile method to store
//                    ftpClient.storeFile(fileItem.getName(), fis);
//                }
//            } catch (IOException e) {
//                log.error("Upload file exception", e);
//                uploaded = false;
//
//            } finally {
//                //Close connections and FileStream
//                fis.close();
//                ftpClient.disconnect();
//            }
//        }
//        return uploaded;
//    }

    private boolean uploadFile(String remotePath, List<MultipartFile> fileList) throws IOException, JSchException {
        boolean uploaded = true;
        InputStream fis = null;
        //Connect to FTP server
//        if (connectServer(this.getIp(), this.getUser(), this.getPwd())) {
        if (connectServerSSH(ftpIp, ftpPort, ftpUser, ftpPass)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //Set to binary format to prevent garbled code
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                //Passive mode storage
                ftpClient.enterLocalPassiveMode();
                //Traverse file store
                for (MultipartFile fileItem : fileList) {
                    //Convert files to streams
                    fis = fileItem.getInputStream();
                    //Call storeFile method to store
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException e) {
                log.error("Upload file exception", e);
                uploaded = false;

            } finally {
                //Close connections and FileStream
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    /**
     * Connect to FTP server
     *
     * @param ip
     * @param user
     * @param pwd
     * @return
     */
    private boolean connectServer(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            log.error("FTP Server connection failed", e);
        }
        return isSuccess;
    }

    private boolean connectServerSSH(String ip, int port, String user, String pwd) throws JSchException {
        boolean isSuccess = false;
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, ip, port);
            session.setConfig("PreferredAuthentications", "password");
            session.setPassword(pwd);
            session.connect();
            Channel channel = session.openChannel("sftp");
            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.connect();
            isSuccess = true;
        } catch (JSchException e) {
            log.error("FTP Server connection failed", e);
        }
        return isSuccess;
    }

    private String ip;
    private int port;
    private String user;
    private String pass;
    private FTPClient ftpClient;

    public static String getFtpTp() {
        return ftpIp;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
