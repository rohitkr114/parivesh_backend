package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_details_of_effluent_generation_load_in_respect", schema = "master")
public class EcForm10DetailsOfEffluentGenerationLoadInRespect extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "total_load_in_effluent", nullable = true)
    private String total_land;

    @Column(name = "treatment_facility_existing", nullable = true)
    private String treatment_facility;

    @Column(name = "total_load_in_effluent_after_proposed_change", nullable = true)
    private String total_load;

    @Column(name = "treatment_facility_proposed", nullable = true)
    private String capacity_proposed;

    @Column(name = "remarks", nullable = true,length = 500)
    private String rem;

}
