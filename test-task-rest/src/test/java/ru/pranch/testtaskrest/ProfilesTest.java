package ru.pranch.testtaskrest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfilesTest {

    @Autowired
    Environment environment;

    @Test
    public void testSpringProfiles() {
        for (final String profileName : environment.getActiveProfiles()) {
            System.out.println("Currently active profile - " + profileName);
        }
        Assert.assertEquals("test", environment.getActiveProfiles()[0]);
    }
}
