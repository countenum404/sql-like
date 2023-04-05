package com.digdes.school;

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
    public List<Map<String, Object>> execute(String query) throws OperationNotFoundException {
        return process(query);
    }

    private List<Map<String, Object>> process(String query) throws OperationNotFoundException {
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

    private List<Map<String, Object>> select(Query query) {
        if (!query.isWhere())
            return database;
        return database;
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
