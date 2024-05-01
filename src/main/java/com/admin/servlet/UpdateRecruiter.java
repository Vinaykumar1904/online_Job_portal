package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.RecruiterDao;
import com.util.DBConnect;

@WebServlet("/admin/updaterec")
public class UpdateRecruiter extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String st = req.getParameter("st");
		//System.out.println(st + " " + id);

		RecruiterDao dao = new RecruiterDao(DBConnect.getConn());
		boolean f = dao.updateAdmin(id, st);
		HttpSession session = req.getSession();
		if (f) {
			session.setAttribute("succMsg", "Status Update Sucessfully");
			resp.sendRedirect("recruiter.jsp");
		} else {
			session.setAttribute("failedMsg", "Something Wrong on Server");
			resp.sendRedirect("recruiter.jsp");
		}

	}

}
