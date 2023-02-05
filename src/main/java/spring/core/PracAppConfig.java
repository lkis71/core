package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.core.practice.reservation.OnlineReserveRepository;
import spring.core.practice.reservation.ReserveRepository;
import spring.core.practice.reservation.ReserveService;
import spring.core.practice.reservation.ReserveServiceImpl;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class PracAppConfig {
}
