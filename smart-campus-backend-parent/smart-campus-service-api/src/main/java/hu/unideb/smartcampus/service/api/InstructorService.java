package hu.unideb.smartcampus.service.api;


import java.util.List;
import java.util.Optional;
import hu.unideb.smartcampus.service.api.domain.Instructor;

public interface InstructorService {

  Optional<Instructor> getInstructorByName(String name);

  List<Instructor> getAllInstructor();

  void saveInstructor(Instructor instructor);
}
