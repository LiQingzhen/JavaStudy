				String url = "http://127.0.0.1:8080/send_message/SendMessageServlet";
				CloseableHttpClient httpClient;
				HttpPost httpPost;
				String responseEntity = null;
				
				//CloseableHttpClient client = HttpClients.createDefault();
				//HttpPost post = new HttpPost(URL);
				//JSONObject response = null;

				//StringEntity s = new StringEntity(jsonData);
				//s.setContentEncoding("UTF-8");
				//s.setContentType("application/json");
				//post.setEntity(s);
				//HttpResponse res = client.execute(post);
				//if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//					HttpEntity entity = res.getEntity();
//					String result = EntityUtils.toString(res.getEntity());
//					response = JSONObject.fromObject(result);
//				}
				//logger.info(">>>>>>>>>>>>responseJson:" + response);
				try {
					httpPost = new HttpPost(url);
					httpClient = HttpClients.createDefault();
					List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
			        parameters.add(new BasicNameValuePair("content", content));
			        parameters.add(new BasicNameValuePair("phone", phone));
			        // ����һ��form����ʽ��ʵ��
			        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "utf-8");
			        // ������ʵ�����õ�httpPost������
			        httpPost.setEntity(formEntity);			
					
					HttpResponse response = httpClient.execute(httpPost);
					responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
					logger.info("��Ӧ��:" + responseEntity);
					if ("912001".equals(responseEntity)) { 
						// ���ŷ��ͳɹ����޸ġ����ŷ��ͱ�־�������ŷ��ʹ������ֶ�
						dao.updateState(noPassInfo.getSfzhm(), count.toString());
						logger.info(name+"[" + phone + "]���ŷ��ͳɹ�");

					} else {
						// ���ŷ���ʧ�ܣ�ֻ�޸Ķ�Ϣ���ʹ����ֶ�
						dao.updateState1(noPassInfo.getSfzhm(), count.toString());
						logger.info(name+"[" + phone + "]���ŷ���ʧ��");
					}
					running = false;

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}