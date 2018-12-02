package hci;


import java.util.Scanner;

import PD.internship.*;
import PD.user.*;

public class InternshipUI {
	private User user;//���� ���� user
	private Internship internship;
	private InternshipList internships=null;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	public void registration(Internship input) {
		//1)���Ͻ� ���� �����Ͱ� ����� Internship ��ü input�� �Ķ���ͷ� �޾ƿ���
		//2) input�� IntershipList�� �߰�
		//���⼭ 
		this.internships=Internships.getInternshipList();
		this.internships.addInternshipList(input);
	}
}
