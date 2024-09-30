package com.tenco.blog_jpa_step2.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

@Slf4j
@Controller
public class UserController {

    /**
     * 회원가입 페이지로 이동
     * 주소설계: http://localhost:8080/join-form
     * 
     * 이 메서드는 사용자를 회원가입 페이지로 이동시킵니다.
     * 반환되는 문자열은 뷰 리졸버(View Resolver)가 처리하여
     * Mustache 템플릿 엔진을 통해 뷰 파일을 렌더링합니다.
     * 
     * - Mustache 템플릿 파일 위치: src/main/resources/templates/user/join-form.mustache
     * 
     * @return 회원가입 페이지 뷰
     */
    @GetMapping("/join-form")
    public String joinForm(Model model) {
        log.info("회원가입 페이지 이동");
        model.addAttribute("name", "회원가입 페이지");
        return "user/join-form"; // Mustache 템플릿 경로: user/join-form.mustache
    }

    /**
     * 로그인 페이지로 이동
     * 주소설계: http://localhost:8080/login-form
     * 
     * 이 메서드는 사용자를 로그인 페이지로 이동시킵니다.
     * 반환되는 문자열은 뷰 리졸버(View Resolver)가 처리하여
     * Mustache 템플릿 엔진을 통해 뷰 파일을 렌더링합니다.
     * 
     * - Mustache 템플릿 파일 위치: src/main/resources/templates/user/login-form.mustache
     * 
     * @return 로그인 페이지 뷰
     */
    @GetMapping("/login-form")
    public String loginForm(Model model) {
        log.info("로그인 페이지 이동");
        model.addAttribute("name", "로그인 페이지");
        return "user/login-form"; // Mustache 템플릿 경로: user/login-form.mustache
    }

    /**
     * 회원 수정 페이지로 이동
     * 주소설계: http://localhost:8080/user/update-form
     * 
     * 이 메서드는 사용자를 회원 정보 수정 페이지로 이동시킵니다.
     * 반환되는 문자열은 뷰 리졸버(View Resolver)가 처리하여
     * Mustache 템플릿 엔진을 통해 뷰 파일을 렌더링합니다.
     * 
     * - Mustache 템플릿 파일 위치: src/main/resources/templates/user/update-form.mustache
     * 
     * @return 회원 정보 수정 페이지 뷰
     */
    @GetMapping("/user/update-form")
    public String updateForm(Model model) {
        log.info("회원 수정 페이지 이동");
        model.addAttribute("name", "회원 수정 페이지");
        return "user/update-form"; // Mustache 템플릿 경로: user/update-form.mustache
    }
}
