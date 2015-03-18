package com.rmadss.dao;

public class SqlConstants {

	public static final String _CHECK_USER = "select useridref,loginid,logintype,u.status from logindetails,userdetails u  where loginid=? and password=? and u.status='Active'";
	public static final String _CHANGE_PASSWORD = "update logindetails set password=? where loginid=? and password=?";
	public static final String _EMPLOYEEID = "select nvl(max(employeeid),1000)+1 from employee_Details";

	public static final String _UPDATE_EMPLOYEE = "update employee set job=?,hiredate=?,departmentno=?,salary=? where employeeidref=?";
	public static final String _VIEW_USER_PROFILE = "select u.userid,u.firstname,u.lastname,u.dob,u.emailid,u.photograph,u.gender, a.addressid,a.addresstype,a.houseno,a.STREET,a.city,a.DISTRICT,a.STATE,a.COUNTRY,a.PINCODE,a.phonetype,a.phoneno from userdetails u,addresses a where  u.userid=a.useridref  and u.userid=?";
	public static final String _RECOVER_PASSWORD = "select password from logindetails l,userdetails u where l.loginid=? and u.forgotpwquestion=? and u.forgotpwanswer=?";
	public static final String _NEW_PASSWORD = "update logindetails set password=? where loginid=?";
	public static final String _UPDATE_USERDETAILS = "update userdetails set firstname=?,lastname=?,dob=?,photograph=?,emailid=?,gender=? where userid=?";
	public static final String _UPDATE_ADDRESS = "update addresses set houseno= ?, street= ?, city= ?,district=?, state= ?,country= ?, pincode= ?,phoneno=? where useridref =?";

	// company
	public static final String _ADD_COMPANY = "insert into company values((select nvl(max(companyid),100)+1 from company),?,?,?,?,?,?,?,?,'Request at Admin')";
	public static final String _VIEW_COMPANY = "select * from company ";
	public static final String _DELETE_COMPANY = "delete company where companyid=?";

