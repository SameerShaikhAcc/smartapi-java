package com.smartcn.smartcndashboard.addtemplate;

public interface TemplateService {

//	String saveTemplate(TemplateModel templateModel);
//
//	TemplateModel getByName(String name);

	TemplateModel save(TemplateModel templateModel);

	TemplateDTO fetchValues(String name, String owner, String createdDate, String lastChange, String action);

}
