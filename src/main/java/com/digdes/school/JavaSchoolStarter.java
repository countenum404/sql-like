package com.digdes.school;

import com.digdes.school.operations.BooleanExecutor;
import com.digdes.school.operations.BooleanTree;
import com.digdes.school.operations.OperationNotFoundException;
import com.digdes.school.query.Query;

import java.util.*;

public class JavaSchoolStarter implements QueryExecutor {

    private ParserService service;
    private List<Map<String, Object>> database = new ArrayList<>();

    public JavaSchoolStarter() {
        service = new ParserService();
    }

    @Override
    public List<Map<String, Object>> execute(String query) throws Exception {
        return process(query);
    }

    private List<Map<String, Object>> process(String query) throws Exception {
        List<Map<String, Object>> output = null;
        Query queryObj = service.getQuery(query);
        switch (queryObj.getOperation()) {
            case INSERT -> output = insert(queryObj);
            case SELECT -> output = select(queryObj);
            case DELETE -> output = delete(queryObj);
            case UPDATE -> output = update(queryObj);
        }
        return output;
    }

    private List<Map<String, Object>> insert(Query query) {
        database.add(query.getValues());
        return database;
    }

    private List<Map<String, Object>> select(Query query) throws Exception{
        if (!query.isWhere())
            return database;
        BooleanTree tree = query.getWhereValues();
        BooleanExecutor executor = new BooleanExecutor();
        List<Map<String, Object>> output = new ArrayList<>();
        for (var map:
             database) {
            executor.setComparable(map);
            if (executor.execute(tree)) {
                output.add(map);
            }
        }
        return output;
    }

    private List<Map<String, Object>> update(Query query) {
        return null;
    }

    private List<Map<String, Object>> delete(Query query) {
        if (!query.isWhere()) {
            database.clear();
            return database;
        }
        return database;
    }


}
