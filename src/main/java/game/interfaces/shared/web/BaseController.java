package game.interfaces.shared.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by pengyi on 2016/3/30.
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    private MessageSource getMessageSource() {
        return messageSource;
    }

    protected String getMessage(String code, Object[] parameterArr, Locale locale) {
        return this.getMessageSource().getMessage(code, parameterArr, locale);
    }
}
