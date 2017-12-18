/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot application. This is an example implementation
 * for Lippu API project.
 *
 * @see <a href="https://github.com/finnishtransportagency/lippu-api">Lippu API</a>.
 *
 * @author markkuko
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"fi.ficora.lippu", "fi.ficora.lippu.controller"})
@EnableMapRepositories
public class Application implements CommandLineRunner {


    public static void main(String[] args) throws Exception {
        new SpringApplication(Application.class).run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new Application.ExitException();
        }
    }

    static class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
