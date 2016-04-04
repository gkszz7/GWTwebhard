package com.webhard.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.webhard.client.model.FileDto;
import com.webhard.client.model.FolderDto;
import com.webhard.client.model.ItemDto;
import com.webhard.client.model.UserDto;
import com.webhard.server.dao.FileDao;
import com.webhard.server.dao.FolderDao;

public class FileUpload extends HttpServlet {     
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletFileUpload upload = new ServletFileUpload();
		ServletContext application = getServletContext();
		String root = application.getRealPath("/WEB-INF/file/");
		String tempPath = application.getRealPath("/WEB-INF/temp/");
		File path = new File(root);
		int itemnum = 0;  
		int compnum = 0;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 2);// 임시 파일 생성 여부 기준 설정(크기보다 클경우
													// 생성 아닌경우 x)
		factory.setRepository(new File(tempPath));// 임시 파일 저장소 설정

		// 3. 요청 정보를 읽고 재구성할 객체 만들기
		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setFileSizeMax(1024 * 1024 * 10);// 업로드 파일의 최대 크기 설정
		
		try {
			List<FileItem> items = uploader.parseRequest(request);
			FileDto dto = new FileDto();
			HttpSession session = request.getSession();
			dto.setUserId(((UserDto) session.getAttribute("user")).getUserId());
			
			List<FileDto> files = new ArrayList<FileDto>();
			
			for (FileItem item : items) {
				if (item.isFormField()) { // 폼데이터인 경우 (파일이 아닌경우)
					if (item.getFieldName().equals("title")) {
						itemnum = Integer.parseInt(item.getString());
					}
					if(item.getFieldName().equals("title1")){
						compnum = Integer.parseInt(item.getString());
					}
				} else { // 파일인 경우
					if (item.getSize() > 0) { // 파일의 내용이 있는 경우
						String fileName = item.getName();// 파일이름
						String uniqueFileName = getUniqueFileName(fileName);
						item.write(new File(root, uniqueFileName));// 파일 저장
						FileDto file = new FileDto();
						file.setName(fileName);					
						files.add(file);
						
						int filesizes =(int)item.getSize();
						String filesize = Integer.toString(filesizes);
						
						int index = fileName.lastIndexOf(".");
						int index1 = fileName.lastIndexOf("\\");
						
						String FileType = fileName.substring(index+1);
						String name = fileName.substring(index1+1);
						
						// 파일 저장
						FileDao dao = new FileDao();
						dao.addNewFile(name, itemnum, ((UserDto) session.getAttribute("user")).getUserId(), compnum, fileName,filesize, FileType);					}
				}
				
			}
		} catch (Exception e) {

		}
	}
	 public static String getUniqueFileName(String path, String fileName)
	    {   
	        String name =
	            fileName.substring(0, fileName.lastIndexOf("."));
	        String ext =
	            fileName.substring(fileName.lastIndexOf("."));
	        int index = 1;
	        while (true) {
	           File file = 
	              new File(path + "\\" + name + "_" + index + ext);
	           if (file.exists())
	              index++;
	           else
	              break;
	        }

	        return name + "_" + index;
	    }
	   
	   //textfile.txt -> jengaekjrngkajnbgbeabeu.txt 같은이름파일이 들어왔을때 파일이름중복 안되게2
	   public static String getUniqueFileName(String fileName)
	    {   
	        int index1 = fileName.lastIndexOf("\\");
	        String name = fileName.substring(index1+1);
	        //String name = UUID.randomUUID().toString();

	        return name;
	    }
}