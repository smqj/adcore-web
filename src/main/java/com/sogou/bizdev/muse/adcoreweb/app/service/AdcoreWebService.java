package com.sogou.bizdev.muse.adcoreweb.app.service;

import java.util.List;
import javax.jws.WebService;
import com.sogou.bizdev.muse.adcoreweb.app.dto.AdcoreDto;

@WebService
public interface AdcoreWebService{
	public AdcoreDto addAdcore(AdcoreDto adcoreDto);
	public void updateAdcore(AdcoreDto adcoreDto);
	public void deleteAdcore(Long cpcId);
	public List<AdcoreDto> getAll(Integer curPage, Integer pageSize);
	public AdcoreDto getById(Long id);
}
