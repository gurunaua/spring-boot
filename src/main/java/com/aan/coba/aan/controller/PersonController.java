package com.aan.coba.aan.controller;


import com.aan.coba.aan.model.Person;
import com.aan.coba.aan.repository.RepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PersonController {

    @Autowired
    private RepositoryDao repositoryDao;

    @GetMapping("/persons")
    private List<Person> getAllPersons(@RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "id", required = false) Long id,
                                       @RequestParam(name = "start", required = false) int start,
                                       @RequestParam(name = "to", required = false) int to
                                       ){



        if(id!=null && id >0){
            List<Person> list = new ArrayList<>();
            list.add(repositoryDao.findByIdAan(id));// test native query
            return list;
        }else if (name==null ||name.isEmpty())
            return repositoryDao.findAll();
        else
            return repositoryDao.findByName(name);
    }

    @GetMapping("/personsPage")
    private Page<Person> getAllPersons(@RequestParam(name = "page", required = false) int page,
                                       @RequestParam(name = "size", required = false) int size){


        Pageable paging = PageRequest.of(page, size);
        return repositoryDao.findAll(paging);
    }


}