	// DeviceTO
	public static final String _DEVICE_COMPANY_NAMES = "select companyid,companyname from company";
	public static final String _ADD_DEVICE = "insert into devices values((select nvl(max(deviceid),100)+1 from devices),?,?,?,?)";
	public static final String _DEVICES = "select Deviceid,devicename,devicedescription,(select companyname from company where companyid=devices.companyname),deviceimage from devices";
	public static final String _ADD_DEVICES_REQUEST = "insert into device_request values((select nvl(max(requestid),100)+1 from device_request),?,sysdate,?,'pending',?)";
	public static final String _DEVICE = "select a.requestid,(select loginid from logindetails l where l.useridref=a.useridref),a.requestdate,a.quantity,a.status,(select devicename from devices d where d.deviceid=a.deviceidref ),a.deviceidref,a.useridref from device_request a";
	public static final String _UPDATE_STATUS_ADMIN = "update device_request set status='Request at Inventary' where requestid=?";
	public static final String _PURCHAGE_DEVICE = "insert into device_purchage values((select nvl(max(purchageid),100)+1 from device_purchage),?,?,?,?,?,?)";
	public static final String DEVICE_UPDATE = "update device_request set where status='Accepted' where requestid=?";
	public static final String VIEW_DEVICES_REQUEST = "select Deviceid,devicename,devicedescription,(select companyname from company where companyid=devices.companyname),deviceimage from devices";
	public static final String VIEW_PURCHAGE_DEVICES = "select purchageid,(select devicename from devices where deviceid=device_purchage.deviceidref),devicequantiry,(select companyname from company where companyid=device_purchage.companyidref),deviceprice,totalprice,purchagedate from device_purchage ";
	public static final String _ADD_RATIO = "insert into doctor_device values(?,?,?,?,?,(select nvl(max(devicedetailsid),100)+1 from doctor_device))";
	public static final String _VIEW_DOCTOR_DEVICE = "select devicedetailsid,(select loginid from logindetails where useridref=doctor_device.useridref),(select devicename from devices where deviceid=doctor_device.deviceidref),devicequeantity,patientratio,deviceworkingstatus from doctor_Device";
	public static final String _UPDATE_VIEW_DOCTOR_DEVICE = "select devicedetailsid,(select loginid from logindetails where useridref=doctor_device.useridref),(select devicename from devices where deviceid=doctor_device.deviceidref),devicequeantity,patientratio,deviceworkingstatus from doctor_Device where devicedetailsid=?";
	public static final String _UPDATE_DOCTOR_DEVICE = "update doctor_Device set patientratio=? ,deviceworkingstatus=? where devicedetailsid=?";
	public static final String _INVENTARY_DEVICE = "select * from inventary";
	public static final String INSERT_INVENTARY_DEVICE = "insert into inventary values((select nvl(max(id),100)+1 from inventary),?,?)";
	public static final String UPDATE_INVENTARY_DEVICE = "update inventary set quantity=? where deviceidref=?";
	public static final String _INVENTARY_DEVICES = "select id,(select devicename from devices where deviceid=inventary.deviceidref),quantity from inventary";
	public static final String UPDATE_DEVICE_REQUEST = "update device_request set status='Approved' where requestid=?";
	public static final String INSERT_APPROVED_DEVICE = "insert into device_delivary values((select nvl(max(id),100)+1 from device_delivary),?,?,?,sysdate,?)";
	public static final String _DOCTOR_APPROVED_DEVICES = "select id,(select firstname from userdetails where userid=device_delivary.providerid),(select firstname from userdetails where userid=device_delivary.receiverid),(select devicename from devices where deviceid=device_delivary.receiveddeviceid),devicequantity,devicereceivedate from device_delivary";
	// department
	public static final String _ADD_DEPARTMENT = "insert into department values((select nvl(max(departmentno),100)+1 from department),?, ?)";
	public static final String _VIEW_USER = "select * from employee_details ed ,employee_address ea,employee e "
			+ "where employeeid=? and ed.employeeid=ea.EMPLOYEEIDREF "
			+ "and ed.employeeid=e.employeeidref and ed.status='Active'";
	public static final String _DEPARTMENT = "select * from department where departmentno=?";
	public static final String _VIEW_DEPARTMENT = "select * from department";
	public static final String _SELECT_SALARY = "select salary from employee where employeeidref=?";
	public static final String _VIEW_DATEWISE_EMPLOYEES = "select e.employeeidref,e.employeename,e.job,e.hiredate,"
			+ "e.departmentno,e.salary from Employee e,employee_details ed "
			+ "where ed.status='Active' and e.hiredate<=?  and ed.employeeid=e.employeeidref and e.leftdate is null";
	public static final String _VIEW_EMPIDWIES_EMPLOYEES = "select e.employeeidref,e.employeename,e.job,e.hiredate,"
			+ "e.departmentno,e.salary from Employee e,employee_details ed "
			+ "where ed.status='Active' and e.employeeidref=?  and ed.employeeid=e.employeeidref and e.leftdate is null";
	public static final String _DELETE_DEPARTMENT = "delete department where departmentno=?";
	public static final String _EMP_SAL = "select salary from employee where employeeidref=?";
	public static final String _UPDATE_VIEW_EMPLOYEE = "select e.employeeidref,e.employeename,e.job,e.hiredate,(select departname from department where departmentno=e.departmentno),e.salary,(select location from department where departmentno=e.departmentno ),(select departmentno from department where departmentno=e.departmentno) from employee e where employeeidref=? and leftdate is null";
	public static final String _VIEW_EMP_SALARY = "select e.employeeidref,(select employeename from employee where employeeidref=e.employeeidref),e.salarydate,e.salaryamount,e.salarystatus from employee_salaries e where departmentidref=? and to_char(salarydate,'mon')=to_char(to_date(?,'dd/mm/yyyy'),'mon')";

	
	// mails
	public static final String _MAIL_CONTACTS = "select userid,(select loginid from logindetails where userdetails.userid=logindetails.useridref) from userdetails";
	public static final String _SEND_MAIL = "insert into INBOX_MAILS values((select nvl(max(messageid),100)+1 from INBOX_MAILS),?,?,?,?,sysdate)";
	public static final String _SEND_MAIL1 = "insert into OUTBOX_MAILS values((select nvl(max(messageid),100)+1 from OUTBOX_MAILS),?,?,?,?,sysdate)";
	public static final String _VIEW_OUT_MAILS = "select * from OUTBOX_MAILS where frommailid=?";
	public static final String _VIEW_OUT_MAIL = "select * from OUTBOX_MAILS where messageid=?";
	public static final String _VIEW_IN_MAILS = "select * from INBOX_MAILS where tomailid=?";
	public static final String _VIEW_IN_MAIL = "select * from INBOX_MAILS where messageid=?";
	public static final String _VIEW_MAILID = "select loginid from logindetails where useridref=?";
	public static final String _DELETE_MAIL = "delete OUTBOX_MAILS where messageid=?";
	public static final String _DELETE_MAIL1 = "delete INBOX_MAILS where messageid=?";
	public static final String _TIPS_SUGGESTION = "delete TIPS_SUGGESTIONS where tipsid=?";
	
