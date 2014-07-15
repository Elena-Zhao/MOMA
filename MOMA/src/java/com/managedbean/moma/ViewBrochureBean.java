/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.DiaryDao;
import com.dao.hibernate.PhotoDao;
import com.dao.hibernate.VideoDao;
import com.entity.moma.Brochure;
import com.entity.moma.Diary;
import com.entity.moma.Photo;
import com.entity.moma.Video;
import com.helperClass.moma.BrochureMemory;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class ViewBrochureBean {

    int brochureId;
    Brochure brochure;
    ArrayList<BrochureMemory> memoryList = new ArrayList();
    BrochureMemory memory ;
    
    public int getBrochureId() {
        return brochureId;
    }

    public void setBrochureId(int brochureId) {
        this.brochureId = brochureId;
    }

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public ArrayList<BrochureMemory> getMemoryList() {
        return memoryList;
    }

    public void setMemoryList(ArrayList<BrochureMemory> memoryList) {
        this.memoryList = memoryList;
    }

    public BrochureMemory getMemory() {
        return memory;
    }

    public void setMemory(BrochureMemory memory) {
        this.memory = memory;
    }

    
    /**
     * Creates a new instance of ViewBrochureBean
     */
    public ViewBrochureBean() throws FileNotFoundException, IOException {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureToViewId") != null) {
            brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("brochureToViewId"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("brochureToViewId", brochureId);
        } else {
            brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("brochureToViewId").toString());
        }
        brochure = BrochureDao.findby_brochureId(brochureId);
        ArrayList<BrochureMemory> unSortedmemoryList = new ArrayList();
        if (DiaryDao.findby_brochureId(brochureId) != null) {
            for (Diary tempDiary : DiaryDao.findby_brochureId(brochureId)) {
                BrochureMemory brochureMemory = new BrochureMemory("Diary", tempDiary.getDiaryModifiedTime(), tempDiary.getDiaryId());
                unSortedmemoryList.add(brochureMemory);
            }
        }
        if (PhotoDao.findby_brochureId(brochureId) != null) {
            for (Photo tempPhoto : PhotoDao.findby_brochureId(brochureId)) {
                BrochureMemory brochureMemory = new BrochureMemory("Photo", tempPhoto.getPhotoModifyTime(), tempPhoto.getPhotoId());
                unSortedmemoryList.add(brochureMemory);
            }
        }
        if (VideoDao.findby_brochureId(brochureId) != null) {
            for (Video tempVideo : VideoDao.findby_brochureId(brochureId)) {
                BrochureMemory brochureMemory = new BrochureMemory("Video", tempVideo.getVideoModifyTime(), tempVideo.getVideoId());
                unSortedmemoryList.add(brochureMemory);
            }
        }
        memoryList = unSortedmemoryList;
        for (BrochureMemory temp_brochureMemory :memoryList) {
            System.out.println("In view Brochure " + temp_brochureMemory.getMemoryUrl());
            System.out.println("In view Brochure " + temp_brochureMemory.getMemoryType());
        }
        
    }

    private ArrayList<BrochureMemory> sortMemoryList(ArrayList<BrochureMemory> memoryList) {
        ArrayList<BrochureMemory> sortedMemoryList = new ArrayList();
        if (memoryList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < memoryList.size(); i++) {
            BrochureMemory tempBrochureMemory = memoryList.get(0);
            for (BrochureMemory eachBrochureMemory : memoryList) {
                if (tempBrochureMemory.getMemoryDate().before(eachBrochureMemory.getMemoryDate())) {
                    tempBrochureMemory = eachBrochureMemory;
                }
            }
            memoryList.remove(tempBrochureMemory);
            sortedMemoryList.add(tempBrochureMemory);
        }
        return sortedMemoryList;
    }
}
