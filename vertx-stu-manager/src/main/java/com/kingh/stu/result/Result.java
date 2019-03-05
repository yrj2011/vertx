package com.kingh.stu.result;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/4 20:32
 */
public class Result {

    public static final String JSON = "JSON";
    public static final String XML = "XML";
    public static final String HTML = "HTML";
    public static final String STATIC = "STATIC";
    public static final String FORWARD = "FORWARD";
    public static final String REDIRECT = "REDIRECT";

    private String type = "JSON";
    private String view;
    private Object data;

    public Result() {
    }

    public static Result JSON(Object data) {
        return new Result()
                .setData(data)
                .setType(JSON);
    }

    public static Result XML(Object data) {
        return new Result()
                .setData(data)
                .setType(XML);
    }

    public static Result HTML(String view) {
        return new Result()
                .setView(view)
                .setType(HTML);
    }

    public static Result FORWARD(String view) {
        return new Result()
                .setView(view)
                .setType(FORWARD);
    }

    public static Result REDIRECT(String view) {
        return new Result()
                .setView(view)
                .setType(REDIRECT);
    }

    public static Result STATIC(String view) {
        return new Result()
                .setView(view)
                .setType(STATIC);
    }

    public String getType() {
        return type;
    }

    public Result setType(String type) {
        this.type = type;
        return this;
    }

    public String getView() {
        return view;
    }

    public Result setView(String view) {
        this.view = view;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
