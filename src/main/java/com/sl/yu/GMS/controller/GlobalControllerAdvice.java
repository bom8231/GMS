package com.sl.yu.GMS.controller;

import com.sl.yu.GMS.model.usertable;
import com.sl.yu.GMS.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("user")
    public void setUserRoleAttribute(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("id");
        List<usertable> user = userRepository.findRoleByUserID(userID);
        model.addAttribute("user", user);
    }

}
