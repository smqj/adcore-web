package com.sogou.bizdev.muse.adcoreweb.app.web.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.testng.log4testng.Logger;

import com.sogou.bizdev.muse.adcoreweb.app.common.Escape;
import com.sogou.bizdev.muse.adcoreweb.app.common.ModuleConstants;
import com.sogou.bizdev.muse.adcoreweb.app.common.ServiceLocator;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewController;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData.TypeEnum;
import com.sogou.bizdev.muse.adcoreweb.app.dto.AdcoreDto;
import com.sogou.bizdev.muse.adcoreweb.app.service.AdcoreWebService;
import com.sogou.bizdev.utils.DateUtils;

public class UpdateAdcoreController extends ViewController{
	Logger logger = Logger.getLogger(UpdateAdcoreController.class);
	public AdcoreWebService getAdcoreWebService() {		
		return (AdcoreWebService)ServiceLocator.getInstance().getBean("adcoreWebService");
	}
	
	@Override
	public ViewData process(Map<String, Object> param, HttpSession session) {
		ViewData vd = new ViewData();
	
		Integer maxPrice = -1;
		Long id = -1L, accountId = -1L;
		String url = getParam(param, "url");		
		String maxPriceStr = getParam(param, "maxPrice");
		String keyWord = getParam(param, "keyWord");
		String accountIdStr = getParam(param, "accountId");
		String cpcIdStr = getParam(param, "id");
		String createDateStr = getParam(param, "createDate");
		
		keyWord = Escape.unescape(Escape.unescape(keyWord.trim()));
		url = Escape.unescape(Escape.unescape(url.trim()));
		
		if (keyWord == null){
			vd.setMsg("关键词不能为空");
			vd.setType(TypeEnum.ERROR.ordinal());
			return vd;
		}
		if (createDateStr == null){
			vd.setMsg("创建时间不能为空");
			vd.setType(TypeEnum.ERROR.ordinal());
			return vd;
		}		
		if (maxPriceStr == null) {
			vd.setMsg("最高点击价不能为空！");
			vd.setType(TypeEnum.ERROR.ordinal());
			return vd;
		}
		else {
			if (ModuleConstants.isNum(maxPriceStr)) {
				maxPrice = Integer.parseInt(maxPriceStr);
			}
			else {
				vd.setMsg("最高点击价必须为正整数！");
				vd.setType(TypeEnum.ERROR.ordinal());
				return vd;
			}
		}
		if (cpcIdStr == null) {
			vd.setMsg("id不能为空！");
			vd.setType(TypeEnum.ERROR.ordinal());
			return vd;
		}
		else {
			if (ModuleConstants.isNum(cpcIdStr)) {
				id = Long.parseLong(cpcIdStr);
			}
			else {
				vd.setMsg("id数值异常！");
				vd.setType(TypeEnum.ERROR.ordinal());
				return vd;
			}
		}
		if (accountIdStr == null) {
			vd.setMsg("账户id不能为空！");
			vd.setType(TypeEnum.ERROR.ordinal());
			return vd;
		}
		else {
			if (ModuleConstants.isNum(accountIdStr)) {
				accountId = Long.parseLong(accountIdStr);
			}
			else {
				vd.setMsg("账户id异常！");
				vd.setType(TypeEnum.ERROR.ordinal());
				return vd;
			}
		}
		//
		AdcoreDto adcoreDto = new AdcoreDto();	
		adcoreDto.setCpcId(id);
		adcoreDto.setAccountId(accountId);
		adcoreDto.setKey(keyWord);
		adcoreDto.setMaxPrice(maxPrice);
		adcoreDto.setUrl(url);
		try {
			adcoreDto.setCreateDate(ModuleConstants.dateFormat1.parse(createDateStr));
		} catch (ParseException e) {
			logger.error("竞价广告创建时间格式错误！", e);
			vd.setMsg("竞价广告创建时间格式错误！");
			vd.setType(TypeEnum.ERROR.ordinal());
			return vd;
		}
		adcoreDto.setChgDate(DateUtils.getToday());
		getAdcoreWebService().updateAdcore(adcoreDto);
		
		vd.setMsg("计划更新成功");
		vd.setType(TypeEnum.NORMAL.ordinal());
		return vd;
	}

}
