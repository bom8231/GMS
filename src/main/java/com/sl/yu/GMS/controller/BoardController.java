package com.sl.yu.GMS.controller;

import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BoardController {
    @GetMapping("/registration")
    public String registration(){

        return "board/registration";
    }

    @GetMapping("/progress")
    public String progress(){
        return "board/progress";
    }

    @GetMapping("/list")
    public String list(){
        return "board/list";
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
    public String checkIn(Model model){
        List<maintable> maintables = boardRepository.findAll();
        model.addAttribute("boards", maintables);
        return "board/checkIn";
    }

    @GetMapping("/checkOut")
    public String checkOut(){
        return "board/checkOut";
    }
}
