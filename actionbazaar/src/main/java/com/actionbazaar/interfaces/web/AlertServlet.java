package com.actionbazaar.interfaces.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "AlertServlet", urlPatterns = { "/alerts" })
public class AlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/xml");

		PrintWriter out = response.getWriter();

		out.println("<alerts>");
		out.println("<greeting>" + "Welcome, will sending available alerts"
				+ "</greeting>");

		long userId = Long.parseLong(request.getParameter("user_id"));

		String[] alerts = { "Fraud alert!", "Outbid alert!",
				"Item available alert!", "Fees due alert!",
				"Payment failure alert!" };
		for (int i = 0; i < alerts.length; i++) {
			out.println("<alert><user>" + userId + "</user><text>" + alerts[i]
					+ "</text></alert>");
		}

		out.println("<goodbye>" + "No more alerts for now, timing out"
				+ "</goodbye>");
		out.println("</alerts>");
	}
}