package stu.vertx.third;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.mail.*;

/**
 * 发送邮件演示
 *
 * @author 孔冠华
 */
public class MailClientVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        MailConfig config = new MailConfig();
        config.setUsername("18366131816@163.com");
        config.setPassword("**********");
        config.setHostname("smtp.163.com");
        config.setStarttls(StartTLSOptions.REQUIRED);

        MailClient mailClient = MailClient.createShared(vertx, config, "exampleclient");

        MailMessage message = new MailMessage();
        message.setSubject("Vert.x资料");
        message.setFrom("18366131816@163.com");
        message.setTo("3124944383@qq.com");
//        message.setCc("Another User <another@example.net>");
        message.setText("this is the plain message text");
        message.setHtml("this is html text <a href=\"http://vertx.io\">vertx.io</a>");


        MailAttachment attachment = new MailAttachment();
        attachment.setContentType("text/plain");
        attachment.setName("Vert.x权威资料");
        attachment.setData(Buffer.buffer("更多的资料请访问 https://blog.csdn.net/king_kgh"));

        message.setAttachment(attachment);

        mailClient.sendMail(message, result -> {
            if (result.succeeded()) {
                System.out.println(result.result());
            } else {
                result.cause().printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MailClientVerticle());
    }
}
