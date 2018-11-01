package com.taashee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String pass = request.getParameter("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root@123");
			PreparedStatement ps = con
					.prepareStatement("select USERNAME,PASSWORD from student where USERNAME=? and PASSWORD=?");
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				response.sendRedirect("success.html");
				return;
			}
			response.sendRedirect("error.html");
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
