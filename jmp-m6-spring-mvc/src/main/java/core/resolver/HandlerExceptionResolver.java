package core.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerExceptionResolver
 * Date: 03/06/2023
 *
 * @author Yauheni Antsipenka
 */
@Component
public class HandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        try {
            if (ex instanceof NullPointerException) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("err", ex);
                modelAndView.setViewName("errors/error.html");
                return modelAndView;
            }
        } catch (Exception handlerException) {
            LOGGER.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }
        return null;
    }
}
