package com.iiht.fse.SellerService.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Class use for Message Source Configuration.
 *
 */
@Configuration
public class MessageSourceConfig
{

    /** The Constant MESSAGES. */
    private static final String MESSAGES_BASENAME = "messages";

    /**
     * Method use to configure and create messageSource object.
     *
     * @return messageSource
     */
    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MESSAGES_BASENAME);
        return messageSource;
    }
}
