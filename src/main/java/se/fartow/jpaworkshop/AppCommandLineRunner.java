package se.fartow.jpaworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.fartow.jpaworkshop.dao.*;
import se.fartow.jpaworkshop.entity.*;

import java.text.NumberFormat;
import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserDao appUserDao;

    DetailsDao detailsDao;

    BookDao bookDao;

    BookLoanDao bookLoanDao;
    AuthorDao authorDao;

    @Autowired
    public AppCommandLineRunner(AppUserDao appUserDao, DetailsDao detailsDao, BookDao bookDao, BookLoanDao bookLoanDao, AuthorDao authorDao) {
        this.appUserDao = appUserDao;
        this.detailsDao = detailsDao;
        this.bookDao = bookDao;
        this.bookLoanDao = bookLoanDao;
        this.authorDao = authorDao;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
