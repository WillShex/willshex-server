//
//  ChallengeServlet.java
//  willshex-server
//
//  Created by William Shakour (billy1380) on 15 Jun 2016.
//  Copyright © 2016 WillShex Limited. All rights reserved.
//
package com.willshex.server.letsencrypt;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.willshex.server.ContextAwareServlet;

/**
 * @author William Shakour (billy1380)
 *
 */
public class ChallengeServlet extends ContextAwareServlet {

	private static final long serialVersionUID = 4205103893856450963L;

	/* (non-Javadoc)
	 * 
	 * @see com.willshex.server.ContextAwareServlet#doGet() */
	@Override
	protected void doGet () throws ServletException, IOException {
		super.doGet();

		HttpServletResponse response = RESPONSE.get();

		if (!REQUEST.get().getRequestURI()
				.startsWith("/.well-known/acme-challenge/")) {
			response.sendError(404);
			return;
		}

		Properties properties = System.getProperties();
		String challengeId = REQUEST.get().getRequestURI()
				.substring("/.well-known/acme-challenge/".length());
		
		if (!System.getProperties().contains(challengeId)) {
			response.sendError(404);
			return;
		}

		response.setContentType("text/plain");
		response.getOutputStream().print(properties.getProperty(challengeId));
	}

}
