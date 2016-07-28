package com.qtrf.mobile.application;

import java.util.Hashtable;

public class BIZLIVE_Repository {

		Hashtable<String,String> table = new Hashtable<String,String>();
		Hashtable<String,String> typeTable = new Hashtable<String,String>();
		public BIZLIVE_Repository()
		{
			table.put("url", "http://staging-bizlive.ais.co.th:7010");
			table.put("ชื่อผู้ใช้งาน", "//*[@id='userName']");
			typeTable.put("ชื่อผู้ใช้งาน", "xpath");
			table.put("รหัสผ่าน", "//*[@id='password']");
			typeTable.put("รหัสผ่าน", "xpath");
			table.put("เข้าระบบ", "//*[@name='BntLogin']");
			typeTable.put("เข้าระบบ", "xpath");
			table.put("HeadMessage", "//div[@class='contentWrap']/table/tbody/tr/td");
			typeTable.put("HeadMessage", "xpath");
			table.put("รายงาน", "รายงาน");
			typeTable.put("รายงาน", "linkText");			
			 	table.put("รายงานสรุปผลการตอบกลับ SMS", "รายงานสรุปผลการตอบกลับ SMS");
			 	typeTable.put("รายงานสรุปผลการตอบกลับ SMS", "linkText");
			 	table.put("การส่ง SMS แบบถึงผู้รับหลายคน", "//input[@id='broadcast']");
			 	typeTable.put("การส่ง SMS แบบถึงผู้รับหลายคน", "xpath");
			 	table.put("การส่ง SMS แบบโต้ตอบ", "//input[@id='interactive']");
			 	typeTable.put("การส่ง SMS แบบโต้ตอบ", "xpath");
			 	table.put("จาก", "//input[@id='fromDate']");
			 	typeTable.put("จาก", "xpath");
			 	table.put("ถึง", "//input[@id='toDate']");
			 	typeTable.put("ถึง", "xpath");
			 	table.put("หมายเลขผู้ให้บริการ", "//input[@id='formSenderCheck']");
			 	typeTable.put("หมายเลขผู้ให้บริการ", "xpath");
			 	table.put("หมายเลขผู้ให้บริการ_Listbox", "//select[@id='sender']");
			 	typeTable.put("หมายเลขผู้ให้บริการ_Listbox", "xpath");			
			 	table.put("สอบถามข้อมูล Campaignname", "//input[@id='formCamCheck']");
			 	typeTable.put("สอบถามข้อมูล Campaignname", "xpath");
			 	table.put("สอบถามข้อมูล Campaignname_Listbox", "//select[@id='campaign']");
			 	typeTable.put("สอบถามข้อมูล Campaignname_Listbox", "xpath");
			 	table.put("รายงาน2", "//input[@id='submitreport']");
			 	typeTable.put("รายงาน2", "xpath");
			 	table.put("calendarDay", "//tbody/tr/td/a/font[text()='");
			 	typeTable.put("calendarDay", "xpath");
			 	table.put("calendarDate", "//tbody/tr/td/table/tbody/tr/td/font");
			 	typeTable.put("calendarDate", "xpath");
			 	table.put("calendarNextYear", "//tbody/tr/td/table/tbody/tr/td/a/img[@src='img/next_year.gif']");
			 	typeTable.put("calendarNextYear", "xpath");
			 	table.put("calendarPreviousYear", "//tbody/tr/td/table/tbody/tr/td/a/img[@src='img/prev_year.gif']");
			 	typeTable.put("calendarPreviousYear", "xpath");
			 	table.put("calendarNextMonth", "//tbody/tr/td/table/tbody/tr/td/a/img[@src='img/next.gif']");
			 	typeTable.put("calendarNextMonth", "xpath");
			 	table.put("calendarPreviousMonth", "//tbody/tr/td/table/tbody/tr/td/a/img[@src='img/prev.gif']");
			 	typeTable.put("calendarPreviousMonth", "xpath");			
			table.put("การสร้างแคมเปญ", "การสร้างแคมเปญ");
			typeTable.put("การสร้างแคมเปญ", "linkText");	
			 	table.put("การส่ง SMS/MMS แบบโต้ตอบ", "การส่ง SMS/MMS แบบโต้ตอบ");
			 	typeTable.put("การส่ง SMS/MMS แบบโต้ตอบ", "linkText");	
			 	table.put("การสร้างคำถาม", "การสร้างคำถาม");
			 	typeTable.put("การสร้างคำถาม", "linkText");	
			 	table.put("สร้างแคมเปญQuiz", "//*[@href='prepareAddCampaign.do?projectTypeId=3&']/img");
			 	typeTable.put("สร้างแคมเปญQuiz", "xpath");
			 	table.put("หมายเลขบริการ", "//select[@id='shortcodeId']");
			 	typeTable.put("หมายเลขบริการ", "xpath");
			 	table.put("ชื่อแคมเปญQuiz", "//input[@id='serviceName']");
			 	typeTable.put("ชื่อแคมเปญQuiz", "xpath");
			 	table.put("วันที่เริ่มต้น", "//input[@name='startDate']");
			 	typeTable.put("วันที่เริ่มต้น", "xpath");
			 	table.put("วันที่สิ้นสุด", "//input[@name='stopDate']");
			 	typeTable.put("วันที่สิ้นสุด", "xpath");
			 	table.put("รหัส Campaign ที่ผู้ใช้กด", "//input[@name='keyName']");
			 	typeTable.put("รหัส Campaign ที่ผู้ใช้กด", "xpath");
			 	table.put("บริษัท", "//input[@id='reverseChargeY']");
			 	typeTable.put("บริษัท", "xpath");
			 	table.put("ลูกค้า", "//input[@id='reverseChargeN']");
			 	typeTable.put("ลูกค้า", "xpath");
			 	table.put("ใช่", "//input[@id='nextQuestionY']");
			 	typeTable.put("ใช่", "xpath");
			 	table.put("ไม่ใช่", "//input[@id='nextQuestionN']");
			 	typeTable.put("ไม่ใช่", "xpath");
			 	table.put("ระยะเวลาการเล่นในแต่ละคำถาม:วัน", "//input[@id='timeoutDate']");
			 	typeTable.put("ระยะเวลาการเล่นในแต่ละคำถาม:วัน", "xpath");
			 	table.put("ระยะเวลาการเล่นในแต่ละคำถาม:ชั่วโมง", "//input[@id='timeoutHour']");
			 	typeTable.put("ระยะเวลาการเล่นในแต่ละคำถาม:ชั่วโมง", "xpath");
			 	table.put("ระยะเวลาการเล่นในแต่ละคำถาม:นาที", "//input[@id='timeoutMin']");
			 	typeTable.put("ระยะเวลาการเล่นในแต่ละคำถาม:นาที", "xpath");
			 	table.put("SMS", "//input[@id='msgReplyTypeSMS']");
			 	typeTable.put("SMS", "xpath");
			 	table.put("MMS", "//input[@id='msgReplyTypeMMS']");
			 	typeTable.put("MMS", "xpath");
			 	table.put("บันทึกQuiz", "//input[@name='submit']");
			 	typeTable.put("บันทึกQuiz", "xpath");
			 	
		}
		
}

