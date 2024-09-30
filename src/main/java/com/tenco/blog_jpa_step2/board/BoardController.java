package com.tenco.blog_jpa_step2.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardController는 블로그 게시글과 관련된 HTTP 요청을 처리하는 컨트롤러 클래스입니다.
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    // 서비스 객체 없이 바로 사용
    private final BoardNativeRepository boardNativeRepository; 
    // 사용하기 
    private final BoardRepository boardRepository;

    /**
     * 게시글 업데이트를 처리하는 메서드
     * 요청 주소: **POST http://localhost:8080/board/{id}/update**
     */
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable(name = "id") Integer id, String title, String content) {
        // 1. 게시글 업데이트
        boardNativeRepository.updateById(id, title, content);
        // 2. 상세 페이지로 리다이렉트
        return "redirect:/board/" + id;
    }

    /**
     * 게시글 수정 폼을 표시하는 메서드
     * 요청 주소: **GET http://localhost:8080/board/{id}/update-form**
     */
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        // 1. 게시글 조회
        Board board = boardNativeRepository.findById(id);
        // 2. 조회한 게시글을 요청 속성에 추가
        request.setAttribute("board", board);
        // 3. 수정 폼 템플릿 반환
        return "board/update-form";
    }

    /**
     * 게시글 삭제를 처리하는 메서드
     * 요청 주소: **POST http://localhost:8080/board/{id}/delete**
     */
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id) {
        // 1. 게시글 삭제
        boardNativeRepository.deleteById(id);
        // 2. 메인 페이지로 리다이렉트
        return "redirect:/";
    }

    /**
     * 새로운 게시글을 저장하는 메서드
     * 요청 주소: **POST http://localhost:8080/board/save**
     */
    @PostMapping("/board/save")
    public String save(@RequestParam("title") String title, @RequestParam("content") String content) {
        log.warn("메서드 실행됨: 제목={}, 내용={}", title, content); // 파라미터가 올바르게 전달되는지 확인
        boardNativeRepository.save(title, content); // 게시글 저장
        return "redirect:/";
    }

    /**
     * 메인 페이지를 표시하는 메서드
     * 요청 주소: **GET http://localhost:8080/**
     */
    @GetMapping("/")
    public String index(Model model) {
        // 1. 모든 게시글 조회
        List<Board> boardList = boardNativeRepository.findAll();
        // 2. 조회한 게시글 목록을 모델에 추가
        model.addAttribute("boardList", boardList);
        // 3. 메인 페이지 템플릿 반환
        return "index";
    }

    /**
     * 게시글 작성 폼을 표시하는 메서드
     * 요청 주소: **GET http://localhost:8080/board/save-form**
     */
    @GetMapping("/board/save-form")
    public String saveForm() {
        // 1. 게시글 작성 폼 템플릿 반환
        return "board/save-form";
    }

    /**
     * 게시글 상세보기 메서드 - Eager Fetching 사용
     * 요청 주소: GET http://localhost:8080/board/1 
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        // 1. 게시글 조회
        Board board = boardNativeRepository.findById(id);
        // 2. 조회한 게시글을 요청 속성에 추가
        request.setAttribute("board", board);
        // 3. 게시글 상세 페이지 템플릿 반환
        return "board/detail";
    }


}
