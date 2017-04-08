package hu.unideb.smartcampus.service.api.converter.todomain;


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
public class UserEntityToUserConverter implements Converter<UserEntity, User> {

  private final Converter<SubjectDetailsEntity, SubjectDetails> subjectDetailsConverter;

  @Autowired
  public UserEntityToUserConverter(final Converter<SubjectDetailsEntity, SubjectDetails> subjectDetailsConverter) {
    this.subjectDetailsConverter = subjectDetailsConverter;
  }

  @Override
  public User convert(final UserEntity userEntity) {
    return userEntity == null ? null : convertUserEntityToUser(userEntity);
  }

  private User convertUserEntityToUser(final UserEntity userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .fullName(userEntity.getFullName())
        .neptunIdentifier(userEntity.getNeptunIdentifier())
        .role(userEntity.getRole())
        .subjectDetailsList(convertSubjectDetailsSetToSubjectDetailsEntitySet(userEntity.getActualSubjects()))
        .build();
  }

  private List<SubjectDetails> convertSubjectDetailsSetToSubjectDetailsEntitySet(final List<SubjectDetailsEntity> subjectDetailsEntitySet) {
    return subjectDetailsEntitySet == null ? null : subjectDetailsEntitySet.parallelStream()
        .map(subjectDetailsEntity -> subjectDetailsConverter.convert(subjectDetailsEntity))
        .collect(Collectors.toList());
  }
}
