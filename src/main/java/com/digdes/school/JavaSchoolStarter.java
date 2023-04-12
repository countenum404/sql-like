package com.digdes.school;

import java.util.*;

public class JavaSchoolStarter implements QueryExecutor {

    private ParserService service = new ParserService();
    private BooleanExecutor executor = new BooleanExecutor();
    private List<Map<String, Object>> database = new ArrayList<>();

    public JavaSchoolStarter() {

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

    private List<Map<String, Object>> insert(Query query) throws Exception {
        if (query.getValues().keySet().stream().allMatch(k -> query.getValues().get(k).equals("null"))) {
            throw new Exception("All values are null");
        }
        database.add(query.getValues());
        return database;
    }

    private List<Map<String, Object>> select(Query query) throws Exception{
        if (!query.isWhere())
            return database;
        BooleanTree tree = query.getWhereValues();
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
        if (!query.isWhere()) {
            this.database.forEach(m -> {
                query.getValues().forEach((k,v) -> {
                    m.replace(k, m.get(k), v);
                });
            });
            return database;
        }
        List<Map<String, Object>> output = new ArrayList<>();
        this.database.forEach(m -> {
            executor.setComparable(m);
            try {
                if (executor.execute(query.getWhereValues())) {
                    query.getValues().forEach((k,v) -> {
                        m.replace(k, m.get(k), v);
                    });
                    output.add(m);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return output;
    }

    private List<Map<String, Object>> delete(Query query) {
        List<Map<String, Object>> output;
        if (!query.isWhere()) {
            database.clear();
            output = database;
            return output;
        }
        output = new ArrayList<>();
        this.database.forEach(m -> {
            executor.setComparable(m);
            try {
                if (executor.execute(query.getWhereValues())) {
                    output.add(m);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        this.database.removeIf(m -> {
            try {
                executor.setComparable(m);
                return executor.execute(query.getWhereValues());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return output;
    }
}
