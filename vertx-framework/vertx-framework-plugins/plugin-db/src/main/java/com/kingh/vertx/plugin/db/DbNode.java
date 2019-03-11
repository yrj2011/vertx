package com.kingh.vertx.plugin.db;

import com.kingh.vertx.core.anno.Node;
import com.kingh.vertx.core.node.StaticNode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 14:14
 */
public class DbNode {

    @Node
    public StaticNode create() {

        Map<String, StaticNode.Param> params = new HashMap<>();
        params.put("sql", new StaticNode.Param()
                .setName("sql")
                .setDescription("要执行的SQL")
                .setMust(true)
                .setTypeName(String.class.getName()));

        params.put("params", new StaticNode.Param()
                .setName("params")
                .setDescription("执行的SQL需要的参数")
                .setMust(false)
                .setTypeName(JsonArray.class.getName()));

        Map<String, StaticNode.Param> result = new HashMap<>();
        result.put("rows", new StaticNode.Param()
                .setName("rows")
                .setDescription("返回多行的结果")
                .setMust(false)
                .setTypeName(List.class.getName()));

        result.put("row", new StaticNode.Param()
                .setName("row")
                .setDescription("返回单行的结果")
                .setMust(false)
                .setTypeName(JsonObject.class.getName()));

        result.put("column", new StaticNode.Param()
                .setName("column")
                .setDescription("返回单行单列的结果")
                .setMust(false)
                .setTypeName(Object.class.getName()));

        result.put("count", new StaticNode.Param()
                .setName("count")
                .setDescription("返回影响的结果的行数")
                .setMust(true)
                .setTypeName(Integer.class.getName()));

        StaticNode node = new StaticNode("DB-NODE", DbService.address, "operate")
                .setDescription("数据库服务节点")
                .setParams(params)
                .setResult(result);

        return node;
    }

}
