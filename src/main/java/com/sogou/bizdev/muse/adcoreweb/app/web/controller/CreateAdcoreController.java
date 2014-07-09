package com.sogou.bizdev.muse.adcoreweb.app.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import com.sogou.bizdev.muse.adcoreweb.app.common.Escape;
import com.sogou.bizdev.muse.adcoreweb.app.common.ModuleConstants;
import com.sogou.bizdev.muse.adcoreweb.app.common.ServiceLocator;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewController;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData;
import com.sogou.bizdev.muse.adcoreweb.app.common.ViewData.TypeEnum;
import com.sogou.bizdev.muse.adcoreweb.app.dto.AdcoreDto;
import com.sogou.bizdev.muse.adcoreweb.app.service.AdcoreWebService;
import com.sogou.bizdev.utils.DateUtils;

public class CreateAdcoreController extends ViewController{
	
	private static final long serialVersionUID = 9111446288139469217L;
	private static Long DEFAULT_ACCOUNT_ID = 10870L;

	public AdcoreWebService getAdcoreWebService() {		
		return (AdcoreWebService)ServiceLocator.getInstance().getBean("adcoreWebService");
	}
	
	@Override
	public ViewData process(Map<String, Object> param, HttpSession session) {
		ViewData vd = new ViewData();
		
		Integer maxPrice = -1;
		String url = getParam(param, "url");		
		String maxPriceStr = getParam(param, "maxPrice");
		String keyWord = getParam(param, "keyWord");
		
		keyWord = Escape.unescape(Escape.unescape(keyWord.trim()));
		url = Escape.unescape(Escape.unescape(url.trim()));
		
		if (keyWord == null){
			vd.setMsg("关键词不能为空");
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
		AdcoreDto adcoreDto = new AdcoreDto();		
		adcoreDto.setAccountId(DEFAULT_ACCOUNT_ID);
		adcoreDto.setKey(keyWord);
		adcoreDto.setMaxPrice(maxPrice);
		adcoreDto.setUrl(url);
		adcoreDto.setCreateDate(DateUtils.getToday());
		adcoreDto.setChgDate(DateUtils.getToday());
		getAdcoreWebService().addAdcore(adcoreDto);
		
		vd.setMsg("开销添加成功");
		vd.setType(TypeEnum.NORMAL.ordinal());
		return vd;
	}
}
