package com.goodee.mvcBoard;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("톰캣 시작시 실행되는 코드");
    	
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MariaDB 드라이버 로딩 실패");
			e.printStackTrace();
		}
    	System.out.println("MariaDB 드라이버 로딩 성공");
    }
	
}
