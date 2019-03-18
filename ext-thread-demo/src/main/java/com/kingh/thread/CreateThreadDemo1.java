package com.kingh.thread;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/13 19:19
 */
public class CreateThreadDemo1 extends Thread {

    @Override
    public void run() {
        while (true) {
            // 输出线程的名字，与主线程名称相区分
            System.out.println(Thread.currentThread().getName() + " running ...");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // 注意这里，要调用start方法才能启动线程，不能调用run方法
        new CreateThreadDemo1().start();

        // 演示主线程继续向下执行
        while (true) {
            System.out.println(Thread.currentThread().getName() + " running ...");
            Thread.sleep(1000);
        }
    }
}
