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
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import com.willshex.server.ContextAwareServlet;

/**
 * @author William Shakour (billy1380)
 *
 */
@WebServlet(name = "letsencrypt", urlPatterns = ChallengeServlet.ROOT_PATH)
@ServletSecurity(value = @HttpConstraint(transportGuarantee = TransportGuarantee.NONE))
public class ChallengeServlet extends ContextAwareServlet {

	private static final long serialVersionUID = 4205103893856450963L;

	private static final Logger LOG = Logger
			.getLogger(ChallengeServlet.class.getName());
	public static final String ROOT_PATH = "/.well-known/acme-challenge/";
	private static final int LENGTH = ROOT_PATH.length();

	/* (non-Javadoc)
	 * 
	 * @see com.willshex.server.ContextAwareServlet#doGet() */
	@Override
	protected void doGet () throws ServletException, IOException {
		super.doGet();

		HttpServletResponse response = RESPONSE.get();

		if (!REQUEST.get().getRequestURI().startsWith(ROOT_PATH)) {
			LOG.warning("[" + REQUEST.get().getRequestURI()
					+ "] does not start with [" + ROOT_PATH + "]");
			response.sendError(404);
			return;
		}

		Properties properties = System.getProperties();
		String challengeId = REQUEST.get().getRequestURI();

		if (challengeId != null && challengeId.length() > LENGTH) {
			challengeId = challengeId.substring(LENGTH);
		}

		if (challengeId == null) {
			LOG.severe("Challenge id was null");
			response.sendError(500);
			return;
		}

		if (System.getProperty(challengeId, null) == null) {
			LOG.warning("[" + challengeId + "] system property ["
					+ System.getProperty(challengeId) + "] not found");
			response.sendError(404);
			return;
		}

		response.setContentType("text/plain");
		response.getOutputStream().print(properties.getProperty(challengeId));
	}

}
