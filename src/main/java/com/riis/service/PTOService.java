package com.riis.service;

import com.riis.shared.dto.PTODto;

public interface PTOService {
	PTODto createPTO(PTODto ptoDetails);
	PTODto findPTO(int id);
	PTODto updatePTO(int id, PTODto ptoDetails);
}
