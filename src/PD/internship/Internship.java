package internship;

import java.util.Date;
import java.util.ArrayList;

import PD.user.*;
import PD.apply.*;
import PD.alarm.*;
import PD.applicant.*;

public class Internship implements java.io.Serializable {
	private boolean managerAdmit;
	private String job;
	private double salary;
	private String[] workplace;
	private String workinghour;
	private Date deadline;
	private int recruitmentNumber;
	private String contact;
	private boolean visa;
	private Organization organization;
	private ArrayList<Application> applications;
    private InterestedInternshipList interestedInternshipList;
    private ArrayList<Notification> notifications;
    
    //set, get ManagerAdmit
    public void setManagerAdmit(boolean managerAdmit) {
		this.managerAdmit = managerAdmit;
	}
	public boolean isManagerAdmit() {
		return managerAdmit;
	}
	//set, get Job
	public void setJob(String job) {
		this.job = job;
	}
	public String getJob() {
		return job;
	}

	//set, get Salary
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public float getSalary() {
		return salary;
	}
	//set, get Workplace
	public void setWorkplace(String[] workplace) {
		this.workplace = workplace;
	}
	public String[] getWorkplace() {
		return workplace;
	}
	//set, get Workinghour
	public void setWorkinghour(String workinghour) {
		this.workinghour = workinghour;
	}
	public int getWorkinghour() {
		return workinghour;
	}
	//set, get Deadlinecd
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getDeadline() {
		return deadline;
	}
	//set, get RecruitmentNumber
	public void setRecruitmentNumber(int recruitmentNumber) {
		this.recruitmentNumber = recruitmentNumber;
	}
	public int getRecruitmentNumber() {
		return recruitmentNumber;
	}
	//set, get Contact
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact() {
		return contact;
	}
	//set, get visa
	public void setVisa(boolean visa) {
		this.visa = visa;
	}
	public boolean isVisa() {
		return visa;
	}
	//set, get organization
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Organization getOrganization() {
		return organization;
	}
	//add, get applications
	public void addApplications(Application application) {
		if(this.applications == null) {
			this.applications = new ArrayList<Application>();
		}
		this.applications.add(application);
	}
	public ArrayList<Application> getApplications() {
		return applications;
	}
	//set, get InterestedInternshipList
	public void setInterestedInternshipList(InterestedInternshipList interestedInternshipList) {
		this.interestedInternshipList = interestedInternshipList;
	}
	public InterestedInternshipList getInterestedInternshipList() {
		return interestedInternshipList;
	}
	//add, get Notifications
	public void addNotifications(Notification notification) {
		if(this.notifications == null)
			this.notifications = new ArrayList<Notification>();
		this.notifications.add(notification);
	}
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	//疫꿸퀡�뮟 method
	/*
	public boolean requestToApply(Application application) {
		boolean bool = true;
		
		return bool;
	}
	*/
	public void notificate() {
		
	}
	public boolean checkQualification(Qualification qual) {
		boolean bool = true;
		if(qual.getGrades4p5()<(float)3.0)
		{
			System.out.println("筌욑옙占쎌뜚占쎌쁽占쎈뻷占쎌벥 占쎈린占쎌젎占쎌뵠 占쎈퉸占쎈뼣 鈺곌퀗援뷂옙肉� �뜮袁る퉸 �겫占썼�곌퉲鍮�占쎈빍占쎈뼄. \n");
			return false;
		}
		if(qual.getSemeter()<4)
		{
			System.out.println("筌욑옙占쎌뜚占쎌쁽占쎈뻷占쎌벥 占쎌뵠占쎈땾 占쎈린疫꿸퀗占� �겫占썼�곌퉲鍮�占쎈빍占쎈뼄. \n");
			return false;
		}
		return bool;
	}
}
