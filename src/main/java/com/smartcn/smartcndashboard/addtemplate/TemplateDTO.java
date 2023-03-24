package com.smartcn.smartcndashboard.addtemplate;

import java.util.List;

public class TemplateDTO {
	List<TemplateModel> templateModel;
	Integer size;
	public List<TemplateModel> getTemplateModel() {
		return templateModel;
	}
	public void setTemplateModel(List<TemplateModel> templateModel) {
		this.templateModel = templateModel;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "TemplateDTO [templateModel=" + templateModel + ", size=" + size + "]";
	}
}
