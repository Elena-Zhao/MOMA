/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.DiaryDao;
import com.entity.moma.Brochure;
import com.entity.moma.Diary;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class DiaryListBean {
    Brochure brochure ;
    int brochureId;
    int diaryCount;
    ArrayList<Diary> diaryList = new ArrayList();

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public int getDiaryCount() {
        return diaryCount;
    }

    public void setDiaryCount(int diaryCount) {
        this.diaryCount = diaryCount;
    }

    public ArrayList<Diary> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(ArrayList<Diary> diaryList) {
        this.diaryList = diaryList;
    }
    
    
    /**
     * Creates a new instance of DiaryListBean
     */
    public DiaryListBean() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureToeditId") != null) {
            brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureToeditId"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("brochureToeditId", brochureId);
        }
        else {
            brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("brochureToeditId").toString());
        }
        System.out.println("Brochure to edit id is " + brochureId);
        this.brochure = BrochureDao.findby_brochureId(brochureId);
        System.out.println(brochure.getBrochureName());
        this.diaryList = new ArrayList(DiaryDao.findby_brochureId(brochureId));
        for(Diary tempdiary :diaryList){
            System.out.println("DiaryList has " + tempdiary.getDiaryTitle());
        }
        this.diaryCount = this.diaryList.size();
    }
}
