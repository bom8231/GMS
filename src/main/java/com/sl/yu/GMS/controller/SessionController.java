package com.sl.yu.GMS.controller;

import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.model.usertable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SessionController {
    @PostMapping("/session/admin")
    public String sessionAdmin(HttpSession session){
        String userID="seungkyuyoo";
        session.setAttribute("id",userID);
        return "redirect:/main";
    }
    @PostMapping("/session/worker")
    public String sessionWorker(HttpSession session){
        String userID="yu1234";
        session.setAttribute("id",userID);
        return "redirect:/main";
    }
    @PostMapping("/session/guard")
    public String sessionGuard(HttpSession session){
        String userID="bom8231";
        session.setAttribute("id",userID);
        return "redirect:/main";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
