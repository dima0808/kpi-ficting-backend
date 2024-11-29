package kpi.ficting.kpitestplatform.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuestionTypeValidator.class)
public @interface ValidQuestionType {

  String message() default "Invalid question type (single_choice, multiple_choices, matching)";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
