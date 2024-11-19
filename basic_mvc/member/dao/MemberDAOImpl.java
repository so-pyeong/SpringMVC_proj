package kopo.aisw.basic_mvc.member.dao;

import kopo.aisw.basic_mvc.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List selectAllMemberList() throws DataAccessException {
        List<MemberVO> membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
        return membersList;
    }

    @Override
    public int insertMember(MemberVO memberVO) throws DataAccessException {
        return sqlSession.insert("mapper.member.insertMember", memberVO);
    }

    @Override
    public int updateMember(MemberVO memberVO) throws DataAccessException {
        return sqlSession.update("mapper.member.updateMember", memberVO);
    }

    @Override
    public int deleteMember(String id) throws DataAccessException {
        return sqlSession.delete("mapper.member.deleteMember", id);
    }

    @Override
    public MemberVO selectMember(String id) throws DataAccessException {
        MemberVO memberVO = sqlSession.selectOne("mapper.member.selectMember", id);
        return memberVO;
    }

    @Override
    public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
        MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
        return vo;
    }

}
