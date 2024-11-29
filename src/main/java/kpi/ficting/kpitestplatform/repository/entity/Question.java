package kpi.ficting.kpitestplatform.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import kpi.ficting.kpitestplatform.common.QuestionType;
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
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  private Integer points;

  private QuestionType type;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Answer> answers;

  @ManyToOne(fetch = FetchType.LAZY)
  private Collection collection;

  @ManyToOne(fetch = FetchType.LAZY)
  private Test test;
}
