package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.msg.learning.shop.bf.SalesReportBf;
import ro.msg.learning.shop.payload.revenue.RevenuesResponse;

import javax.validation.Valid;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesReportController {

    private final SalesReportBf salesReportBf;

    @GetMapping("/report")
    public ResponseEntity<RevenuesResponse> computeSalesReport(@Valid @RequestParam String date) {
        return ResponseEntity.ok(this.salesReportBf.getRevenuesForDate(date));
    }

}
