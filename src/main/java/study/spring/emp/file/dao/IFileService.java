package study.spring.emp.file.dao;

import java.util.List;

import study.spring.emp.file.model.FileVO;

public interface IFileService {

	public void upLoadFile(FileVO fvo);
	public FileVO getFile(int fileId);
	public List<FileVO> getFileList();
	public List<FileVO> getDirectoryFileList(String name);
}
