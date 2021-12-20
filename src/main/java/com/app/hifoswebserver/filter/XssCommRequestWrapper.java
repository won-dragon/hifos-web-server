package com.app.hifoswebserver.filter;


import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class XssCommRequestWrapper extends HttpServletRequestWrapper {

    private byte[] bRequestBody;

    public XssCommRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        InputStream inputStream = super.getInputStream();

        bRequestBody = IOUtils.toByteArray(inputStream);
        String strRequestBody = new String(bRequestBody);

        strRequestBody = checkXssBlackList(strRequestBody);
        bRequestBody = strRequestBody.getBytes();
    }

    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bRequestBody);
        return new ServletInputStreamImpl(byteArrayInputStream);
    }

    private String checkXssBlackList(String requestBody){
        String originRequestBody = requestBody;
        try{
            String[] patterns = {
                    "<script>(.*?)</script>" , "cookie" , "onerror", "document"
            };
            Pattern scriptPattern = null;
            for(String pattern : patterns){
                scriptPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
                requestBody = scriptPattern.matcher(requestBody).replaceAll("");
            }
        }catch (Exception e){
            e.printStackTrace();
            requestBody = originRequestBody;
        }
        return requestBody;
    }

    private class ServletInputStreamImpl extends ServletInputStream{
        private final InputStream inputStream;

        public ServletInputStreamImpl(InputStream inputStream){
            this.inputStream = inputStream;
        }

        public int read() throws IOException {
            return inputStream.read();
        }

        public int read(byte[] b) throws IOException {
            return inputStream.read(b);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
        }
    }
}



