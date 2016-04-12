package edu.ben.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.Blob;

@Entity 
@Table (name = "FILES_UPLOAD")
public class UploadFile {
    private long id;
    private String fileName;
    private Blob data;
    private User profile;
    private MultipartFile file;

	

	public UploadFile(){
    	super();
    }
 
    public UploadFile(long id, String fileName, Blob data, User profile) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.data = data;
		this.profile = profile;
	}

	@Id
    @GeneratedValue
    @Column(name = "FILE_ID")
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "FILE_NAME")
    public String getFileName() {
        return fileName;
    }
 
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
 
    @Column(name = "FILE_DATA")
    public Blob getData() {
        return data;
    }
 
    public void setData(Blob data) {
        this.data = data;
    }
    
    public User getProfile() {
		return profile;
	}

	public void setProfile(User profile) {
		this.profile = profile;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}