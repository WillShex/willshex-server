//
//  ContextAwareServlet.java
//  willshex-server
//
//  Created by William Shakour on 19 Jul 2013.
//  Copyright © 2013 WillShex Limited. All rights reserved.
//
package com.willshex.server;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author billy1380
 * 
 */
@SuppressWarnings("serial")
public abstract class ContextAwareServlet extends HttpServlet {

	public static final ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<HttpServletRequest>();
	public static final ThreadLocal<HttpServletResponse> RESPONSE = new ThreadLocal<HttpServletResponse>();
	public static final ThreadLocal<ServletContext> CONTEXT = new ThreadLocal<ServletContext>();

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void doDelete (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDelete();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doHead(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void doHead (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doHead();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doOptions(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void doOptions (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doOptions();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void doPut (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPut();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doTrace(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void doTrace (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doTrace();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) */
	@Override
	protected void service (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		set(req, resp);

		service();

		super.service(req, resp);
	}

	/**
	 * @param req
	 * @param resp
	 */
	protected void set (HttpServletRequest req, HttpServletResponse resp) {
		REQUEST.set(req);
		RESPONSE.set(resp);
		CONTEXT.set(getServletContext());
	}

	protected void doDelete () throws ServletException, IOException {}

	protected void doGet () throws ServletException, IOException {}

	protected void doHead () throws ServletException, IOException {}

	protected void doOptions () throws ServletException, IOException {}

	protected void doPost () throws ServletException, IOException {}

	protected void doPut () throws ServletException, IOException {}

	protected void doTrace () throws ServletException, IOException {}

	protected void service () throws ServletException, IOException {}

}