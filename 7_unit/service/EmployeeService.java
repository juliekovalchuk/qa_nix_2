package unit7.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit7.dto.EmployeeUpdateDto;
import unit7.entity.Employee;
import unit7.entity.Project;
import unit7.repository.EmployeeRepository;
import unit7.repository.ProjectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeService {

    EmployeeRepository employeeRepository;
    ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow();
    }

    @Transactional
    public void updateById(Long employeeId, EmployeeUpdateDto dto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void create(EmployeeUpdateDto dto) {
        Employee employee = new Employee();
        employee.setLastName(dto.getLastName());
        employee.setFirstName(dto.getFirstName());
        employeeRepository.save(employee);
    }

    @Transactional
    public void addEmployeeToProject(Long projectId, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        final Set<Employee> employees = project.getEmployees();
        if(!employees.isEmpty()) {
            employees.add(employee);
            project.setEmployees(employees);
        } else {
            Set<Employee> employeeSet = new HashSet<>();
            employeeSet.add(employee);
            project.setEmployees(employeeSet);
        }
        employeeRepository.save(employee);
        projectRepository.save(project);
    }
}
