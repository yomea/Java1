import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestSession extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession(true);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><title>setSession</title></head><body><h1>setSession</h1>");
		pw.println("new session:" + hs.isNew() + "<br />");
		pw.println("sessionId:" + hs.getId() + "<br />");
		pw.println("Create time:" +new Date(hs.getCreationTime()) + "<br />");
		pw.println("last access time:" + new Date(hs.getLastAccessedTime()) + "<br />");
		pw.println("<h1>hello</h1>");
		pw.println("isrequestSessionIdFromCookie:" + request.isRequestedSessionIdFromCookie() + "<br />");
		pw.println("isRequestSessionIdFromURL:" + request.isRequestedSessionIdFromURL() + "<br />");
		pw.println("isRequestedSessionIdValid:" + request.isRequestedSessionIdValid() + "<br />");
		pw.println("<span><a href=" + response.encodeURL("TestSession") + ">Ë¢ÐÂ</a></span></body></html>");
		pw.flush();
		pw.close();
	}
	
}
