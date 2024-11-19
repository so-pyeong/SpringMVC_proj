package kopo.aisw.basic_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StaticPageController {

    // main page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/static_pages/main");
        return modelAndView;
    }

    // main page
    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    private ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/static_pages/main");
        return modelAndView;
    }

}
