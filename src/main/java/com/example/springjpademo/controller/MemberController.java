package com.example.springjpademo.controller;

import com.example.springjpademo.dto.MemberFormDto;
import com.example.springjpademo.entity.Member;
import com.example.springjpademo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Member", description = "사용자 관련")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @Operation(
            summary = "Member",
            description = "사용자 저장",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "메인페이지로 포워딩"
                    )
            }
    )
    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try{
            //MemberDto -> Member 엔티티에 담기
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            //DB 저장
            memberService.saveMember(member);
        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping(value ="/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }
    @GetMapping(value="/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }
}
