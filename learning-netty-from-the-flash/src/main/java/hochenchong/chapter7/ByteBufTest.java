package hochenchong.chapter7;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author hochenchong
 * @date 2024/08/21
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("allocate ByteBuf(9, 100)", buffer);

        // write 方法改变写指针，写完之后写指针未到 capacity
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print("writeBytes(1, 2, 3, 4)", buffer);

        // 写入一个 int，int 占 4 个字节
        buffer.writeInt(12);
        print("writeInt(12);", buffer);

        // 写完之后写指针等于 capacity 时，不可写
        buffer.writeBytes(new byte[]{5});
        print("writeBytes(new byte[]{5})", buffer);

        // 写时发现容量不够，开始扩容。扩容为 2 的幂次方
        buffer.writeBytes(new byte[]{6});
        print("writeBytes(new byte[]{6})", buffer);

        // get 方法不改变读写指针
        System.out.println("getByte(3) return: " + buffer.getByte(3));
        System.out.println("getShort(3) return: " + buffer.getShort(3));
        System.out.println("getInt(3) return: " + buffer.getInt(3));
        print("getByte()", buffer);

        // set 同理

        // read 方法改变读指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        print("readBytes(" + dst.length + ")", buffer);
    }

    public static void print(String action, ByteBuf buf) {
        System.out.println("===== " + action + " =====");
        System.out.println("capacity(): " + buf.capacity());
        System.out.println("maxCapacity(): " + buf.maxCapacity());
        System.out.println("readerIndex(): " + buf.readerIndex());
        System.out.println("readableBytes(): " + buf.readableBytes());
        System.out.println("isReadable(): " + buf.isReadable());
        System.out.println("writerIndex(): " + buf.writerIndex());
        System.out.println("writableBytes(): " + buf.writableBytes());
        System.out.println("isWritable(): " + buf.isWritable());
        System.out.println("maxWritableBytes(): " + buf.maxWritableBytes());
        System.out.println();
    }
}
