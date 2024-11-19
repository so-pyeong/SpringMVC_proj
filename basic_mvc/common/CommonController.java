package kopo.aisw.basic_mvc.common;

import kopo.aisw.basic_mvc.common.service.CommonService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

@Controller
public class CommonController {

    @Resource(name = "commonService")
    private CommonService commonService;

    @RequestMapping(value = "/common/downloadFile.do")
    public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception {
        Map<String, Object> map = commonService.selectFileInfo(commandMap.getMap());

        String originalFileName = (String) map.get("ORIGINALFILENAME");
        String storedFileName = (String) map.get("STOREDFILENAME");

        byte[] fileByte = FileUtils.readFileToByteArray(new File("C:\\spring\\image_repo\\" + storedFileName));

        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(fileByte);

        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/common/deleteFile.do")
    public ModelAndView deleteFile(CommandMap commandMap, HttpServletResponse response) throws Exception {
        commonService.deleteFile(commandMap.getMap());
        ModelAndView modelAndView = new ModelAndView("redirect:/board/viewArticle.do?articleNO=" + commandMap.getMap().get("articleNO"));
        return modelAndView;
    }

}
