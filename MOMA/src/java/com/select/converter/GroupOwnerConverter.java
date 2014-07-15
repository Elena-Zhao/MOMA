/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.select.converter;

import com.entity.moma.GroupOwner;
import com.managedbean.moma.FriendsBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ASUS
 */
public class GroupOwnerConverter implements Converter {

    private FriendsBean controller;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        controller = (FriendsBean) context.getApplication().getELResolver().getValue(context.getELContext(), null, "friendsBean");
        GroupOwner owner = controller.findByGroupName(value);
        System.out.println(value + " in converter");
        return owner; 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        return value.toString();

    }
}
