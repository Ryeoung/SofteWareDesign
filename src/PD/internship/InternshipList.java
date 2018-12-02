package internship;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import PD.user.*;

public final class InternshipList implements java.io.Serializable{
	private static final InternshipList INSTANCE = new InternshipList();//singleton ��ü
	private ArrayList<Internship> internships;
	private ArrayList<Internship> searchedList;
	
	//set, get Internship
	private InternshipList() {//������
	      try {
	            FileInputStream fileIn = new FileInputStream("internships.ser");
	            ObjectInputStream in = new ObjectInputStream(fileIn);
	            internships = (ArrayList<Internship>) in.readObject(); // ��������Ʈ ��������
	            in.close();
	            fileIn.close();
	         } catch (IOException i) {
	            i.printStackTrace();
	            return;
	         } catch (ClassNotFoundException c) {
	            System.out.println("User class not found");
	            c.printStackTrace();
	            return;
	         }
	}
	public static InternshipList getInternshipList() {//��ü ��ȯ
		return INSTANCE;
	}

	public void addInternship(Internship internship) {//���� ����� ���Ͻ��� ���� ����ϸ� ����Ʈ�� �߰��Ѵ�.
		internships.add(internship);
	}

	public ArrayList<Internship> search(String[] searchOption) {//�˻� ������ ���� �˻��ϸ� �˻��� ����� ��ȯ
		Internship temp;//�˻� ����� ������ ����
		searchedList = new ArrayList<Internship>();//�˻��� ����� ������ List
		Iterator<Internship> it = internships.iterator();//��� ���Ͻ��� ����� internships���� �˻��Ѵ�. 
		//searchOption[0]�� nation, [1]�� job, [2]�� organization
		if(searchOption[0].equals("x")) {
			if(searchOption[1].equals("x")) {
				if(searchOption[2].equals("x")) {//3���� �˻����ϸ� null return
					return null;
				}
				while(it.hasNext()) {//[2]
					temp = it.next();
					if(temp.getOrganization().getName().equals(searchOption[2])) {
						searchedList.add(temp);
					}
				}
			}
			else {
				if(searchOption[2].equals("x")) {//[1]
					while(it.hasNext()) {//organization�� �˻�
						temp = it.next();
						if(temp.getJob().equals(searchOption[1])) {
							searchedList.add(temp);
						}
					}
				}
				else {//[1][2]
					while(it.hasNext()) {//organization�� �˻�
						temp = it.next();
						if(temp.getJob().equals(searchOption[1]) && temp.getOrganization().getName().equals(searchOption[2])) {
							searchedList.add(temp);
						}
					}
				}
			}
		}
		else {
			if(searchOption[1].equals("x")) {
				if(searchOption[2].equals("x")) {//[0]
					while(it.hasNext()) {//organization�� �˻�
						temp = it.next();
						if(temp.getOrganization().getNation().equals(searchOption[0])) {
							searchedList.add(temp);
						}
					}
				}
				else {//[0][2]
					while(it.hasNext()) {//organization�� �˻�
						temp = it.next();
						if(temp.getOrganization().getNation().equals(searchOption[0]) && temp.getOrganization().getName().equals(searchOption[2])) {
							searchedList.add(temp);
						}
					}
				}
			}
			else {
				if(searchOption[2].equals("x")) {//[0][1]
					while(it.hasNext()) {//organization�� �˻�
						temp = it.next();
						if(temp.getOrganization().getNation().equals(searchOption[0]) && temp.getJob().equals(searchOption[1])) {
							searchedList.add(temp);
						}
					}
				}
				else {//[0][1][2]
					while(it.hasNext()) {//organization�� �˻�
						temp = it.next();
						if(temp.getOrganization().getNation().equals(searchOption[0]) && temp.getJob().equals(searchOption[1]) &&temp.getOrganization().getName().equals(searchOption[2])) {
							searchedList.add(temp);
						}
					}
				}
			}
		}
		return searchedList;
	}
}