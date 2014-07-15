/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helperClass.moma;

import com.dao.hibernate.BrochureDao;
import com.entity.moma.Brochure;
import com.entity.moma.User;
import java.util.ArrayList;

/**
 *
 * @author bianshujun
 */
public class UserUpdate {

    private User user;
    private Brochure brochure;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public UserUpdate(Brochure brochure) {
        this.brochure = brochure;
        System.out.println("In userUpdate " + this.brochure.getBrochureId() + " and " + this.brochure.getLatestChange() + " and " + this.brochure.getLatestChangeType());
    }
    

}
