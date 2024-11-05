package ajfr.diamond.kata.helpers;

import org.springframework.stereotype.Component;

/* 
 This component was created so that we can abstract out how the Application exits. 
 There are 3 ways of doing this for a Spring Boot Command Line Application.
 The first is what we are doing here which is calling System.exit(int). This is fine enough for the current purposes however it present a challenge when testing.
 The second is to call SpringApplication.close(cxt, int) which will eventually call System.exit(int). This has similar issues to above but does provide some nice if unneeded features.
 The third is throwing an unchecked exception which has an uglier output as a Command Line Application but would be preferable if this was a library.
 Inorder to create the integration tests this is mocked out to throw a RuntimeException which is caught.
 */
@Component
public class SystemExit {

    public void exit(int status) {
        System.exit(status);
    }

}
