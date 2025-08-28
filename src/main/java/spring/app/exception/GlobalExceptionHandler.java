package spring.app.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundException(UserNotFoundException userNotFoundException, Model model) {

        model.addAttribute("errorMessage", userNotFoundException.getMessage());

        return "error/user_not_found";
    }
}