package hu.unideb.smartcampus.service.api.converter.toentity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.SubjectDetailsEntity;
import hu.unideb.smartcampus.persistence.entity.UserEntity;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.domain.User;

@Component
public class UserToUserEntityConverter implements Converter<User, UserEntity> {

  private final Converter<SubjectDetails, SubjectDetailsEntity> subjectDetailsConverter;

  @Autowired
  public UserToUserEntityConverter(final Converter<SubjectDetails, SubjectDetailsEntity> subjectDetailsConverter) {
    this.subjectDetailsConverter = subjectDetailsConverter;
  }

  @Override
  public UserEntity convert(final User user) {
    return user == null ? null : convertUserToUserEntity(user);
  }

  private UserEntity convertUserToUserEntity(final User user) {
    return UserEntity.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .role(user.getRole())
        .fullName(user.getFullName())
        .neptunIdentifier(user.getNeptunIdentifier())
        .actualSubjects(convertSubjectDetailsSetToSubjectDetailsEntitySet(user.getSubjectDetailsList()))
        .build();
  }

  private List<SubjectDetailsEntity> convertSubjectDetailsSetToSubjectDetailsEntitySet(final List<SubjectDetails> subjectDetailsSet) {
    return subjectDetailsSet == null ? null : subjectDetailsSet.parallelStream()
        .map(subjectDetails -> subjectDetailsConverter.convert(subjectDetails))
        .collect(Collectors.toList());
  }
}
