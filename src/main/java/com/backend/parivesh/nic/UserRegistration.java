package com.backend.parivesh.nic;

import java.util.Date;

import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.legalEntity;
import com.backend.model.Employee;
import com.backend.model.Organisation;

import lombok.Data;

@Data
public class UserRegistration {
	private String Agency_Name;
	private String Status;
	private String CIDN;
	private String Entity_Type;
	private String Pan_No;
	private String Corporation;
	private String CIN_no;
	private String CIN_year;
	private String KYC_Status;
	private String Created_date;
	private String Agency_ID;
	private String State_ID;
	private String District_ID;
	private String Agency_Sub_ID;
	private String Address1;
	private String Address2;
	private String Pincode;
	private String Landmark;
	private String Email_ID;
	private String STD_Code;
	private String Telephone_No;
	private String Mobile_code;
	private String Mobile_No;
	private String Fax_No;
	private String Website;
	private String IP_Address;
	private String Previous_Proposal_Status;
	private String Security_question;
	private String Security_answer;
	private String Status1;
	private String VerificationCode;
	private String password;
	private String ConfirmPassword;
	private String Email_Status;
	private String legalua;
	private String Contact_Name;
	private String Entity_Type_Others;
	private String Agency_Profile_id;
	private String First_Name;
	private String Middle_Name;
	private String Last_Name;
	private String Gender;
	private String Designation;
	private String Created_Date;
	private String Simple_Password;
	private String New_Email_Id;
	private String Relation_Entity;
	private String Name_Proponent;
	private String Implementing_Agency;
	private String Contact_Person;
	private String Std_code;
	private String Fax_Code;
	private String Email_Address;
	private String website;
	private String State_UA;
	private String District_UA;
	private String Townvill_UA;
	private String Security_Question;
	private String Security_Answer;
	private String Password;
	private String Confirm_Password;
	private String Simple_password;
	private String Ip_Address;
	private String Pin;
	private String uniid;
	private String applidesig;
	private String Propaddress;
	private String street;
	private String Entity_Pan_Status;

	public UserRegistration(Employee employee, Organisation organisation, String password) {
		this.Address1 = employee.getAddress();
		if (organisation != null) {
			this.Agency_Name = organisation.getName();
			this.CIN_no = organisation.getCin_no();
			this.CIN_year = organisation.getYear_of_inc() != null ? organisation.getYear_of_inc().toString() : "";
		}
		else
		{
			this.Agency_Name = null;
			this.CIN_no = null;
			this.CIN_year = null;
		}
		this.Address2 = "";
		this.Agency_ID = "";
		this.Agency_Profile_id = "";
		this.Agency_Sub_ID = "";
		this.CIDN = "";
		this.Confirm_Password = "";
		this.ConfirmPassword = "";
		this.Contact_Name = employee.getName_of_Entity();
		this.Contact_Person = employee.getName_of_Entity();
		this.Corporation = "";
		this.Created_date = new Date().toString();
		this.Created_Date = new Date().toString();
		this.Designation = employee.getDesignation();
		this.District_ID = employee.getDistrict();
		this.District_UA = "";
		this.Email_Address = employee.getEmail();
		this.Email_ID = employee.getEmail();
		this.Email_Status = "F";
		this.Entity_Pan_Status = "";
		for (legalEntity entities : AppConstant.legalEntity.values()) {
			if (entities.toString().equals(employee.getUser_type())) {
				this.Entity_Type = String.valueOf(entities.ordinal() + 1); // legal entity code
			}
		}
		this.Entity_Type_Others = "";
		this.Fax_Code = "";
		this.Fax_No = "";
		this.First_Name = employee.getFirstName();
		this.Gender = "";
		this.Implementing_Agency = "";
		this.Ip_Address = "";
		this.IP_Address = "";
		this.KYC_Status = "";
		this.Landmark = "";
		this.Last_Name = "";
		for (legalEntity entities : AppConstant.legalEntity.values()) {
			if (entities.toString().equals(employee.getUser_type())) {
				this.legalua = String.valueOf(entities.ordinal() + 1); // legal entity code
			}
		}
		this.Middle_Name = "";
		this.Mobile_code = "+91";
		this.Mobile_No = employee.getMobile();
		this.Name_Proponent = "";
		this.New_Email_Id = employee.getEmail();
		this.Pan_No = employee.getPan_no();
		this.password = "";
		this.Pin = "";
		this.Pincode = employee.getPincode();
		this.Previous_Proposal_Status = "";
		this.Propaddress = employee.getAddress();
		this.Relation_Entity = "";
		this.Security_answer = "";
		this.Security_Answer = "";
		this.Security_question = "";
		this.Security_Question = "";
		this.Simple_password = password; // password
		this.Simple_Password = password;
		this.State_ID = employee.getState_ut();
		this.State_UA = "";
		this.Status = "T";
		this.Status1 = "T";
		this.Std_code = "";
		this.STD_Code = "";
		this.street = employee.getAddress();
		this.Telephone_No = "";
		this.Townvill_UA = "";
		this.uniid = "";
		this.VerificationCode = "";
		this.website = employee.getWebsite();
		this.applidesig = "";
	}
}
