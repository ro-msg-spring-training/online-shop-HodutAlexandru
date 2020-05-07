package ro.msg.learning.shop.util.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.models.entities.*;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.RevenueRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SalesReportScheduler {

    private final LocationRepository locationRepository;

    private final RevenueRepository revenueRepository;

    @Scheduled(cron = "0 0 21 * * *")
    public void generateSalesReport() {
        this.locationRepository.findAll().stream().forEach(location -> {
            Revenue revenue = new Revenue();
            revenue.setSum(new BigDecimal("0"));
            revenue.setDate(LocalDate.now());
            revenue.setLocation(location);
            location.getOrders().stream().forEach(order -> {
                if(order.getCreatedAt().toLocalDate().equals(LocalDate.now())) {
                    order.getOrderDetails().stream().forEach(orderDetail -> {
                        revenue.setSum(revenue.getSum().add(new BigDecimal(
                                orderDetail.getProduct().getPrice().doubleValue() * orderDetail.getQuantity()
                        )));
                    });
                }
            });

            this.revenueRepository.save(revenue);
        });
    }

}
