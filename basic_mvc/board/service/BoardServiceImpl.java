package kopo.aisw.basic_mvc.board.service;

import kopo.aisw.basic_mvc.board.dao.BoardDAO;
import kopo.aisw.basic_mvc.board.vo.BoardVO;
import kopo.aisw.basic_mvc.common.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
    @Resource
    private FileUtils fileUtils;

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public List listArticles() throws DataAccessException {
        List articlesList = null;
        articlesList = boardDAO.selectAllArticlesList();
        return articlesList;
    }

    @Override
    public int addArticle(Map<String, Object> map, HttpServletRequest request) throws Exception {
        // 수정 Max ArticleNO +1 -> 새글 번호 생성
        int maxNum = boardDAO.maxArticleNum() + 1;
        map.put("articleNO", maxNum);

        // 파일 업로드 추가
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iterator = multipartRequest.getFileNames();
        MultipartFile multipartFile = null;
        while (iterator.hasNext()) {
            multipartFile = multipartRequest.getFile(iterator.next());
            if (multipartFile.isEmpty() == false) {
                System.out.println("-------------------- file start -------------------");
                System.out.println("name : " + multipartFile.getName());
                System.out.println("fileName : " + multipartFile.getOriginalFilename());
                System.out.println("size : " + multipartFile.getSize());
                System.out.println("---------------------------------------------------");
            }
        }

        List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);

        for (int i = 0; i < list.size(); i++) {
            boardDAO.insertFile(list.get(i));
        }

        return boardDAO.insertArticle(map);
    }

    @Override
    public int removeArticle(String articleNO) throws DataAccessException {
        return boardDAO.deleteArticle(articleNO);
    }

    @Override
    public BoardVO getArticle(String articleNO) throws DataAccessException {
        // 조회수 업데이트
        BoardVO bVO = boardDAO.selectHitCnt(articleNO);
        bVO.setHitCnt(bVO.getHitCnt() + 1);
        boardDAO.updateHitCnt(bVO);

        BoardVO boardVO = boardDAO.selectArticle(articleNO);
        return boardVO;
    }

    @Override
    public int updateArticle(Map<String, Object> map, HttpServletRequest request) throws Exception {

        // 파일 업로드 추가
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iterator = multipartRequest.getFileNames();
        MultipartFile multipartFile = null;
        while (iterator.hasNext()) {
            multipartFile = multipartRequest.getFile(iterator.next());
            if (multipartFile.isEmpty() == false) {
                System.out.println("-------------------- file start -------------------");
                System.out.println("name : " + multipartFile.getName());
                System.out.println("fileName : " + multipartFile.getOriginalFilename());
                System.out.println("size : " + multipartFile.getSize());
                System.out.println("---------------------------------------------------");
            }
        }

        List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);

        for (int i = 0; i < list.size(); i++) {
            boardDAO.insertFile(list.get(i));
        }

        return boardDAO.updateArticle(map);
    }

    @Override
    public List<Map<String, Object>> selectFileList(String articleNO) {
        return boardDAO.selectFileList(articleNO);
    }

}
