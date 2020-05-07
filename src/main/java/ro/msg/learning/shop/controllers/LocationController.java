package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.msg.learning.shop.bf.LocationBf;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationBf locationBf;

    @GetMapping(
            value = "/export/{locationId}",
            produces = "text/csv",
            consumes = "application/json"
    )
    public void exportStocks(@PathVariable("locationId") Integer locationId, HttpServletResponse response) throws IOException {
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Stocks.csv");
        response.getWriter().print(this.locationBf.exportStock(locationId));
    }

}
