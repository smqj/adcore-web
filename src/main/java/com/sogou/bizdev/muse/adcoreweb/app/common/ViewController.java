package com.sogou.bizdev.muse.adcoreweb.app.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @author wl
 *
 */
public abstract class ViewController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4217128889004207045L;
	private Logger log = Logger.getLogger(ViewController.class);
	
	public abstract ViewData process(Map<String, Object> param, HttpSession session);
	
	private void errorProcess(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException {
		try {
			ViewData vd = new ViewData();
			vd.setMsg("system exception! " + e.getMessage());
			sendData(request, response, vd.toJson());
		} catch (Exception e1) {
			e1.printStackTrace();
			//throw new ServletException(e1.getMessage(), e1);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			log.error(e.getMessage() ,e);
			errorProcess(request, response, e);
			//FIXME TO WL
			//throw new ServletException(e.getMessage() ,e);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			log.error(e.getMessage() ,e);
			errorProcess(request, response, e);
			//FIXME TO WL
			//throw new ServletException(e.getMessage() ,e);
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ct = request.getContentType();
		ViewData result = process(request.getParameterMap(), request.getSession());		
		sendData(request, response, result.toJson());		
	}
	
	private void sendData(HttpServletRequest request, HttpServletResponse response, String data) throws Exception {
		OutputStream os = null;
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			os = response.getOutputStream();
			os.write(data.getBytes("UTF-8"));
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(null != os) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	public String getParam(Map<String, Object> param, String name) {
		if(null == param.get(name)) {
			return null;
		}

		return ((String[]) param.get(name))[0];
	}

	public String[] getParams(Map<String, Object> param, String name) {
		if(null == param.get(name)) {
			return null;
		}

		return (String[]) param.get(name);
	}

}
