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
import unit7.dto.ProjectUpdateDto;
import unit7.entity.Employee;
import unit7.entity.Project;
import unit7.service.ProjectService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/api/projects")
public class ProjectController {

    ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getById(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.findById(projectId));
    }

    @GetMapping("/{projectId}/employees")
    public ResponseEntity<Set<Employee>> getAllByEmployeeId(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.findById(projectId).getEmployees());
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Void> updateById(@PathVariable Long projectId,
                                           @RequestBody ProjectUpdateDto dto) {
        projectService.updateById(projectId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long projectId) {
        projectService.deleteById(projectId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProjectUpdateDto dto) {
        projectService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/add/{projectId}/{employeeId}")
    public ResponseEntity<Void> addProjectToEmployee(@PathVariable Long projectId,
                                                     @PathVariable Long employeeId) {
        projectService.addProjectToEmployee(projectId, employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
