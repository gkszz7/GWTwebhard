package com.webhard.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.webhard.client.model.FileDto;
import com.webhard.server.dao.FileDao;

public class DownloadServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1. 요청 정보에서 다운로드할 파일 번호를 읽고 변수에 저장 (없으면 목록으로 이동)
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		int itemnum = 0;
		String filename;
		factory.setSizeThreshold(1024 * 1024 * 2);// 임시 파일 생성 여부 기준 설정(크기보다 클경우
												  // 생성 아닌경우 x)
		ServletFileUpload uploader = new ServletFileUpload(factory);
		String fileNo= req.getParameter("title1");
		try{
		List<FileItem> items = uploader.parseRequest(req);
		for (FileItem item : items) {
			if (item.isFormField()) { // 폼데이터인 경우 (파일이 아닌경우)
				if (item.getFieldName().equals("title1")) {
					itemnum = Integer.parseInt(item.getString());
					System.out.println(itemnum);
				}
			}
		}
			
		/*if(fileNo==null || fileNo.length()==0){
			resp.sendRedirect("list.action");
			return;
		}*/
		
		//2. 데이터베이스에서 파일번호에 해당하는 파일 조회( 없으면 목록으로 이동)
		FileDao dao = new FileDao();
		FileDto file = dao.selectFileByItemNum(itemnum); 
		filename = file.getName();
		if(file==null){
			resp.sendRedirect("list.action");
			return;
		}
		
		//3.다운로드 처리
		//3-1. 응답 컨텐츠 정보 설정 ( 파일 다운로드 설정)
		
		resp.setContentType("application/octet-stream"); //응답 컨텐츠의 종류 설정
		resp.setContentType("application/unknown");

		//다운로드 창에 표시될 파일 이름 설정
		byte[] unicodeFileName=file.getName().getBytes("utf-8");	//파일이름을 utf-8형식으로
		String asciiFileName=new String(unicodeFileName,"iso-8859-1");	//아스키로 다시 인코딩
		String name = file.getFileURL();
		resp.setHeader("content-Disposition", "Attachment;filename=\""+file.getName()+"\"");
		
		//3-2. 2의 파일의 내용을 응답 스트림에 쓰기
		String path = req.getServletContext().getRealPath("/WEB-INF/file");
		
		File file2 = new File(path+"\\"+file.getName());
		System.out.println(file2);
		OutputStream ostream = resp.getOutputStream();
		InputStream istream = new FileInputStream(file2);
		
		while(true){
			int data= istream.read(); //한 바이트를 읽어서
			if(data==-1)
				break;
			ostream.write(data); //읽은 데이터 쓰기
		}
		istream.close();
	
	}catch (Exception e) {
				// TODO: handle exception
	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doGet(req, resp);
		
	}
	
}
