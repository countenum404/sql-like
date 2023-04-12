package com.digdes.school;


public class App {
    public static void main(String[] args) {
        JavaSchoolStarter executor = new JavaSchoolStarter();
        try {
            executor.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
            executor.execute("INSERT VALUES 'lastName' = 'Шабашов','id'=4, 'age'=22, 'active'=true");
            executor.execute("INSERT VALUES 'lastName' = 'Алиев','id'=null, 'age'=32, 'active'=false");
            executor.execute("INSERT VALUES 'lastName' = 'Чайковский','id'=6, 'age'=45, 'active'=false");
            executor.execute("INSERT VALUES 'lastName' = 'Горбачев','id'=null, 'age'=null, 'active'=null");
            System.out.println(executor.execute("SELECT"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
