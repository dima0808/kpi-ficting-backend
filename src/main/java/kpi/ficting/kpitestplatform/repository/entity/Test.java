package kpi.ficting.kpitestplatform.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Test {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  private LocalDateTime openDate;
  private LocalDateTime deadline;
  private Integer minutesToComplete;

  @OneToMany(mappedBy = "test", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<TestSession> sessions;

  @OneToMany(mappedBy = "test", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Question> questions;

  @OneToMany(mappedBy = "test", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Sample> samples;
}
