

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ByteBufTest {
    @Test
    public void testDirectBuf() {
        ByteBuf directBuf=  ByteBufAllocator.DEFAULT.directBuffer();
        ByteBuffer directBuf2 =  ByteBufAllocator.DEFAULT.directBuffer().nioBuffer();
        directBuf.writeBytes("我测测directBuf".getBytes(UTF_8));
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            //把数据读取到堆内存array中，再进行Java处理
            directBuf.getBytes(directBuf.readerIndex(), array);
            System.out.println(new String(array, UTF_8));
        }
        directBuf.release();
    }
}
