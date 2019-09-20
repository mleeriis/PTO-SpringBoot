package com.riis.service;

import java.util.List;

import com.riis.shared.dto.PTODto;

public interface PTOService {
	PTODto createPTO(PTODto ptoDetails);
	PTODto getPTO(int id);
	PTODto updatePTO(int id, PTODto ptoDetails);
	void deletePTO(int id);
	List<PTODto> getAllPTO(int employeeId, int page, int limit);
}
