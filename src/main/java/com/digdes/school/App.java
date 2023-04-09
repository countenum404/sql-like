package com.digdes.school;


import com.digdes.school.operations.BooleanExecutor;
import com.digdes.school.operations.BooleanTree;
import com.digdes.school.operations.LogicalOperations;
import com.digdes.school.operations.OperationNotFoundException;

public class App {
    public static void main(String[] args) {
        JavaSchoolStarter executor = new JavaSchoolStarter();
        try {
            executor.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active' =true");
            executor.execute("INSERT VALUES 'lastName' = 'Шабашов','id'=4, 'age'=22, 'active'=true");
            executor.execute("INSERT VALUES 'lastName' = 'Алиев','id'=5, 'age'=32, 'active'=false");
            var output = executor.execute("SELECT WHERE 'id'=4");
            System.out.println(output);

        } catch (OperationNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
