package com.example.vinod.mymodels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vinod on 3/24/2018.
 */


public class ModelObject {



        Employee employee1 = new Employee.EmployeeBuilder(1234, "Ankit", "Sinhal")
                .withOptionalAddress("Address")
                .withOptionalAge(25)
                .withOptionalPhone("1234567890")
                .buildEmployee();

        Employee employee2 = new Employee.EmployeeBuilder(1234, "Ankit", "Sinhal")
                .withOptionalAge(25)
                .buildEmployee();

        Employee employee3 = new Employee.EmployeeBuilder(1234, "Ankit", "Sinhal")
                .buildEmployee();



    //not static
    public static class Employee {

        private int id;
        private String firstName;
        private String lastName;
        private int age;
        private String phone;
        private String address;

        public Employee(EmployeeBuilder employeeBuilder) {
            this.id = employeeBuilder.id;
            this.firstName = employeeBuilder.firstName;
            this.lastName = employeeBuilder.lastName;

            this.age = employeeBuilder.age;
            this.phone = employeeBuilder.phone;
            this.address = employeeBuilder.address;
        }

        public int getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }


        public static class EmployeeBuilder {

            private int id;
            private String firstName;
            private String lastName;
            private int age;
            private String phone;
            private String address;

            public EmployeeBuilder(int id, String firstName, String lastName) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
            }

            public EmployeeBuilder withOptionalAge(int age) {
                this.age = age;
                return this;
            }

            public EmployeeBuilder withOptionalPhone(String phone) {
                this.phone = phone;
                return this;
            }

            public EmployeeBuilder withOptionalAddress(String address) {
                this.address = address;
                return this;
            }

            public Employee buildEmployee() {
                isValidateEmployeeData();
                return new Employee(this);
            }

            private boolean isValidateEmployeeData() {
                //Do some basic validations to check
                return true;
            }
        }
    }

}