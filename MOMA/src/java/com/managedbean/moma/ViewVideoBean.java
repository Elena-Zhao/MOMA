/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.VideoDao;
import com.entity.moma.Video;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianshujun
 */
@ManagedBean
@RequestScoped
public class ViewVideoBean {
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    
    /**
     * Creates a new instance of ViewVideoBean
     */
    public ViewVideoBean() {
        int videoId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("viewVideoId").toString());
        System.out.println("videoID is " + videoId);
        video = VideoDao.findby_videoId(videoId);
        System.out.println("video title is " + video.getVideoTitle());
    }
}
