package com.wwwxy.employeemanagement.ui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

import com.wwwxy.employeemanagement.control.CheckDetailsControl;
import com.wwwxy.employeemanagement.dao.*;
import com.wwwxy.employeemanagement.entity.CheckDetails;
public class CheckDateilsUi {
	SignMethodUi smu=new SignMethodUi();
	Scanner input=new Scanner(System.in);
	CheckDetailsControl cdc=new CheckDetailsControl();
	CheckDetailsDao cdd=new CheckDetailsDao();

	public void all(){
		String f = "y";
		do{
			System.out.println("1����ʾ���п�����Ϣ");
			System.out.println("2������Ա��ID��ѯ������Ϣ");
			System.out.println("3���޸Ŀ���״̬��Ϣ");
			System.out.println("4��ɾ��������Ϣ");
			System.out.println("5��������һ��");
			System.out.println("��������Ҫ���еĹ��������");
			int action=0;
			boolean flag = true;
			while(flag){
				try {
					if(action!=5){
						action = input.nextInt();
						}
					flag = false;
				} catch (Exception e) {
					System.out.println("��������,����������:");
					input = new Scanner(System.in);
					
					flag = true;
					continue;
				}
			}
			switch(action){
			case 1:
				getAllCheckDateils();
				break;
			case 2:
				System.out.println("��������Ҫ��ѯ�Ŀ���Ա��ID��");
 			    getCheckDateilsByempid(action);
				break;
			case 3:
				System.out.println("��������Ҫ�޸ĵĿ���Ա��ID��");
				updateCheckDetailsBYCid();
				break;
			case 4:
				delCheckDetailsBycid(action);
				break;
			case 5:
				f="n";
				break;
			default:
				System.out.println("��������");
			}
			if(action!=5){
				System.out.println("�Ƿ������y/n����");
				f = input.next();
				while(!"n".equals(f)&&!"y".equals(f)){
					System.out.println("��������������y����n");
					f = input.next();
				}
			}
			if(action == 5){
				System.out.println("���˳����ڹ���");
			}
		}while("y".equals(f));
	}
	//��ʾ���п�����Ϣ
		public void getAllCheckDateils(){
			List<CheckDetails> list = cdc.getAllCheckDetails();
			System.out.println("����id\tԱ��id\t ǩ��ʱ��\t\tǩ��ʱ��\t\t����״̬\t��������\t");
			for(CheckDetails cd:list){
				System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
					+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
			}
		}
		//����Ա��id��ѯ������Ϣ
		public void getCheckDateilsByempid(int action){
			boolean flag = true;
			int empid=0;
			while(flag){
				try {
					empid = input.nextInt();
					flag = false;
				} catch (Exception e) {
					System.out.println("��������,����������:");
					input = new Scanner(System.in);
					flag = true;
					continue;
				}
			}
			List<CheckDetails> list = cdc.getCheckDetailsByempid(empid);
			if(list.size()!=0){
				System.out.println("����id\tԱ��id\tǩ��ʱ��\t\tǩ��ʱ��\t\t����״̬\t��������\t");
				for(CheckDetails cd:list){
					System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
							+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
				}
			}else{
				System.out.println("δ��ѯ����Ӧ��Ϣ��");
			}		
		}		
		//ɾ��������Ϣ
	public void delCheckDetailsBycid(int action){
		cdd.getCheckDetailsByempid(action);
		getAllCheckDateils();
		System.out.println("��ѡ����Ҫɾ���Ŀ���id��");
		boolean flag = true;
		int cid=0;
		int empid = 0;
		while(flag){
			try {
				cid = input.nextInt();
				flag = false;
			} catch (Exception e) {
				System.out.println("��������,����������:");
				input = new Scanner(System.in);
				flag = true;
				continue;
			}
		}
		empid = cdd.GetEmpidByCid(cid);
		flag = true;
		boolean flag1 = true;
		while(flag){
			if(empid==0){
				
				System.out.println("����Ŀ���id������,����������:");
				
				while(flag1){
					try {
						cid = input.nextInt();
						flag1 = false;
					} catch (Exception e) {
						System.out.println("��������,����������:");
						input = new Scanner(System.in);
						flag1 = true;
						continue;
					}
				}
				empid = cdd.GetEmpidByCid(cid);
				flag = true;
				
			}else{
				flag = false;
			}
		}
		
		int row = cdc.delCheckDetailsBYCid(cid);
		if(row>0){
			System.out.println("ɾ���ɹ���");
			getAllCheckDateils();
		}else{
			System.out.println("ɾ��ʧ�ܡ�");
		}
	}
	//�޸Ŀ�����Ϣ
	public void updateCheckDetailsBYCid(){
		getAllCheckDateils();
		int empid;
		System.out.println("��������Ҫ�޸ĵĿ���id:");
		boolean flag = true;
		int cid=0;
		while(flag){
			try {
				cid = input.nextInt();
				flag = false;
			} catch (Exception e) {
				System.out.println("��������,����������:");
				input = new Scanner(System.in);
				flag = true;
				continue;
			}
		}
		empid = cdd.GetEmpidByCid(cid);
		flag = true;
		boolean flag1 = true;
		while(flag){
			if(empid==0){
				System.out.println("����Ŀ���id������,����������:");
				
				while(flag1){
					try {
						cid = input.nextInt();
						flag1 = false;
					} catch (Exception e) {
						System.out.println("��������,����������:");
						input = new Scanner(System.in);
						flag1 = true;
						continue;
					}
				}
				empid = cdd.GetEmpidByCid(cid);
				flag = true;
			}else{
				flag = false;
			}
		}
			System.out.println("��ѡ���޸ĺ��ǩ��״̬��");
			int row = to(cid);
		if(row>0){
			System.out.println("�޸ĳɹ�");
			//����������Ϣ
			
			empid = cdd.GetEmpidByCid(cid);
			SignMethodDao smd = new SignMethodDao();
			smd.UpdateEventByEmpid(empid, cid);
			
		}else{
			System.out.println("�޸�ʧ��");
		}
	}
	public int to(int cid){
		String flag ="";
		System.out.println("1.����");
		System.out.println("2.�ٵ�");
		System.out.println("3.����");
		System.out.println("4.����");
		System.out.println("5.�ٵ����Ӱ�");
		System.out.println("6.�Ӱ�");
		System.out.println("7.�ٵ�������");
		int choose=input.nextInt();
		switch (choose) {
		case 1:
			flag ="����";
			break;
		case 2:
			flag = "�ٵ�";
			break;
		case 3:
			flag = "����";
			break;
		case 4:
			flag = "����";
			break;
		case 5:
			flag = "�ٵ����Ӱ�";
			break;
		case 6:
			flag = "�Ӱ�";
			break;
		case 7:
			flag = "�ٵ�������";
			break;
		default:
			System.out.println("��������");
			break;
		}
		int row = cdd.UpdateStatusById(cid, flag);
		return row;
	}
	}
	
	
	
		
	


