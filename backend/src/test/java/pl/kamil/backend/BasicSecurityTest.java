package pl.kamil.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicSecurityTest {

    @Autowired
    private TestRestTemplate template;


    @Test
    public void givenRequestOnPublicService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template
          .getForEntity("/public/token", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("adminn", "password")
          .getForEntity("/private/token", String.class);
                    System.out.println( result.getBody());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenBadAuthRequestOnPrivateService_shouldUnauthorizedWith401() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("adminn", "")
          .getForEntity("/private/token", String.class);
                    System.out.println( result.getBody());

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void givenRequestOnPrivateService_shouldUnauthorizedWith401() throws Exception {
        ResponseEntity<String> result = template
          .getForEntity("/private/token", String.class);

          System.out.println(result.getBody());
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }
}