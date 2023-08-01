package com.backend.clearance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class LocalClearance {
    public static String getClearanceByCat (String formName, String cat) {
        ClearanceMappingPojo EC_FORM_1_A = new ClearanceMappingPojo("EC_FORM_1", "A", "Fresh TOR");
        ClearanceMappingPojo EC_FORM_1_B = new ClearanceMappingPojo("EC_FORM_1", "B1", "Fresh TOR");
        ClearanceMappingPojo EC_FORM_1_b2 = new ClearanceMappingPojo("EC_FORM_1", "B2", "Fresh EC");


        ClearanceMappingPojo EC_FORM_2_A = new ClearanceMappingPojo("EC_FORM_2", "A", "Fresh EC");
        ClearanceMappingPojo EC_FORM_2_B = new ClearanceMappingPojo("EC_FORM_2", "B1", "Fresh EC");
        ClearanceMappingPojo EC_FORM_2_c = new ClearanceMappingPojo("EC_FORM_2", "B2", "NA");


        ClearanceMappingPojo EC_FORM_4_PART_A_1 = new ClearanceMappingPojo("EC_FORM_4_PART_A", "A", "Amendment in EC");
        ClearanceMappingPojo EC_FORM_4_PART_A_2 = new ClearanceMappingPojo("EC_FORM_4_PART_A", "B1", "Amendment in EC");
        ClearanceMappingPojo EC_FORM_4_PART_A_3 = new ClearanceMappingPojo("EC_FORM_4_PART_A", "B2", "Amendment in EC");

        ClearanceMappingPojo EC_FORM_3_PART_A_1 = new ClearanceMappingPojo("EC_FORM_3_PART_A", "A", "Amendment in TOR");
        ClearanceMappingPojo EC_FORM_3_PART_A_2 = new ClearanceMappingPojo("EC_FORM_3_PART_A", "B1", "Amendment in TOR");
        ClearanceMappingPojo EC_FORM_3_PART_A_3 = new ClearanceMappingPojo("EC_FORM_3_PART_A", "B2", "Amendment in TOR");


        ClearanceMappingPojo EC_FORM_7_1 = new ClearanceMappingPojo("EC_FORM_7", "A", "Transfer of EC");
        ClearanceMappingPojo EC_FORM_7_2 = new ClearanceMappingPojo("EC_FORM_7", "B1", "Transfer of EC");
        ClearanceMappingPojo EC_FORM_7_3 = new ClearanceMappingPojo("EC_FORM_7", "B2", "Transfer of EC");


        ClearanceMappingPojo EC_FORM_8_1 = new ClearanceMappingPojo("EC_FORM_8", "A", "Transfer of TOR");
        ClearanceMappingPojo EC_FORM_8_2 = new ClearanceMappingPojo("EC_FORM_8", "B1", "Transfer of TOR");
        ClearanceMappingPojo EC_FORM_8_3 = new ClearanceMappingPojo("EC_FORM_8", "B2", "Transfer of TOR");

        List<ClearanceMappingPojo> l = new ArrayList<>(Arrays
                .asList(EC_FORM_1_A, EC_FORM_1_B, EC_FORM_1_b2, EC_FORM_2_A,EC_FORM_2_B, EC_FORM_2_c,  EC_FORM_4_PART_A_1, EC_FORM_4_PART_A_2, EC_FORM_4_PART_A_3,
                        EC_FORM_3_PART_A_1, EC_FORM_3_PART_A_2, EC_FORM_3_PART_A_3, EC_FORM_7_1, EC_FORM_7_2, EC_FORM_7_3, EC_FORM_8_1, EC_FORM_8_2, EC_FORM_8_3));

        HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
        for(ClearanceMappingPojo s : l) {
            if (hm.containsKey(s.form)) {
                hm.get(s.form).add(s.categoryType+":"+s.categoryName);
            } else {
                hm.put(s.form, new ArrayList<String>());
                hm.get(s.form).add(s.categoryType+":"+s.categoryName);
            }
        }
        System.out.println(hm);
        System.out.println(getCategoryName(hm, formName, cat));

        return getCategoryName(hm, formName, cat);
    }

    public static String getCategoryName(HashMap<String, List<String>> hm, String formType, String category) {
        List<String> categoryList = hm.get(formType);
        AtomicReference<String> categoryName = new AtomicReference<String>();
        /*categoryList.forEach (
                (temp) -> {
                    if (temp.contains(category)) {
                        String[] buffer = temp.split(":");
                        categoryName.set(buffer[1]);
                        
                    }
                });*/
        
        Optional<String> catg =  categoryList.stream().filter(temp -> temp.contains(category) ).findFirst();
        String[] buffer = catg.get().split(":");
        categoryName.set(buffer[1]);
        
        return categoryName.get();
    }
}
