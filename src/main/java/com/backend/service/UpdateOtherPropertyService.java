package com.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ProponentApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateOtherPropertyService {
	
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;
	
	/**
	 * <b>updateOtherProperty</b>
	 * <p>
	 * Update other_property in proponent_applications
	 * </p>
	 * 
	 * 
	 * @param proposalId
	 * @param newPropertyData
	 * @return
	 */
	public ProponentApplications updateOtherProperty(Integer proposalId, Map<String, Object> newPropertyData) {

		log.info("UPDATEOTHERPROPERTY  proposalId:{} , newPropertyData:{}", proposalId, newPropertyData);
		ProponentApplications proponentApplications = proponentApplicationRepository
				.getApplicationByProposalId(proposalId);
		String otherProperty = proponentApplications.getOther_property();
		log.info("PROPONENTAPPLICATIONS string-otherProperty: {}", otherProperty);
		try {
			JSONArray otherPropertyJSONArray = new JSONArray();
			Map<String, Object> propertyData = new HashMap<String, Object>();
			if (!StringUtils.isAllEmpty(otherProperty)) {
				otherPropertyJSONArray = new JSONArray(otherProperty);
				propertyData.putAll(convertJsonArrayToMap(otherPropertyJSONArray));

			}
			log.info("OLD DATA jsonArray-propertyData: {}", propertyData);
			propertyData.putAll(newPropertyData);
			JSONArray updatedOtherPropertyJSONArray = convertMapToJSONArray(propertyData);
			log.info("NEW DATA updatedOtherPropertyJSONArray: {}", updatedOtherPropertyJSONArray);

			proponentApplications.setOther_property(updatedOtherPropertyJSONArray.toString());

			proponentApplicationRepository.save(proponentApplications);
			log.info("UPDATED OtherProperty JSONArray: {}", updatedOtherPropertyJSONArray);
		} catch (JSONException ex) {
			log.info("ERROR - JSONException while parsing json array: {}", ex.getMessage());
			log.error("Exception:: ", ex);
		} catch (Exception ex) {
			log.info("ERROR - Exception while parsing json array: {}", ex.getMessage());
			log.error("Exception:: ", ex);
		}

		return proponentApplications;

	}
	
	/**
	 * <b>convertJsonArrayToMap</b>
	 * <p>
	 * Convert JsonArray to Map
	 * </p>
	 * 
	 * @param jsonArray
	 * @return
	 * @throws JSONException
	 */
	public Map<String, Object> convertJsonArrayToMap(JSONArray jsonArray) throws JSONException {
		Map<String, Object> otherProperty = new HashMap<String, Object>();
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject prop = (JSONObject) jsonArray.get(i);
				otherProperty.put(prop.get("label").toString(), prop.get("value"));

			}
		}
		log.info("convertJsonArryToMap otherProperty: {}", otherProperty);
		return otherProperty;
	}

	/**
	 * <b>convertMapToJSONArray</b>
	 * <p>
	 * Convert Map to JsonArray
	 * </p>
	 * 
	 * @param otherProperty
	 * @return
	 * @throws JSONException
	 */
	public JSONArray convertMapToJSONArray(Map<String, Object> otherProperty) throws JSONException {
		JSONArray otherPropertyJSONArray = new JSONArray();
		if (!otherProperty.isEmpty()) {
			otherProperty.forEach((key, value) -> {
				try {
					JSONObject prop = new JSONObject();
					prop.put("label", key);
					prop.put("value", value);
					otherPropertyJSONArray.put(prop);
				} catch (JSONException ex) {
					log.info("ERROR in convertMapToJSONArray error msg: {}", ex.getMessage());
					log.error("Exception:: ", ex);
				}

			});
		}
		log.info("convertMapToJSONArray otherPropertyJSONArray: {}", otherPropertyJSONArray);
		return otherPropertyJSONArray;
	}
}
