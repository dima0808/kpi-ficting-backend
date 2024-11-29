package kpi.ficting.kpitestplatform.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnswerValidator.class)
public @interface ValidAnswer {

  String message() default "Answer must have either both content and isCorrect or both leftOption and rightOption";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
