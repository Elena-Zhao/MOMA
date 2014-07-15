/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.GroupDao;
import com.dao.hibernate.UserDao;
import com.entity.moma.GroupOwner;
import com.entity.moma.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class FriendsBean {
    private User user ;
    private String searchName;
    private List<GroupOwner> groupList;
    private String chosedGroupName;
    private List<User> userFriendList;
    
    public List<User> getUserFriendList() {
        return userFriendList;
    }

    public void setUserFriendSet(List<User> userFriendList) {
        this.userFriendList = userFriendList;
    }

    public List<GroupOwner> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupOwner> groupList) {
        this.groupList = groupList;
    }

    public String getChosedGroupName() {
        return chosedGroupName;
    }

    public void setChosedGroupName(String chosedGroupName) {
        System.out.println("In set method " + chosedGroupName);
        this.chosedGroupName = chosedGroupName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    /**
     * Creates a new instance of FriendsBean
     */
    public FriendsBean() {
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        user = UserDao.findby_userName(userName);
        userFriendList = new ArrayList(user.getUsersForFirstUserId());
        System.out.println("userFriendList is " + userFriendList.size());
        groupList = GroupDao.findby_ownerId(user.getUserId());
        System.out.println("groupList size is " + groupList.size());
    }
    
    public void doFilter() {
        String group_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("group_id");
        int id = Integer.parseInt(group_id);
        for (GroupOwner ownerTemp : groupList) {
            if (ownerTemp.getGroupId() == id) {
                // userFriendList = ownerTemp.getUsers();
                ArrayList tempList = new ArrayList(ownerTemp.getUsers());
                userFriendList = tempList;
            }
        }
    }

    public SelectItem[] getGroupNameList() {
        int size = groupList.size() + 1;
        SelectItem[] items = new SelectItem[size];
        items[0] = new SelectItem("", "请选择一个分组");
        int i = 1;
        for (GroupOwner groupOwnerTemp : groupList) {
            items[i++] = new SelectItem(groupOwnerTemp, groupOwnerTemp.getGroupName());

        }
        return items;
    }

    public void doAdd() {
        String selectedName = chosedGroupName;
        System.out.println("seleectedName is " + selectedName + " in doAdd");

        String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("friendUserName");
        System.out.println("userName is " + userName + " in doAdd");

        User userTemp = UserDao.findby_userName(userName);
        for (GroupOwner ownerTemp : groupList) {
            if (ownerTemp.getGroupName().equals(selectedName)) {
                
                ownerTemp.getUsers().add(userTemp);
                //存入数据库
                GroupDao.modify_group(ownerTemp);
                
                ArrayList tempList = new ArrayList(ownerTemp.getUsers());
                userFriendList = tempList;
            }
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Friends.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GroupOwner findByGroupName(String groupName) {
        for (GroupOwner ownerTemp : groupList) {
            if (ownerTemp.getGroupName().equals(groupName)) {
                return ownerTemp;
            }
        }
        return null;
    }
    
    public void doSearch() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchName", searchName);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("searchList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doRedirect() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("searchList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void  removeFriend(ActionEvent e)  {
        String friendName = (String) e.getComponent().getAttributes().get("friendName");
        System.out.println("friendName is " + friendName);
        User friend = UserDao.findby_userName(friendName);
        String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName").toString();
        User user = UserDao.findby_userName(userName);
        user.getUsersForFirstUserId().remove(friend);
        user.getUsersForSecondUserId().remove(friend);
        UserDao.modify_user(user);
        for (User temp_user : user.getUsersForSecondUserId()) {
            System.out.println(temp_user.getUserName());
        }
    }
    
}
