package unit7.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit7.dto.EmployeeUpdateDto;
import unit7.entity.Employee;
import unit7.entity.Project;
import unit7.service.EmployeeService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @GetMapping("/{employeeId}/projects")
    public ResponseEntity<Set<Project>> getByProjectByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId).getProjects());
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateById(@PathVariable Long employeeId,
                                           @RequestBody EmployeeUpdateDto dto) {
        employeeService.updateById(employeeId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmployeeUpdateDto dto) {
        employeeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/add/{projectId}/{employeeId}")
    public ResponseEntity<Void> addEmployeeToProject(@PathVariable Long projectId,
                                                     @PathVariable Long employeeId) {
        employeeService.addEmployeeToProject(projectId, employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
