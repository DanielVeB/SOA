package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("password_validator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String password = o.toString();
        if (!(validPassword(password))) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_FATAL,
                    "Password is invalid",
                    null);
            throw new ValidatorException(msg);
        }

    }

    private boolean validPassword(String password){
        if(password.length() < 8 || password.length()> 20){
            return false;
        }
        if(password.contains(" ")){
            return false;
        }
        return true;
    }
}