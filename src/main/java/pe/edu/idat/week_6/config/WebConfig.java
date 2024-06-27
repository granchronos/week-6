package pe.edu.idat.week_6.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CustomerConverter customerConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(customerConverter);
        registry.addFormatterForFieldType(LocalDate.class ,new LocalDateFormatter());
    }

    public static class LocalDateFormatter implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(String text, Locale locale) {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        @Override
        public String print(LocalDate object, Locale locale) {
            return object.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

}
