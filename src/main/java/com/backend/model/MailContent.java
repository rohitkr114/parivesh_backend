package com.backend.model;

import java.util.Map;

import lombok.Data;

@Data
public class MailContent {

	private String subject;// subject of the email
	private Map<String, String> body;// body of the email
	private String service;// service from which email is generated
	private String to;// email id of receiver
	private String cc;// carbon copy
	private String bcc;// Blind carbon copy
	private String senderid;// email id of sender
	private String lang;// language like english
	private String tag;// tag trans for transactional and promo for promotional
	private String vmtype;// from which module email is being generated like otp
							// for otphandler
	private String appname; // name of the application
	private String vmname;// name of the VM to be shown
	private String trkr;// unique tracker id to track the user.
	private String uid;// unique user id
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Map<String, String> getBody() {
		return body;
	}
	public void setBody(Map<String, String> body) {
		this.body = body;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getVmtype() {
		return vmtype;
	}
	public void setVmtype(String vmtype) {
		this.vmtype = vmtype;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getVmname() {
		return vmname;
	}
	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	public String getTrkr() {
		return trkr;
	}
	public void setTrkr(String trkr) {
		this.trkr = trkr;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	
	
}
