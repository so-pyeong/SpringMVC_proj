package kopo.aisw.basic_mvc.common.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("commonDAO")
public class CommonDAO extends AbstractDAO {

    @SuppressWarnings("unchecked")
    public Map<String, Object> selectFileInfo(Map<String, Object> map) {
        return (Map<String, Object>) selectOne("mapper.common.selectFileInfo", map);
    }

    public void deleteFile(Map<String, Object> map) throws DataAccessException {
        delete("mapper.common.deleteFile", map);
    }
}
