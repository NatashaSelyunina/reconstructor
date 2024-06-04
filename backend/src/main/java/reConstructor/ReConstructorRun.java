package reConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ReConstructorRun {
  public static void main(String[] args) {

    System.out.println(Greetings.helloReconstructorMe);

    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));

    SpringApplication.run(ReConstructorRun.class,args);
  }
}
