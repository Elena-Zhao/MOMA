/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.DiaryDao;
import com.entity.moma.Diary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class ViewDiaryBean {

    private int diaryId;
    private Diary diary;
    private String content;

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Creates a new instance of ViewDiaryBean
     */
    public ViewDiaryBean() throws FileNotFoundException, IOException {
        diaryId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("diaryViewId"));
        diary = DiaryDao.findby_diaryId(diaryId);
        File diaryUrl = new File((DiaryDao.findby_diaryId(diaryId)).getDiaryUrl());
        System.out.println((DiaryDao.findby_diaryId(diaryId)).getDiaryUrl());
        BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(diaryUrl)));
        this.content = buf.readLine();
    }
}
