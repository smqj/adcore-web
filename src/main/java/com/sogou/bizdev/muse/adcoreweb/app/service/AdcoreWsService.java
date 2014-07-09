package com.sogou.bizdev.muse.adcoreweb.app.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.jws.WebService;

import com.sogou.bizdev.muse.adcoreweb.app.dto.AdcoreDto;

@WebService(targetNamespace="AdcoreWsService")
public interface AdcoreWsService{
	public AdcoreDto addAdcore(AdcoreDto adcoreDto) throws RemoteException;
	public void updateAdcore(AdcoreDto adcoreDto) throws RemoteException;
	public void deleteAdcore(Long cpcId) throws RemoteException;
	public List<AdcoreDto> getAll(Integer curPage, Integer pageSize)throws RemoteException;
	public AdcoreDto getById(Long id)throws RemoteException;
}
