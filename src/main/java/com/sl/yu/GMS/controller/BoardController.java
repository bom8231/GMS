package com.sl.yu.GMS.controller;

import com.sl.yu.GMS.Service.BoardService;
import com.sl.yu.GMS.Service.FileService;
import com.sl.yu.GMS.model.attachtable;
import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.repository.BoardRepository;
import jakarta.persistence.EntityManager;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {


    @Autowired
    private BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/main")
    public String mainDirection(){
        return "board/main";
    }

    private final FileService fileService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("board",new maintable());
        return "board/registration";
    }
    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute maintable board, @ModelAttribute attachtable attach,
                                     @RequestParam(required = false) MultipartFile file,
                                     @RequestParam(required = false) List<MultipartFile> files) throws IOException{

        if (file != null) {
            fileService.saveFile(file, attach);
        }

        if (files != null) {
            for (MultipartFile multipartFile : files) {
                if (!multipartFile.isEmpty()) {
                    fileService.saveFile(multipartFile, attach);
                }
            }
        }


        boardRepository.save(board);
    return "redirect:/";
    }

    @Autowired
    private EntityManager entityManager;
    @RequestMapping("/list")// "/list"
    public String list(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable,
                       String searchKeyword, String type, String[] state, String startDate, String endDate) {

                      
        // 날짜 지난 것 미방문 상태로 바꾸기
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = now.format(formatter);
        Page <maintable> boards = boardRepository.findByStateIDAndDeDateLessThan ("0", dateStr, pageable);
        for (maintable board : boards) {
            board.setStateID("3");
            boardRepository.save(board);
        }

        /*searchKeyword = 검색하는 단어*/
        if(searchKeyword == null && type ==null && startDate == null){
            model.addAttribute("resultMap", boardService.boardList(pageable));
        }else{
            model.addAttribute("resultMap", boardService.boardSearchList(searchKeyword, pageable, type, state, startDate, endDate));
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
            model.addAttribute("resultMap", boardService.findStatusAllByKeyword("1",searchKeyword, type, pageable));
        }


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
    @GetMapping(value={ "/checkIn/edit", "/checkOut/edit"})
    public String edit(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            model.addAttribute("board",new maintable());
        } else {
            maintable board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/edit";
    }

    @PostMapping("/checkIn/edit")
    public String checkInEdit(@ModelAttribute maintable board){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        String dateStr = now.format(formatter);
        board.setREQ_DATE(dateStr);
        boardRepository.save(board);
        return "redirect:/checkIn";
    }

}
