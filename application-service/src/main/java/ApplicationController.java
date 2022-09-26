//package com.swa.apply.controller;
//
//import com.swa.apply.dto.ApplicationDto;
//import com.swa.apply.service.ApplicationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/apply")
//public class ApplicationController {
//
////    @Autowired private ApplicationService applicationService;
////
////    @PostMapping
////    public ResponseEntity<ApplicationDto> save(@RequestBody ApplicationDto applicationDto){
////        applicationDto = applicationService.save(applicationDto);
////        return new ResponseEntity<>(applicationDto, HttpStatus.CREATED);
////    }
////
////    @GetMapping
////    public ResponseEntity<List<ApplicationDto>> findAll(){
////        List<ApplicationDto> applicationDtos = applicationService.findAll();
////        return new ResponseEntity<>(applicationDtos, HttpStatus.OK);
////    }
////
////    @GetMapping("{id}")
////    public ResponseEntity<ApplicationDto> findById(@PathVariable Integer id){
////        ApplicationDto applicationDto = applicationService.findById(id);
////        return new ResponseEntity<>(applicationDto, HttpStatus.OK);
////    }
////
////    @PutMapping
////    public ResponseEntity<ApplicationDto> update(@RequestBody ApplicationDto applicationDto){
////        applicationDto = applicationService.update(applicationDto);
////        return new ResponseEntity<>(applicationDto, HttpStatus.OK);
////    }
//
//
//}
