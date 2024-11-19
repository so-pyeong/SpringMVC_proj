package kopo.aisw.basic_mvc.member.controller;

import kopo.aisw.basic_mvc.member.service.MemberService;
import kopo.aisw.basic_mvc.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired(required=false)
    private MemberVO memberVO;

    @Override
    @RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
    public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        List membersList = memberService.listMembers();
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("membersList", membersList);
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/addMember.do", method = RequestMethod.GET)
    public ModelAndView addMember(@ModelAttribute("memberInfo") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        memberService.addMember(memberVO);
        ModelAndView mav = new ModelAndView("redirect:/main.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/modifyMember.do", method = RequestMethod.GET)
    public ModelAndView modifyMember(@ModelAttribute("memberInfo") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        System.out.println("확인 : " + memberVO.getId() + " ; " + memberVO.getPwd() + " ; " + memberVO.getName() + " ; " + memberVO.getEmail());
        memberService.updateMember(memberVO);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
    public ModelAndView deleteMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        memberService.deleteMember(id);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/editMember.do", method = RequestMethod.GET)
    public ModelAndView editMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MemberVO memberView = memberService.getMember(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/member/viewMember");
        mav.addObject("memberView", memberView);
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("member") MemberVO memberVO, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        memberVO = memberService.login(memberVO);
        if (memberVO != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", memberVO);
            session.setAttribute("isLogOn", true);
            mav.setViewName("redirect:/board/listArticles.do");
        } else {
            rAttr.addAttribute("result", "loginFailed");
            mav.setViewName("redirect:/member/loginForm.do");
        }
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("member");
        session.removeAttribute("isLogOn");
        ModelAndView mav = new ModelAndView("redirect:/board/listArticles.do");
        return mav;
    }

    @RequestMapping(value = "/member/loginForm.do", method = RequestMethod.GET)
    private ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }

    @RequestMapping(value = "/member/registerForm.do", method = RequestMethod.GET)
    private ModelAndView registerForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }

    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");

        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
        }

        int end;
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        String viewName = uri.substring(begin, end);
        if (viewName.indexOf(".") != -1) {
            viewName = viewName.substring(0, viewName.lastIndexOf("."));
        }
        if (viewName.lastIndexOf("/") != -1) {
            viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
        }

        return viewName;
    }
}
