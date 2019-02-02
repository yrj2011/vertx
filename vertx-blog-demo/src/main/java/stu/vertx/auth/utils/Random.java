package stu.vertx.auth.utils;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.auth.VertxContextPRNG;

/**
 * 生成伪随机数
 *
 * @author lenovo
 */
public class Random extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        int randomInt = VertxContextPRNG.current(vertx).nextInt();

        System.out.println(generateId(new PRNG(vertx), 16));
        System.out.println(randomInt);
    }

    private static String generateId(PRNG rng, int length) {
        final byte[] bytes = new byte[length];
        rng.nextBytes(bytes);

        final char[] hex = new char[length * 2];
        for (int j = 0; j < length; j++) {
            int v = bytes[j] & 0xFF;
            hex[j * 2] = HEX[v >>> 4];
            hex[j * 2 + 1] = HEX[v & 0x0F];
        }

        return new String(hex);
    }

    private static final char[] HEX = "0123456789abcdef".toCharArray();

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new Random());
    }
}
