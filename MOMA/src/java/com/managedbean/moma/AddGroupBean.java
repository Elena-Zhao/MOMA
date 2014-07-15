/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class AddGroupBean {

    /**
     * Creates a new instance of AddGroupBean
     */
    public AddGroupBean() {
    }
    
    
    public String jumpToAdd() {
        String tempName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("friendUserName");
        System.out.println(tempName+" in jumpToAdd");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("friendUserName", tempName);
        return "addToGroup";
        
    }
}
