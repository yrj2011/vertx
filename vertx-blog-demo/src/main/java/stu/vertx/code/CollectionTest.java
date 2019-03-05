package stu.vertx.code;

import sun.awt.util.IdentityLinkedList;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/2/25 16:38
 */
public class CollectionTest {

    public static void main(String[] args) {
        Set<String> s = Collections.newSetFromMap(new IdentityHashMap<>());
        new IdentityLinkedList<>();
        s.add("hello");
        s.add(new String("hello"));
        System.out.println(s);
    }


}
