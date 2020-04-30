package ro.msg.learning.shop.util.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.models.dto.StockCsvDto;

import java.io.IOException;
import java.lang.reflect.Type;

@Component
@RequiredArgsConstructor
public class StockMessageConverter {

    private final MessageConverter<StockCsvDto> stockMessageConverter;

    public String writeInternal(Object o, Type type) throws IOException, HttpMessageNotWritableException {
        this.stockMessageConverter.writeInternal(o, type, null);
        return this.stockMessageConverter.getCsvData();
    }

    public Object read(Type type, Class aClass) throws IOException, HttpMessageNotReadableException {
        return this.stockMessageConverter.read(type, aClass, null);
    }

}
