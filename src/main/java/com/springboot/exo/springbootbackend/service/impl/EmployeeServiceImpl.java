package com.springboot.exo.springbootbackend.service.impl;

import com.springboot.exo.springbootbackend.exception.RessourceNotFoundException;
import com.springboot.exo.springbootbackend.model.Employee;
import com.springboot.exo.springbootbackend.repository.EmployeeRepository;
import com.springboot.exo.springbootbackend.service.EmployeeService;
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
//        //Methode classique
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()) {
//            return employee.get();
//        }else{
//            throw  new RessourceNotFoundException("Employee","Id", id);
//        }
//    }
        //version Lambda expression
        return employeeRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        // We need to check whether employee with given id is present in DB or not
        Employee employeeExist = employeeRepository.findById(id).orElseThrow(
                () -> new RessourceNotFoundException("Employee","Id", id)
        );
        employeeExist.setFirstName(employee.getFirstName());
        employeeExist.setLastName(employee.getLastName());
        employeeExist.setEmail(employee.getEmail());

        // save existing employee to db
        employeeRepository.save(employeeExist);
        return employeeExist;
    }

    @Override
    public void deleteEmployee(long id) {

        // check whether a employee exist in the db or net
        employeeRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Employee","id", id));

        employeeRepository.deleteById(id);
    }
}
