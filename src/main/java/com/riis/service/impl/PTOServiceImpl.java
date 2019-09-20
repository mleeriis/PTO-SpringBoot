package com.riis.service.impl;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.riis.io.entity.PTOEntity;
import com.riis.io.repositories.PTORepository;
import com.riis.service.PTOService;
import com.riis.shared.dto.PTODto;
import com.riis.ui.model.response.ErrorMessages;
import com.riis.ws.exceptions.PTOServiceException;

@Service
public class PTOServiceImpl implements PTOService {

	@Autowired
	PTORepository ptoRepository;

	@Override
	public PTODto createPTO(PTODto ptoDetails) {
		if (ptoDetails.getEndDate().before(ptoDetails.getStartDate()))
			throw new PTOServiceException(ErrorMessages.INVALID_END_DATE.getErrorMessage());
		if (ptoDetails.getStartDate().before(new Date(System.currentTimeMillis()))
				|| ptoDetails.getStartDate().equals(new Date(System.currentTimeMillis())))
			throw new PTOServiceException(ErrorMessages.DATES_MUST_BE_IN_FUTURE.getErrorMessage());

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
	public PTODto getPTO(int id) {
		PTOEntity ptoDetails = ptoRepository.findById(id);

		if (ptoDetails == null)
			throw new PTOServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		PTODto returnValue = new PTODto();

		BeanUtils.copyProperties(ptoDetails, returnValue);

		return returnValue;
	}

	@Override
	public PTODto updatePTO(int id, PTODto ptoDetails) {
		PTODto returnValue = new PTODto();

		PTOEntity foundPto = ptoRepository.findById(id);

		if (foundPto == null)
			throw new PTOServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if (foundPto.getStatus() == 1 || foundPto.getStatus() == 3)
			throw new PTOServiceException(ErrorMessages.REQUEST_ALREADY_APPROVED.getErrorMessage());

		foundPto.setStatus(ptoDetails.getStatus());

		if (ptoDetails.getStatus() == 1) {
			int empID = foundPto.getEmployeeID();
			Integer currentHoursBalance = ptoRepository.getCurrentHoursBalanceForEmployee(empID);

			int hoursUsed = this.calculateHoursUsed(empID, foundPto);
			currentHoursBalance -= hoursUsed;
			ptoRepository.updateHoursBalance(currentHoursBalance, empID);
		}

		PTOEntity updatedPtoDetails = ptoRepository.save(foundPto);
		BeanUtils.copyProperties(updatedPtoDetails, returnValue);

		return returnValue;
	}

	@Override
	public void deletePTO(int id) {
		PTOEntity foundPto = ptoRepository.findById(id);

		if (foundPto == null)
			throw new PTOServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		if(foundPto.getStatus() == 1) {
			int empID = foundPto.getEmployeeID();
			Integer currentHoursBalance = ptoRepository.getCurrentHoursBalanceForEmployee(empID);
			int hoursUsed = this.calculateHoursUsed(empID, foundPto);
			currentHoursBalance += hoursUsed;
			ptoRepository.updateHoursBalance(currentHoursBalance, empID);
			foundPto.setHoursBalance(currentHoursBalance);
			
		}

		ptoRepository.delete(foundPto);
	}

	@Override
	public List<PTODto> getAllPTO(int employeeId, int page, int limit) {
		List<PTODto> returnValue = new ArrayList<>();
		Page<PTOEntity> ptoPage;

		Pageable pageableRequest = PageRequest.of(page, limit);
		if (employeeId == -1) {
			ptoPage = ptoRepository.findAllPtoWithFullName(pageableRequest);
		} else {
			ptoPage = ptoRepository.findAllPtoByEmpID(employeeId, pageableRequest);
		}

		List<PTOEntity> ptoRequests = ptoPage.getContent();

		for (PTOEntity ptoEntity : ptoRequests) {
			PTODto ptoDto = new PTODto();
			BeanUtils.copyProperties(ptoEntity, ptoDto);
			ptoDto.setFullName(ptoEntity.getFirstname() + " " + ptoEntity.getLastname());
			returnValue.add(ptoDto);
		}

		return returnValue;
	}

	private int calculateHoursUsed(int empID, PTOEntity foundPto) {
		// TODO: Calculate out weekends. Currently, this calculates based on calendar days, not work/week days
		
		int daysUsed = 0;

		if (foundPto.getStartDate().compareTo(foundPto.getEndDate()) == 0) {
			daysUsed = 1;
		} else {
			long timeDiff = foundPto.getEndDate().getTime() - foundPto.getStartDate().getTime();
			long daysDiff = (timeDiff / (24 * 60 * 60 * 1000)) + 1;
			daysUsed = (int) daysDiff;
		}
		int hoursUsed = daysUsed * 8;
		return hoursUsed;
	}

}
