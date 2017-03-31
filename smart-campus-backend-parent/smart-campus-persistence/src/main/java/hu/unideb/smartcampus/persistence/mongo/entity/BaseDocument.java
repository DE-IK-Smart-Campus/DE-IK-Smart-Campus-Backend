package hu.unideb.smartcampus.persistence.mongo.entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;

@Getter
public abstract class BaseDocument {

  @Id
  protected String id;

  public BaseDocument(String id) {
    super();
    this.id = id;
  }

}
