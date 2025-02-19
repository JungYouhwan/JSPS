package com.yedam.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 
 * Views폴더를 만드는 이유는 모든html파일이 컨트롤러를 거칠수 있도록 만들어서 넣음
 */
public interface Control {
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			 throws ServletException, IOException;
}
