package br.com.automatodev;

import java.io.PrintStream;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KafkaProducerApplication.class);
        application.setBanner(new CustomBanner());
        application.run(args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /*
     * https://stackoverflow.com/questions/44026687/show-custom-variable-in-spring-boot-banner
     * */
    private static class CustomBanner implements Banner {

        private static final String[] BANNER = {
            "\n\noooooooooooo ooooo    ooo      ooooo ooooo  .oooooo.   oooo    oooo       .o. ",
            "888'     `8 `888'     `8'      `888 `888'  d8P'  `Y8b  `888   .8P'       .888. ",
            "888          888       8        888  888  888      888  888  d8'        .8 888. ",
            "888oooo8     888       8        888  888  888      888  88888[         .8' `888. ",
            "888          888       8  Y88   888  888  888      888  888`88b.      .88ooo8888. ",
            "888          `88.    .8'  Y88   888  888  `88b    d88'  888  `88b.   .8'     `888. ",
            "o888o         `Y8bood8P'  Y888P888P o888o  `Y8bood8P'  o888o  o888o o88o     o8888o \n\n"
        };

        private static final String SPRING_BOOT = " :: VERSÃO SPRING :: ";
        private static final String ORGANIZACAO = " :: ORGANIZAÇÃO   :: ";
        private static final String PROJETO = " :: PROJETO       :: ";
        private static final String PERFIL_AIVO = " :: PERFIL ATIVO  :: ";
        private static final String PORTA_PROJETO = " :: PORTA PROJETO  :: ";

        @Override
        public void printBanner(
                final Environment environment, final Class<?> sourceClass, final PrintStream printStream) {
            String padding = "";
            for (String line : BANNER) {
                printStream.println(line);
            }
            printStream.println(
                    AnsiOutput.toString(
                            AnsiColor.YELLOW,
                            PORTA_PROJETO,
                            AnsiColor.DEFAULT,
                            padding,
                            AnsiStyle.FAINT,
                            environment.getProperty("server.port")));
            printStream.println(
                    AnsiOutput.toString(
                            AnsiColor.YELLOW,
                            ORGANIZACAO,
                            AnsiColor.DEFAULT,
                            padding,
                            AnsiStyle.FAINT,
                            environment.getProperty("app.organization")));
            printStream.println(
                    AnsiOutput.toString(
                            AnsiColor.YELLOW,
                            PROJETO,
                            AnsiColor.DEFAULT,
                            padding,
                            AnsiStyle.FAINT,
                            environment.getProperty("app.name")));
            printStream.println(
                    AnsiOutput.toString(
                            AnsiColor.YELLOW,
                            PERFIL_AIVO,
                            AnsiColor.DEFAULT,
                            padding,
                            AnsiStyle.FAINT,
                            environment.getProperty("spring.profiles.active")));
            printStream.println(
                    AnsiOutput.toString(
                            AnsiColor.YELLOW,
                            SPRING_BOOT,
                            AnsiColor.DEFAULT,
                            padding,
                            AnsiStyle.FAINT,
                            SpringBootVersion.getVersion()));
            printStream.println();
        }
    }
}
