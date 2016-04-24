package edu.ben.template.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import edu.ben.template.model.UploadFile;

@Controller
public class FileUploadController extends BaseController {

	// public FileUploadController(){
	// setCommandClass(UploadFile.class);
	// setCommandName("fileUploadForm");
	// }
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	/**
	 * Upload single file
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				// logger.info("Server File Location="
				// + serverFile.getAbsolutePath());
				return "You successfully uploaded " + name + "!";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultiple", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				// logger.info("Server File Location="
				// + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name + "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}

	@RequestMapping(value = "/massRegister", method = RequestMethod.POST)
	public String massRegistration(Model model, @RequestParam("multiple") MultipartFile file)
			throws IOException, SerialException, SQLException {

		// MultipartHttpServletRequest multipartRequest =
		// (MultipartHttpServletRequest) request;
		// MultipartFile multipartFile = multipartRequest.getFile("multiple");
		// System.out.println(multiple);
		// if(multiple.length > 0){
		// UploadFile file = new UploadFile();
		// file.setFileName(multipartFile.getOriginalFilename());
		// // file.setNotes(ServletRequestUtils.getStringParameter(request,
		// // "notes"));
		// // file.setType(multipartFile.getContentType());
		// if (file != null) {
		// byte[] bytes = multipartFile.getBytes();
		// Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		// file.setData((com.mysql.jdbc.Blob) blob);
		// getUserDao().addMultiple(file.getFileName());
		// }
		// }

		if (!file.isEmpty()) {
			UploadFile fileObj = new UploadFile();
			fileObj.setFileName(file.getOriginalFilename());
			// file.setNotes(ServletRequestUtils.getStringParameter(request,
			// "notes"));
			// file.setType(multipartFile.getContentType());
			byte[] bytes = file.getBytes();
			fileObj.setData(bytes);
			//Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			//fileObj.setData((com.mysql.jdbc.Blob) blob);
			getFileUploadDao().addFile(fileObj);
		}

		// model.addAttribute("active", "index");
		return "admin";
	}

}