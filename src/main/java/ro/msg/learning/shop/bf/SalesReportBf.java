package ro.msg.learning.shop.bf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.revenue.RevenuesResponse;
import ro.msg.learning.shop.repositories.RevenueRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SalesReportBf {

    private final RevenueRepository revenueRepository;

    public RevenuesResponse getRevenuesForDate(String date) {
        return new RevenuesResponse(
            StatusCode.OK,
            ApplicationConstants.SUCCESSFULLY_FETCHED_REVENUES,
            this.revenueRepository.findAllByDate(LocalDate.parse(date))
        );
    }

}
