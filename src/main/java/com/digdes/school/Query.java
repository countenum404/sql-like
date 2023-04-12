package com.digdes.school;

import java.util.Map;

public class Query {
    private OperationKeywords operation;
    private boolean isValues;
    private boolean isWhere;

    private Map<String, Object> values;
    private BooleanTree whereValues;

    public Query() {

    }

    public Query(OperationKeywords operation,
                 boolean isValues,
                 boolean isWhere,
                 Map<String, Object> values,
                 BooleanTree whereValues) {
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
        private BooleanTree whereValues;

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

        public QueryBuilder setWhereValues(BooleanTree whereValues) {
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

    public boolean isWhere() {
        return isWhere;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public BooleanTree getWhereValues() {
        return whereValues;
    }
}
