package study.spring.emp.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.spring.emp.file.dao.IFileService;
import study.spring.emp.file.model.FileVO;

@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MASTER')")
@Controller
public class FileController {
	
	@Autowired
	IFileService fileService;
	
	@RequestMapping("/file")
	public ModelAndView fileHome() {
		ModelAndView mav = new ModelAndView();
		//ModelAndView 리턴타입이 여러개임으로 자유롭게 값을 보낼수잇음
		mav.setViewName("/file/index");
		return mav;
	}
	
	@GetMapping("/file/new")
	public String uploadFile(Model model) {
		return "/file/form";
	}
	
	@PostMapping("/file/new")
	public String uploadFile(@RequestParam(value="dir" , required = false, defaultValue = "/")
	String dir , @RequestParam MultipartFile file ,RedirectAttributes redirectAttrs) {
		try {
			if(file != null && !file.isEmpty()) {
				FileVO newFile = new FileVO();
				newFile.setDirectoryName(dir);
				newFile.setFileName(file.getOriginalFilename());
				newFile.setFileSize(file.getSize());
				newFile.setFileContentType(file.getContentType());
				newFile.setFileDate(file. getBytes());
				fileService.upLoadFile(newFile);
			}
		}catch (IOException e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message",e.getMessage());
		}
		return "redirect:/file/list";
		
	}
	
	@GetMapping({"/img/{fileId}","/pds/{fileId}"})
	public ResponseEntity<byte[]> getImageFile(@PathVariable int fileId){
		// 파일 내보낼때 리턴타입
		FileVO file = fileService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		if(file != null) {
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
			headers.setContentDispositionFormData("attachment",file.getFileName());
			headers.setContentLength(file.getFileSize());
			return new ResponseEntity<byte[]>(file.getFileDate(),headers, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
			//not_found 404error
		}
	}
	
	@RequestMapping("/file/list")
	public String getFileList(Model model) {
		model.addAttribute("fileList" , fileService.getFileList());
		return "/file/list";
	}
	
	@RequestMapping("/file/list/{dir}")
	public String getFileListByDir(@PathVariable String dir, Model model) {
		model.addAttribute("fileList",fileService.getDirectoryFileList("/"+dir));
		return "/file/list";
	}
	
	
	

}
