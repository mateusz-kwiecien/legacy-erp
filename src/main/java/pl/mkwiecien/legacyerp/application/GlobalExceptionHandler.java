package pl.mkwiecien.legacyerp.application;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }
}
