package mostwanted.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationUtilImpl implements ValidationUtil {

    public <E> boolean isValid(E entity) {
    
    	ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    	Validator validator = validatorFactory.getValidator();
    	Set<ConstraintViolation<E>> violations = validator.validate(entity);
    	
    		if(violations.size() > 0) {
    			return false;
    		}
    		
    		return true;
    	}
}
