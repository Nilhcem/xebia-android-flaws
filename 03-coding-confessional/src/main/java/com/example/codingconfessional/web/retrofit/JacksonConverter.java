package com.example.codingconfessional.web.retrofit;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

public class JacksonConverter implements Converter {

    private static final String ENCODING = "UTF-8";
    private static final String MIME_TYPE = "application/json; charset=" + ENCODING;

    private final ObjectMapper mObjectMapper = new ObjectMapper();

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        JavaType javaType = mObjectMapper.getTypeFactory().constructType(type);

        try {
            return mObjectMapper.readValue(body.in(), javaType);
        } catch (IOException e) {
            throw new ConversionException(e);
        }
    }

    @Override
    public TypedOutput toBody(Object object) {
        try {
            return new JsonTypedOutput(mObjectMapper.writeValueAsString(object).getBytes(ENCODING));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static class JsonTypedOutput implements TypedOutput {
        private final byte[] jsonBytes;

        JsonTypedOutput(byte[] jsonBytes) {
            this.jsonBytes = jsonBytes;
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return MIME_TYPE;
        }

        @Override
        public long length() {
            return jsonBytes.length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }
    }
}
