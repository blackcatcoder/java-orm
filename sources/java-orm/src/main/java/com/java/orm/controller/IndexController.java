package com.java.orm.controller;

import com.java.orm.entities.Person;
import com.java.orm.entities.Dossier;
import com.java.orm.entities.DossierPerson;
import com.java.orm.repository.DossierRepository;
import com.java.orm.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class IndexController {

    private final DossierRepository dossierRepository;
    private final PersonRepository personRepository;

    public IndexController(DossierRepository dossierRepository, PersonRepository personRepository){
        this.dossierRepository = dossierRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("dossier")
    public String getDossier(){
        Dossier rs =  dossierRepository.findById(1l).orElse(null);
        List<DossierPerson> dossierPersonList = rs.getDossierPersonList();
        System.out.println(dossierPersonList.size());
        Person p = dossierPersonList.get(0).getPerson();
        System.out.println(p.getDossierPersonList().size());

        Dossier active = p.getDossierPersonList().stream().filter(d -> d.getStatus().equals("inactive")).map(d -> d.getDossier()).findFirst().orElse(null);
        System.out.println(active);

        return rs != null ? rs.toString() : "";
    }

    @GetMapping("person")
    public String getPerson(){
        Person p = personRepository.findById(2l).orElse(null);
        System.out.println(p.getDossierPersonList().size());
        System.out.println("");
        return "lala";
    }

}
