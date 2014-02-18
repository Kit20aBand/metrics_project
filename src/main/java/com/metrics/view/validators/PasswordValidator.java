package com.metrics.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.metrics.view.util.MessageSender;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

	@Override
	public void validate(final FacesContext context,
			final UIComponent component, final Object value)
					throws ValidatorException {
		final String password = value.toString();

		final UIInput uiInputConfirmPassword = (UIInput) component
				.getAttributes().get("confirmPassword");
		final String confirmPassword = uiInputConfirmPassword
				.getSubmittedValue().toString();

		// Let required="true" do its job.
		if (password == null || password.isEmpty() || confirmPassword == null
				|| confirmPassword.isEmpty()) {
			return;
		}

		if (!password.equals(confirmPassword)) {
			uiInputConfirmPassword.setValid(false);
			throw new ValidatorException(MessageSender.getMessage(
					"account.PasswordsDoNotConfirm",
					FacesMessage.SEVERITY_ERROR));
		}

	}

}
