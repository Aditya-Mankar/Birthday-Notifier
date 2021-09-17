package com.birthdaynotifier.model;

import java.util.Map;

public class Mail {

    private String mailFrom;
    private String mailTo;
    private String subject;
    private Map<String, Object> props;

    public Mail(String mailFrom, String mailTo, String subject, Map<String, Object> props) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.props = props;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

}
