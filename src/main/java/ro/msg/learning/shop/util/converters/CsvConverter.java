package ro.msg.learning.shop.util.converters;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.util.List;

public class CsvConverter<T> {

    private File uploadedFile;

    public List<T> fromCsv(Class<T> tClass) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(tClass);

        MappingIterator<T> it = mapper.readerFor(tClass).with(schema)
                .readValues(uploadedFile);

        return it.readAll();
    }

    public String toCsv(Class<T> tClass, List<T> data) throws IOException {

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(tClass);
        schema = schema.withUseHeader(true);
        schema = schema.withColumnSeparator('\t');

        return mapper.writer(schema).writeValueAsString(data);
    }

    public File getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
