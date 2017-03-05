package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.Set;

import hu.unideb.smartcampus.service.api.domain.Subject;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Wrapper for result of Retrieve Subjects.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SubjectResponseWrapper extends BaseWrapper {

  /**
   * Wrapped subjects in set.
   */
  private Set<Subject> subjectSet;

  /**
   * Constructs a wrapper for response.
   */
  @Builder
  public SubjectResponseWrapper(final Set<Subject> subjectSet) {
    this.subjectSet = subjectSet;
  }



}
