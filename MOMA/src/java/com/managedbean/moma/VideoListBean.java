/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.DiaryDao;
import com.dao.hibernate.UserDao;
import com.dao.hibernate.VideoDao;
import com.entity.moma.Brochure;
import com.entity.moma.Diary;
import com.entity.moma.User;
import com.entity.moma.Video;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class VideoListBean {
    private Brochure brochure ;
    private int brochureId;
    private int videoCount;
    private String uploadUrl;
    private String uploadName;
    private ArrayList<Video> videoList = new ArrayList();

    public Brochure getBrochure() {
        return brochure;
    }

    public void setBrochure(Brochure brochure) {
        this.brochure = brochure;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public ArrayList<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(ArrayList<Video> videoList) {
        this.videoList = videoList;
    }

    public int getBrochureId() {
        return brochureId;
    }

    public void setBrochureId(int brochureId) {
        this.brochureId = brochureId;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    
    
    /**
     * Creates a new instance of DiaryListBean
     */
    public VideoListBean() {
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
        this.videoList = new ArrayList(VideoDao.findby_brochureId(brochureId));
        for(Video tempVideo :videoList){
        System.out.println("videoList has " + tempVideo.getVideoTitle());
        }
        this.videoCount = this.videoList.size();
    }
    
    public void upload() {
        String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
        User user = UserDao.findby_userName(userName);
        Video video = new Video();
        video.setUser(user);
        video.setBrochure(brochure);
        video.setVideoStartTime(new Date());
        video.setVideoModifyTime(new Date());
        video.setVideoTitle(uploadName);
        video.setVideoUrl(uploadUrl);
        VideoDao.add_video(video);
    }
    
}
