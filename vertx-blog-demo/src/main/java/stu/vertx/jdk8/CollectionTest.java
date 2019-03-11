package stu.vertx.jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/5 17:22
 */
public class CollectionTest {

    private static List<String> list = new ArrayList<>();


    public static void main(String[] args) {
        list.add("bbbbbc");
        list.add("aaaaa");
        list.add("cccc");
        System.out.println(list);
        list.sort(String::compareTo);
        list.sort(Comparator.comparing(String::length));
        System.out.println(list);


    }


}
