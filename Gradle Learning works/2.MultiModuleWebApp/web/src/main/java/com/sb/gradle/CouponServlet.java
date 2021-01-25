package com.sb.gradle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/coupon") //"coupan" is the servlet name
public class CouponServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            // request and response are objects and are handled internally.
        	response.getWriter().print("SUPERSALE");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       // Request is recieved. A "discount" attribute is created which is a kind of variable and its
       // value is set as below and the request and the response is sent to response.jsp
       String coupon = request.getParameter("coupon");
       request.setAttribute("discount","Discount for coupon "+coupon+" is 50%" );
       request.getRequestDispatcher("response.jsp").forward(request,response);
    }
}