package aiss.dailymotionminer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@OpenAPIDefinition(
        info = @Info(
                title = "DailymotionMiner API",
                version = "1.0",
                description = "REST API to extract accounts, channels, videos, subtitles, and tags from Dailymotion and transforms them into the VideoMiner format."
        )
)

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class DailyMotionMinerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyMotionMinerApplication.class, args);
    }
}