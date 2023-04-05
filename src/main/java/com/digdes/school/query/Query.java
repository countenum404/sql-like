package com.digdes.school.query;

import com.digdes.school.operations.OperationKeywords;

import java.util.List;
import java.util.Map;

public class Query {
    private OperationKeywords operation;
    private boolean isValues;
    private boolean isWhere;

    private Map<String, Object> values;
    private List<Map<String, Object>> whereValues;

    public Query() {

    }

    public Query(OperationKeywords operation,
                 boolean isValues,
                 boolean isWhere,
                 Map<String, Object> values,
                 List<Map<String, Object>> whereValues) {
        this.operation = operation;
        this.isValues = isValues;
        this.isWhere = isWhere;
        this.values = values;
        this.whereValues = whereValues;
    }

    public static QueryBuilder builder() {
        return new QueryBuilder();
    }

    public static class QueryBuilder {
        private OperationKeywords operation;
        private boolean isValues;
        private boolean isWhere;
        private Map<String, Object> values;
        private List<Map<String, Object>> whereValues;

        public QueryBuilder() {

        }

        public QueryBuilder setOperation(OperationKeywords operation) {
            this.operation = operation;
            return this;
        }

        public QueryBuilder setIsValues(boolean values) {
            isValues = values;
            return this;
        }

        public QueryBuilder setWhere(boolean where) {
            isWhere = where;
            return this;
        }

        public QueryBuilder setValues(Map<String, Object> values) {
            this.values = values;
            return this;
        }

        public QueryBuilder setWhereValues(List<Map<String, Object>> whereValues) {
            this.whereValues = whereValues;
            return this;
        }

        public Query build() {
            if (isValues == false) {
                values = null;
            }
            if (isWhere == false) {
                whereValues = null;
            }
            return new Query(operation, isValues, isWhere, values, whereValues);
        }
    }

    public OperationKeywords getOperation() {
        return operation;
    }

    public void setOperation(OperationKeywords operation) {
        this.operation = operation;
    }

    public boolean isValues() {
        return isValues;
    }

    public void setValues(boolean values) {
        isValues = values;
    }

    public boolean isWhere() {
        return isWhere;
    }

    public void setIsWhere(boolean where) {
        isWhere = where;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public List<Map<String, Object>> getWhereValues() {
        return whereValues;
    }

    public void setWhereValues(List<Map<String, Object>> whereValues) {
        this.whereValues = whereValues;
    }
}
