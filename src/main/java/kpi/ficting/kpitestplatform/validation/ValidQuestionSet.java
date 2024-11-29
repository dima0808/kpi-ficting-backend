package kpi.ficting.kpitestplatform.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuestionSetValidator.class)
public @interface ValidQuestionSet {

  String message() default "At least one of 'questions' or 'samples' must be provided";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
