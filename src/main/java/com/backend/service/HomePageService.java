package com.backend.service;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.backend.constant.AppConstant;
import com.backend.exceptions.FileNotSupportedException;
import com.backend.model.FAQ;
import com.backend.model.HomeApprovalMaster;
import com.backend.model.HomeHeroMaster;
import com.backend.model.HomeKPIMaster;
import com.backend.model.HomePageMedia;
import com.backend.model.HomeRecentMaster;
import com.backend.model.HomeUpdateMaster;
import com.backend.model.HomeUsefulLinkMaster;
import com.backend.model.HomeRecentUploadsMaster;
import com.backend.repository.postgres.ApprovalMasterRepository;
import com.backend.repository.postgres.FAQRepository;
import com.backend.repository.postgres.HeroMasterRepository;
import com.backend.repository.postgres.HomePageMediaRepository;
import com.backend.repository.postgres.KpiMasterRepository;
import com.backend.repository.postgres.RecentMasterRepository;
import com.backend.repository.postgres.RecentUploadMasterRepository;
import com.backend.repository.postgres.UpdateMasterRepository;
import com.backend.repository.postgres.UsefulLinkMasterRepository;
import com.backend.response.ResponseHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HomePageService {

	@Autowired
	private HeroMasterRepository heroMasterRepository;

	@Autowired
	private KpiMasterRepository kpiMasterRepository;

	@Autowired
	private ApprovalMasterRepository approvalMasterRepository;

	@Autowired
	private RecentMasterRepository recentMasterRepository;

	@Autowired
	private UpdateMasterRepository updateMasterRepository;

	@Autowired
	private RecentUploadMasterRepository recentUploadMasterRepository;

	@Autowired
	private UsefulLinkMasterRepository usefulLinkMasterRepository;
	
	@Autowired
	private HomePageMediaRepository homePageMediaRepository;
	
	@Autowired
	private FAQRepository faqRepository;
	
	@Autowired
	private Environment environment;

	private final Path fileStorageLocation;

	@Autowired
	public HomePageService() {
		this.fileStorageLocation = Paths.get("/opt/apache-tomcat-9.0.58/webapps/images").toAbsolutePath().normalize();
//		this.fileStorageLocation = Paths.get("C:\\Users\\JS494ET\\Rahul\\images").toAbsolutePath().normalize();
	}

	public ResponseEntity<Object> getHeroMaster(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",heroMasterRepository.get_all_Hero(active));
			//return heroMasterRepository.get_all_Hero(active);
		} else {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",heroMasterRepository.get_all_Heros());
			//return heroMasterRepository.get_all_Heros();
		}
	}

	public HomeHeroMaster saveHero(HomeHeroMaster heroModel) {
		if (heroModel.getId() == 0) {
			List<HomeHeroMaster> temp = heroMasterRepository.findAll();
			if (temp.isEmpty()) {
				heroModel.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount == 0 || maxCount != null) {
					heroModel.setOrder_no(maxCount + 1);
				}
			}
			HomeHeroMaster tempHero = heroMasterRepository.save(heroModel);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempHero);
			return tempHero;
		} else {
			HomeHeroMaster tempHero = heroMasterRepository.save(heroModel);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempHero);
			return tempHero;
		}
	}

	public ResponseEntity<Object> getApprovalMaster(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",approvalMasterRepository.get_all_Approval(active));
			//return approvalMasterRepository.get_all_Approval(active);
		} else {
			//return approvalMasterRepository.get_all_Approvals();
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",approvalMasterRepository.get_all_Approvals());
		}
	}

	public HomeApprovalMaster saveApprovals(HomeApprovalMaster homeApprovals) {
		if (homeApprovals.getId() == 0) {
			List<HomeApprovalMaster> temp = approvalMasterRepository.findAll();
			if (temp.isEmpty()) {
				homeApprovals.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount == 0 || maxCount != null) {
					homeApprovals.setOrder_no(maxCount + 1);
				}
			}
			HomeApprovalMaster tempApproval = approvalMasterRepository.save(homeApprovals);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempApproval);
			return tempApproval;
		} else {
			HomeApprovalMaster tempApproval = approvalMasterRepository.save(homeApprovals);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempApproval);
			return tempApproval;
		}
	}

	public ResponseEntity<Object> getKpiMaster(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",kpiMasterRepository.get_all_Kpi(active));
			//return kpiMasterRepository.get_all_Kpi(active);
		} else {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",kpiMasterRepository.get_all_Kpis());
			//return kpiMasterRepository.get_all_Kpis();
		}
	}

	public HomeKPIMaster saveKPI(HomeKPIMaster kpiMaster) {
		if (kpiMaster.getId() == 0) {
			List<HomeKPIMaster> temp = kpiMasterRepository.findAll();
			if (temp.isEmpty()) {
				kpiMaster.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount == 0 || maxCount != null) {
					kpiMaster.setOrder_no(maxCount + 1);
				}
			}
			HomeKPIMaster tempKPI = kpiMasterRepository.save(kpiMaster);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempKPI);
			return tempKPI;
		} else {
			HomeKPIMaster tempKPI = kpiMasterRepository.save(kpiMaster);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempKPI);
			return tempKPI;
		}
	}

	public ResponseEntity<Object> getRecentMaster(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",recentMasterRepository.get_all_recent(active));
			//return recentMasterRepository.get_all_recent(active);
		} else {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",recentMasterRepository.get_all_Recents());
			//return recentMasterRepository.get_all_Recents();
		}

	}

	public HomeRecentMaster saveRecent(HomeRecentMaster homeRecent) {
		if (homeRecent.getId() == 0) {
			List<HomeRecentMaster> temp = recentMasterRepository.findAll();
			if (temp.isEmpty()) {
				homeRecent.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount == 0 || maxCount != null) {
					homeRecent.setOrder_no(maxCount + 1);
				}
			}
			HomeRecentMaster tempRecent = recentMasterRepository.save(homeRecent);
			return tempRecent;
		} else {
			HomeRecentMaster tempRecent = recentMasterRepository.save(homeRecent);
			return tempRecent;
		}
	}

	public ResponseEntity<Object> getMedia(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Get Media",HttpStatus.OK,"",homePageMediaRepository.get_all_Media(active));
		} else {
			return ResponseHandler.generateResponse("Get Media6u",HttpStatus.OK,"",homePageMediaRepository.get_all_Media());
		}

	}

	public HomePageMedia saveMedia(HomePageMedia homePageMedia) {
		if (homePageMedia.getId() == 0) {
			List<HomePageMedia> temp = homePageMediaRepository.findAll();
			if (temp.isEmpty()) {
				homePageMedia.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount == 0 || maxCount != null) {
					homePageMedia.setOrder_no(maxCount + 1);
				}
			}
			HomePageMedia tempMedia = homePageMediaRepository.save(homePageMedia);
			return tempMedia;
		} else {
			HomePageMedia tempMedia = homePageMediaRepository.save(homePageMedia);
			return tempMedia;
		}
	}
	
	public ResponseEntity<Object> getFAQ(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",faqRepository.get_all_faq(active));
		} else {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",faqRepository.get_all_faq());
		}

	}

	public FAQ saveFAQ(FAQ faq) {
		if (faq.getId() == 0) {
			List<FAQ> temp = faqRepository.findAll();
			if (temp.isEmpty()) {
				faq.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount == 0 || maxCount != null) {
					faq.setOrder_no(maxCount + 1);
				}
			}
			FAQ tempfaq = faqRepository.save(faq);
			return tempfaq;
		} else {
			FAQ tempfaq = faqRepository.save(faq);
			return tempfaq;
		}
	}
		
	public ResponseEntity<Object> getUpdateMaster(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",updateMasterRepository.get_all_update(active));
			//return updateMasterRepository.get_all_update(active);
		} else {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",updateMasterRepository.get_all_updates());
			//return updateMasterRepository.get_all_updates();
		}

	}

	public ResponseEntity<Object> saveUpdate(HomeUpdateMaster homeUpdate) {
		if (homeUpdate.getId() == 0) {
			List<HomeUpdateMaster> temp = updateMasterRepository.findAll();
			if (temp.isEmpty()) {
				homeUpdate.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount != null) {
					homeUpdate.setOrder_no(maxCount + 1);
				}
			}
			HomeUpdateMaster tempUpdate = updateMasterRepository.save(homeUpdate);
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
			//return tempUpdate;
		} else {
			HomeUpdateMaster tempUpdate = updateMasterRepository.save(homeUpdate);
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
			//return tempUpdate;
		}
	}

	public HomeUsefulLinkMaster saveHomeUsefulLink(HomeUsefulLinkMaster homeUsefulLink) {
		if (homeUsefulLink.getId() == 0) {
			List<HomeUsefulLinkMaster> temp = usefulLinkMasterRepository.findAll();
			if (temp.isEmpty()) {
				homeUsefulLink.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount != null) {
					homeUsefulLink.setOrder_no(maxCount + 1);
				}
			}
			HomeUsefulLinkMaster tempRecent = usefulLinkMasterRepository.save(homeUsefulLink);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempRecent);
			return tempRecent;
		} else {
			HomeUsefulLinkMaster tempRecent = usefulLinkMasterRepository.save(homeUsefulLink);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempRecent);
			return tempRecent;
		}
	}

	public ResponseEntity<Object> getHomeUsefulLink(String active) {
		if (active != null) {
			//return usefulLinkMasterRepository.get_all_link(active);
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",usefulLinkMasterRepository.get_all_link(active));
		} else {
			//return usefulLinkMasterRepository.get_all_links();
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",usefulLinkMasterRepository.get_all_links());
		}

	}

	public HomeRecentUploadsMaster saveRecentUpload(HomeRecentUploadsMaster homeRecentUploadsMaster) {
		if (homeRecentUploadsMaster.getId() == 0) {
			List<HomeRecentUploadsMaster> temp = recentUploadMasterRepository.findAll();
			if (temp.isEmpty()) {
				homeRecentUploadsMaster.setOrder_no(1);
			} else {
				Integer maxCount = temp.stream().map(e -> e.getOrder_no()).max(Comparator.comparing(Integer::valueOf))
						.get();
				if (maxCount != null) {
					homeRecentUploadsMaster.setOrder_no(maxCount + 1);
				}
			}
			HomeRecentUploadsMaster tempRecent = recentUploadMasterRepository.save(homeRecentUploadsMaster);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempRecent);
			return tempRecent;
		} else {
			HomeRecentUploadsMaster tempRecent = recentUploadMasterRepository.save(homeRecentUploadsMaster);
			//return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",tempRecent);
			return tempRecent;
		}
	}

	public ResponseEntity<Object> getHomeRecentUpload(String active) {
		if (active != null) {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",recentUploadMasterRepository.get_all_upload(active));
			//return recentUploadMasterRepository.get_all_upload(active);
		} else {
			return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"",recentUploadMasterRepository.get_all_uploads());
			//return recentUploadMasterRepository.get_all_uploads();
		}

	}

	public String fileUpload(MultipartFile file, Integer id, String type) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());
        
		String fileName = timeStamp+StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileSystemException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			// JPG,png,pdf,xslx,doc
			Tika tika = new Tika();
			try {
//				String fileType = tika.detect(file.getBytes());
				if (type.equals("recentUploads")) {
					if ((file.getContentType()
							.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
							|| (file.getContentType().equals("application/msword"))
							|| (file.getContentType().equals("application/pdf"))
							|| (file.getContentType().equals("application/vnd.ms-excel")) || file.getContentType()
									.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
						Path targetLocation = this.fileStorageLocation.resolve(fileName);
						Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
						String url = environment.getProperty(AppConstant.imageUrl) + fileName;
						HomeRecentUploadsMaster temp = recentUploadMasterRepository.getById(id);
						temp.setDoc_url(url);
						recentUploadMasterRepository.save(temp);
					} else {
						throw new FileNotSupportedException(
								"File type not supported to upload in Recent upload section: " + file.getContentType());
					}
				} else {
					if ((file.getContentType().equals("image/jpg")) || (file.getContentType().equals("image/png"))
							|| file.getContentType().equals("image/jpeg")) {
						Path targetLocation = this.fileStorageLocation.resolve(fileName);
						Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
						String url = environment.getProperty(AppConstant.imageUrl) + fileName;

						// Copy file to the target location (Replacing existing file with the same name)
						if (type.equals("hero")) {
							HomeHeroMaster temp = heroMasterRepository.getById(id);
							temp.setImage_url(url);
							heroMasterRepository.save(temp);
						} else if (type.equals("approvals")) {
							HomeApprovalMaster temp = approvalMasterRepository.getById(id);
							temp.setImage_url(url);
							approvalMasterRepository.save(temp);
						} else if (type.equals("kpi")) {
							HomeKPIMaster temp = kpiMasterRepository.getById(id);
							temp.setImage_url(url);
							kpiMasterRepository.save(temp);
						}else if (type.equals("media")) {
							HomePageMedia temp = homePageMediaRepository.getById(id);
							temp.setUrl(url);
							homePageMediaRepository.save(temp);
						}
						else {
							HomeRecentMaster temp = recentMasterRepository.getById(id);
							temp.setImage_url(url);
							recentMasterRepository.save(temp);
						}
					} else {
						throw new FileNotSupportedException(
								"File type not supported to upload in Other section: " + file.getContentType());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return fileName;
		} catch (IOException ex) {
			throw new FileSystemNotFoundException("Could not store file " + fileName + ". Please try again!");
		}
	}

	public ResponseEntity<Object> updateOrder(List<Object> order, String type) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(order);
			if (type.equals("hero")) {
				List<HomeHeroMaster> temp = mapper.readValue(json, new TypeReference<List<HomeHeroMaster>>() {
				});
				temp.stream().map(this::saveHero).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Home Page Hero",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("approvals")) {
				List<HomeApprovalMaster> temp = mapper.readValue(json, new TypeReference<List<HomeApprovalMaster>>() {
				});
				temp.stream().map(this::saveApprovals).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Home Page Approval",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("kpi")) {
				List<HomeKPIMaster> temp = mapper.readValue(json, new TypeReference<List<HomeKPIMaster>>() {
				});
				temp.stream().map(this::saveKPI).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Home Page KPI",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("updates")) {
				List<HomeUpdateMaster> temp = mapper.readValue(json, new TypeReference<List<HomeUpdateMaster>>() {
				});
				temp.stream().map(this::saveUpdate).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Home Page Update",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("usefulLink")) {
				List<HomeUsefulLinkMaster> temp = mapper.readValue(json,
						new TypeReference<List<HomeUsefulLinkMaster>>() {
						});
				temp.stream().map(this::saveHomeUsefulLink).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Home Page Useful Link",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("recentUploads")) {
				List<HomeRecentUploadsMaster> temp = mapper.readValue(json,
						new TypeReference<List<HomeRecentUploadsMaster>>() {
						});
				temp.stream().map(this::saveRecentUpload).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Recent Uploads",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("media")) {
				List<HomePageMedia> temp = mapper.readValue(json,
						new TypeReference<List<HomePageMedia>>() {
						});
				temp.stream().map(this::saveMedia).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Home Page Media",HttpStatus.OK,"","SUCCESS");
			} else if (type.equals("faq")) {
				List<FAQ> temp = mapper.readValue(json,
						new TypeReference<List<FAQ>>() {
						});
				temp.stream().map(this::saveFAQ).collect(Collectors.toList());
				return ResponseHandler.generateResponse("FAQ",HttpStatus.OK,"","SUCCESS");
			}else {
				List<HomeRecentMaster> temp = mapper.readValue(json, new TypeReference<List<HomeRecentMaster>>() {
				});
				temp.stream().map(this::saveRecent).collect(Collectors.toList());
				return ResponseHandler.generateResponse("Home Page Recent",HttpStatus.OK,"","SUCCESS");
				//return "success";
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseHandler.generateResponse("Districts",HttpStatus.BAD_REQUEST,"","FAILED");
		//return "failed";
	}

	public ResponseEntity<Object> deleteContent(Object obj, String type) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(obj);
			if (type.equals("hero")) {
				HomeHeroMaster temp = mapper.readValue(json, HomeHeroMaster.class);
				heroMasterRepository.save(temp);
				return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("approvals")) {
				HomeApprovalMaster temp = mapper.readValue(json, HomeApprovalMaster.class);
				approvalMasterRepository.save(temp);
				return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("kpi")) {
				HomeKPIMaster temp = mapper.readValue(json, HomeKPIMaster.class);
				kpiMasterRepository.save(temp);
				return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("updates")) {
				HomeUpdateMaster temp = mapper.readValue(json, HomeUpdateMaster.class);
				updateMasterRepository.save(temp);
				return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("usefulLink")) {
				HomeUsefulLinkMaster temp = mapper.readValue(json, HomeUsefulLinkMaster.class);
				usefulLinkMasterRepository.save(temp);
				return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("recentUploads")) {
				HomeRecentUploadsMaster temp = mapper.readValue(json, HomeRecentUploadsMaster.class);
				recentUploadMasterRepository.save(temp);
				return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
				//return "success";
			} else if (type.equals("media")) {
				HomePageMedia temp = mapper.readValue(json, HomePageMedia.class);
				homePageMediaRepository.save(temp);
				return ResponseHandler.generateResponse("Home Page Media",HttpStatus.OK,"","SUCCESS");
			}else if (type.equals("faq")) {
				FAQ temp = mapper.readValue(json, FAQ.class);
				faqRepository.save(temp);
				return ResponseHandler.generateResponse("Home Page FAQ",HttpStatus.OK,"","SUCCESS");
				//return "success";
			}else {
				HomeRecentMaster temp = mapper.readValue(json, HomeRecentMaster.class);
				recentMasterRepository.save(temp);
				return ResponseHandler.generateResponse("Districts",HttpStatus.OK,"","SUCCESS");
				//return "success";
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseHandler.generateResponse("Districts",HttpStatus.BAD_REQUEST,"","FAILED");
		//return "failed";
	}

}