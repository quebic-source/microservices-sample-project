package com.quebic.auth.api.common.util.email.template.impl;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.quebic.auth.api.common.util.email.template.EmailTemplate;
import com.quebic.auth.api.model.User;

public class UserVerificationEmailTemplate implements EmailTemplate{

	private String templateFile;
	private User user;
	private String token;

	public UserVerificationEmailTemplate(String templateFile, User user, String token) {
		this.templateFile = templateFile;
		this.user = user;
		this.token = token;
	}

	@Override
	public String getTextContent() throws Exception{
		
		Resource resource = new ClassPathResource(templateFile);
		try {
			byte[] bytes = new byte[1024];
			InputStream resourceInputStream = resource.getInputStream();
			StringBuilder str = new StringBuilder();
			int read = resourceInputStream.read(bytes);
			while(read != -1){
				str.append(new String(bytes));
				read = resourceInputStream.read(bytes);
			}
			String htmlString = str.toString();
			return String.format(htmlString, user.getFirstName(), token);
		} catch (IOException e) {
			throw new Exception("UserVerificationEmailTemplate Genarating Error", e);
		}
	}
}
