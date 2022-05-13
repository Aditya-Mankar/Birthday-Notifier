package com.birthdaynotifier;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.constant.LoggingConstants;
import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.service.BirthdayService;
import com.birthdaynotifier.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class BirthdayNotifierApplication {

    private final MailService mailService;
    private final BirthdayService birthdayService;

    public BirthdayNotifierApplication(MailService mailService, BirthdayService birthdayService) {
        this.mailService = mailService;
        this.birthdayService = birthdayService;
    }

    Logger logger = LoggerFactory.getLogger(BirthdayNotifierApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BirthdayNotifierApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void init(){
        System.out.println("Spring boot application running in IST timezone : " + new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    }

    @Scheduled(cron = Constants.cron_value)
    public void dailyJob() {
        Date today = new Date();
        int todaysMonth = today.getMonth() + 1;
        int todaysDate = today.getDate();

        logger.info(LoggingConstants.message_started_sending_birthday_mail + today);

        Birthday birthdayRequest = Birthday.builder()
                .setRemindDate(todaysDate)
                .setRemindMonth(todaysMonth)
                .build();

        List<Birthday> birthdayList = birthdayService.getBirthdaysByDateAndMonth(birthdayRequest);

        birthdayList.forEach(birthday -> {
            mailService.sendBirthdayMail(birthday);
            logger.info(LoggingConstants.success_sending_birthday_mail + birthday.getEmailId() + Constants.for_name + birthday.getName());
        });
    }

}
