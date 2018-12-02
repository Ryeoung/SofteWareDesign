package app;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import PD.alarm.*;
import PD.applicant.*;
import PD.apply.*;
import PD.internship.*;
import PD.post.*;
import PD.user.*;

// main method
public class app {
	// �쟾�뿭蹂��닔
	public static String msg = "�썝�븯�떆�뒗 �빆紐⑹쓽 踰덊샇瑜� �엯�젰�빐二쇱꽭�슂 ex)1";
	public static String msg1= "硫붿씤�솕硫� �룎�븘媛�湲�";
	public static String msg2= "�봽濡쒓렇�옩 醫낅즺";
	public static User user = null; // �궗�슜以묒씤 �쑀��
	public static ArrayList<User> ulist = null; // �쟾泥� �궗�슜�옄 �쑀��
	
	// 硫붿씤�븿�닔
	public static void main(String[] args) {
		
		// �뒪罹먮꼫 
		Scanner sc = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		int option=10;
		int option2=0;
		int iid=0;
		int idx= 0;
		int logout=0;
		int who; // �쉶�썝媛��엯 ���엯 �뙋�떒
        String msgbuf; // �쉶�썝媛��엯 �엯�젰 踰꾪띁
        String pw1,pw2; // pw �쉶�썝媛��엯 �엯�젰 踰꾪띁
        Boolean tf;
        int num;
        Date dt=null;
        float fl;
        boolean same = false;
        User newUser2 = null;
        
		String str; // �엯�젰�슜 string
		String strarr[] = new String[10]; // �엯�젰�슜 string array
		
		
		// �뙆�씪 �뿴湲� (�쑀�� 由ъ뒪�듃 媛��졇�삤湲�)
		try {
	         FileInputStream fileIn = new FileInputStream("userlist.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         ulist = (ArrayList<User>) in.readObject(); // �쑀��由ъ뒪�듃 媛��졇�삤湲�
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
		// �뙆�씪 �뿴湲� (�씤�꽩�돺 由ъ뒪�듃 媛��졇�삤湲�)
		
		// 硫붿씤�븿�닔
		while(logout==0){
			logout=1;
		
			// user媛� null�씠 �븘�땲硫�
			if(user!=null) {
				System.out.println(user.getName()+ "�떂 濡쒓렇�븘�썐�빀�땲�떎!");
				user =null;
			}
			
			// �떆�옉 �룞�옉(user 濡쒓렇�씤 �븞�릺�뼱�엳�떎硫�)
			while(user==null && option != 3){
				
				// *** 0. �떆�옉 �솕硫� 
				System.out.println("****************************************");
				System.out.println(msg);
				System.out.println("1. 濡쒓렇�씤 �븯湲� ");
				System.out.println("2. �쉶�썝媛��엯 �븯湲�");
				System.out.println("3. �봽濡쒓렇�옩 醫낅즺");
				System.out.print("�엯�젰 媛� : ");
	
				// �샃�뀡 媛� �엯�젰 諛쏄린
				option=sc.nextInt();
				
				// 以꾨컮轅�
				System.out.println();
				
				// �샃�뀡媛믪뿉 �뵲�씪
				switch(option) {
				
				// 濡쒓렇�씤
				case 1:
					System.out.println("****************************************");
					System.out.println("濡쒓렇�씤 �븯湲�!");
					System.out.printf("ID : ");
					strarr[0] = sc.next(); // ID �엯�젰
					System.out.printf("PW : ");
					strarr[1] = sc.next(); // PW �엯�젰
					
					// �쑀�� 李얘린
					for(User u : ulist) {
						// 二쇱뼱吏� ID�� PW媛� 媛숈� User 媛앹껜瑜� 李얠븯�쓣 �븣 
						if (u.getId().equals(strarr[0]) && u.getPasswd().equals(strarr[1]))
							user = u;
					}
					// 濡쒓렇�씤 �떎�뙣 
					if(user ==null)
						System.out.println("濡쒓렇�씤 �떎�뙣!");
					// 濡쒓렇�씤 �꽦怨�
					else
						System.out.println("濡쒓렇�씤 �꽦怨�! "+user.getName()+"�떂 諛섍컩�뒿�땲�떎!");
					break;
					
				// �쉶�썝媛��엯 �븯湲�
				case 2:
					System.out.println("****************************************");
					System.out.println("�쉶�썝媛��엯 �븯湲�!");
					System.out.print("吏��썝�옄(1) or 湲곌�(2): ");
				      who=scan.nextInt();
				         
				   // 吏��썝�옄 !!!!!!!!!!!!
				      if(who == 1) {
				         Applicant newUser = new Applicant();
				         Qualification qual =new Qualification();
				         LanguageAblity lang = new LanguageAblity();
				         Major maj = new Major();
				         Minor mi = new Minor(); 
				         DoubledMajor dmaj = new DoubledMajor(); 
				         
				         //�쉶�썝媛��엯(怨듯넻 湲곗엯�궗�빆)
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�븘�씠�뵒 �엯�젰: ");
				         msgbuf=scan.next();
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         newUser.setId(msgbuf);
				         do{
				             System.out.print("鍮꾨�踰덊샇 �엯�젰: ");
				             pw1 = scan.next();
				             System.out.print("鍮꾨�踰덊샇 �솗�씤 �엯�젰: ");
				             pw2 = scan.next();
				            if (pw1.equals(pw2))
				            same = true;
				            else
				            System.out.print("鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒿�땲�떎. �떎�떆 �엯�젰�빐二쇱꽭�슂");
				         }while (same == false);
				         newUser.setPasswd(pw1);
				         
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�씠由� �엯�젰: ");
				         msgbuf=scan.next();
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         newUser.setName(msgbuf);
				         
				         System.out.print("�꽦蹂� �엯�젰(�궓:true,�뿬:false): ");
				         tf=scan.nextBoolean();
				         newUser.setGender(tf);
				         
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("二쇱냼 �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setAddress(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�뿰�씫泥� �엯�젰(xxx-xxxx-xxxx): ");
				         msgbuf=scan.next();
				         newUser.setContact(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         //�쉶�썝媛��엯 (user�뿉�뵲瑜� 異붽�湲곗엯�궗�빆)
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�븰踰� �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setStudentID(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("�븰湲� �엯�젰(ex. 3): ");
				         num=scan.nextInt();
				         qual.setSemester(num);
				         newUser.setQualification(qual);
				         
				         System.out.print("�븰�젏(4.3)�엯�젰 (ex. 3.5): ");
				         fl=scan.nextFloat();
				         qual.setGrades(fl);
				         newUser.setQualification(qual);
	
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�뼱�븰�옄寃⑹쬆 �엯�젰: ");
				         msgbuf=scan.next();
				         lang.setCertificate(msgbuf);
				         qual.addLanguageAblity(lang);
				         newUser.setQualification(qual);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("�뼱�븰�젏�닔 �엯�젰. �닽�옄濡쒕쭔 �엯�젰!(ex.700),(ex.level 6->6) �엯�젰 : ");
				         num=scan.nextInt();
				         lang.setScore(num);
				         qual.addLanguageAblity(lang);
				         newUser.setQualification(qual);
	
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�쟾怨� �엯�젰: ");
				         msgbuf=scan.next();
				         maj.setName(msgbuf);
				         qual.addMajor(maj);
				         newUser.setQualification(qual);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�엯�젰�븯怨� �떢�� �쟾怨듭쓣 �꽑�깮�븯�꽭�슂,遺��쟾怨�(1),蹂듭닔�쟾怨�(2),�뾾�쓬(3) : ");
				         num=scan.nextInt();
				         if(num==1) {
				            System.out.print("遺��쟾怨듭쓣 �엯�젰�븯�꽭�슂: ");
				            msgbuf=scan.next();
				            mi.setName(msgbuf);
				            qual.addMajor(mi);
				            newUser.setQualification(qual);
				         }else if(num==2) {
				            System.out.print("蹂듭닔�쟾怨듭쓣 �엯�젰�븯�꽭�슂: ");
				            msgbuf=scan.next();
				            dmaj.setName(msgbuf);
				            qual.addMajor(dmaj);
				            newUser.setQualification(qual);
				         }
				         
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				        
				         System.out.println("****************************************");
				         System.out.println("�쉶�썝媛��엯 �셿猷�!");
				         newUser2=newUser;
				      }
				      // 湲곌�!!!!!!!!!!!!
				      else {
				         Organization newUser = new Organization();
				         SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				         
				         //�쉶�썝媛��엯(怨듯넻 湲곗엯�궗�빆)
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�븘�씠�뵒 �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setId(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         do{
				             System.out.print("鍮꾨�踰덊샇 �엯�젰: ");
				             pw1 = scan.next();
				             System.out.print("鍮꾨�踰덊샇 �솗�씤 �엯�젰: ");
				             pw2 = scan.next();
				            if (pw1.equals(pw2))
				            same = true;
				            else
				            System.out.println("鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒿�땲�떎. �떎�떆 �엯�젰�빐二쇱꽭�슂");
				         }while (same == false);
				         newUser.setPasswd(pw1);
				         
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�씠由� �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setName(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("�꽦蹂� �엯�젰(�궓:true,�뿬:false): ");
				         tf=scan.nextBoolean();
				         newUser.setGender(tf);
				        
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("二쇱냼 �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setAddress(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�뿰�씫泥� �엯�젰(xxx-xxxx-xxxx): ");
				         msgbuf=scan.next();
				         newUser.setContact(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         //�쉶�썝媛��엯 (user�뿉�뵲瑜� 異붽�湲곗엯�궗�빆)
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�쐞移� �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setLocation(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("吏곸썝 �닔 �엯�젰(ex. 100): ");
				         num=scan.nextInt();
				         newUser.setEmployeeScale(num);
				         
				         System.out.print("�룊洹� 留ㅼ텧�븸 �엯�젰(ex. 1000000): ");
				         num=scan.nextInt();
				         newUser.setSaleAmount(num);
				         
				         System.out.print("�꽕由쎌씪 �엯�젰(MM-dd-yyyy): ");
				         msgbuf=scan.next();
				         try {
				             //Parsing the String
				             dt = sdf.parse(msgbuf);
				         } catch (ParseException e) {
				             // TODO Auto-generated catch block
				             e.printStackTrace();
				         }
				         newUser.setEstablishDate(dt);
	
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("���몴�씠由� �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setRepresentative(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(�쉶�썝 �젙蹂� �엯�젰 以묐떒�븯湲�  : quit!)");
				         System.out.print("�쎒二쇱냼 �엯�젰: ");
				         msgbuf=scan.next();
				         newUser.setWebaddress(msgbuf);
				         // �엯�젰 以묐떒
				         if(msgbuf.equals("quit!"))
				        	 break;
	
						 System.out.println("****************************************");
				         System.out.println("�쉶�썝媛��엯 �셿猷�!");
				         newUser2=newUser;
				      }
					
					ulist.add(newUser2); // �쑀�� 異붽�
					break;
					
				// �봽濡쒓렇�옩 醫낅즺
				case 3:
					System.out.println("****************************************");
	//				System.out.println("�봽濡쒓렇�옩�쓣 醫낅즺�빀�땲�떎!");
					break;
				}
				
				// 以꾨컮轅�
				System.out.println();
				
			} // end while
			
			// *** 3-1. 吏��썝�옄 硫붿씤�솕硫�
			// 醫낅즺 �샃�뀡�� 100 
			while(user instanceof Applicant && option != 100) {
				
				// 紐⑸줉蹂닿린 諛붾줈 �븞媛� �븣 
				if(option2 != 100) {
					// 異쒕젰 �솕硫� 
					System.out.println("****************************************");
					System.out.println("吏��썝�옄 硫붿씤�솕硫�");
//					System.out.println("****************************************");
					System.out.println(msg);
					System.out.println("0. 濡쒓렇�븘�썐");
					System.out.println("1. �씤�꽩�돺 寃��깋");
					System.out.println("2. �옄寃⑹슂嫄� 愿�由�");
					System.out.println("3. 愿��떖�씤�꽩�돺 紐⑸줉 蹂닿린");
					System.out.println("4. �궡 吏��썝紐⑸줉 蹂닿린 ");
					System.out.println("5. 硫붿씤�솕硫� 媛�湲�");
					System.out.println("6. �봽濡쒓렇�옩 醫낅즺");
					System.out.print("�엯�젰 媛� : ");
					
					// �샃�뀡 媛� �엯�젰 諛쏄린
					option=sc.nextInt();
					
					// �삁�쇅泥섎━
					if(option>6 || option<0)
						break;
					
					// �샃�뀡媛� �뵲�씪
					if(option ==0) {
						logout=0; // 濡쒓렇�븘�썐
						break;
					}
					
					// ** 3-1-1 寃��깋
					if (option==1) {
						System.out.println("****************************************");
						System.out.println("�씤�꽩�돺 寃��깋");
//						System.out.println("****************************************");
						String strArr[] = new String[3]; // String array 
						
						// 援�媛� �엯�젰
						System.out.println();
						System.out.println("1. 硫붿씤 �솕硫� 媛�湲� ");
						System.out.println("2. �봽濡쒓렇�옩 醫낅즺");
						System.out.println("�빐�떦 寃��깋�샃�뀡 �궗�슜�쓣 �썝移� �븡�쑝�떆硫� x瑜� �엯�젰�븯�꽭�슂. (�엯�젰  : x)");
						System.out.println("寃��깋�븯怨좎옄 �븯�뒗 援�媛�瑜� �엯�젰�븯�꽭�슂. ex(�엯�젰 : america)");
						System.out.print("�엯�젰 : ");
						strArr[0] = sc.next(); // 援�媛� �엯�젰諛쏄린
						
						// 硫붿씤�솕硫� 媛�湲�
						if(strArr[0].equals("1"))
							break;
						// 醫낅즺
						if(strArr[0].equals("2")) {
							option=100; // 醫낅즺
							break;
						}
						
						// �쟾怨� �엯�젰
						System.out.println();
						System.out.println("1. 硫붿씤 �솕硫� 媛�湲� ");
						System.out.println("2. �봽濡쒓렇�옩 醫낅즺");
						System.out.println("�빐�떦 寃��깋�샃�뀡 �궗�슜�쓣 �썝移� �븡�쑝�떆硫� x瑜� �엯�젰�븯�꽭�슂. (�엯�젰  : x)");
						System.out.println("寃��깋�븯怨좎옄 �븯�뒗 �쟾怨듭쓣 �엯�젰�븯�꽭�슂. ex(�엯�젰 : it)");
						System.out.print("�엯�젰 : ");
						strArr[1] = sc.next(); // �쟾怨� �엯�젰諛쏄린
						
						// 硫붿씤�솕硫� 媛�湲�
						if(strArr[1].equals("1"))
							break;
						// 醫낅즺
						if(strArr[1].equals("2")) {
							option=100; // 醫낅즺
							break;
						}
						
						// 湲곌� �엯�젰
						System.out.println();
						System.out.println("1. 硫붿씤 �솕硫� 媛�湲� ");
						System.out.println("2. �봽濡쒓렇�옩 醫낅즺");
						System.out.println("�빐�떦 寃��깋�샃�뀡 �궗�슜�쓣 �썝移� �븡�쑝�떆硫� x瑜� �엯�젰�븯�꽭�슂. (�엯�젰  : x)");
						System.out.println("寃��깋�븯怨좎옄 �븯�뒗 湲곌��쓣 �엯�젰�븯�꽭�슂. ex(�엯�젰 : samsung)");
						System.out.print("�엯�젰 : ");
						strArr[2] = sc.next(); // 湲곌� �엯�젰諛쏄린
						
						// 硫붿씤�솕硫� 媛�湲�
						if(strArr[2].equals("1"))
							break;
						// 醫낅즺
						if(strArr[2].equals("2")) {
							option=100; // 醫낅즺
							break;
						}
						
						// 寃��깋 �븣由�
						System.out.println();
						System.out.println("�떎�쓬�쓽 寃��깋 �샃�뀡�쑝濡� 寃��깋�쓣 �떎�떆�빀�땲�떎");
						System.out.println("援�媛� - "+strArr[0]+ ", �쟾怨� - "+ strArr[1] +", 湲곌� - " +strArr[2]);
						
						// 寃��깋 �닔�뻾
		//				InternshipList itslist  =
					}// end if
					
					// ** 3-1-1-1 寃��깋 寃곌낵 蹂댁뿬二쇨린 �떎�떆 while
					while(logout!=0 && option != 2 &&option != 3 && option != 4 &&option != 5) {
		//				for(Internship i : itslist){}
						System.out.println("****************************************");
						System.out.println("�긽�꽭 蹂닿린瑜� �썝�븯�뒗 �씤�꽩�돺 id瑜� �엯�젰�빐二쇱꽭�슂. (�엯�젰 : 4)");
						System.out.println("-1. 硫붿씤 �솕硫� 媛�湲�");
						System.out.println("-2. �봽濡쒓렇�옩 醫낅즺");
						System.out.print("�엯�젰 : ");
						iid = sc.nextInt(); // �엯�젰諛쏄린
						
						// 硫붿씤 �솕硫� 媛�湲�
						if(iid == -1)
							break;
						
						// 醫낅즺 �븯湲�
						if(iid == -2) {
							option=100; // 醫낅즺
							break;
							
						}
						
						// �빐�떦 ID濡� 寃��깋 
		//				for(Internship i : itslist) {}
						
						// * 3-1-1-1-1 �빐�떦 �씤�꽩�돺 �긽�꽭蹂닿린 
						System.out.println("****************************************");
	//					System.out.println(internshipname + "�씤�꽩�돺�쓽 �긽�꽭 �젙蹂� 蹂닿린");
						System.out.println(msg);
						System.out.println("1. 吏��썝�븯湲�");
						System.out.println("2. 愿��떖�씤�꽩�돺 �벑濡�");
						System.out.println("3. 寃��깋 寃곌낵 紐⑸줉 �룎�븘媛�湲�");
						System.out.println("4. 硫붿씤 �솕硫� 媛�湲�");
						System.out.println("5. �봽濡쒓렇�옩 醫낅즺");
						System.out.print("�엯�젰 : ");
						option2 = sc.nextInt();
						
						// 吏��썝�븯湲� (option 2 �븞�뿉�꽌 泥섎━)
						
						// 愿��떖�씤�꽩�돺 �벑濡� - �벑濡앹셿猷� 硫붿꽭吏� 異쒕젰 �썑 寃곌낵紐⑸줉媛�湲�
						
						// 寃곌낵紐⑸줉媛�湲�
						if(option2 == 3) {
							option2= 100; //寃곌낵 紐⑸줉媛�湲�
						}
						
						// 硫붿씤 �솕硫� 媛�湲�
						if(option2 == 4)
							break;
						
						// 醫낅즺 �븯湲�
						if(option2 == 5) {
							option=100; // 醫낅즺
							break;
						}
						
						// 愿��떖�씤�꽩�돺 �벑濡� 
					}// end while
				
					
					// 3-1-2 �옄寃⑹슂嫄닿�由�
					if(option== 2) {
						System.out.println("****************************************");
						System.out.println(user.getName()+"�떂�쓽 �옄寃� �슂嫄� �닔�젙");
//						System.out.println("****************************************");
					}
					
					// 3-1-3 愿��떖�씤�꽩�돺 紐⑸줉 蹂닿린
					if(option==3)
						;
					
					// 3-1-4 �궡 吏��썝紐⑸줉蹂닿린
					if(option==4) {
						System.out.println("****************************************");
						System.out.println(user.getName()+"�떂�쓽 吏��썝 紐⑸줉 蹂닿린");
//						System.out.println("****************************************");
					}
					// 硫붿씤�솕硫� 媛�湲�
					if(option==5)
						;
						
					// 醫낅즺 �븯湲�
					if(option ==6) { 
						option= 100; // 醫낅즺�샃�뀡二쇨린
					}
				}
			}
			
			// *** 3-2. 湲곌� 硫붿씤�솕硫�
			// 醫낅즺 �샃�뀡�� 100
			while(user instanceof Organization && option != 100) {
				// 異쒕젰 �솕硫� 
				System.out.println("****************************************");
				System.out.println("湲곌� 硫붿씤�솕硫�");
//				System.out.println("****************************************");
				System.out.println(msg);
				System.out.println("0. 濡쒓렇�븘�썐");
				System.out.println("1. �씤�꽩�돺 �벑濡�");
				System.out.println("2. �궡 �씤�꽩�돺 愿�由�");
				System.out.println("3. 硫붿씤�솕硫� 媛�湲�");
				System.out.println("4. �봽濡쒓렇�옩 醫낅즺");
				System.out.print("�엯�젰 媛� : ");
				
				// �샃�뀡 媛� �엯�젰 諛쏄린
				option=sc.nextInt();
				
				// �샃�뀡 媛믪뿉 �뵲�씪
				if(option == 0) {
					logout=0;
					break;
				}
				
				// 硫붿씤�솕硫� 媛�湲�
				if(option == 3)
					;
								
				// 醫낅즺 �븯湲�
				if(option==4) { 
					option= 100; // 醫낅즺�샃�뀡二쇨린
				}
			}
		}
		
		System.out.println("**************�떆�뒪�뀥�쓣 醫낅즺�빀�땲�떎**************");
		
		// �뙆�씪 ���옣�븯湲� (�쑀��由ъ뒪�듃)
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
		
		// �뙆�씪 ���옣�븯湲� (�씤�꽩�돺 由ъ뒪�듃)
	}
}
