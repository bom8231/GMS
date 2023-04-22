package com.sl.yu.GMS.controller;

import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.Service.BoardService;
import com.sl.yu.GMS.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {


    @Autowired
    private BoardRepository boardRepository;
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

    @RequestMapping("/list")// "/list"
    public String list(Model model,
                       @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, String searchKeyword, String type, String[] state) {

        /*searchKeyword = 검색하는 단어*/
        if(searchKeyword == null && type ==null && state ==null){
            model.addAttribute("resultMap", boardService.boardList(pageable));
        }else{
            model.addAttribute("resultMap", boardService.boardSearchList(searchKeyword, pageable, type, state));
        }
        return "board/list";
    }

    @GetMapping("/progress")
    public String progress(){
        return "board/progress";
    }


    @GetMapping("/checkIn")
    public String checkIn(Model model,
                          @PageableDefault(page = 0, sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, String searchKeyword, String type){

        if(searchKeyword == null && type ==null){
            model.addAttribute("resultMap", boardService.findStatusDateAll("0",pageable));
        }else{
            model.addAttribute("resultMap", boardService.findStatusDateByKeyword("0",searchKeyword, type, pageable));
        }
        return "board/checkIn";
    }


    @GetMapping("/checkOut")
    public String checkOut(Model model,
                           @PageableDefault(page = 0,sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, String searchKeyword, String type){
        if(searchKeyword == null && type ==null){
            model.addAttribute("resultMap", boardService.findStatusAll("1",pageable));
        }else{
            model.addAttribute("resultMap", boardService.findStatusDateByKeyword("1",searchKeyword, type, pageable));
        }


        return "board/checkOut";
    }

    @GetMapping(value={"/list/detail", "/checkIn/detail", "/checkOut/detail"})
    public String detail(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            model.addAttribute("board",new maintable());
        } else {
            maintable board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/detail";
    }

}
