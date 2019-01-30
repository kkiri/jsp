package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Greeting;
import model.Hello;
import model.Introduce;
import model.Welcome;

public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = -7284792879344548448L;
	private Map<String, Object> instances = new HashMap<>();
	
	@Override //init 프로젝트가 톰캣에 올라갔을때 최초 실행
	public void init(ServletConfig config) throws ServletException {
		
		// properties 파일경로 추출
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF")+"/commandURI.properites";
		
		// properties 객체생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		
		try {
			// commandURI.properties 파일과 스트림 연결
			fis = new FileInputStream(path);
			
			// 입력스트림으로 commandURI.properties 파일 데이터 읽기
			prop.load(fis);
				
			// 스트림 해제
			fis.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// Model 클래스 캑체를 생성해서 instances에 저장
		Iterator it = prop.keySet().iterator();
		
		while(it.hasNext()) {
			
			String k = it.next().toString();
			String v = prop.getProperty(k);
			
			try {
				Class<?> obj = Class.forName(v);
				Object instance = obj.newInstance();
				
				instances.put(k, instance);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getContextPath(); 
		String uri = req.getRequestURI();
		String action = uri.substring(path.length());
		
		CommonAction instance = (CommonAction) instances.get(action);
		String view = instance.requestProc(req, resp);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		
	}
	
}
