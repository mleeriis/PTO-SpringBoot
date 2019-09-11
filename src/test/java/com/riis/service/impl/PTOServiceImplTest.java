package com.riis.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.riis.ws.exceptions.PTOServiceException;
import com.riis.io.entity.PTOEntity;
import com.riis.io.repositories.PTORepository;
import com.riis.shared.dto.PTODto;

class PTOServiceImplTest {
	@InjectMocks
	PTOServiceImpl ptoService;

	@Mock
	PTORepository ptoRepository;

	private PTOEntity ptoEntityStub = new PTOEntity();
	private PTODto ptoDtoStub = new PTODto();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		ptoEntityStub.setId(1);
		ptoEntityStub.setEmployeeID(1);
		ptoEntityStub.setStartDate(new Date(2019 - 10 - 11));
		ptoEntityStub.setEndDate(new Date(2019 - 10 - 30));
		ptoEntityStub.setStatus(2);
		
		ptoDtoStub.setId(1);
		ptoDtoStub.setEmployeeID(1);
		ptoDtoStub.setStartDate(new Date(2019 - 10 - 11));
		ptoDtoStub.setEndDate(new Date(2019 - 10 - 30));
		ptoDtoStub.setStatus(2);

	}

	@Test
	void successfullyGetPtoWithCorrectDetails() {
		when(ptoRepository.findById(anyInt())).thenReturn(ptoEntityStub);

		PTODto returnedPtoDto = ptoService.findPTO(1);

		assertNotNull(returnedPtoDto);
		assertEquals(ptoEntityStub.getId(), returnedPtoDto.getId());
		assertEquals(ptoEntityStub.getEmployeeID(), returnedPtoDto.getId());
		assertEquals(ptoEntityStub.getStartDate(), returnedPtoDto.getStartDate());
		assertEquals(ptoEntityStub.getEndDate(), returnedPtoDto.getEndDate());
		assertEquals(ptoEntityStub.getStatus(), returnedPtoDto.getStatus());
	}

	@Test
	void throwExceptionWhenPtoNotFound() {
		when(ptoRepository.findById(anyInt())).thenReturn(null);

		assertThrows(PTOServiceException.class, () -> {ptoService.findPTO(1);});
		assertThrows(PTOServiceException.class, () -> {ptoService.updatePTO(1, ptoDtoStub);});
		assertThrows(PTOServiceException.class, () -> {ptoService.deletePTO(2);});
	}

	@Test
	void successfullyUpdatePtoDetails() {
		when(ptoRepository.findById(anyInt())).thenReturn(ptoEntityStub);
		when(ptoRepository.save(any(PTOEntity.class))).thenReturn(ptoEntityStub);
		
		ptoDtoStub.setStatus(1);

		PTODto updatedPtoDto = ptoService.updatePTO(1, ptoDtoStub);

		assertNotNull(updatedPtoDto);
		assertEquals(ptoDtoStub.getStatus(), updatedPtoDto.getStatus());
		verify(ptoRepository, times(1)).save(any(PTOEntity.class));
	}
	
	@Test
	void successfullyCreatePto() {
		when(ptoRepository.save(any(PTOEntity.class))).thenReturn(ptoEntityStub);
		
		PTODto createdPtoDto = ptoService.createPTO(ptoDtoStub);
		
		assertNotNull(createdPtoDto);
		assertEquals(ptoDtoStub.getId(), createdPtoDto.getId());
		assertEquals(ptoDtoStub.getEmployeeID(), createdPtoDto.getId());
		assertEquals(ptoDtoStub.getStartDate(), createdPtoDto.getStartDate());
		assertEquals(ptoDtoStub.getEndDate(), createdPtoDto.getEndDate());
		assertEquals(ptoDtoStub.getStatus(), createdPtoDto.getStatus());
	}
	
	

}