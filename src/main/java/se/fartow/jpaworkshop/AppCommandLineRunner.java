package se.fartow.jpaworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.fartow.jpaworkshop.dao.AppUserDao;
import se.fartow.jpaworkshop.dao.BookDao;
import se.fartow.jpaworkshop.dao.BookLoanDao;
import se.fartow.jpaworkshop.dao.DetailsDao;
import se.fartow.jpaworkshop.entity.AppUser;
import se.fartow.jpaworkshop.entity.Book;
import se.fartow.jpaworkshop.entity.BookLoan;
import se.fartow.jpaworkshop.entity.Details;

import java.text.NumberFormat;
import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserDao appUserDao;

    DetailsDao detailsDao;

    BookDao bookDao;

    BookLoanDao bookLoanDao;

    @Autowired
    public AppCommandLineRunner(AppUserDao appUserDao, DetailsDao detailsDao, BookDao bookDao, BookLoanDao bookLoanDao) {
        this.appUserDao = appUserDao;
        this.detailsDao = detailsDao;
        this.bookDao = bookDao;
        this.bookLoanDao = bookLoanDao;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("######## CREATE ######## ");
        //Details createdDetail1 = detailsDao.create(new Details( "far@test.se", "Farhad", LocalDate.parse("2000-02-01")));
        //AppUser farhad = appUserDao.create(new AppUser("farhad", "pass",));
        AppUser user1 = appUserDao.create(new AppUser("user1", "12345", new Details("far@test.se", "Farhad", LocalDate.parse("2000-02-01"))));
        AppUser user2 = appUserDao.create(new AppUser("user2", "12345", new Details("faraaa@test.se", "Farhad", LocalDate.parse("2020-02-01"))));

        System.out.println(user1.getUserName());
        System.out.println(user2.getUserName());

        System.out.println(detailsDao.findById(1).getAppUser());
        System.out.println(detailsDao.findById(2).getAppUser());

        System.out.println("######## UPDATE ######## ");
        user2.setUserName("Test");
        appUserDao.update(user2);
        System.out.println(appUserDao.findAll());

        System.out.println("######## DELETE ######## ");
        /*appUserDao.delete(1);
        System.out.println(user2);*/

        System.out.println("######## Book & BookLoan ######## ");
        Book book1 = bookDao.create(new Book("978-91-88489-20-3", "Ledarskap i Offentlighet", 10 ));
        Book book2 = bookDao.create(new Book("978-91-88489-20-4", "Offentlig Ledare", 50 ));

        BookLoan loan1 = bookLoanDao.create(new BookLoan(LocalDate.parse("2023-01-01"), false, user1, book1));

        System.out.println(bookLoanDao.findById(1).getBorrower());
        book2.setTitle("Test BOOK");
        System.out.println(bookDao.update(book2));
        loan1.setReturned(true);
        System.out.println(bookLoanDao.update(loan1));


    }
}
