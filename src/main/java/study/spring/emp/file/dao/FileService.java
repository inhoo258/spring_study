package study.spring.emp.file.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.emp.file.model.FileVO;

@Service
public class FileService implements IFileService{
	
	@Autowired
	IFileRepository filerepository;

	@Override
	public void upLoadFile(FileVO fvo) {
		fvo.setFileId(filerepository.getMaxFileId()+1);
		filerepository.uploadFile(fvo);
	}

	@Override
	public FileVO getFile(int fileId) {
		return filerepository.getFile(fileId);
		
	}

	@Override
	public List<FileVO> getFileList() {
		return filerepository.getFileList();
	}

	@Override
	public List<FileVO> getDirectoryFileList(String name) {
		return filerepository.getDirectoryFileList(name);
	}

		

}
