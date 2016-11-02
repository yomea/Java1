import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		for (int i = 0; i < 3; i++) {
			Cookie cookie = new Cookie("addSLifeCookieName-" + i, "addSCookieValue-" + i);
			response.addCookie(cookie);
			cookie = new Cookie("addLongCookieName-" + i, "addLongCookieValue-" + i);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);

		}
		response.setContentType("text/html;charset=utf-8");
		System.out.println("doGet");
		PrintWriter pw = response.getWriter();
		pw.println(
				"<html><head><title>addCookie</title></head><body><h1><a href='ShowCookie' target='_blank'>addCookie</a></h1></body></html>");
		
	}

}
