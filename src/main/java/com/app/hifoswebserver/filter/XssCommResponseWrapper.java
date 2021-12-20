package com.app.hifoswebserver.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class XssCommResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream byteArrayOutputStream;

    public XssCommResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutStreamImpl(byteArrayOutputStream);
    }

    public byte[] getDataStream(){
        return byteArrayOutputStream.toByteArray();
    }

    private class ServletOutStreamImpl extends ServletOutputStream {
        private final DataOutputStream dataOutputStream;

        public ServletOutStreamImpl(OutputStream outputStream) {
            this.dataOutputStream = new DataOutputStream(outputStream);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }

        @Override
        public void write(int b) throws IOException {
            this.dataOutputStream.write(b);
        }
    }
}



