package com.birthdaynotifier;

import com.birthdaynotifier.model.Birthday;
import com.birthdaynotifier.service.IBirthdayService;
import com.birthdaynotifier.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class BirthdayNotifierApplication {

    @Autowired
    private IBirthdayService birthdayService;

    @Autowired
    private IMailService mailService;

    public static void main(String[] args) {
        SpringApplication.run(BirthdayNotifierApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @EventListener(ApplicationReadyEvent.class)
//	@Scheduled(cron = "0 0 7 * * *")
    public void dailyJob() throws MessagingException {
        Date today = new Date();
        int todaysMonth = today.getMonth() + 1;
        int todaysDate = today.getDate();

        Birthday request = new Birthday(todaysDate, todaysMonth);

        List<Birthday> birthdayList = birthdayService.getBirthdaysByDateAndMonth(request);

        for (Birthday birthday : birthdayList) {
            System.out.println(birthday);

            mailService.sendBirthdayMail(birthday);
        }
    }

}
