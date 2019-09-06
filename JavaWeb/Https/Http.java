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
			        // 构造一个form表单式的实体
			        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "utf-8");
			        // 将请求实体设置到httpPost对象中
			        httpPost.setEntity(formEntity);			
					
					HttpResponse response = httpClient.execute(httpPost);
					responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
					logger.info("响应体:" + responseEntity);
					if ("912001".equals(responseEntity)) { 
						// 短信发送成功，修改“短信发送标志”“短信发送次数”字段
						dao.updateState(noPassInfo.getSfzhm(), count.toString());
						logger.info(name+"[" + phone + "]短信发送成功");

					} else {
						// 短信发送失败，只修改短息发送次数字段
						dao.updateState1(noPassInfo.getSfzhm(), count.toString());
						logger.info(name+"[" + phone + "]短信发送失败");
					}
					running = false;

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}