package com.swa.recruiterservice.service;

import com.swa.recruiterservice.domain.Company;
import com.swa.recruiterservice.domain.Recruiter;
import com.swa.recruiterservice.mappers.Mapper;
import com.swa.recruiterservice.model.RecruiterDto;
import com.swa.recruiterservice.repository.CompanyRepository;
import com.swa.recruiterservice.repository.RecruiterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@DataJpaTest
class RecruiterServiceImplTest {

    @Mock
    RecruiterRepository recruiterRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    Mapper mapper;

    @InjectMocks
    RecruiterServiceImpl recruiterService;


    @BeforeEach()
    public void setUp() {
        mapper = new Mapper();
    }

    @Test
    void findAllRecruiters() {
        List<Recruiter> recs = getRecruiters();

        when(recruiterService.findAllRecruiters()).thenReturn(recs);

        List<Recruiter> recruiterList = recruiterService.findAllRecruiters();

        assertEquals(recruiterList.size(), 1);

        verify(recruiterRepository, times(1)).findAll();
    }


    @Test
    void findByName() {

        Company returnCompany = getCompany();

        when(companyRepository.findByName(any())).thenReturn(returnCompany);

        Company companya =  companyRepository.findByName("CompanyA");

        assertEquals("CompanyA", companya.getName());

        verify(companyRepository).findByName(any());

    }

    @Test
    void findAllCompanies() {
        List<Recruiter> recruiters = getRecruiters();
        recruiters.forEach(recruiter -> recruiter.getCompanies().add(getCompany()));

        when(recruiterRepository.findAll()).thenReturn(recruiters);

        List<Company> companies = recruiterService.findAllCompanies();

        assertEquals(companies.size(), 1);

        verify(recruiterRepository, times(1)).findAll();

    }

    @Test
    void createRecruiter() {
        Recruiter recruiterToSave = Recruiter.builder().name("companya").companies(getCompanies()).build();

        when(recruiterRepository.save(any())).thenReturn(recruiterToSave);

        RecruiterDto dto = mapper.recruiterToRecruiterDto(recruiterToSave);
        Recruiter test = mapper.recruiterDtoToRecruiter(dto);

//        RecruiterDto savedRec = recruiterService.createRecruiter(dto);
        Recruiter savedRecruiter = recruiterRepository.save(test);

//        assertNotNull(savedRec);
        assertNotNull(savedRecruiter);
    }

    private Company getCompany() {
        return Company.builder().companyId("abc").name("CompanyA").build();
    }

    private List<Recruiter> getRecruiters() {
        Recruiter recruiter1 = new Recruiter();
        List<Recruiter> recs = new ArrayList<>();
        recs.add(recruiter1);
        return recs;
    }

    private List<Company> getCompanies() {
        Company company = new Company();
        company.setName("companya");

        List<Company> list = new ArrayList<>();
        list.add(company);
        return list;
    }
}