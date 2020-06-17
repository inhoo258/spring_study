package study.spring.emp.file.dao;

import java.util.List;

import study.spring.emp.file.model.FileVO;

public interface IFileRepository {
	
	public int getMaxFileId();
	public void uploadFile(FileVO file);
	public FileVO getFile(int fileId);
	public List<FileVO> getFileList();
	public List<FileVO> getDirectoryFileList(String name);
}
