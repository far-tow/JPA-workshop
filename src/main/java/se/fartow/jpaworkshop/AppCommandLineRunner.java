package se.fartow.jpaworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.fartow.jpaworkshop.dao.AppUserDao;
import se.fartow.jpaworkshop.dao.DetailsDao;
import se.fartow.jpaworkshop.entity.AppUser;
import se.fartow.jpaworkshop.entity.Details;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserDao appUserDao;

    DetailsDao detailsDao;

    @Autowired
    public AppCommandLineRunner(AppUserDao appUserDao, DetailsDao detailsDao) {
        this.appUserDao = appUserDao;
        this.detailsDao = detailsDao;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("########## TESTING #######");
        //Details createdDetail1 = detailsDao.create(new Details( "far@test.se", "Farhad", LocalDate.parse("2000-02-01")));
        //AppUser farhad = appUserDao.create(new AppUser("farhad", "pass",));
        AppUser farhad = appUserDao.create(new AppUser("farhad", "12345", new Details( "far@test.se", "Farhad", LocalDate.parse("2000-02-01"))));

        System.out.println(farhad);

        System.out.println(detailsDao.findById(1).getAppUser());
    }
}
