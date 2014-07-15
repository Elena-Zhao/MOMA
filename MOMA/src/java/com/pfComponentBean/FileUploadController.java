/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfComponentBean;

import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
  
import org.primefaces.event.FileUploadEvent;  
import org.primefaces.model.UploadedFile;  
  
public class FileUploadController {  
  
    public void handleFileUpload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}  
   
