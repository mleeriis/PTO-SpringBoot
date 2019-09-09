package com.riis.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.riis.io.entity.PTOEntity;
import com.riis.io.repositories.PTORepository;
import com.riis.service.PTOService;
import com.riis.shared.dto.PTODto;
import com.riis.ws.exceptions.PTOServiceException;

@Service
public class PTOServiceImpl implements PTOService {

	@Autowired
	PTORepository ptoRepository;

	@Override
	public PTODto createPTO(PTODto ptoDetails) {
		// TODO Auto-generated method stub
		PTOEntity ptoEntity = new PTOEntity();
		BeanUtils.copyProperties(ptoDetails, ptoEntity);

		ptoEntity.setEmployeeID(ptoDetails.getEmployeeID());
		ptoEntity.setStartDate(ptoDetails.getStartDate());
		ptoEntity.setEndDate(ptoDetails.getEndDate());
		ptoEntity.setStatus(ptoDetails.getStatus());

		PTOEntity storedPTODetails = ptoRepository.save(ptoEntity);

		PTODto returnValue = new PTODto();
		BeanUtils.copyProperties(storedPTODetails, returnValue);

		return returnValue;
	}
	
	@Override
	public PTODto findPTO(int id) {
		PTOEntity ptoDetails = ptoRepository.findById(id);
		
		if(ptoDetails == null) throw new PTOServiceException("Id not found.");
		
		PTODto returnValue = new PTODto();
		
		BeanUtils.copyProperties(ptoDetails, returnValue);
		
		
		return returnValue;
	}


	@Override
	public PTODto updatePTO(int id, PTODto ptoDetails) {
		PTODto returnValue = new PTODto();
		
		PTOEntity foundPto = ptoRepository.findById(id);
		
		if(foundPto == null) throw new PTOServiceException("Id not found.");

		foundPto.setStatus(ptoDetails.getStatus());	
		
		PTOEntity updatedPtoDetails = ptoRepository.save(foundPto);
		BeanUtils.copyProperties(updatedPtoDetails, returnValue);		
		
		return returnValue;
	}


}
