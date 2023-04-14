package com.sl.yu.GMS.controller;

import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.Service.BoardService;
import com.sl.yu.GMS.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("board",new maintable());
        return "board/registration";
    }
    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute maintable board){
    boardRepository.save(board);
    return "redirect:/";
    }

    @GetMapping("/progress")
    public String progress(){
        return "board/progress";
    }

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        model.addAttribute("resultMap", boardService.findAll(pageable));
        return "board/list";
    }
    @GetMapping("/list/{postId}")
    public String listbyid(@PathVariable Long postId, Model model) {
        maintable post = boardService.findOne(postId).orElseThrow();
        model.addAttribute("post", post);

        return "board/detail2";
    }

    @GetMapping("/detail1")
    public String detail_1(){
        return "board/detail1";
    }

    @GetMapping("/detail2")
    public String detail_2(){
        return "board/detail2";
    }

    @Autowired
    private BoardRepository boardRepository;
    @GetMapping("/checkIn")
    public String checkIn(){

        return "board/checkIn";
    }


    @GetMapping("/checkOut")
    public String checkOut(){
        return "board/checkOut";
    }
}
