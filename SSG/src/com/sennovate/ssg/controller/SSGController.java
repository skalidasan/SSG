package com.sennovate.ssg.controller;

//import music.data.ReportDB;
//import music.data.InvoiceDB;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

//import music.business.*;

public class SSGController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
    	
            doGet(request,response);

    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
    	
        String requestURI = request.getRequestURI();
        String url = "/reports";
        
        if (requestURI.endsWith("/ADAccountDisabled")) {
            url = displayADAccountDisabled(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

	private String displayADAccountDisabled(HttpServletRequest request,
			HttpServletResponse response) {
		
		return null;
	}

        
    }