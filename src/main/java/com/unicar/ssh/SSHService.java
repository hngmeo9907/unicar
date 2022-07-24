package com.unicar.ssh;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.unicar.entities.ProductMedia;
import com.unicar.repos.ProductMediaRepository;
import lombok.extern.slf4j.Slf4j;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
@Slf4j
public class SSHService {

    @Autowired
    private ProductMediaRepository mediaRepo;
    private final static String ip = "103.173.155.25";
    private final static Integer port = 9020;
    private final static String user = "root";
    private final static String pass = "69k2FkXw3RUx";

    private final String remoteDir = "/var/www/html/imgs-unicar/";

    private ChannelSftp channelSftp;

    @PostConstruct
    public void setUpSsh() throws JSchException {
        try {
            JSch jsch = new JSch();
            Session jschSession = jsch.getSession(user,
                    ip, port);
            jschSession.setPassword(pass);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            jschSession.setConfig(config);
            jschSession.connect();
            this.channelSftp = (ChannelSftp) jschSession.openChannel("sftp");
            this.channelSftp.connect();
        } catch (Exception e) {
            log.error("Error ssh: {0}", e);
        }
    }

    public boolean store(MultipartFile file, Integer type) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new Exception(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {

                this.channelSftp.put(inputStream, remoteDir + filename);
            }
            ProductMedia pm = new ProductMedia();
            pm.setUrl(String.format("%s%s", "http://unicarlimousine.vn/imgs-unicar/", filename));
            pm.setFilename(filename);
            pm.setUpdatedDate(new Date());
            pm.setTypeId(type);
            mediaRepo.save(pm);
        } catch (IOException e) {
            throw new IOException("Failed to store file " + filename, e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
        return true;
    }
}
