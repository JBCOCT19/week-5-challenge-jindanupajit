package jbc.oct21.jindanupajit.jobposting.initializer;

import jbc.oct21.jindanupajit.jobposting.component.Context;
import jbc.oct21.jindanupajit.jobposting.model.UserDetail;
import jbc.oct21.jindanupajit.jobposting.model.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (userDetailRepository.count() == 0)
            userDetailRepository.save(new UserDetail(
                    "krissada", "password", "Krissada Jindanupajit"
            ));

        // Auto Login
        Context.setAuthenticatedUser(userDetailRepository.findByUsername("krissada"));

    }
}
