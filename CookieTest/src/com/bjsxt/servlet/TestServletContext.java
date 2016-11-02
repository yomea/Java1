package com.bjsxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServletContext extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		Integer count = (Integer) application.getAttribute("accessCount");
		if (count == null) {
			count = new Integer(0);
		} else {
			count = count + 1;
		}
		application.setAttribute("accessCount", count);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println(
				"<html><meta charset='utf-8' /><head><title>Application</title></head><body><h1 style='text-align:center;'>"
						+ count + "</h1></body></html>");
		pw.flush();
		pw.close();
	}

}
