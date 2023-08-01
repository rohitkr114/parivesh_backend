package com.backend.model;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "caf_others", schema = "master")
public class CafOthers extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caf_id")
//	@JsonBackReference(value = "caf_detail_reference")
    @JsonIgnore
    private CommonFormDetail commonFormDetail;

    @Column(name = "rnr_involved", nullable = true)
    private String rnr_involved;

    @Column(length = 10, nullable = true)
    private Integer no_of_villages;

    @Column(length = 10)
    private Integer no_of_project_displaced_families;

    @Column(length = 10)
    private Integer no_of_project_affected_families;

    private String rnr_status;


    @Column(name = "is_required_warehouse_shifting", nullable = true)
    private String is_required_warehouse_shifting;

    @Column(length = 500)
    private String warehouse_shifting_description;


    @Column(name = "is_alternative_sites_examined", nullable = true)
    private String is_alternative_sites_examined;

    public String getAlternative_site() {
        return alternative_site;
    }

    public void setAlternative_site(String alternative_site) {
        this.alternative_site = alternative_site;
    }

    @Transient
    @JsonProperty
    private List<CafKML> cafKMLs = new ArrayList<>();

    @Column(length = 500)
    private String alternative_sites_description;

    @Column(length = 500)
    private String enviroment_consideration_justification;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "alternative_site_order_copy_id", nullable = true)
    private DocumentDetails alternative_site_order_copy;

    @Column(name = "is_any_govt_restriction", nullable = true)
    private String is_any_govt_restriction;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "caf_government_orders_id", nullable = true)
    @Where(clause = "is_deleted='false'")
    private Set<CafGovernmentOrders> cafGovernmentOrders = new HashSet<>();


    @Column(name = "is_any_litigation_pending", nullable = true)
    private String is_any_litigation_pending;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "caf_litigation_id", nullable = true)
    @Where(clause = "is_deleted='false'")
    private Set<CafLitigations> cafLitigations = new HashSet<>();


    @Column(name = "is_any_violayion_involved", nullable = true)
    private String is_any_violayion_involved;
    @Column(name = "alternative_site", nullable = true, length = 5000)
    private String alternative_site;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "caf_violation_id", nullable = true)
    @Where(clause = "is_deleted='false'")
    private Set<CafViolations> cafViolations = new HashSet<>();

    private boolean is_active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CommonFormDetail getCommonFormDetail() {
        return commonFormDetail;
    }

    public void setCommonFormDetail(CommonFormDetail commonFormDetail) {
        this.commonFormDetail = commonFormDetail;
    }

    public String getRnr_involved() {
        return rnr_involved;
    }

    public void setRnr_involved(String rnr_involved) {
        this.rnr_involved = rnr_involved;
    }

    public int getNo_of_villages() {
        return no_of_villages;
    }

    public void setNo_of_villages(int no_of_villages) {
        this.no_of_villages = no_of_villages;
    }

    public int getNo_of_project_displaced_families() {
        return no_of_project_displaced_families;
    }

    public void setNo_of_project_displaced_families(int no_of_project_displaced_families) {
        this.no_of_project_displaced_families = no_of_project_displaced_families;
    }

    public int getNo_of_project_affected_families() {
        return no_of_project_affected_families;
    }

    public void setNo_of_project_affected_families(int no_of_project_affected_families) {
        this.no_of_project_affected_families = no_of_project_affected_families;
    }

    public String getRnr_status() {
        return rnr_status;
    }

    public void setRnr_status(String rnr_status) {
        this.rnr_status = rnr_status;
    }

    public String getIs_required_warehouse_shifting() {
        return is_required_warehouse_shifting;
    }

    public void setIs_required_warehouse_shifting(String is_required_warehouse_shifting) {
        this.is_required_warehouse_shifting = is_required_warehouse_shifting;
    }

    public String getWarehouse_shifting_description() {
        return warehouse_shifting_description;
    }

    public void setWarehouse_shifting_description(String warehouse_shifting_description) {
        this.warehouse_shifting_description = warehouse_shifting_description;
    }

    public String getIs_alternative_sites_examined() {
        return is_alternative_sites_examined;
    }

    public void setIs_alternative_sites_examined(String is_alternative_sites_examined) {
        this.is_alternative_sites_examined = is_alternative_sites_examined;
    }

    public List<CafKML> getCafKMLs() {
        return cafKMLs;
    }

    public void setCafKMLs(List<CafKML> cafKMLs) {
        this.cafKMLs = cafKMLs;
    }

    public String getAlternative_sites_description() {
        return alternative_sites_description;
    }

    public void setAlternative_sites_description(String alternative_sites_description) {
        this.alternative_sites_description = alternative_sites_description;
    }

    public String getEnviroment_consideration_justification() {
        return enviroment_consideration_justification;
    }

    public void setEnviroment_consideration_justification(String enviroment_consideration_justification) {
        this.enviroment_consideration_justification = enviroment_consideration_justification;
    }

    public String getIs_any_govt_restriction() {
        return is_any_govt_restriction;
    }

    public void setIs_any_govt_restriction(String is_any_govt_restriction) {
        this.is_any_govt_restriction = is_any_govt_restriction;
    }

    public String getIs_any_litigation_pending() {
        return is_any_litigation_pending;
    }

    public void setIs_any_litigation_pending(String is_any_litigation_pending) {
        this.is_any_litigation_pending = is_any_litigation_pending;
    }

    public String getIs_any_violayion_involved() {
        return is_any_violayion_involved;
    }

    public void setIs_any_violayion_involved(String is_any_violayion_involved) {
        this.is_any_violayion_involved = is_any_violayion_involved;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public DocumentDetails getAlternative_site_order_copy() {
        return alternative_site_order_copy;
    }

    public void setAlternative_site_order_copy(DocumentDetails alternative_site_order_copy) {
        this.alternative_site_order_copy = alternative_site_order_copy;
    }

    public Set<CafGovernmentOrders> getCafGovernmentOrders() {
        return cafGovernmentOrders;
    }

    public void setCafGovernmentOrders(Set<CafGovernmentOrders> cafGovernmentOrders) {
        this.cafGovernmentOrders = cafGovernmentOrders;
    }

    public Set<CafLitigations> getCafLitigations() {
        return cafLitigations;
    }

    public void setCafLitigations(Set<CafLitigations> cafLitigations) {
        this.cafLitigations = cafLitigations;
    }

    public Set<CafViolations> getCafViolations() {
        return cafViolations;
    }

    public void setCafViolations(Set<CafViolations> cafViolations) {
        this.cafViolations = cafViolations;
    }

    public void setNo_of_villages(Integer no_of_villages) {
        this.no_of_villages = no_of_villages;
    }

    public void setNo_of_project_displaced_families(Integer no_of_project_displaced_families) {
        this.no_of_project_displaced_families = no_of_project_displaced_families;
    }

    public void setNo_of_project_affected_families(Integer no_of_project_affected_families) {
        this.no_of_project_affected_families = no_of_project_affected_families;
    }


}
