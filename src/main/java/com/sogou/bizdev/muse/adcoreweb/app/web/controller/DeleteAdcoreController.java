package com.sogou.bizdev.muse.adcoreweb.app.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.sogou.bizdev.muse.adcoreweb.app.common.ServiceLocator;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewController;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData.TypeEnum;
import com.sogou.bizdev.muse.adcoreweb.app.service.AdcoreWebService;


public class DeleteAdcoreController extends ViewController{

	private static final long serialVersionUID = 6885641820423277343L;

	public AdcoreWebService getAdcoreWebService() {		
		return (AdcoreWebService)ServiceLocator.getInstance().getBean("adcoreWebService");
	}
	
	@Override
	public ViewData process(Map<String, Object> param, HttpSession session) {
		ViewData vd = new ViewData();
		
		Long id = -1L;		
		if (getParam(param, "id") == null){
			vd.setMsg("开销id不能为空");
			vd.setType(TypeEnum.ERROR.ordinal());
			return vd;
		}
		else {
			id = Long.parseLong(getParam(param, "id"));
		}
		
		getAdcoreWebService().deleteAdcore(id);
		
		vd.setMsg("开销删除成功");
		vd.setType(TypeEnum.NORMAL.ordinal());
		return vd;
	}

}
