package hci;


import PD.apply.*;
import PD.applicant.*;
import PD.internship.*;

import user.User;

public class ApplyUI {
	private Applicant user;
	public User getUser() {
		return user;
	}
	public void setUser(Applicant user) {
		this.user = user;
	}

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	private Internship internship;
	
	public boolean apply(String filepath){
		boolean checkQual=false;
		Resume resume=new Resume();
		
		Qualification qual=user.getQualification();
		checkQual=this.internship.checkQualification(qual);// �ش� ���� ������ Ȯ���Ѵ�.
		if(checkQual == false)
			return false;// ���� ������ ���� ���ϸ� �Ф�
		
		Application app=new Application();
		
		resume.setFilename(filepath);//���� ��� ���
		
		app.setResume(resume);//resume�� application�� ���
		resume.setApplication(app);//application�� resume�� ���
		
		if(!app.saveFile())//���� ������ ����� ���� ������ 
			return false;
		
		this.user.addApplications(app);//�����ڿ��� application�� �߰��ϰ�
		this.internship.addApplications(app);//���Ͻ����� application�� �߰��Ͽ���.
		
		
		return true;
	}
}
