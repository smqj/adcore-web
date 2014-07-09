package com.sogou.bizdev.muse.adcoreweb.app.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.sogou.bizdev.muse.adcoreweb.app.common.ModuleConstants;
import com.sogou.bizdev.muse.adcoreweb.app.common.ServiceLocator;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewController;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData.TypeEnum;
import com.sogou.bizdev.muse.adcoreweb.app.diso.AdcoreDo;
import com.sogou.bizdev.muse.adcoreweb.app.dto.AdcoreDto;
import com.sogou.bizdev.muse.adcoreweb.app.service.AdcoreWebService;

public class AdcoreListController extends ViewController{

	private static final long serialVersionUID = 5847945869666445382L;

	public AdcoreWebService getAdcoreWebService() {		
		return (AdcoreWebService)ServiceLocator.getInstance().getBean("adcoreWebService");
	}
	
	@Override
	public ViewData process(Map<String, Object> param, HttpSession session) {
		ViewData vd = new ViewData();
		try {
			long total = -1L, id = -1L;
			Integer pageSize = -1, curPage = -1, choice = -1;
			
			if (getParam(param, "rows") != null&&ModuleConstants.isNum(getParam(param, "rows"))) {
				pageSize = Integer.parseInt(getParam(param, "rows"));
			}
			if (getParam(param, "page") != null&&ModuleConstants.isNum(getParam(param, "page"))) {
				curPage = Integer.parseInt(getParam(param, "page"));
			}
			if (getParam(param, "choice") != null&&ModuleConstants.isNum(getParam(param, "choice"))) {
				choice = Integer.parseInt(getParam(param, "choice"));
			}
			if (getParam(param, "id") != null&&ModuleConstants.isNum(getParam(param, "id"))) {
				id = Integer.parseInt(getParam(param, "id"));
			}
			
			Object result = new Object();			
	
			switch (ModuleConstants.ChoiceEnum.parse(choice)) {
				case FINDALL:
					if(pageSize == -1||curPage == -1){
						vd.setType(TypeEnum.ERROR.ordinal());
						vd.setMsg("pageSize或者curPage值有误！");
						return vd;
					}
					
					result = getAdcoreWebService().getAll(curPage, pageSize);
					if (result != null) {
						List<AdcoreDto> adDtoList = (List<AdcoreDto>)result;
						List<AdcoreDo> adDoList = new ArrayList<AdcoreDo>();
						if (adDtoList != null) {
							AdcoreDto aDto = adDtoList.get(adDtoList.size()-1);	
							total = aDto.getAccountId();
							adDtoList.remove(adDtoList.size()-1);
							for (AdcoreDto a : adDtoList) {
								adDoList.add(adcoreDto2Do(a));
							}
						}
						result = adDoList;				
					}
					else {
						vd.setType(TypeEnum.ERROR.ordinal());
						vd.setMsg("分页查找失败！");
						return vd;
					}			
					break;
				case FINDBYID:
					if(id == -1){
						vd.setType(TypeEnum.ERROR.ordinal());
						vd.setMsg("id值有误！");
						return vd;
					}
					AdcoreDto aDto = getAdcoreWebService().getById(id);
					result = adcoreDto2Do(aDto);
					break;
				default:
					vd.setType(TypeEnum.ERROR.ordinal());
					vd.setMsg("查询类型异常！");
					return vd;			
			}
			
			
			vd.setTotal(total);
			vd.setType(TypeEnum.NORMAL.ordinal());
			vd.setData(result);
			
		} catch (Exception e) {
			vd.setType(TypeEnum.ERROR.ordinal());
			vd.setMsg("查找计划异常！");
		}
		return vd;
	}

	private AdcoreDo adcoreDto2Do(AdcoreDto aDto) {
		AdcoreDo aDo = new AdcoreDo();
		aDo.setAccountId(aDto.getAccountId());
		aDo.setCpcId(aDto.getCpcId());
		aDo.setKey(aDto.getKey());
		aDo.setMaxPrice(aDto.getMaxPrice());
		aDo.setUrl(aDto.getUrl());
		aDo.setCreateDate(ModuleConstants.dateFormat1.format(aDto.getCreateDate()));
		aDo.setChgDate(ModuleConstants.dateFormat1.format(aDto.getChgDate()));
		return aDo;
	}
}
