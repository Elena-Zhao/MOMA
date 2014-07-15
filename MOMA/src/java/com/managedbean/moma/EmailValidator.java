/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.hibernate.tool.hbm2x.StringUtils;

/**
 *
 * @author ASUS
 */
@ManagedBean
@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator{

    /**
     * Creates a new instance of EmailValidator
     */
    public EmailValidator() {
    }
    
    @Override
    public void validate(FacesContext facesContext,UIComponent uiComponent,Object value) throws ValidatorException {
        org.apache.commons.validator.EmailValidator emailValidator = org.apache.commons.validator.EmailValidator.getInstance();
        HtmlInputText htmlInputText = (HtmlInputText) uiComponent;
        
        String email = (String) value;
        
        if(!StringUtils.isEmpty(email)) {
            if(!emailValidator.isValid(email)) {
                FacesMessage facesMessage = new FacesMessage("邮件地址不对哦");
                throw new ValidatorException(facesMessage);
            }
        }
    }

  
    
}
