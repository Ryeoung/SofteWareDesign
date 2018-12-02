package internship;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import PD.user.*;

public final class InternshipList implements java.io.Serializable{
	private static final InternshipList INSTANCE = new InternshipList();//singleton 객체
	private ArrayList<Internship> internships;
	private ArrayList<Internship> searchedList;
	
	//set, get Internship
	private InternshipList() {//생성자
	      try {
	            FileInputStream fileIn = new FileInputStream("internships.ser");
	            ObjectInputStream in = new ObjectInputStream(fileIn);
	            internships = (ArrayList<Internship>) in.readObject(); // 유저리스트 가져오기
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
	public static InternshipList getInternshipList() {//객체 반환
		return INSTANCE;
	}

	public void addInternship(Internship internship) {//만약 기관이 인턴쉽을 새로 등록하면 리스트에 추가한다.
		internships.add(internship);
	}

	public ArrayList<Internship> search(String[] searchOption) {//검색 조건을 통해 검색하면 검색된 목록을 반환
		Internship temp;//검색 결과를 저장할 변수
		searchedList = new ArrayList<Internship>();//검색된 목록을 저장할 List
		Iterator<Internship> it = internships.iterator();//모든 인턴쉽이 저장된 internships에서 검색한다. 
		//searchOption[0]은 nation, [1]은 job, [2]는 organization
		if(searchOption[0].equals("x")) {
			if(searchOption[1].equals("x")) {
				if(searchOption[2].equals("x")) {//3개다 검색안하면 null return
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
					while(it.hasNext()) {//organization만 검색
						temp = it.next();
						if(temp.getJob().equals(searchOption[1])) {
							searchedList.add(temp);
						}
					}
				}
				else {//[1][2]
					while(it.hasNext()) {//organization만 검색
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
					while(it.hasNext()) {//organization만 검색
						temp = it.next();
						if(temp.getOrganization().getNation().equals(searchOption[0])) {
							searchedList.add(temp);
						}
					}
				}
				else {//[0][2]
					while(it.hasNext()) {//organization만 검색
						temp = it.next();
						if(temp.getOrganization().getNation().equals(searchOption[0]) && temp.getOrganization().getName().equals(searchOption[2])) {
							searchedList.add(temp);
						}
					}
				}
			}
			else {
				if(searchOption[2].equals("x")) {//[0][1]
					while(it.hasNext()) {//organization만 검색
						temp = it.next();
						if(temp.getOrganization().getNation().equals(searchOption[0]) && temp.getJob().equals(searchOption[1])) {
							searchedList.add(temp);
						}
					}
				}
				else {//[0][1][2]
					while(it.hasNext()) {//organization만 검색
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