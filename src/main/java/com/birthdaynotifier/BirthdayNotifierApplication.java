package com.birthdaynotifier;

import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.service.IBirthdayService;
import com.birthdaynotifier.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class BirthdayNotifierApplication {

    @Autowired
    private IBirthdayService birthdayService;

    @Autowired
    private IMailService mailService;

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

	@Scheduled(cron = "0 0 9 * * *")
    public void dailyJob() throws MessagingException {
        Date today = new Date();
        int todaysMonth = today.getMonth() + 1;
        int todaysDate = today.getDate();

        logger.info("Started birthday mail sending function for date: " + today);

        Birthday request = new Birthday(todaysDate, todaysMonth);

        List<Birthday> birthdayList = birthdayService.getBirthdaysByDateAndMonth(request);

        for (Birthday birthday : birthdayList) {
            mailService.sendBirthdayMail(birthday);

            logger.info("Birthday notification to " + birthday.getEmailId() + " for " + birthday.getName());
        }
    }

}
