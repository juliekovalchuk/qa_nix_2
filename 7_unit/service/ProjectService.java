package unit7.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit7.dto.ProjectUpdateDto;
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
public class ProjectService {

    ProjectRepository projectRepository;
    EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Project findById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow();
    }

    @Transactional
    public void updateById(Long projectId, ProjectUpdateDto dto) {
        Project project = findById(projectId);
        project.setDescription(dto.getDescription());
        project.setName(dto.getName());
        projectRepository.save(project);
    }

    @Transactional
    public void deleteById(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public void create(ProjectUpdateDto dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        projectRepository.save(project);
    }

    @Transactional
    public void addProjectToEmployee(Long projectId, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Project project = findById(projectId);
        final Set<Project> projects = employee.getProjects();
        if (!projects.isEmpty()) {
            projects.add(project);
            employee.setProjects(projects);
        } else {
            final HashSet<Project> objects = new HashSet<>();
            objects.add(project);
            employee.setProjects(objects);
        }
        employeeRepository.save(employee);
        projectRepository.save(project);
    }
}
