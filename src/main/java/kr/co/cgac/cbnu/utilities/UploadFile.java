//package kr.co.cgac.cbnu.utilities;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.UUID;
//
//
//
//public class UploadFile {
//
//	private MultipartFile[] files;
//
//	public MultipartFile[] getFiles() {
//		return files;
//	}
//
//	public void setFiles(MultipartFile[] files) {
//		this.files = files;
//	}
//
//	public String UploadFiles(MultipartFile file, String savePath,String url,String fileName) {
//
//		String filename = file.getOriginalFilename();
//		String pathAndFileName="/resources/upload/file/"+url;
//		if (!file.isEmpty()) {
//			try {
//
//				byte[] bytes = file.getBytes();
//
//				File path = new File(savePath);
//				if (!path.exists()) {
//					path.mkdirs();
//				}
//				// creating the file on server
//				File serverFile = new File(savePath + File.separator + fileName);
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();
//
//				System.out.println(serverFile.getAbsolutePath());
////				System.out.println("You are successfully uploaded file " + fileName);
//				pathAndFileName+="/"+fileName;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("You are failed to upload " + fileName + " => " + e.getMessage());
//			}
//		} else {
//			System.out.println("You are failed to upload " + filename + " because the file was empty!");
//		}
//
//		return pathAndFileName;
//	}
//
//}
