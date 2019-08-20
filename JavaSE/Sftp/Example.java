			InputStream input = new ByteArrayInputStream(ImageUtil.baseToByte(picture));
			SftpUtil sftpUtil = new SftpUtil("image", "image123", "127.0.0.1");
			sftpUtil.login();
			try {
				// 存到指定位置
				sftpUtil.upload("/usr/images/", "xxx.png", input);
			} catch (SftpException e) {
				e.printStackTrace();
			}
			sftpUtil.logout();