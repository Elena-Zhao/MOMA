/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import com.dao.hibernate.BrochureDao;
import com.dao.hibernate.DiaryDao;
import com.entity.moma.Brochure;
import com.entity.moma.Diary;
import java.io.*;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class WriteDiaryBean {

    private Diary diary;
    private String title;
    private String content;
    private int brochureId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    /**
     * Creates a new instance of WriteDiaryBean
     */
    public WriteDiaryBean() {
        diary = new Diary();
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        brochureId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("brochureToeditId").toString());
    }

    public void doCompletion() throws IOException {
        String fileDir = "/Users/bianshujun/Downloads/MOMA/MOMA/web/Data/Brochure/" + brochureId + "/diary";
        File file = new File(fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        System.out.println("In diary doCompletion");
        System.out.println("currentBrochureId is " + brochureId);
        diary.setDiaryStartTime(new Date());
        diary.setDiaryModifiedTime(new Date());
        diary.setDiaryUrl("temp_url");
        diary.setBrochure(BrochureDao.findby_brochureId(brochureId));
        DiaryDao.add_diary(diary);
        diary.setDiaryUrl("/Users/bianshujun/Downloads/MOMA/MOMA/web/Data/Brochure/" + brochureId + "/diary/" + diary.getDiaryId() + ".txt");
        DiaryDao.modify_diary(diary);

        changeBrochureLatestChange(brochureId);

        File filename = new File(fileDir + "/" + diary.getDiaryId() + ".txt");
        BufferedWriter bfWriter = new BufferedWriter(new FileWriter(filename));
        String allContent = content.toString();
        bfWriter.write(allContent);
        bfWriter.flush();
//        RandomAccessFile tempFile = null;
//        try {
//            tempFile = new RandomAccessFile(filename,"rw");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WriteDiaryBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            tempFile.writeBytes(content);
//    //        DiaryDao.add_diary_brochure(diary.getDiaryId(), brochureId);
//    //        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentBrochureId");
//    //        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentBrochureId") == null) {
//    //        }
//    //        }
////                        BufferedReader buf = new BufferedReader( new InputStreamReader(new FileInputStream(f)));
////            String tempStr = buf.readLine();
//        } catch (IOException ex) {
//            Logger.getLogger(WriteDiaryBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void doModified() {
        diary.setDiaryModifiedTime(new Date());
        DiaryDao.modify_diary(diary);
    }

    private void changeBrochureLatestChange(int brochureId) {
        System.out.println("In changeBrochureLatestChange");
        Brochure currentBrochure = BrochureDao.findby_brochureId(brochureId);
        String userNameChangeDiary;
        if (diary.getUser() != null) {
            userNameChangeDiary = diary.getUser().getUserName();
        } else {
            userNameChangeDiary = "Someone";
        }
        currentBrochure.setBrochureModifyTime(new Date());
        currentBrochure.setLatestChange(userNameChangeDiary + " Diary: " + diary.getDiaryTitle());
        currentBrochure.setLatestChangeType("diary");
        BrochureDao.modify_brochure(currentBrochure);
    }
}
