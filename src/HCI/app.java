package app;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Date;
import PD.alarm.*;
import PD.applicant.*;
import PD.apply.*;
import PD.internship.*;
import PD.post.*;
import PD.user.*;

// main method
public class app {
	// 전역변수
	public static String msg = "원하시는 항목의 번호를 입력해주세요 ex)1";
	public static String msg1= "메인화면 돌아가기";
	public static String msg2= "프로그램 종료";
	public static User user = null; // 사용중인 유저
	public static ArrayList<User> ulist = null; // 전체 사용자 유저
	public static ArrayList<Internship> ilist = null; // 전체 인턴쉽
	public static ArrayList<Internship> searchlist = null; // 검색 인턴쉽
	
	// 메인함수
	public static void main(String[] args) {
		
		// 스캐너 
		Scanner sc = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		int option=10;
		int option2=0;
		int iid=0;
		int idx= 0;
		int logout=0;
		int who; // 회원가입 타입 판단
        String msgbuf; // 회원가입 입력 버퍼
        String pw1,pw2; // pw 회원가입 입력 버퍼
        Boolean tf;
        int num;
        Date dt=null;
        float fl;
        boolean same = false;
        User newUser2 = null;
	    SimpleDateFormat sdf = null;

	      
		String str; // 입력용 string
		String strarr[] = new String[10]; // 입력용 string array
		
		
		// 파일 열기 (유저 리스트 가져오기)
		try {
	         FileInputStream fileIn = new FileInputStream("userlist.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         ulist = (ArrayList<User>) in.readObject(); // 유저리스트 가져오기
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
		
		// 파일 열기 (인턴쉽 리스트 가져오기)
		try {
	         FileInputStream fileIn = new FileInputStream("internshiplist.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         ilist = (ArrayList<Internship>) in.readObject(); // 유저리스트 가져오기
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Internship class not found");
	         c.printStackTrace();
	         return;
	      }
		
		// 메인함수
		while(logout==0){
			logout=1;
		
			// user가 null이 아니면
			if(user!=null) {
				System.out.println(user.getName()+ "님 로그아웃합니다!");
				user =null;
			}
			
			// 시작 동작(user 로그인 안되어있다면)
			while(user==null && option != 3){
				
				// *** 0. 시작 화면 
				System.out.println("****************************************");
				System.out.println(msg);
				System.out.println("1. 로그인 하기 ");
				System.out.println("2. 회원가입 하기");
				System.out.println("3. 프로그램 종료");
				System.out.print("입력 값 : ");
	
				// 옵션 값 입력 받기
				option=sc.nextInt();
				
				// 줄바꿈
				System.out.println();
				
				// 옵션값에 따라
				switch(option) {
				
				// 로그인
				case 1:
					System.out.println("****************************************");
					System.out.println("로그인 하기!");
					System.out.printf("ID : ");
					strarr[0] = sc.next(); // ID 입력
					System.out.printf("PW : ");
					strarr[1] = sc.next(); // PW 입력
					
					// 유저 찾기
					for(User u : ulist) {
						// 주어진 ID와 PW가 같은 User 객체를 찾았을 때 
						if (u.getId().equals(strarr[0]) && u.getPasswd().equals(strarr[1]))
							user = u;
					}
					// 로그인 실패 
					if(user ==null)
						System.out.println("로그인 실패!");
					// 로그인 성공
					else
						System.out.println("로그인 성공! "+user.getName()+"님 반갑습니다!");
					break;
					
				// 회원가입 하기
				case 2:
					System.out.println("****************************************");
					System.out.println("회원가입 하기!");
					System.out.print("지원자(1) or 기관(2): ");
				      who=scan.nextInt();
				         
				   // 지원자 !!!!!!!!!!!!
				      if(who == 1) {
				         Applicant newUser = new Applicant();
				         Qualification qual =new Qualification();
				         LanguageAblity lang = new LanguageAblity();
				         Major maj = new Major();
				         Minor mi = new Minor(); 
				         DoubledMajor dmaj = new DoubledMajor(); 
				         
				         //회원가입(공통 기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("아이디 입력: ");
				         msgbuf=scan.next();
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         newUser.setId(msgbuf);
				         do{
				             System.out.print("비밀번호 입력: ");
				             pw1 = scan.next();
				             System.out.print("비밀번호 확인 입력: ");
				             pw2 = scan.next();
				            if (pw1.equals(pw2))
				            same = true;
				            else
				            System.out.print("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				         }while (same == false);
				         newUser.setPasswd(pw1);
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("이름 입력: ");
				         msgbuf=scan.next();
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         newUser.setName(msgbuf);
				         
				         System.out.print("성별 입력(남:true,여:false): ");
				         tf=scan.nextBoolean();
				         newUser.setGender(tf);
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("주소 입력: ");
				         msgbuf=scan.next();
				         newUser.setAddress(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("연락처 입력(xxx-xxxx-xxxx): ");
				         msgbuf=scan.next();
				         newUser.setContact(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         //회원가입 (user에따른 추가기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("학번 입력: ");
				         msgbuf=scan.next();
				         newUser.setStudentID(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("학기 입력(ex. 3): ");
				         num=scan.nextInt();
				         qual.setSemester(num);
				         newUser.setQualification(qual);
				         
				         System.out.print("학점(4.3)입력 (ex. 3.5): ");
				         fl=scan.nextFloat();
				         qual.setGrades(fl);
				         newUser.setQualification(qual);
	
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("어학자격증 입력: ");
				         msgbuf=scan.next();
				         lang.setCertificate(msgbuf);
				         qual.addLanguageAblity(lang);
				         newUser.setQualification(qual);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("어학점수 입력. 숫자로만 입력!(ex.700),(ex.level 6->6) 입력 : ");
				         num=scan.nextInt();
				         lang.setScore(num);
				         qual.addLanguageAblity(lang);
				         newUser.setQualification(qual);
	
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("전공 입력: ");
				         msgbuf=scan.next();
				         maj.setName(msgbuf);
				         qual.addMajor(maj);
				         newUser.setQualification(qual);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("입력하고 싶은 전공을 선택하세요,부전공(1),복수전공(2),없음(3) : ");
				         num=scan.nextInt();
				         if(num==1) {
				            System.out.print("부전공을 입력하세요: ");
				            msgbuf=scan.next();
				            mi.setName(msgbuf);
				            qual.addMajor(mi);
				            newUser.setQualification(qual);
				         }else if(num==2) {
				            System.out.print("복수전공을 입력하세요: ");
				            msgbuf=scan.next();
				            dmaj.setName(msgbuf);
				            qual.addMajor(dmaj);
				            newUser.setQualification(qual);
				         }
				         
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				        
				         System.out.println("****************************************");
				         System.out.println("회원가입 완료!");
				         newUser2=newUser;
				      }
				      // 기관!!!!!!!!!!!!
				      else {
				         Organization newUser = new Organization();
				         sdf = new SimpleDateFormat("yyyy-MM-dd");
				         
				         //회원가입(공통 기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("아이디 입력: ");
				         msgbuf=scan.next();
				         newUser.setId(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         do{
				             System.out.print("비밀번호 입력: ");
				             pw1 = scan.next();
				             System.out.print("비밀번호 확인 입력: ");
				             pw2 = scan.next();
				            if (pw1.equals(pw2))
				            same = true;
				            else
				            System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				         }while (same == false);
				         newUser.setPasswd(pw1);
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("이름 입력: ");
				         msgbuf=scan.next();
				         newUser.setName(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("성별 입력(남:true,여:false): ");
				         tf=scan.nextBoolean();
				         newUser.setGender(tf);
				        
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("주소 입력: ");
				         msgbuf=scan.next();
				         newUser.setAddress(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("연락처 입력(xxx-xxxx-xxxx): ");
				         msgbuf=scan.next();
				         newUser.setContact(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         //회원가입 (user에따른 추가기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("위치 입력: ");
				         msgbuf=scan.next();
				         newUser.setLocation(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("직원 수 입력(ex. 100): ");
				         num=scan.nextInt();
				         newUser.setEmployeeScale(num);
				         
				         System.out.print("평균 매출액 입력(ex. 1000000): ");
				         num=scan.nextInt();
				         newUser.setSaleAmount(num);
				         
				         System.out.print("설립일 입력(yyyy-MM-dd): ");
				         msgbuf=scan.next();
				         try {
				             //Parsing the String
				             dt = sdf.parse(msgbuf);
				         } catch (ParseException e) {
				             // TODO Auto-generated catch block
				             e.printStackTrace();
				         }
				         newUser.setEstablishDate(dt);
	
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("대표이름 입력: ");
				         msgbuf=scan.next();
				         newUser.setRepresentative(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("웹주소 입력: ");
				         msgbuf=scan.next();
				         newUser.setWebaddress(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
	
						 System.out.println("****************************************");
				         System.out.println("회원가입 완료!");
				         newUser2=newUser;
				      }
					
					ulist.add(newUser2); // 유저 추가
					break;
					
				// 프로그램 종료
				case 3:
					System.out.println("****************************************");
	//				System.out.println("프로그램을 종료합니다!");
					break;
				}
				
				// 줄바꿈
				System.out.println();
				
			} // end while
			
			// *** 3-1. 지원자 메인화면
			// 종료 옵션은 100 
			while(user instanceof Applicant && option != 100) {
				
				// 목록보기 바로 안갈 때 
				if(option2 != 100) {
					searchlist = null; // 검색 초기화
					
					// 출력 화면 
					System.out.println("****************************************");
					System.out.println("지원자 메인화면");
//					System.out.println("****************************************");
					System.out.println(msg);
					System.out.println("0. 로그아웃");
					System.out.println("1. 인턴쉽 검색");
					System.out.println("2. 자격요건 관리");
					System.out.println("3. 관심인턴쉽 목록 보기");
					System.out.println("4. 내 지원목록 보기 ");
					System.out.println("5. 메인화면 가기");
					System.out.println("6. 프로그램 종료");
					System.out.print("입력 값 : ");
					
					// 옵션 값 입력 받기
					option=sc.nextInt();
					
					// 예외처리
					if(option>6 || option<0)
						break;
					
					// 옵션값 따라
					if(option ==0) {
						logout=0; // 로그아웃
						break;
					}
					
					// ** 3-1-1 검색
					if (option==1) {
						System.out.println("****************************************");
						System.out.println("인턴쉽 검색");
//						System.out.println("****************************************");
						String strArr[] = new String[3]; // String array 
						
						// 국가 입력
						System.out.println();
						System.out.println("1. 메인 화면 가기 ");
						System.out.println("2. 프로그램 종료");
						System.out.println("해당 검색옵션 사용을 원치 않으시면 x를 입력하세요. (입력  : x)");
						System.out.println("검색하고자 하는 국가를 입력하세요. ex(입력 : america)");
						System.out.print("입력 : ");
						strArr[0] = sc.next(); // 국가 입력받기
						
						// 메인화면 가기
						if(strArr[0].equals("1"))
							break;
						// 종료
						if(strArr[0].equals("2")) {
							option=100; // 종료
							break;
						}
						
						// 전공 입력
						System.out.println();
						System.out.println("1. 메인 화면 가기 ");
						System.out.println("2. 프로그램 종료");
						System.out.println("해당 검색옵션 사용을 원치 않으시면 x를 입력하세요. (입력  : x)");
						System.out.println("검색하고자 하는 전공을 입력하세요. ex(입력 : it)");
						System.out.print("입력 : ");
						strArr[1] = sc.next(); // 전공 입력받기
						
						// 메인화면 가기
						if(strArr[1].equals("1"))
							break;
						// 종료
						if(strArr[1].equals("2")) {
							option=100; // 종료
							break;
						}
						
						// 기관 입력
						System.out.println();
						System.out.println("1. 메인 화면 가기 ");
						System.out.println("2. 프로그램 종료");
						System.out.println("해당 검색옵션 사용을 원치 않으시면 x를 입력하세요. (입력  : x)");
						System.out.println("검색하고자 하는 기관을 입력하세요. ex(입력 : samsung)");
						System.out.print("입력 : ");
						strArr[2] = sc.next(); // 기관 입력받기
						
						// 메인화면 가기
						if(strArr[2].equals("1"))
							break;
						// 종료
						if(strArr[2].equals("2")) {
							option=100; // 종료
							break;
						}
						
						// 검색 알림
						System.out.println();
						System.out.println("다음의 검색 옵션으로 검색을 실시합니다");
						System.out.println("국가 - "+strArr[0]+ ", 전공 - "+ strArr[1] +", 기관 - " +strArr[2]);
						
						// 검색 수행
//						 searchlist  = search()
					}// end if
					
					// ** 3-1-1-1 검색 결과 보여주기 다시 while
					while(logout!=0 && option != 2 &&option != 3 && option != 4 &&option != 5) {
		//				for(Internship i : itslist){}
						System.out.println("****************************************");
						System.out.println("상세 보기를 원하는 인턴쉽 id를 입력해주세요. (입력 : 4)");
						System.out.println("-1. 메인 화면 가기");
						System.out.println("-2. 프로그램 종료");
						System.out.print("입력 : ");
						iid = sc.nextInt(); // 입력받기
						
						// 메인 화면 가기
						if(iid == -1)
							break;
						
						// 종료 하기
						if(iid == -2) {
							option=100; // 종료
							break;
							
						}
						
						// 해당 ID로 검색 
		//				for(Internship i : itslist) {}
						
						// * 3-1-1-1-1 해당 인턴쉽 상세보기 
						System.out.println("****************************************");
	//					System.out.println(internshipname + "인턴쉽의 상세 정보 보기");
						System.out.println(msg);
						System.out.println("1. 지원하기");
						System.out.println("2. 관심인턴쉽 등록");
						System.out.println("3. 검색 결과 목록 돌아가기");
						System.out.println("4. 메인 화면 가기");
						System.out.println("5. 프로그램 종료");
						System.out.print("입력 : ");
						option2 = sc.nextInt();
						
						// 지원하기 (option 2 안에서 처리)
						
						// 관심인턴쉽 등록 - 등록완료 메세지 출력 후 결과목록가기
						
						
						// 결과목록가기
						if(option2 == 3) {
							option2= 100; //결과 목록가기
						}
						
						// 메인 화면 가기
						if(option2 == 4)
							break;
						
						// 종료 하기
						if(option2 == 5) {
							option=100; // 종료
							break;
						}
						
						// 관심인턴쉽 등록 
					}// end while
				
					
					// 3-1-2 자격요건관리
					if(option== 2) {
						System.out.println("****************************************");
						System.out.println(user.getName()+"님의 자격 요건 수정");
//						System.out.println("****************************************");
					}
					
					// 3-1-3 관심인턴쉽 목록 보기
					if(option==3)
						;
					
					// 3-1-4 내 지원목록보기
					if(option==4) {
						System.out.println("****************************************");
						System.out.println(user.getName()+"님의 지원 목록 보기");
//						System.out.println("****************************************");
					}
					// 메인화면 가기
					if(option==5)
						;
						
					// 종료 하기
					if(option ==6) { 
						option= 100; // 종료옵션주기
					}
				}
			}
			
			// *** 3-2. 기관 메인화면
			// 종료 옵션은 100
			while(user instanceof Organization && option != 100) {
				// 출력 화면 
				System.out.println("****************************************");
				System.out.println("기관 메인화면");
//				System.out.println("****************************************");
				System.out.println(msg);
				System.out.println("0. 로그아웃");
				System.out.println("1. 인턴쉽 등록");
				System.out.println("2. 내 인턴쉽 관리");
				System.out.println("3. 메인화면 가기");
				System.out.println("4. 프로그램 종료");
				System.out.print("입력 값 : ");
				
				// 옵션 값 입력 받기
				option=sc.nextInt();
				
				// 옵션 값에 따라
				if(option == 0) {
					logout=0;
					break;
				}
				
				// 인턴쉽 등록
				if(option==1) {
			         
					// 새인턴쉽 만들기
					Internship intern = new Internship();
				    String[] arrbuf=null;
				    sdf = new SimpleDateFormat("yyyy-MM-dd");

					System.out.println("****************************************");
					System.out.println("인턴십등록하기");
					
					// 업무 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
					System.out.print("해당 업무를 적으세요: ");
				    msgbuf=scan.next();
				    intern.setJob(msgbuf);
				    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 break;

					// 봉급 입력
				    System.out.print("봉급을 적으세요: ");
				    fl=scan.nextFloat();
				    intern.setSalary(fl);
				    
				    // 근무지 입력
				    System.out.print("근무지는 몇개인가요: ");
				    num=scan.nextInt();
				    arrbuf=new String[num];
				    for(int i=0; i<num;i++) {
				       System.out.printf("%d번 근무지를 적어주세요: ",i+1);
				       arrbuf[i]=scan.next();
				    }
				    intern.setWorkplace(arrbuf);
				    

					// 근무시간 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
				    System.out.print("근무시간을 적어주세요: ");
				    msgbuf=scan.next();
				    intern.setWorkinghour(msgbuf);
				    
				    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 break;

					// 마감기한 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
				    System.out.print("인턴십 마감기한을 적어주세요(yyyy-MM-dd)");
				    msgbuf=scan.next();
	
				    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 break;
			         
				      try {
				          //Parsing the String
				          dt = sdf.parse(msgbuf);
				      } catch (ParseException e) {
				          // TODO Auto-generated catch block
				          e.printStackTrace();
				      }
				      intern.setDeadline(dt);
				      
				      // 모집인원 입력
				      System.out.print("모집인원을 적어주세요: ");
				      num=scan.nextInt();
				      intern.setRecruitmentNumber(num);
				      
				      // 전화번호 입력
   					  System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
				      System.out.print("회사 연락처를 적어주세요(전화번호): ");
				      msgbuf=scan.next();
				      intern.setContact(msgbuf);
				      // 입력 중단
			          if(msgbuf.equals("quit!"))
			        	 break;
				         
				      // 비자 여부 입력
				      System.out.print("visa여부(필요시 true, 불필요시 false): ");
				      tf=scan.nextBoolean();
				      intern.setVisa(tf);
	
				      // ilist에 추가
				      ilist.add(intern);
				}
				
				// 내 인턴쉽 보기
				if(option==2)
					;
				
				// 메인화면 가기
				if(option == 3)
					;
								
				// 종료 하기
				if(option==4) { 
					option= 100; // 종료옵션주기
				}
			}
		}
		
		System.out.println("**************시스템을 종료합니다**************");
		
		// 파일 저장하기 (유저리스트)
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("userlist.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(ulist);
	         out.close();
	         fileOut.close();
//	         System.out.printf("Serialized data is saved in /tmp/userlist.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		// 파일 저장하기 (인턴쉽 리스트)
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("internshiplist.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(ilist);
	         out.close();
	         fileOut.close();
//			         System.out.printf("Serialized data is saved in /tmp/userlist.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
}
