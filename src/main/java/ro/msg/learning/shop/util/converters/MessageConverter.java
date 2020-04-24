package ro.msg.learning.shop.util.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.csv.CsvConverterException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Component
@RequiredArgsConstructor
public class MessageConverter<T> extends AbstractGenericHttpMessageConverter<T> {

    private final CsvConverter<T> csvConverter;

    private String csvData;

    public MessageConverter() {
        super(new MediaType("text", "csv"));
        this.csvConverter = new CsvConverter<T>();
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        List<T> data;

        if(o instanceof List) {
            data = (ArrayList<T>) o;
        } else if(o instanceof LinkedHashMap) {
            throw new CsvConverterException(ApplicationConstants.CSV_CONVERTER_EXPORT);
        } else {
            data = Arrays.asList((T) o);
        }

        this.csvData = this.csvConverter.toCsv((Class<T>) data.getClass(), data);
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return this.csvConverter.fromCsv(aClass);
    }

    @Override
    public Object read(Type type, Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(aClass, httpInputMessage);
    }

    public String getCsvData() {
        return csvData;
    }
}
