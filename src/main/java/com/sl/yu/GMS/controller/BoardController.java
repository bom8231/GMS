package com.sl.yu.GMS.controller;

import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.Service.BoardService;
import com.sl.yu.GMS.repository.BoardRepository;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private EntityManager entityManager;
    @RequestMapping("/list")// "/list"
    public String list(Model model,
                       @PageableDefault(page = 0, sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, String searchKeyword, String type) {
        // 날짜 지난 것 미방문 상태로 바꾸기
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = now.format(formatter);
        Page <maintable> boards = boardRepository.findByStateIDAndDeDateLessThan ("0", dateStr, pageable);
        for (maintable board : boards) {
            board.setStateID("3");
            boardRepository.save(board);
        }

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
    //detail
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

    //접수처리
    @PostMapping("/checkIn/receipt/{id}")
    public String receiptCheckIn(Model model, @PathVariable("id") Long id) {
        maintable board = boardRepository.findById(id).orElse(null);
        String replyDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        if (board != null) {
            board.setStateID("1");
            board.setReplyDate(replyDate);
            boardRepository.save(board);
        }
        return "redirect:/checkIn";
    }

    @PostMapping("/list/receipt/{id}")
    public String receiptList(Model model, @PathVariable("id") Long id) {
        maintable board = boardRepository.findById(id).orElse(null);
        String replyDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        if (board != null) {
            board.setStateID("1");
            board.setReplyDate(replyDate);
            boardRepository.save(board);
        }
        return "redirect:/list";
    }

    //완료처리
    @PostMapping("/checkOut/complete/{id}")
    public String completeCheckout(Model model, @PathVariable("id") Long id) {
        maintable board = boardRepository.findById(id).orElse(null);
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        if (board != null) {
            board.setStateID("2");
            board.setEND_DATE(endDate);
            boardRepository.save(board);
        }
        return "redirect:/checkOut";
    }

    @PostMapping("/list/complete/{id}")
    public String completeList(Model model, @PathVariable("id") Long id) {
        maintable board = boardRepository.findById(id).orElse(null);
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        if (board != null) {
            board.setStateID("2");
            board.setEND_DATE(endDate);
            boardRepository.save(board);
        }
        return "redirect:/list";
    }

    //삭제
    @PostMapping("/checkIn/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
            boardRepository.deleteById(id);
        return "redirect:/checkIn";
    }

    //수정
    @GetMapping("/checkIn/edit/{id}")
    public String checkInEdit(Model model){
        model.addAttribute("board",new maintable());
        return "board/registration";
    }

}
