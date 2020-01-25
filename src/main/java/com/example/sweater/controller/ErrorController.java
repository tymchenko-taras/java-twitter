package com.example.sweater.controller;

import com.example.sweater.model.Message;
import com.example.sweater.model.User;
import com.example.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
