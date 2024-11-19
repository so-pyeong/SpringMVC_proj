package kopo.aisw.basic_mvc.comment.controller;

import kopo.aisw.basic_mvc.comment.service.CommentService;
import kopo.aisw.basic_mvc.comment.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("commentController")
public class CommentControllerImpl implements CommentController {
    @Autowired
    private CommentService commentService;

    @Override
    @RequestMapping(value = "/comment/addComment.do", method = RequestMethod.GET)
    public ModelAndView addComment(@ModelAttribute("comment") CommentVO comment, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        commentService.addComment(comment);
        ModelAndView modelAndView = new ModelAndView("redirect:/board/viewArticle.do");
        modelAndView.addObject("articleNO", comment.getParentNO());
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/comment/removeCommnet.do", method = RequestMethod.GET)
    public ModelAndView removeComment(@RequestParam("commentNO") String commentNO, @RequestParam("parentNO") String parentNO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        commentService.removeComment(commentNO);
        ModelAndView modelAndView = new ModelAndView("redirect:/board/viewArticle.do");
        modelAndView.addObject("articleNO", parentNO);
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/comment/modifyComment.do", method = RequestMethod.GET)
    public ModelAndView modifyComment(@ModelAttribute("comment") CommentVO comment, HttpServletRequest request, HttpServletResponse response) throws Exception {
        commentService.modifyComment(comment);
        System.out.println("[확인] :" + comment.getCommentNO() + " : " + comment.getParentNO() + " : " + comment.getContent());
        ModelAndView modelAndView = new ModelAndView("redirect:/board/viewArticle.do");
        modelAndView.addObject("articleNO", comment.getParentNO());
        return modelAndView;
    }
}
