			InputStream input = new ByteArrayInputStream(ImageUtil.baseToByte(picture));
			SftpUtil sftpUtil = new SftpUtil("image", "image123", "127.0.0.1");
			sftpUtil.login();
			try {
				// �浽ָ��λ��
				sftpUtil.upload("/usr/images/", "xxx.png", input);
			} catch (SftpException e) {
				e.printStackTrace();
			}
			sftpUtil.logout();