package hu.unideb.smartcampus.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SampleEntity extends AbstractEntity<Long> {

    private String name;
}
