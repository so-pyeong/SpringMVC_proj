package kopo.aisw.basic_mvc.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Component
public class FileUtils {
    String filePath = "C:\\spring\\image_repo\\";

    public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest req) throws Exception {
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) req;
        MultipartFile mulFile = null;

        String original_File_Name = null;
        String stored_File_Name = null;
        String original_Extension = null;


        Iterator<String> iterator = mulRequest.getFileNames();
        String boardIDX = (String) map.get("articleNO").toString();
        String IDX = null;

        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
        Map<String, Object> fileMap = null;

        /*File file = new File(filePath);
        if (file.exists() == false) {
            file.mkdirs();
        }*/

        while (iterator.hasNext()) {
            mulFile = mulRequest.getFile(iterator.next());

            if (mulFile.isEmpty() == false) {
                original_File_Name = mulFile.getOriginalFilename();
                original_Extension = mulFile.getOriginalFilename().substring(original_File_Name.lastIndexOf("."));
                stored_File_Name = CommonUtils.getRandomString()+original_Extension;

                //file = new File(filePath + stored_File_Name);
                mulFile.transferTo(new File(filePath + stored_File_Name));

                fileMap = new HashMap<String, Object>();
                fileMap.put("BOARD_IDX", boardIDX);
                fileMap.put("ORIGINAL_FILE_NAME", original_File_Name);
                fileMap.put("STORED_FILE_NAME", stored_File_Name);
                fileMap.put("FILE_SIZE", mulFile.getSize());
                fileMap.put("IS_NEW", "Y");
                fileList.add(fileMap);
            } else {
                String requestName = mulFile.getName();
                IDX = "IDX_" + requestName.substring(requestName.indexOf("_") + 1);
                if (map.containsKey(IDX) == true && map.get(IDX) != null) {
                    fileMap = new HashMap<String, Object>();
                    fileMap.put("FILE_IDX", map.get(IDX));
                    fileMap.put("IS_NEW", "N");
                    fileList.add(fileMap);
                }
            }
        }
        return fileList;
    }
}
