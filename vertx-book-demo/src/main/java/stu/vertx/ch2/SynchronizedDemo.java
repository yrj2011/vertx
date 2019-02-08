package stu.vertx.ch2;


import io.netty.handler.codec.http.FullHttpResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * 同步代码案例演示
 *
 * @author 孔冠华
 */
public class SynchronizedDemo {

    /**
     * 同步方法的执行效果，从上往下执行，先输出同步方法执行，然后等待5s之后，再输出同步方法执行完毕
     */
    public void executeBlocking() {
        System.out.println("同步方法开始执行");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("同步方法执行完毕");
    }

    public void execute() {
        System.out.println("异步方法开始执行");
        doSleep(5000, res -> {
            // 异步任务回调处理
            if (res.succeeded()) {
                System.out.println("耗时方法执行成功");
            } else {
                System.out.println("耗时方法执行失败");
            }
        });
        System.out.println("异步方法执行完毕");
    }

    public void doSleep(long millis, Handler<AsyncResult<Void>> resultHandler) {
        try {
            // 模拟执行耗时任务
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // 耗时任务异常结束
            resultHandler.handle(Future.failedFuture(e));
            return;
        }
        // 耗时任务正常结束
        resultHandler.handle(Future.succeededFuture());
    }

    public static void main(String[] args) {
        new SynchronizedDemo().execute();
    }

}
