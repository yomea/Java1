import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSession extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession(false);
		if(hs != null) {
			PrintWriter pw = response.getWriter();
			Integer count = (Integer)hs.getAttribute("accessCount");
			
			if(count == null) {
				int accessCount = 0;
				pw.println(count + "<br />");
				hs.setAttribute("accessCount", accessCount);
			}
			else {
				pw.println((count + 1) + "<br />");
				hs.setAttribute("accessCount", count + 1);
			}
			
			response.setContentType("text/html;charset=utf-8");
			pw.println("<html><head><title>ShowSession</title></head><body bgcolor=green>");
			pw.println("SessionId:" + hs.getId()+ "<br />");
			pw.println("</body></html>");
			pw.flush();
			pw.close();
		}
		
	}
	
}
