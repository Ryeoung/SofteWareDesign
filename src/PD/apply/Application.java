package apply;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import PD.applicant.*;
import PD.apply.*;

public class Application implements java.io.Serializable {
	private Date applyDate;
	private boolean result;
	private Applicant applicant;
	/*
	 * private Internship internship;
	 */
	private Resume resume;
	
	//set, get ApplyDate
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	//set, get Result
	public void setResult(boolean result) {
		this.result = result;
	}
	public boolean isResult() {
		return result;
	}
	//set, get Applicant
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	//set, get resume
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public Resume getResume() {
		return resume;
	}
	public boolean saveFile() {
	      try {
	            FileOutputStream fileOut =new FileOutputStream(this.resume.getFilename());
	            ObjectOutputStream out = new ObjectOutputStream(fileOut);
	            out.writeObject(this.resume);
	            out.close();
	            fileOut.close();
	            System.out.printf("Serialized data is saved in /tmp/resume.ser");
	            return true;
	         } catch (IOException i) {
	            i.printStackTrace();
	            return false;
	         }
	}
}
