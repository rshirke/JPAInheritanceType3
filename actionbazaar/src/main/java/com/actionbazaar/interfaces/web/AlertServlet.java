package com.actionbazaar.interfaces.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
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
		
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		long userId = Long.parseLong(request.getParameter("user_id"));

		try (JsonGenerator generator = Json.createGenerator(out)) {
			generator
					.writeStartObject()
					.write("greeting", "Welcome, will sending available alerts")
					.writeStartArray("alerts");

			String[] alerts = { "Fraud alert!", "Outbid alert!",
					"Item available alert!", "Fees due alert!",
					"Payment failure alert!" };

			for (int i = 0; i < alerts.length; i++) {
				generator.writeStartObject().write("user", userId)
						.write("text", alerts[i]).writeEnd();
			}

			generator
					.writeEnd()
					.write("goodbye", "No more alerts for now, timing out")
					.writeEnd()
					.close();
		}
	}
}