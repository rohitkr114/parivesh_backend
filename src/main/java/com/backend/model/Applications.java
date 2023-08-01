package com.backend.model;

import com.backend.audit.Auditable;
import com.backend.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "applications", schema = "master")
public class Applications extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = true)
    private Integer display_order;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String dd_name;

    @Column(nullable = true)
    private String general_name;

    @Column(nullable = true)
    private String proposal_for;

    @Column(nullable = true)
    private String form_page_url;

    @Column(nullable = true)
    private String view_page_url;

    @Column(nullable = true)
    private String abbr;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String process_history_name;


    @Column(nullable = true)
    private Integer work_group_id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "form_for", nullable = true)
    private AppConstant.Form_for form_for;

    @Column(name = "for_department", updatable = false, insertable = false)
    private Boolean for_department;

    @Column(nullable = false)
    private boolean is_deleted = false;

    @Column(name = "form_code")
    private Integer formCode;

    @Column(name = "clearance_type_code")
    private String clearanceTypeCode;

    @Column(name = "fc_conditions", nullable = true)
    private String fc_conditions;

    @Column(name = "category", nullable = true)
    private String category;

    @OneToMany(mappedBy = "applications")
    @JsonIgnore
    private List<ProponentApplications> proponentApplications = new ArrayList<>();

    @Column(nullable = true)
    private Boolean is_visible_internal;

    @Column(nullable = true)
    private Boolean is_visible_external;

    @Column(name = "approval_id", nullable = true)
    private String approvalId;

    @Column(name = "department_id", nullable = true)
    private String departmentId;

    @Column(name = "ministry_id", nullable = true)
    private String ministryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWork_group_id() {
        return work_group_id;
    }

    public void setWork_group_id(Integer work_group_id) {
        this.work_group_id = work_group_id;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Integer getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(Integer display_order) {
        this.display_order = display_order;
    }

    public String getForm_page_url() {
        return form_page_url;
    }

    public void setForm_page_url(String form_page_url) {
        this.form_page_url = form_page_url;
    }

    public String getView_page_url() {
        return view_page_url;
    }

    public void setView_page_url(String view_page_url) {
        this.view_page_url = view_page_url;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public AppConstant.Form_for getForm_for() {
        return form_for;
    }

    public void setForm_for(AppConstant.Form_for form_for) {
        this.form_for = form_for;
    }

    public Applications() {
        this.for_department = false;
    }

}
