/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helperClass.moma;

import com.dao.hibernate.DiaryDao;
import com.dao.hibernate.PhotoDao;
import com.dao.hibernate.VideoDao;
import java.io.*;
import java.io.File;
import java.util.Date;

/**
 *
 * @author bianshujun
 */
public class BrochureMemory {
    private String memoryType;
    private Date memoryDate;
    private int memoryId;
    private String memoryUrl;
    private String memoryEditor;
    
    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public Date getMemoryDate() {
        return memoryDate;
    }

    public void setMemoryDate(Date memoryDate) {
        this.memoryDate = memoryDate;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public String getMemoryUrl() {
        return memoryUrl;
    }

    public void setMemoryUrl(String memoryUrl) {
        this.memoryUrl = memoryUrl;
    }

    public String getMemoryEditor() {
        return memoryEditor;
    }

    public void setMemoryEditor(String memoryEditor) {
        this.memoryEditor = memoryEditor;
    }
    
    
    public BrochureMemory(String memoryType, Date memoryDate ,Integer memoryId) throws FileNotFoundException, IOException {
        this.memoryId = memoryId;
        this.memoryDate = memoryDate;
        this.memoryType = memoryType;
        
        if (memoryType.equals("Diary")){
            File diaryUrl = new File((DiaryDao.findby_diaryId(memoryId)).getDiaryUrl());
            System.out.println((DiaryDao.findby_diaryId(memoryId)).getDiaryUrl());
            BufferedReader buf = new BufferedReader( new InputStreamReader(new FileInputStream(diaryUrl)));
            this.memoryUrl = buf.readLine();
            System.out.println(this.memoryUrl);
//            this.memoryEditor = (DiaryDao.findby_diaryId(memoryId)).getUser().getUserName();
        }
        else if (memoryType.equals("Photo")) {
            this.memoryUrl = (PhotoDao.findby_photoId(memoryId)).getPhotoUrl();
//            this.memoryEditor = (PhotoDao.findby_photoId(memoryId)).getUser().getUserName();
        }
        else if (memoryType.equals("Video")) {
            this.memoryUrl = (VideoDao.findby_videoId(memoryId)).getVideoUrl();
//            this.memoryEditor = (VideoDao.findby_videoId(memoryId)).getUser().getUserName();
        }
        
    }
}
