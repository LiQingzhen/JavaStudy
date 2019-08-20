package com.ems.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
/**
 * sftp服务器的连接，登陆，上传，退出
 * @author liqin
 *
 */
public class SftpUtil {
	
	private  Logger log = LogManager.getLogger(SftpUtil.class);
    
    private ChannelSftp sftp;
      
    private Session session;
      
    private String username;	// 登录用户名
      
    private String password;	// 登录密码

    private String host;	// FTP 服务器地址IP地址

    // 构造器
    public SftpUtil(){}
    public SftpUtil(String username, String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
    }
    
    /**
     * 账号密码登陆
     */
    public void login(){
        try {
            JSch jsch = new JSch();
            log.info("sftp connect by host:" + host + "username:" + username);
            session = jsch.getSession(username, host);
            log.info("Session is build");
            if (password != null) {
                session.setPassword(password);  
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
              
            session.setConfig(config);
            session.connect();
            log.info("Session is connected");
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            log.info("channel is connected");
  
            sftp = (ChannelSftp) channel;
            log.info(String.format("sftp server host:[%s] is connect successfull", host));
        } catch (JSchException e) {
        	e.printStackTrace();
        }
    }  
    
    public void logout(){
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                log.info("sftp is closed already");
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                log.info("sshSession is closed already");
            }
        }
    }
    
    public void upload(String directory, String sftpFileName, InputStream input) throws SftpException{
        try {  
            sftp.cd(directory);
        } catch (SftpException e) {
            log.warn("directory is not exist");
            sftp.mkdir(directory);
            sftp.cd(directory);
        }
        sftp.put(input, sftpFileName);
        log.info("file:"+sftpFileName+" is upload successful");
    }
    
}
