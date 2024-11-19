package kopo.aisw.basic_mvc.member.service;

import kopo.aisw.basic_mvc.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MemberService {
    public List listMembers() throws DataAccessException;
    public int addMember(MemberVO memberVO) throws DataAccessException;
    public int updateMember(MemberVO memberVO) throws DataAccessException;
    public int deleteMember(String id) throws DataAccessException;

    public MemberVO getMember(String id) throws DataAccessException;

    public MemberVO login(MemberVO memberVO) throws Exception;
}
