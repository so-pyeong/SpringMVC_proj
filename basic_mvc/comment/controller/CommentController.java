package kopo.aisw.basic_mvc.comment.controller;

import kopo.aisw.basic_mvc.comment.vo.CommentVO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommentController {
    public ModelAndView addComment(@ModelAttribute("comment") CommentVO commentVO, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView removeComment(@RequestParam("commentNO") String commentNO, @RequestParam("parentNO") String parentNO, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView modifyComment(@ModelAttribute("comment") CommentVO commentVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
