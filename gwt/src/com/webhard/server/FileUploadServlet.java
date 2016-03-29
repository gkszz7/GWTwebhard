package com.webhard.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("FileUpload")
public class FileUploadServlet extends HttpServlet {

	int BUFFER_SIZE = 1024;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		upload(request, response);

	}

	@SuppressWarnings("unchecked")
	protected void upload(HttpServletRequest request,HttpServletResponse response) {

		/*if (!ServletFileUpload.isMultipartContent(request))
			return;

		ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
		List fileItemsList;
		try {
			fileItemsList = servletFileUpload.parseRequest(request);
			String optionalFileName = "";
			FileItem fileItem = null;

			Iterator it = fileItemsList.iterator();
			while (it.hasNext()) {
				FileItem fileItemTemp = (FileItem) it.next();
				if (fileItemTemp.isFormField()) {
					if ("filename".equals(fileItemTemp.getFieldName()
							.toLowerCase()))
						optionalFileName = fileItemTemp.getString();
				} else
					fileItem = fileItemTemp;
			}

			if (fileItem == null)
				return;

			String fileName = fileItem.getName();

			 Save the uploaded file if its size is greater than 0. 
			if (fileItem.getSize() <= 0)
				return;

			if (fileName == null || "".equals(fileName.trim())) {
				if (optionalFileName.trim().equals(""))
					fileName = FilenameUtils.getName(fileName);
				else
					fileName = optionalFileName;
			}

			// get a utf8 filename here
			fileName = new String(fileName.getBytes(System
					.getProperty("file.encoding")), "utf-8");

			// write a file to temp dir
			String dir = System.getenv("TEMP");
			String fullPath = dir + "/" + fileName;
			fullPath = fullPath.replaceAll("\\\\", "/");
			File saveTo = new File(fullPath);
			try {
				fileItem.write(saveTo);
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print("some response code");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,e.getLocalizedMessage());
				return;
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
*/
	}

}