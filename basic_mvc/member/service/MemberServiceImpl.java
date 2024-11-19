package kopo.aisw.basic_mvc.member.service;

import kopo.aisw.basic_mvc.member.dao.MemberDAO;
import kopo.aisw.basic_mvc.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public List listMembers() throws DataAccessException {
        List membersList = memberDAO.selectAllMemberList();
        return membersList;
    }

    @Override
    public int addMember(MemberVO memberVO) throws DataAccessException {
        return memberDAO.insertMember(memberVO);
    }

    @Override
    public int updateMember(MemberVO memberVO) throws DataAccessException {
        return memberDAO.updateMember(memberVO);
    }

    @Override
    public int deleteMember(String id) throws DataAccessException {
        return memberDAO.deleteMember(id);
    }

    @Override
    public MemberVO getMember(String id) throws DataAccessException {
        MemberVO memberVO = memberDAO.selectMember(id);
        return memberVO;
    }

    @Override
    public MemberVO login(MemberVO memberVO) throws Exception {
        return memberDAO.loginById(memberVO);
    }
}
