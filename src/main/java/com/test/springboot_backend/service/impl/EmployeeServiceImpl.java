package com.test.springboot_backend.service.impl;

import com.test.springboot_backend.model.Employee;
import com.test.springboot_backend.repository.EmployeeRepository;
import com.test.springboot_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee= employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee exsitingEmployee= employeeRepository.findById(id).get();
        exsitingEmployee.setFirstName(employee.getFirstName());
        exsitingEmployee.setLastName(employee.getLastName());
        exsitingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(exsitingEmployee);
        return exsitingEmployee;

    }

    @Override
    public void deleteEmployee(long id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }else
            System.out.println("not found");

    }


}
