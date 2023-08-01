package com.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "user_entity", schema = "authentication")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Auditable<Integer> implements Serializable {
    // Common Fields in User-Registration :: Address State/UT Email-ID Mobile No
    // District PIN Code .
    // Fields in Admin : First Name Last Name Employee ID E-MailId Mobile Number

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entityid;

    @Column(name = "name_of_Entity")
    private String name_of_Entity;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(length = 50, nullable = true)
    private String firstName;

    @Column(length = 50, nullable = true)
    private String middleName;

    @Column(length = 50, nullable = true)
    private String lastName;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "email")
    // @Email(message = "Email can not be empty")
    private String email;

    @Column(name = "mobile")
    // @Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$")
    private String mobile;

    @Column(name = "mobilenumber")
    private String mobileNumber;

    @Column(name = "state_ut", nullable = true)
    private String state_ut;

    @Column(name = "district", nullable = true)
    private String district;

    @Column(name = "pincode", nullable = true)
    // @Pattern(regexp = "^[1-9]{1}[0-9]{2}[0-9]{3}$")
    private String pincode;

    @Column(name = "password", nullable = true)
    @JsonIgnore
    private String password;

    @Column(name = "user_type", nullable = true)
    private String user_type;

    @JsonIgnore
    @Column(name = "usertype", nullable = true)
    private String userType;

    @Column(name = "user_creation_status", length = 30)
    private String userCreationStatus;

    @JsonIgnore
    @Column(name = "status", nullable = true)
    private String status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "auth_letter_id", nullable = true)
    private DocumentDetails auth_letter;

    @JsonIgnore
    @Column(name = "ip_address", nullable = true)
    private String ip_address;

    @JsonIgnore
    @Column(nullable = true)
    private Boolean isActive;

    @Column(nullable = true)
    @JsonProperty(access = Access.READ_ONLY)
    private Integer failedAttemptCount;

    @Column(nullable = true)
    private String emailId;

    @Column(nullable = true)
    private String userid;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "is_test_user")
    private Boolean is_test_user;

    @Column(name = "is_adhaar_verified", nullable = true)
    private Boolean is_adhaar_verified;

    @JsonIgnore
    @Column(name = "is_delete")
    private Boolean is_delete;

    @Column(name = "security_question", nullable = true)
    private String security_question;

    @Column(name = "security_answer", nullable = true)
    private String security_answer;

    @Column(name = "createddate", nullable = true)
    @JsonProperty(access = Access.READ_ONLY)
    private Date createdDate;

    @Column(nullable = true)
    private Boolean is_migrated_to_Parivesh1;

    @Column(nullable = true)
    private Integer selected_office;

    @Column(nullable = true)
    private Integer selected_role;

    @Column(nullable = true)
    private Integer selected_sector;

    @Transient
    private String designation;

    public User() {
        super();
        this.status = "active";
        this.createdDate = new Date();
        this.failedAttemptCount = 0;
        this.is_active = true;
        this.is_delete = false;
        this.isActive = false;
        this.is_adhaar_verified = false;
        is_migrated_to_Parivesh1 = false;
    }

    public User(Integer id, String name_of_Entity, String address, String email, String mobile, String state_ut, String district,
                String pincode, String userType, HttpServletRequest request) {
        super();
        this.entityid = id;
        this.name_of_Entity = name_of_Entity;
        this.user_type = userType;
        this.userType = userType;
        this.firstName = name_of_Entity;
        this.name = name_of_Entity;
        this.address = address;
        this.email = email.trim();
        this.mobile = mobile;
        this.mobileNumber = mobile;
        this.state_ut = state_ut;
        this.district = district;
        this.pincode = pincode;
        this.status = "active";
        this.failedAttemptCount = 0;
        this.createdDate = new Date();
        this.ip_address = request.getRemoteAddr() != null ? request.getRemoteAddr() : null;
        this.is_active = true;
        this.is_delete = false;
        this.isActive = false;
        this.is_adhaar_verified = false;
        is_migrated_to_Parivesh1 = false;
    }

    public User(Integer id, String name_of_Entity, String address, String email, String mobile, String state_ut, String district,
            String pincode, String userType) {
    super();
    this.entityid = id;
    this.name_of_Entity = name_of_Entity;
    this.user_type = userType;
    this.userType = userType;
    this.firstName = name_of_Entity;
    this.name = name_of_Entity;
    this.address = address;
    this.email = email.trim();
    this.mobile = mobile;
    this.mobileNumber = mobile;
    this.state_ut = state_ut;
    this.district = district;
    this.pincode = pincode;
    this.status = "active";
    this.failedAttemptCount = 0;
    this.createdDate = new Date();
    this.is_active = true;
    this.is_delete = false;
    this.isActive = false;
    this.is_adhaar_verified = false;
    is_migrated_to_Parivesh1 = false;
}

    public User(Boolean is_test_user,Integer entityid, String name_of_Entity, String name, String firstName,
                String middleName,
                String lastName,
                String address, String email, String mobile, String mobileNumber, String state_ut, String district,
                String pincode, String password, String user_type, String userType, String status, String ip_address,
                Boolean isActive, Integer failedAttemptCount, String emailId, Boolean is_active, Boolean is_adhaar_verified,
                Boolean is_delete, String security_question, String security_answer, Date createdDate, Integer selected_office, Integer selected_role) {
        this.is_test_user=is_test_user;
        this.entityid = entityid;
        this.name_of_Entity = name_of_Entity;
        this.name = name;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.mobileNumber = mobileNumber;
        this.state_ut = state_ut;
        this.district = district;
        this.pincode = pincode;
        this.password = password;
        this.user_type = user_type;
        this.userType = userType;
        this.status = status;
        this.ip_address = ip_address;
        this.isActive = isActive;
        this.failedAttemptCount = failedAttemptCount;
        this.emailId = emailId;
        this.is_active = is_active;
        this.is_adhaar_verified = is_adhaar_verified;
        this.is_delete = is_delete;
        this.security_question = security_question;
		this.security_answer = security_answer;
		this.createdDate = createdDate;
		this.selected_office = selected_office;
		this.selected_role = selected_role;
    }

    public User(Integer entityid, String name_of_Entity, String name, String firstName,
            String middleName,
            String lastName,
            String address, String email, String mobile, String mobileNumber, String state_ut, String district,
            String pincode, String password, String user_type, String userType, String status, String ip_address,
            Boolean isActive, Integer failedAttemptCount, String emailId, Boolean is_active, Boolean is_adhaar_verified,
            Boolean is_delete, String security_question, String security_answer, Date createdDate, Integer selected_office, Integer selected_role) {

    this.entityid = entityid;
    this.name_of_Entity = name_of_Entity;
    this.name = name;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
    this.mobileNumber = mobileNumber;
    this.state_ut = state_ut;
    this.district = district;
    this.pincode = pincode;
    this.password = password;
    this.user_type = user_type;
    this.userType = userType;
    this.status = status;
    this.ip_address = ip_address;
    this.isActive = isActive;
    this.failedAttemptCount = failedAttemptCount;
    this.emailId = emailId;
    this.is_active = is_active;
    this.is_adhaar_verified = is_adhaar_verified;
    this.is_delete = is_delete;
    this.security_question = security_question;
	this.security_answer = security_answer;
	this.createdDate = createdDate;
	this.selected_office = selected_office;
	this.selected_role = selected_role;
}

    public Integer getEntityid() {
        return entityid;
    }

    public void setEntityid(Integer entityid) {
        this.entityid = entityid;
    }

    public String getName_of_Entity() {
        return name_of_Entity;
    }

    public void setName_of_Entity(String name_of_Entity) {
        this.name_of_Entity = name_of_Entity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState_ut() {
        return state_ut;
    }

    public void setState_ut(String state_ut) {
        this.state_ut = state_ut;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public String getSecurity_answer() {
        return security_answer;
    }

    public void setSecurity_answer(String security_answer) {
        this.security_answer = security_answer;
    }

    public User(Integer entityid, String name_of_Entity, String user_type) {
        this.entityid = entityid;
        this.name_of_Entity = name_of_Entity;
        this.user_type = user_type;
    }


}
