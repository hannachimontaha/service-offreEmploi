package com.iset.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iset.dao.OffreRepository;
import com.iset.entities.Offre;

@RestController
@RequestMapping("/Offres") 
public class RestOffres { 
@Autowired
OffreRepository offreRepository; 

@GetMapping
public List<Offre> getAll() { 
return offreRepository.findAll(); 
} 

@GetMapping("/{uid}") 
public Offre getbyid(@PathVariable Long uid ) { 
return offreRepository.findById(uid).get(); 
}

@PostMapping
public Offre saveoffre(@RequestBody Offre newoffre) { 
return offreRepository.save(newoffre); 
}

@DeleteMapping("/{id}") 
public void deleteoffre(@PathVariable Long id) { 
offreRepository.deleteById(id); 
}

@PutMapping("/Offres/{id}")
public Offre updateoffre(@PathVariable("id") long id, @RequestBody Offre o) 
{
	Offre ofr = offreRepository.findById(id).
			orElseThrow(() -> new RuntimeException("Offre introuvable avec id : : " +id));
	ofr.setCode(o.getCode());
	ofr.setIntitulé(o.getIntitulé());
	ofr.setSpecialité(o.getSpecialité());
	ofr.setSociété(o.getSociété());
	ofr.setNbpostes(o.getNbpostes());
	ofr.setPays(o.getPays());
   
	final Offre updateoffre =  offreRepository.save(ofr); 
	return updateoffre;
}
}