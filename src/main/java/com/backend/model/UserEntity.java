//package com.backend.model;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Transient;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import com.backend.util.ServiceUtilities;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table(name = "user_entity", schema = "authentication")
//@JsonIgnoreProperties({ "authorities", "userCreationStatus", "password", "forgotPasswordToken" })
////@Inheritance(strategy = InheritanceType.JOINED)
//public class UserEntity implements Serializable {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_seq")
//	@SequenceGenerator(name = "_seq", sequenceName = "users_sequence", allocationSize = 1, initialValue = 1)
//	private Long entityId;
//
//	@Column(name = "name_of_entity")
//	private String nameOfEntity;
//
//	@Column(name = "firstname")
//	private String firstName;
//
//	@Column(name = "state_ut")
//	private String stateUT;
//
//	@Column(name = "uuid")
//	private String uuid;
//
//	@Column(name = "pp_document_url")
//	private String ppDocumentUrl;
//
//	@Column(name = "name", length = 200)
//	private String name;
//
//	@Column(name = "emailid", length = 100, nullable = false)
//	private String emailId;
//
//	@Column(name = "email", length = 100)
//	@JsonIgnore
//	private String email;
//	
//	@Column(name = "username", length = 100)
//	@JsonIgnore
//	private String username;
//
//	@Column(name = "mobilenumber", length = 15)
//	private String mobileNumber;
//
//	@Column(name = "mobile", length = 15)
//	private String mobile;
//	
//	@Column(name = "telno", length = 20)
//	private String telno;
//
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "dob")
//	private Date dob;
//
//	@Column(name = "user_creation_status", length = 30)
//	private String userCreationStatus;
//
//	@Column(name = "usertype", length = 30)
//	private String userType;
//
//	@Column(name = "address", length = 200)
//	private String address;
//
//	@Column(name = "address2", length = 200)
//	private String address2;
//
//	@Column(name = "city", length = 50)
//	private String city;
//
//	@Column(name = "tahsil", length = 50)
//	private String tahsil;
//
//	@Column(name = "district", length = 50)
//	private String district;
//
//	@Column(name = "state", length = 50)
//	private String state;
//
//	@Column(name = "pin", length = 6)
//	private String pin;
//
//	@Column(name = "password", length = 255)
//	@JsonIgnore
//	private String password;
//
//	@Column(name = "isfirstattempt", nullable = true)
//	private Boolean isFirstAttempt;
//
//	@Column(name = "failedattemptcount", nullable = true)
//	private Integer failedAttemptCount;
//
//	@Column(name = "lastpasswordresetdate", nullable = true)
//	private Long lastPasswordResetDate;
//
//	@Column(name = "forgotpasswordtoken", length = 50, nullable = true)
//	private String forgotPasswordToken;
//
//	@Column(name = "is_mapped_to_role", nullable = true)
//	private Boolean ismappedToRole = false;
//
//	@Column(name = "is_super_user", nullable = true)
//	private Boolean isSuperUser = false;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "state_id")
//	private State stateEntity;
//
//	@Column(updatable = false)
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	@Temporal(TemporalType.TIMESTAMP)
//	@CreationTimestamp
//	private Date createdDate;
//
//	@ManyToOne(fetch = FetchType.LAZY)
////	@CreatedBy
//	@JsonIgnore
//	@JoinColumn(name = "created_by")
//	private UserEntity createdBy;
//
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	@Temporal(TemporalType.TIMESTAMP)
//	@UpdateTimestamp
//	private Date updatedDate;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	// @LastModifiedBy
//	@JsonIgnore
//	@JoinColumn(name = "updated_by")
//	private UserEntity updatedBy;
//
//	@Column(nullable = true)
//	private Boolean isActive = true;
//
//	@Column(name = "userid")
//	private String userid;
//
//	@Transient
//	String strdob;
//
//	@ElementCollection(fetch = FetchType.EAGER)
//	@JoinTable(name = "users_related_document", schema = "ua", joinColumns = @JoinColumn(name = "id"))
//	private Collection<UsersRelatedDocument> relatedDoc;
//
//	@Column(name = "is_adhaar_verified")
//	private Boolean adhaarVerified;
//
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "auth_letter_id")
//	private DocumentDetails authLetterId;
//
//	@Column(name = "selected_role")
//	private Long selectedRole;
//
//	@Column(name = "selected_office")
//	private Long selectedOffice;
//	
//	@Column(name = "selected_sector")
//	private Long selectedSector;
//
//	@Transient
//	private UploadDocument projectProponentDocument;
//
//	public UploadDocument getProjectProponentDocument() {
//		return projectProponentDocument;
//	}
//
//	public void setProjectProponentDocument(UploadDocument projectProponentDocument) {
//		this.projectProponentDocument = projectProponentDocument;
//	}
//
//	public DocumentDetails getAuthLetterId() {
//		return authLetterId;
//	}
//
//	public void setAuthLetterId(DocumentDetails authLetterId) {
//		this.authLetterId = authLetterId;
//	}
//
//	public Boolean getAdhaarVerified() {
//		return adhaarVerified;
//	}
//
//	public void setAdhaarVerified(Boolean adhaarVerified) {
//		this.adhaarVerified = adhaarVerified;
//	}
//
//	public String getStrdob() {
//		if (dob != null) {
//			return ServiceUtilities.getDate(dob);
//		}
//		return strdob;
//
//	}
//
//	public void setStrdob(String strdob) {
//		this.strdob = strdob;
//	}
//
//	public UserEntity() {
//	}
//
//	public UserEntity(UserEntity uProfile) {
//		this.setEntityId(uProfile.getEntityId());
//		this.name = uProfile.name;
//		this.emailId = uProfile.emailId;
//		this.mobileNumber = uProfile.mobileNumber;
//		if (uProfile.getStrdob() != null) {
//			dob = ServiceUtilities.getDate(uProfile.getStrdob());
//		}
//		// this.dob = uProfile.dob;
//		this.city = uProfile.city;
//		this.district = uProfile.district;
//		this.tahsil = uProfile.tahsil;
//		this.pin = uProfile.pin;
//		this.address = uProfile.address;
//		this.address2 = uProfile.address2;
//
//	}
//
//	public String getTelno() {
//		return telno;
//	}
//
//	public void setTelno(String telno) {
//		this.telno = telno == null ? null : telno.trim();
//	}
//
//	public String getEmailId() {
//		return emailId;
//	}
//
//	public void setEmailId(String emailId) {
//		this.emailId = emailId == null ? null : emailId.trim();
//	}
//
//	public String getMobileNumber() {
//		return mobileNumber;
//	}
//
//	public void setMobileNumber(String mobileNumber) {
//		this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
//	}
//	
//	public String getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}
//
//	public String getUserType() {
//		return userType;
//	}
//
//	public void setUserType(String userType) {
//		this.userType = userType;
//	}
//
//	public String getUserCreationStatus() {
//		return userCreationStatus;
//	}
//
//	public void setUserCreationStatus(String userCreationStatus) {
//		this.userCreationStatus = userCreationStatus == null ? null : userCreationStatus.trim();
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address == null ? null : address.trim();
//	}
//
//	public String getAddress2() {
//		return address2;
//	}
//
//	public void setAddress2(String address2) {
//		this.address2 = address2 == null ? null : address2.trim();
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city == null ? null : city.trim();
//	}
//
//	public String getTahsil() {
//		return tahsil;
//	}
//
//	public void setTahsil(String tahsil) {
//		this.tahsil = tahsil == null ? null : tahsil.trim();
//	}
//
//	public String getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(String district) {
//		this.district = district == null ? null : district.trim();
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state == null ? null : state.trim();
//	}
//
//	public String getPin() {
//		return pin;
//	}
//
//	public void setPin(String pin) {
//		this.pin = pin == null ? null : pin.trim();
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name == null ? null : name.trim();
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public Boolean isFirstAttempt() {
//		return isFirstAttempt;
//	}
//
//	public void setFirstAttempt(Boolean isFirstAttempt) {
//		this.isFirstAttempt = isFirstAttempt;
//	}
//
//	public Integer getFailedAttemptCount() {
//		return failedAttemptCount;
//	}
//
//	public void setFailedAttemptCount(Integer failedAttemptCount) {
//		this.failedAttemptCount = failedAttemptCount;
//	}
//
//	public Long getLastPasswordResetDate() {
//		return lastPasswordResetDate;
//	}
//
//	public void setLastPasswordResetDate(Long lastPasswordResetDate) {
//		this.lastPasswordResetDate = lastPasswordResetDate;
//	}
//
//	public String getForgotPasswordToken() {
//		return forgotPasswordToken;
//	}
//
//	public void setForgotPasswordToken(String forgotPasswordToken) {
//		this.forgotPasswordToken = forgotPasswordToken;
//	}
//
//	public Boolean getIsFirstAttempt() {
//		return isFirstAttempt;
//	}
//
//	public void setIsFirstAttempt(Boolean isFirstAttempt) {
//		this.isFirstAttempt = isFirstAttempt;
//	}
//
//	public Boolean getIsmappedToRole() {
//		return ismappedToRole;
//	}
//
//	public void setIsmappedToRole(Boolean ismappedToRole) {
//		this.ismappedToRole = ismappedToRole;
//	}
//
//	public Boolean getIsSuperUser() {
//		return isSuperUser;
//	}
//
//	public void setIsSuperUser(Boolean isSuperUser) {
//		this.isSuperUser = isSuperUser;
//	}
//
//	public Long getEntityId() {
//		return entityId;
//	}
//
//	public void setEntityId(Long entityId) {
//		this.entityId = entityId;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public UserEntity getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(UserEntity createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public Date getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//	public UserEntity getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(UserEntity updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//
//	public Boolean getIsActive() {
//		return isActive;
//	}
//
//	public void setIsActive(Boolean isActive) {
//		this.isActive = isActive;
//	}
//
//	public String getUserid() {
//		return userid;
//	}
//
//	public void setUserid(String userid) {
//		this.userid = userid;
//	}
//
//	public State getStateEntity() {
//		return stateEntity;
//	}
//
//	public void setStateEntity(State stateEntity) {
//		this.stateEntity = stateEntity;
//	}
//
//	public Collection<UsersRelatedDocument> getRelatedDoc() {
//		return relatedDoc;
//	}
//
//	public void setRelatedDoc(Collection<UsersRelatedDocument> relatedDoc) {
//		this.relatedDoc = relatedDoc;
//	}
//
//	public String getNameOfEntity() {
//		return nameOfEntity;
//	}
//
//	public void setNameOfEntity(String nameOfEntity) {
//		this.nameOfEntity = nameOfEntity;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getStateUT() {
//		return stateUT;
//	}
//
//	public void setStateUT(String stateUT) {
//		this.stateUT = stateUT;
//	}
//
//	public String getUuid() {
//		return uuid;
//	}
//
//	public void setUuid(String uuid) {
//		this.uuid = uuid;
//	}
//
//	public String getPpDocumentUrl() {
//		return ppDocumentUrl;
//	}
//
//	public void setPpDocumentUrl(String ppDocumentUrl) {
//		this.ppDocumentUrl = ppDocumentUrl;
//	}
//
//	public Long getSelectedRole() {
//		return selectedRole;
//	}
//
//	public void setSelectedRole(Long selectedRole) {
//		this.selectedRole = selectedRole;
//	}
//
//	public Long getSelectedOffice() {
//		return selectedOffice;
//	}
//
//	public void setSelectedOffice(Long selectedOffice) {
//		this.selectedOffice = selectedOffice;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public Long getSelectedSector() {
//		return selectedSector;
//	}
//
//	public void setSelectedSector(Long selectedSector) {
//		this.selectedSector = selectedSector;
//	}
//	
//	
//}