/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfComponentBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Administrator
 */
@ManagedBean
public class AutoCompleteBean {
    private String[] txt;
    public List<String> complete(String quary){
        txt = new String[10];
        txt[0] = "@163.com";
        txt[1] = "@126.com";
        txt[2] = "@gmail.com";
        txt[3] = "@hotmail.com";
        txt[4] = "@qq.com";
        txt[5] = "@sina.com";
        txt[6] = "@tom.com";
        txt[7] = "@yeah.com";
        txt[8] = "@foxmail.com";
        txt[9] = "@yahoo.com.cn";
        
        List<String> results = new ArrayList<String>();
        
        for(int i = 0; i < 10; i++){
            results.add(quary + txt[i]);
        }
        return results;
    }

    public String[] getTxt() {
        return txt;
    }

    public void setTxt(String[] txt) {
        this.txt = txt;
    }
    
}
