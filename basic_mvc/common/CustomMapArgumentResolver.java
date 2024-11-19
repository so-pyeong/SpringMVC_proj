package kopo.aisw.basic_mvc.common;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Logger;

public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public Object resolveArgument(MethodParameter para, ModelAndViewContainer mav, NativeWebRequest webReq, WebDataBinderFactory binderFactory) throws Exception {
        CommandMap commandMap = new CommandMap();

        HttpServletRequest req = (HttpServletRequest) webReq.getNativeRequest();
        Enumeration<?> enumeration = req.getParameterNames();

        String key = null;
        String[] values = null;
        while (enumeration.hasMoreElements()) {
            key = (String) enumeration.nextElement();
            values = req.getParameterValues(key);
            if (values != null) {
                commandMap.put(key, (values.length > 1) ? values : values[0]);
            }
        }
        return commandMap;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CommandMap.class.isAssignableFrom(parameter.getParameterType());
    }
}
