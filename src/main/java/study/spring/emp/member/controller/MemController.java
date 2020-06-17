package study.spring.emp.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.spring.emp.member.model.MemVO;
import study.spring.emp.member.service.IMemService;

@Controller
@RequestMapping("/member")
public class MemController {

	@Autowired
	IMemService memService;

	@Autowired
	BCryptPasswordEncoder bpe;

	@GetMapping("/insert")
	public void insert() {
	}

	@PostMapping("/insert")
	public String insert(Model model, MemVO member, RedirectAttributes redirectAttributes) {
		member.setPassword(bpe.encode(member.getPassword()));
		member.setEnabled(1);
		memService.insertMember(member);
		redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
		return "redirect:/";
	}

	@RequestMapping("/mypage")
	public String myPage(Model model, @RequestParam(value = "userId") String userId) {
		model.addAttribute("userInfo", memService.getInfo(userId));
		return "member/mypage";
	}

	@RequestMapping("/update")
	public String upDate(@RequestParam(value = "userId") String userId, RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("userId", userId);
		redirectAttributes.addFlashAttribute("message", "update");
		return "redirect:/member/mypage";
	}

	@RequestMapping("/update.info")
	public String upDate(@RequestParam(value="userId") String userId ,MemVO member,RedirectAttributes redirectAttributes) {
		if (!bpe.matches(member.getPassword(), memService.getPassword(userId))) {
			return "error/pasworderror";
		} else {
			memService.updateMember(member);
			redirectAttributes.addAttribute("userId", userId);
			return "redirect:/member/mypage";
		}
	}

}
