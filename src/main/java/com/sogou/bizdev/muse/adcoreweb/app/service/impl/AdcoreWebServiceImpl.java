package com.sogou.bizdev.muse.adcoreweb.app.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.sogou.bizdev.muse.adcoreweb.app.dto.AdcoreDto;
import com.sogou.bizdev.muse.adcoreweb.app.service.AdcoreWebService;
import com.sogou.bizdev.muse.adcoreweb.app.service.AdcoreWsService;


@WebService
public class AdcoreWebServiceImpl implements AdcoreWebService{

	Logger logger = Logger.getLogger(AdcoreWebServiceImpl.class);
	private AdcoreWsService adWsService;
	
	public AdcoreWsService getAdWsService() {
		return adWsService;
	}

	public void setAdWsService(AdcoreWsService adWsService) {
		this.adWsService = adWsService;
	}

	@Override
	public AdcoreDto addAdcore(AdcoreDto adcoreDto) {
		try {
			return adWsService.addAdcore(adcoreDto);
		} catch (RemoteException e) {
			logger.error("发布竞价广告失败！",e);
		}
		return null;
	}

	@Override
	public void updateAdcore(AdcoreDto adcoreDto) {
		try {
			adWsService.updateAdcore(adcoreDto);
		} catch (RemoteException e) {
			logger.error("竞价广告修改失败！",e);
		}
	}

	@Override
	public void deleteAdcore(Long cpcId) {
		try {
			adWsService.deleteAdcore(cpcId);
		} catch (RemoteException e) {
			logger.error("竞价广告删除失败！",e);
		}		
	}

	@Override
	public List<AdcoreDto> getAll(Integer curPage, Integer pageSize) {
		try {
			return adWsService.getAll(curPage, pageSize);
		} catch (RemoteException e) {
			logger.error("获取竞价广告失败！",e);
		}
		return null;
	}

	@Override
	public AdcoreDto getById(Long id) {
		try {
			return adWsService.getById(id);
		} catch (RemoteException e) {
			logger.error("按id获取竞价广告失败！",e);
		}
		return null;
	}

}
