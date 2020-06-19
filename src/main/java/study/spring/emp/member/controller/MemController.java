package study.spring.emp.member.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

//@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MASTER')")
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

	@PreAuthorize("isAuthenticated() and (#userId == principal) or hasAnyRole('ROLE_ADMIN' ,'ROLE_MASTER')")
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

	@PreAuthorize("isAuthenticated() and (#userId == principal) or hasAnyRole('ROLE_ADMIN' ,'ROLE_MASTER')")
	@RequestMapping("/update.info")
	public String upDate(@RequestParam(value = "userId") String userId, @RequestParam(value = "userAuth") String auth,
			MemVO member, RedirectAttributes redirectAttributes) {
		// 토큰에서 꺼낸 값이라 []가 붙어서 들어옴
//		@AuthenticationPrincipal Principal user 접속한 유저의 아이디 가져올수잇음
		String authorities = auth.substring(1, auth.length() - 1);
		if (authorities.equals("ROLE_USER")) {
			if (!bpe.matches(member.getPassword(), memService.getPassword(userId))) {
				return "error/pasworderror";
			} else {
				memService.updateMember(member);
				redirectAttributes.addAttribute("userId", userId);
				return "redirect:/member/mypage";
			}
		} else if (authorities.equals("ROLE_ADMIN") || authorities.equals("ROLE_MASTER")) {
			memService.updateMember(member);
			redirectAttributes.addAttribute("userId", userId);
			return "redirect:/member/mypage";
		}
		return null;

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@RequestMapping("/auth/update")
	public String authUpdate(@RequestParam(value = "auth") String enabled,
			@RequestParam(value = "userId") String userId, RedirectAttributes redirectAttributes) {
		memService.enabledupdate(enabled, userId);
		redirectAttributes.addAttribute("userId", userId);
		return "redirect:/member/update";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@RequestMapping("/list")
	public String memberList(Model model, @RequestParam(value = "select", required = false) String select) {
		if (select != null) {
			model.addAttribute("memberList", memService.selectList(select));
			return "member/memberlist";
		}
		model.addAttribute("memberList", memService.memberList());
		return "member/memberlist";
	}

	@PreAuthorize("isAuthenticated() and (#userId == principal) or hasAnyRole('ROLE_ADMIN' ,'ROLE_MASTER')")
	@RequestMapping("/member.delete")
	public String delete(@RequestParam(value = "userAuth") String auth,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "userId") String userId, @AuthenticationPrincipal Principal authId) {
		String authorities = auth.substring(1, auth.length() - 1);
		if (authorities.equals("ROLE_USER")) {
			if (!bpe.matches(password, memService.getPassword(userId))) {
				return "error/pasworderror";
			} else {
				memService.member_auth_Delete(userId);
				return "redirect:/logout";
			}
		} else if (authorities.equals("ROLE_ADMIN") || authorities.equals("ROLE_MASTER")) {
			if (authId.getName().equals(memService.getInfo(userId).getUserId())) {
				System.out.println(userId);
				memService.member_auth_Delete(userId);
				return "redirect:/logout";
			}
			memService.member_auth_Delete(userId);
			return "redirect:/member/list";
		}
		return null;

	}


}
