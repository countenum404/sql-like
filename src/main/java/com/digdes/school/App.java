package com.digdes.school;


import com.digdes.school.operations.BooleanExecutor;
import com.digdes.school.operations.BooleanTree;
import com.digdes.school.operations.LogicalOperations;
import com.digdes.school.operations.OperationNotFoundException;

public class App {
    public static void main(String[] args) {
        JavaSchoolStarter executor = new JavaSchoolStarter();
        try {
            executor.execute("INSERT VALUES 'last Name ' = 'Федоров ' , 'id'=3, 'age'=40, 'active' =true");
            executor.execute("INSERT VALUES 'lastName' = 'Шабашов','id'=4, 'age'=22, 'active'=true");
            executor.execute("INSERT VALUES 'lastName' = 'Алиев','id'=5, 'age'=32, 'active'=true");
            var output = executor.execute("SELECT");
            System.out.println(output);


            var left = new BooleanTree(LogicalOperations.EQUALS);
            left.setKey("'age'");
            left.setValue(40L);

            var right = new BooleanTree(LogicalOperations.EQUALS);
            right.setKey("'id'");
            right.setValue(3L);

            BooleanTree btree = new BooleanTree();
            btree.setData(LogicalOperations.AND);
            btree.setLeft(left);
            btree.setRight(right);

            BooleanExecutor executor_b = new BooleanExecutor();
            output.forEach(m -> {
                        executor_b.setComparable(m);

                boolean bool = false;
                try {
                    bool = executor_b.execute(btree);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(bool);
                    });

        } catch (OperationNotFoundException e) {
            e.printStackTrace();
        }
    }
}
