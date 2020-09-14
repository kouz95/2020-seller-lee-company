package com.jikgorae.api.member.presentation;

import static com.jikgorae.api.member.presentation.AuthController.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jikgorae.api.member.application.MemberService;

@RestController
@RequestMapping(MEMBER_API_URI)
public class AuthController {
    public static final String MEMBER_API_URI = "/api/members";

    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<Boolean> findNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(memberService.findNickname(nickname));
    }
}