	// citizen
	public static final String _POST_SUGGESSITION = "insert into tips_suggestions values((select nvl(max(tipsid),100)+1 from tips_suggestions),?,?,?)";
	public static final String _POST_RESUME = "insert into RESUME values((select nvl(max(resumeid),100)+1 from resume),?,?)";
	public static final  String _VIEW_RESUME="select * from resume";
	// cases
	public static final String _ADD_CASE_DETAILS = "insert into cases values((select nvl(max(caseid),100)+1 from cases),?,sysdate,?,'open')";
	public static final String _ADD_CASE_FILE = "insert into case_details values((select max(caseid) from cases),(select nvl(max(casedetailsid),100)+1 from case_details),?,?)";
	public static final String _ADD_CASE_EVIDENCES = "insert into case_details values(?,(select nvl(max(casedetailsid),100)+1 from case_details),?,?)";

	public static final String _VIEW_CASES = "select * from CASES where casestatus='open'";
	public static final String _VIEW_AGENT_CASES = "select caseidref,(select casename from cases where caseid=caseidref),caseassigndate,casestatus from case_assing_agent where agentidref=?";
	public static final String _VIEW_AGENCY_ASSIGNED_CASES = "select case_assig_id,(select casename from cases where caseid=case_id_ref),(select agencyname from agency where agencyid=agency_id),assign_date,status from  cases_assign";
	public static final String _VIEW_AGENCY_CASES = "select case_assig_id,(select casename from cases where caseid=case_id_ref),(select agencyname from agency where agencyid=agency_id),assign_date,status from  cases_assign where agency_id=(select agencyid from agency where agencychief=?)";
	public static final String _VIEW_CASES_UPDATE = "select * from cases where caseid=?";
	public static final String _UPDATE_CASE = "update cases set casedetails=?,casestatus=? where caseid=?";
	public static final String _DELETE_HOLIDAY = "delete cases where caseid=?";
	public static final String _VIEW_AGENCY_CHIEF = "select useridref,loginid from logindetails where logintype='AgentChief'";
	public static final String _ADD_AGENCY_DETAILS = "insert into agency values((select nvl(max(agencyid),100)+1 from agency),?,?,?,?,?)";
	public static final String _VIEW_AGENCY = "select agencyid,agencyname,agencydescription,address,contact,(select loginid from logindetails where logindetails.useridref=agencychief) from Agency";
	public static final String _VIEW_CASE_FILES = "select caseidref,casedetailsid,casefile,filetype,(select casename from cases where caseid=?) from case_Details where caseidref=?";
	public static final String _ASSIGN_CASE_AGENCY_DETAILS = "insert into cases_assign values((select nvl(max(case_assig_id),100)+1 from cases_assign),?,?,sysdate,'Process')";
	public static final String _VIEW_ASSIGNED_CASES = "select case_id_ref,(select casename from cases where caseid=case_id_ref),(select agencyid from agency where agencychief=?),(select agencyname from agency where agencychief=?) from cases_assign where agency_id=(select agencyid from agency where agencychief=?)";
	public static final String _VIEW_AGENCY_ASSIGN_CASES = "select agencyid,agencyname,agencydescription,address,contact,(select loginid from logindetails where logindetails.useridref=agencychief) from Agency";
	public static final String _VIEW_AGENTS = "select useridref,loginid,agent.agentid from logindetails,agent where logintype='Agent' and agent.agencychiefid=? and agent.agentid=LOGINDETAILS.USERIDREF ";
	public static final String _ADD_CASE_AGENT = "insert into case_assing_agent values((select nvl(max(case_assignid),100)+1 from case_assing_agent),?,?,sysdate,'process')";
	public static final String _VIEW_AGENT_ASSIGNED_CASES = "select case_assignid,(select casename from cases where caseid=caseidref),(select loginid from logindetails where useridref=agentidref),caseassigndate,casestatus from  case_assing_agent";

	
	public static final String _TIPS_SUGGESSTIONS = "select * from tips_suggestions";
	
	// profile
	public static final String _VIEW_LIST_USERS = "select u.userid,u.firstname,u.lastname,u.dor,a.houseno,a.street,a.city,a.district,a.state,a.country,"
			+ "a.pincode,a.phoneno,u.photograph from userdetails u,logindetails "
			+ "l,addresses a where l.useridref=a.useridref and a.useridref=u.userid and u.userid=l.useridref and l.logintype=? and u.status='Active'";
	public static final String _DELETE_USER = "update userdetails set status='DeActive' where userid=?";
	public static final String _DELETE_RESUME = "delete resume where resumeid=?";
}