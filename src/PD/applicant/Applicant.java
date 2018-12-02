/*addToapplicaiton()
 * addToInterestedInternship()
 * requestQualification()
 * checkQualification()
 * checkDocument()
 * hasInterestedInternship()
 * */
package applicant;

import PD.user.*;
import PD.applicant.*;
import PD.apply.*;
import PD.internship.*;
import PD.alarm.*;

import java.util.ArrayList;

public class Applicant extends User implements java.io.Serializable {
	private String studentID;
	private DocumentCheckList documentCheckList;
	private Qualification qualification;
	private ArrayList<Application> applications;
	private InterestedInternshipList interestedInternshipList;
	private ArrayList<Notification> notifications;

	public Applicant() {
		super();
	}
	//set, get StudentID
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentID() {
		return this.studentID; 
	}
	//set, get DocumentCheckList
	public void setDocumentCheckList(DocumentCheckList documentCheckList) {
		this.documentCheckList = documentCheckList;
	}
	public DocumentCheckList getDocumentCheckList() {
		return this.documentCheckList;
	}
	//set,get Qualification
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public Qualification getQualification() {
		return this.qualification;
	}
	//add, get Application
	public void addApplications(Application application) {
		if(this.applications == null) 
			this.applications = new ArrayList<Application>();
		else
			this.applications.add(application);
	}
	public ArrayList<Application> getApplications(){
		return this.applications;
	}
	//add, get interestedInternshipList
	public void addInterestedInternshipList(Internship internship) {
		if(this.hasInterestedInterestedInternshipList()) {
			interestedInternshipList.addInternship(internship);
		}
		else {
			this.interestedInternshipList = new InterestedInternshipList();
			interestedInternshipList.addInternship(internship);
		}
	}
	public InterestedInternshipList getInterestedInternshipList() {
		return this.interestedInternshipList;
	}
	//add, get notification
	public void addNotifications(Notification notification) {
		if(this.notifications == null) {
			this.notifications = new ArrayList<Notification>();
			notifications.add(notification);
		}
		else notifications.add(notification);
	}
	public ArrayList<Notification> getNotifications(){
		return this.notifications;
	}
	
	public void addToApplication(Application application) {
		
	}
	
	public void addToInterestedInternshipList(Internship internship) {
		
	}
	
	public void requestQualification() {
		
	}
	
	public boolean checkQualification() {
		boolean bool = true;
		
		return bool;
	}
	
	public boolean checkDocumentCheckList() {
		boolean bool = true;
		
		return bool;
	}
	
	public boolean hasInterestedInterestedInternshipList() {
		if(this.interestedInternshipList == null) return false;
		else return true;
	}
}
