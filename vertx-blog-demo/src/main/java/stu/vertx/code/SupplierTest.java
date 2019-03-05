package stu.vertx.code;

import java.util.function.Supplier;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/2/27 15:22
 */
public class SupplierTest {

    public static void main(String[] args) {
        Supplier<String> s = new Supplier<String>() {
            @Override
            public String get() {
                return new String();
            }
        };

        Supplier<String> ss = String::new;
        String ssss = "";
        Supplier<String> sss = () -> ssss;
    }

}
