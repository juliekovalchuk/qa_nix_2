package unit7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unit7.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
