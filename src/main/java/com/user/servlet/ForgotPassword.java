package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.RecruiterDao;
import com.dao.UserDao;
import com.model.Recruiter;
import com.model.User;
import com.util.DBConnect;
import com.util.SendEmail;

@WebServlet("/forgot")
public class ForgotPassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");
		String email = req.getParameter("email");

		RecruiterDao rdao = new RecruiterDao(DBConnect.getConn());
		UserDao udao = new UserDao(DBConnect.getConn());
		HttpSession session = req.getSession();

		if ("rec".equals(type)) {
			Recruiter rec = rdao.getByEmail(email);
			if (rec != null) {
				SendEmail.SendEmail(rec.getEmail(), rec.getRecruiterName(), rec.getPassword());
				session.setAttribute("succMsg", "Password sent to email");
			} else {
				session.setAttribute("succMsg", "user not availble");
			}
		} else {
			User user = udao.getByEmail(email);
			if (user != null) {
				SendEmail.SendEmail(user.getEmail(), user.getName(), user.getPassword());
				session.setAttribute("succMsg", "Password sent to email");
			} else {
				session.setAttribute("failedMsg", "user not availble");
			}
		}
		resp.sendRedirect("forgot.jsp?ty="+type);
	}

}
