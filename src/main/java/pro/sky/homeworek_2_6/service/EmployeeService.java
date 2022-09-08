package pro.sky.homeworek_2_6.service;

import org.springframework.stereotype.Service;
import pro.sky.homeworek_2_6.exception.EmployeeAlreadyAddedException;
import pro.sky.homeworek_2_6.exception.EmployeeNotFoundException;
import pro.sky.homeworek_2_6.exception.EmployeeStorageIsFullException;
import pro.sky.homeworek_2_6.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    final private static int SIZE = 5;
    final private List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee addEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException(" Такой сотрудник уже есть! ");
        }
        if (employees.size() < SIZE) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException(" Список сотрудников переполнен! ");
    }

    public Employee removeEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException(" Сотрудник не найден! ");
    }

    public Employee findEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException(" Сотрудник не найден! ");
        }
        return employee;
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }
}
