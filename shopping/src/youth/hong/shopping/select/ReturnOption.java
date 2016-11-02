package youth.hong.shopping.select;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import youth.hong.shopping.Category;

public class ReturnOption extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = request.getParameter("id");
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("text/html;charset=gbk2312");
		int id = 0;
		StringBuilder str = new StringBuilder();
		if(strId != null) {
			try {
				id = Integer.parseInt(strId.trim());
			} catch(NumberFormatException e) {
				out.println("error");
				return;
			}
			List<Category> categories = Category.getCategories();
			for(int i = 0; i < categories.size(); i++) {
				Category c = categories.get(i);
				if(c.getPid() == id) {
					str.append(c.getId() + "," + c.getName() + "-");
				}
			}
			if(categories.size() > 0) str.deleteCharAt(str.length() - 1);
			System.out.println(str.toString());
			out.println("<html charset='gb2312'><body>" + str.toString() + "</body></html>");
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
