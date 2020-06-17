package study.spring.emp.file.model;

import java.sql.Timestamp;

public class FileVO {

	private int fileId;
	private String directoryName;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private Timestamp fileUploadDate;
	private byte[] fileDate;
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Timestamp getFileUploadDate() {
		return fileUploadDate;
	}

	public void setFileUploadDate(Timestamp fileUploadDate) {
		this.fileUploadDate = fileUploadDate;
	}

	public byte[] getFileDate() {
		return fileDate;
	}

	public void setFileDate(byte[] fileDate) {
		this.fileDate = fileDate;
	}

}
