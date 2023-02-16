package com.rpg175.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestIO {
    static byte[] buffer = new byte[1024];
    static int rIndex;
    static int wIndex;

    static class MyInputStream extends InputStream {
        @Override
        public int read() throws IOException {
            return buffer[rIndex++];
        }
    }

    static class MyOutputStream extends OutputStream {
        @Override
        public void write(int b) throws IOException {
            buffer[wIndex++] = (byte)b;
        }
    }

    public static void main(String[] args) {
        File file = new File("");
    }
}
