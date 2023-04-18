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
                       @PageableDefault(page = 0, sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, String searchKeyword, String type) {
        /*검색기능-3*/
        Page<maintable> list = null;

        /*searchKeyword = 검색하는 단어*/
        if(searchKeyword == null || type ==null){
            list = boardService.boardList(pageable); //기존의 리스트보여줌
        }else{
            list = boardService.boardSearchList(searchKeyword, pageable, type); //검색리스트반환
        }

        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재페이지를 가지고올수있다 * 0부터시작하니까 +1
        int startPage = Math.max(nowPage - 4, 1); //매개변수로 들어온 두 값을 비교해서 큰값을 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        //BoardService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
        model.addAttribute("list" , list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/list";
    }

    @GetMapping("/progress")
    public String progress(){
        return "board/progress";
    }


    @GetMapping("/checkIn")
    public String checkIn(Model model,
                          @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable){
        model.addAttribute("resultMap", boardService.findStatusDateAll("0",pageable));
        return "board/checkIn";
    }


    @GetMapping("/checkOut")
    public String checkOut(Model model,
                           @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable){
        model.addAttribute("resultMap", boardService.findStatusAll("1",pageable));

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
