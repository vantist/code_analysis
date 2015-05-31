package ntut.csie.detect.controller;

import javax.servlet.ServletContext;

import ntut.csie.detect.service.FileManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AnalysisController {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	/*
	 * 分析Jar檔
	 */
	@RequestMapping(value="/analysis", method = RequestMethod.POST)
	public String analysis(@RequestPart("file") MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		return "/index";
	}

//	@RequestMapping(value = "/manage/equipment/new", method = RequestMethod.POST)
//	public String manageEquipment(EquipmentComp equipmentComp, @RequestParam("file") MultipartFile file) throws IOException {
//		if (!file.isEmpty()) {
//			String path = "resources/pic/equipment";
//			String realpath = servletContext.getRealPath(servletContext.getContextPath());
//			InputStream inputStream = file.getInputStream();
//
//			String fileName = System.currentTimeMillis() + "." + file.getOriginalFilename();
//			equipmentComp.setPicture_path(path + "/" + fileName);
//			fileName = realpath + "/" + path + "/" + fileName;
//			
//			OutputStream outputStream = new FileOutputStream(fileName);
//			
//			// write the file to disk
//			int readBytes = 0;
//			byte[] buffer = new byte[1024];
//			while ((readBytes = inputStream.read(buffer)) != -1)
//			{
//				outputStream.write(buffer, 0, readBytes);
//			}
//			outputStream.close();
//			inputStream.close();
//		}
//		
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		if(user.getUsername().equals("ntut")){
//			Equipment equipment = new Equipment();
//			equipment.setName(equipmentComp.getName());
//			equipment.setContact_email(equipmentComp.getContact_email());
//			equipment.setOwner(equipmentComp.getOwner());
//			equipment.setDescription(equipmentComp.getDescription());
//			equipment.setType(equipmentComp.getType());
//			equipment.setContact_person(equipmentComp.getContact_person());
//			equipment.setContact_method(equipmentComp.getContact_method());
//			equipment.setPicture_path(equipmentComp.getPicture_path());
//			equipment.setJoin_time(new LocalDateTime());
//			equipment.setModify_time(new LocalDateTime());
//			equipmentService.save(equipment);
//			return "redirect:/manage/equipment";
//		}
//		else{
//			EquipmentUnAuth equipmentUnAuth = new EquipmentUnAuth();
//			equipmentUnAuth.setAction("new");
//			equipmentUnAuth.setName(equipmentComp.getName());
//			equipmentUnAuth.setContact_email(equipmentComp.getContact_email());
//			equipmentUnAuth.setOwner(user.getUsername());
//			equipmentUnAuth.setDescription(equipmentComp.getDescription());
//			equipmentUnAuth.setType(equipmentComp.getType());
//			equipmentUnAuth.setContact_person(equipmentComp.getContact_person());
//			equipmentUnAuth.setContact_method(equipmentComp.getContact_method());
//			equipmentUnAuth.setPicture_path(equipmentComp.getPicture_path());
//			equipmentUnAuth.setReason("");
//			equipmentUnAuth.setState("waiting");
//			equipmentUnAuth.setJoin_time(new LocalDateTime());
//			equipmentUnAuth.setModify_time(new LocalDateTime());
//			equipmentUnAuthService.save(equipmentUnAuth);
//			return "redirect:/manage/equipment_allow_result";
//		}
//	}
}
