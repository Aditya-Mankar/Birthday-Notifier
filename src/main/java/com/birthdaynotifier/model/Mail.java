package com.birthdaynotifier.model;

import java.util.Map;

public class Mail {

    private String mailFrom;
    private String mailTo;
    private String subject;
    private Map<String, Object> props;

    public static Mail builder() {
        return new Mail();
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public Mail setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
        return this;
    }

    public String getMailTo() {
        return mailTo;
    }

    public Mail setMailTo(String mailTo) {
        this.mailTo = mailTo;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Mail setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public Mail setProps(Map<String, Object> props) {
        this.props = props;
        return this;
    }

    public Mail build() {
        return this;
    }

}
