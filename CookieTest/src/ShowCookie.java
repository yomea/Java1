import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCookie extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		System.out.println("showDoGet");
		pw.println(
				"<html><head><title>getCookies</title></head><body><h1>getCookies</h1><table align=center border=1>");
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				pw.println("<tr><td>" + cookies[i].getName() + "</td>" + "<td>" + cookies[i].getValue() + "</td></tr>");
			}
			pw.println("</table></body></html>");
		}
		
		pw.flush();
		 pw.close();
	}

}
