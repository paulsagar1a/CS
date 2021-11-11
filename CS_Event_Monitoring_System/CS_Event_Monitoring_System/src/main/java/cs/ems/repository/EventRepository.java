package cs.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cs.ems.bean.Event;

@RepositoryRestResource(path="data-rest-courses")
@Transactional
public interface EventRepository extends JpaRepository<Event, String> {

}
