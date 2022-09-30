package com.swa.recruiterservice.bootstrap;


import com.swa.recruiterservice.domain.Company;
import com.swa.recruiterservice.domain.Recruiter;
import com.swa.recruiterservice.repository.CompanyRepository;
import com.swa.recruiterservice.repository.RecruiterRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final RecruiterRepository recruiterRepository;
    private final CompanyRepository companyRepository;

    public Bootstrap(RecruiterRepository recruiterRepository, CompanyRepository companyRepository) {
        this.recruiterRepository = recruiterRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if(recruiterRepository.count() == 0){
            loadData();
        }

    }

    private void loadData(){

        Company microsoft = new Company();
        microsoft.setName("microsoft");

        Company apple = new Company();
        apple.setName("apple");

        Company amazon = new Company();
        amazon.setName("amazon");

        Company google = new Company();
        google.setName("google");

        companyRepository.save(microsoft);
        companyRepository.save(apple);
        companyRepository.save(amazon);
        companyRepository.save(google);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setName("John Doe");
        recruiter1.setEmail("john@itconsultant.com");
        recruiter1.setRecruiterCompany("IT Consultant Ltd");
        recruiter1.setPhone("6364567890");
        recruiter1.getCompanies().add(google);
        recruiter1.getCompanies().add(amazon);


        Recruiter recruiter2 = new Recruiter();
        recruiter2.setName("George Conor");
        recruiter2.setEmail("george@hiringservices.com");
        recruiter2.setRecruiterCompany("Hiring Services Ltd");
        recruiter2.setPhone("7372456789");
        recruiter2.getCompanies().add(apple);
        recruiter2.getCompanies().add(microsoft);

        recruiterRepository.save(recruiter1);
        recruiterRepository.save(recruiter2);
    }
}
