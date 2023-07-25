package com.appli.springjwt.service;


import com.appli.springjwt.dto.*;
import com.appli.springjwt.models.*;
import com.appli.springjwt.repository.*;
import com.appli.springjwt.view.ConcourstciInfo;
import com.appli.springjwt.view.ConcourstciView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConcourstciService {
    @Autowired
    private ConcourstciRepository concourstciRepository;
    @Autowired
    private ConcourstciRepository1 concourstciRepository1;
    @Autowired
    private ChefcentreconcourstciRepository chefcentreconcourstciRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private CentreconcourstciRepository centreconcourstciRepository;
    @Autowired
    private MatiereconcourstciRepository matiereconcourstciRepository;
    @Autowired
    private CalendrierconcourstciRepository calendrierconcourstciRepository;
    @Autowired
    UserRepository userRepository;

    ArrayList<ConcoursTCISansDesDto> concoursTCISansDesDto;


    public Integer findPersonIdByFullName(String firstName, String lastName) {
        Optional<Personne> personOptional = personneRepository.findByNomAndPrenoms(firstName, lastName);
        //personOptional.ifPresent(personne -> System.out.println("ID de la personne : " + personne.getId()));
        return personOptional.map(personne -> personne.getId()).orElse(null);
    }

    /*public Integer findByFullSessionAndDescr(String firstName, String lastName) {
        Optional<Concourstci> personOptional = concourstciRepository.findBySessionAndDescr(objConcoursTCIDto.getSessionCTCI(),objConcoursTCIDto.getDescriptionCTCI());
        //personOptional.ifPresent(personne -> System.out.println("ID de la personne : " + personne.getId()));
        return personOptional.map(personne -> personne.getId()).orElse(null);
    }*/

    public Personne creerConcours(ConcoursDto dto){

        ArrayList<CentreConcoursTCIDto> objCentreConcoursTCIDto = dto.getCentreConcoursTCI();

        ArrayList<MatiereDto> objMatiereDtos = dto.getMatiereConcoursTCI();

        ArrayList<CalendrierConcoursTCIDto> ObjCalendrierConcoursTCIDtos = dto.getCalendrierConcoursTCI();

        //dto.getConcoursTCI().getId();

        ConcoursTCIDto objConcoursTCIDto = dto.getConcoursTCI();

        if (objConcoursTCIDto.getId() == null) {

            Concourstci concours = new Concourstci(objConcoursTCIDto.getSessionCTCI(),objConcoursTCIDto.getDescriptionCTCI());

            concourstciRepository.save(concours);
           // Optional<Concourstci> personOptional = concourstciRepository.findBySessionAndDescr(objConcoursTCIDto.getSessionCTCI(),objConcoursTCIDto.getDescriptionCTCI());
            //System.out.println(personOptional.map( concourstci -> concourstci.getId()).orElse(null)
//);          //Optional<Object> id_ctci = personOptional.map(concourstci -> concourstci.getId());

            for (CentreConcoursTCIDto centre : objCentreConcoursTCIDto ){
                int val = 20 ;
                if (centre.getId_centreCTCI() == null) {
                    centre.setNomCentreCTCI(centre.getNomCentreCTCI());
                    centre.setCodePostale(centre.getCodePostale());

  //                  centre.setIdCTCI(id_ctci);

                    Integer id_chefDeCentre = Integer.valueOf(String.valueOf(findPersonIdByFullName(centre.getNom(), centre.getPrenoms())));

                    if (id_chefDeCentre != null){
                        System.out.println("Id personne : "+id_chefDeCentre);
                        centre.setIdPersonne(Integer.valueOf(id_chefDeCentre));
                    }
                   /* PersonneDto personneDto = new PersonneDto();
                    //boolean b = !personneRepository.existsByNomAndPrenoms(centre.getNom(), centre.getPrenoms());
                    if ( !personneRepository.existsByNomAndPrenoms(centre.getNom(), centre.getPrenoms()) ){
                        centre.setIdPersonne(personneDto.getId());
                        System.out.println(personneDto.getId());
                    }else {
                        System.out.println("tsi ita ilay olona");
                    }*/




                    System.out.println("Centre: "+ centre.getNomCentreCTCI());
                    System.out.println("Nom Chef de centre"+centre.getNom());
                    System.out.println("Prenom Chef de centre"+centre.getPrenoms());
                    System.out.println("Id concours TCI "+centre.getIdCTCI());
                    System.out.println("Num tel  Chef de centre"+centre.getTelephone());
                    System.out.println("Id Chef de centre"+centre.getIdPersonne());

                    // Chefcentreconcourstci chefcentreconcourstci = new Chefcentreconcourstci();
                    //Personne personne = new Personne(centre.getNom(),centre.getPrenoms(),centre.getTelephone());

                   // personneRepository.save(personne);

                    // chefcentreconcourstci.setIdPersonne(personne);
                    // chefcentreconcourstciRepository.save(chefcentreconcourstci);

                    //Centreconcourstci centreconcours = new Centreconcourstci(centre.getNom_centreCTCI(),chefcentreconcourstci);
                    Centreconcourstci centreconcours = new Centreconcourstci(centre.getNomCentreCTCI(),centre.getCodePostale(),concours,centre.getIdPersonne());

                    centreconcourstciRepository.save(centreconcours);

                } else {
                    Centreconcourstci centre1 = centreconcourstciRepository.findById(centre.getId_centreCTCI()).orElseThrow();
                    centre1.setNomCentreCTCI(centre.getNom());
                    centre1.setCodePostale(centre.getCodePostale());
                    centre1.getIdPersonne().setNom(centre.getNom());
                    centre1.getIdPersonne().setPrenoms(centre.getPrenoms());
                    centre1.getIdPersonne().setTelephone(centre.getTelephone());

                    centreconcourstciRepository.save(centre1);
                }
            }



            for(int i=0; i<objMatiereDtos.size(); i++) {

                if (objMatiereDtos.get(i).getId() == null) {

                    if (matiereconcourstciRepository.existsByNomMCTCIAndCreditMCTCI(objMatiereDtos.get(i).getNomMCTCI(),objMatiereDtos.get(i).getCreditMCTCI()) ){

                        Matiereconcourstci matiereconcourstci = matiereconcourstciRepository.findByNomMCTCIAndCreditMCTCI(objMatiereDtos.get(i).getNomMCTCI(),objMatiereDtos.get(i).getCreditMCTCI()).orElseThrow();

                        Calendrierconcourstci calendrierconcourstci = new Calendrierconcourstci();
                        calendrierconcourstci.setIdCTCI(concours);
                        calendrierconcourstci.setIdMCTCI(matiereconcourstci);
                        calendrierconcourstci.setDateCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDateCalendrierCTCI());
                        calendrierconcourstci.setDebutHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDebutHeureCalendrierCTCI());
                        calendrierconcourstci.setFinHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getFinHeureCalendrierCTCI());

                        matiereconcourstci.getCalendrierconcourstcis().add(calendrierconcourstci);
                        concours.getCalendrierconcourstcis().add(calendrierconcourstci);

                        System.out.println(calendrierconcourstci.getIdCTCI().getDescriptionCTCI());
                        System.out.println(calendrierconcourstci.getIdMCTCI().getNomMCTCI());

                        calendrierconcourstciRepository.save(calendrierconcourstci);


                    } else {
                        Matiereconcourstci matiereconcourstci = new Matiereconcourstci(objMatiereDtos.get(i).getNomMCTCI(),objMatiereDtos.get(i).getCreditMCTCI());
                        matiereconcourstciRepository.save(matiereconcourstci);

                        Calendrierconcourstci calendrierconcourstci = new Calendrierconcourstci();
                        calendrierconcourstci.setIdCTCI(concours);
                        calendrierconcourstci.setIdMCTCI(matiereconcourstci);
                        calendrierconcourstci.setDateCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDateCalendrierCTCI());
                        calendrierconcourstci.setDebutHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDebutHeureCalendrierCTCI());
                        calendrierconcourstci.setFinHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getFinHeureCalendrierCTCI());

                        matiereconcourstci.getCalendrierconcourstcis().add(calendrierconcourstci);
                        concours.getCalendrierconcourstcis().add(calendrierconcourstci);

                        System.out.println(calendrierconcourstci.getIdCTCI().getDescriptionCTCI());
                        System.out.println(calendrierconcourstci.getIdMCTCI().getNomMCTCI());

                        calendrierconcourstciRepository.save(calendrierconcourstci);

                    }

                } else {

                    Matiereconcourstci matiere = matiereconcourstciRepository.findById(objMatiereDtos.get(i).getId()).orElseThrow();
                    matiere.setNomMCTCI(objMatiereDtos.get(i).getNomMCTCI());
                    matiere.setCreditMCTCI(objMatiereDtos.get(i).getCreditMCTCI());
                    //  Calendrierconcourstci calendrier = calendrierconcourstciRepository.
                    //  Calendrierconcourstci calendrier = matiere.getCalendrierconcourstcis();
                    // matiere.getCalendrierconcourstcis().add(calendrier);
                    matiereconcourstciRepository.save(matiere);

                    Calendrierconcourstci calendrier = calendrierconcourstciRepository.findByIdCTCIAndIdMCTCI(concours,matiere).orElseThrow();
                    calendrier.setIdCTCI(concours);
                    calendrier.setIdMCTCI(matiere);
                    calendrier.setDateCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDateCalendrierCTCI());
                    calendrier.setDebutHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDebutHeureCalendrierCTCI());
                    calendrier.setFinHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getFinHeureCalendrierCTCI());

                    calendrierconcourstciRepository.save(calendrier);
                }

            }

        } else{
            Concourstci concours = concourstciRepository.findById(objConcoursTCIDto.getId()).orElseThrow();
            concours.setSessionCTCI(objConcoursTCIDto.getSessionCTCI());
            //concours.setAnneeCTCI(objConcoursTCIDto.getAnneeCTCI());
            concours.setDescriptionCTCI(objConcoursTCIDto.getDescriptionCTCI());
            concourstciRepository.save(concours);

            for (CentreConcoursTCIDto centre : objCentreConcoursTCIDto ){

                if (centre.getId_centreCTCI() == null) {
                    System.out.println("Centre: "+ centre.getNom());
                    System.out.println(centre.getNomCentreCTCI());
                    System.out.println(centre.getNom());
                    System.out.println(centre.getPrenoms());
                    System.out.println(centre.getTelephone());


                   // Chefcentreconcourstci chefcentreconcourstci = new Chefcentreconcourstci();
                    Personne personne = new Personne(centre.getNom(),centre.getPrenoms(),centre.getTelephone());

                    personneRepository.save(personne);

                   /*chefcentreconcourstci.setIdPersonne(personne);
                    chefcentreconcourstciRepository.save(chefcentreconcourstci);

                    */

                    //Centreconcourstci centreconcours = new Centreconcourstci(centre.getNom_centreCTCI(),chefcentreconcourstci);
                    Centreconcourstci centreconcours = new Centreconcourstci(centre.getNomCentreCTCI(),centre.getCodePostale(),personne,concours);
                    centreconcourstciRepository.save(centreconcours);

                } else {
                    Centreconcourstci centre1 = centreconcourstciRepository.findById(centre.getId_centreCTCI()).orElseThrow();
                    centre1.setNomCentreCTCI(centre.getNom());
                    centre1.setCodePostale(centre.getCodePostale());
                    centre1.getIdPersonne().setNom(centre.getNom());
                    centre1.getIdPersonne().setPrenoms(centre.getPrenoms());
                    centre1.getIdPersonne().setTelephone(centre.getTelephone());

                    centreconcourstciRepository.save(centre1);
                }
            }

            for(int i=0; i<objMatiereDtos.size(); i++) {

                if (objMatiereDtos.get(i).getId() == null) {

                    if (matiereconcourstciRepository.existsByNomMCTCIAndCreditMCTCI(objMatiereDtos.get(i).getNomMCTCI(),objMatiereDtos.get(i).getCreditMCTCI()) ){

                        Matiereconcourstci matiereconcourstci = matiereconcourstciRepository.findByNomMCTCIAndCreditMCTCI(objMatiereDtos.get(i).getNomMCTCI(),objMatiereDtos.get(i).getCreditMCTCI()).orElseThrow();

                        Calendrierconcourstci calendrierconcourstci = new Calendrierconcourstci();
                        calendrierconcourstci.setIdCTCI(concours);
                        calendrierconcourstci.setIdMCTCI(matiereconcourstci);
                        calendrierconcourstci.setDateCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDateCalendrierCTCI());
                        calendrierconcourstci.setDebutHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDebutHeureCalendrierCTCI());
                        calendrierconcourstci.setFinHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getFinHeureCalendrierCTCI());

                        matiereconcourstci.getCalendrierconcourstcis().add(calendrierconcourstci);
                        concours.getCalendrierconcourstcis().add(calendrierconcourstci);

                        System.out.println(calendrierconcourstci.getIdCTCI().getDescriptionCTCI());
                        System.out.println(calendrierconcourstci.getIdMCTCI().getNomMCTCI());

                        calendrierconcourstciRepository.save(calendrierconcourstci);


                    } else {
                        Matiereconcourstci matiereconcourstci = new Matiereconcourstci(objMatiereDtos.get(i).getNomMCTCI(),objMatiereDtos.get(i).getCreditMCTCI());
                        matiereconcourstciRepository.save(matiereconcourstci);

                        Calendrierconcourstci calendrierconcourstci = new Calendrierconcourstci();
                        calendrierconcourstci.setIdCTCI(concours);
                        calendrierconcourstci.setIdMCTCI(matiereconcourstci);
                        calendrierconcourstci.setDateCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDateCalendrierCTCI());
                        calendrierconcourstci.setDebutHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDebutHeureCalendrierCTCI());
                        calendrierconcourstci.setFinHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getFinHeureCalendrierCTCI());

                        matiereconcourstci.getCalendrierconcourstcis().add(calendrierconcourstci);
                        concours.getCalendrierconcourstcis().add(calendrierconcourstci);

                        System.out.println(calendrierconcourstci.getIdCTCI().getDescriptionCTCI());
                        System.out.println(calendrierconcourstci.getIdMCTCI().getNomMCTCI());

                        calendrierconcourstciRepository.save(calendrierconcourstci);

                    }

                } else {

                    Matiereconcourstci matiere = matiereconcourstciRepository.findById(objMatiereDtos.get(i).getId()).orElseThrow();
                    matiere.setNomMCTCI(objMatiereDtos.get(i).getNomMCTCI());
                    matiere.setCreditMCTCI(objMatiereDtos.get(i).getCreditMCTCI());
                    //  Calendrierconcourstci calendrier = calendrierconcourstciRepository.
                    //  Calendrierconcourstci calendrier = matiere.getCalendrierconcourstcis();
                    // matiere.getCalendrierconcourstcis().add(calendrier);
                    matiereconcourstciRepository.save(matiere);

                    Calendrierconcourstci calendrier = calendrierconcourstciRepository.findByIdCTCIAndIdMCTCI(concours,matiere).orElseThrow();
                    calendrier.setIdCTCI(concours);
                    calendrier.setIdMCTCI(matiere);
                    calendrier.setDateCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDateCalendrierCTCI());
                    calendrier.setDebutHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getDebutHeureCalendrierCTCI());
                    calendrier.setFinHeureCalendrierCTCI(ObjCalendrierConcoursTCIDtos.get(i).getFinHeureCalendrierCTCI());

                    calendrierconcourstciRepository.save(calendrier);

                }
            }
        }

        return null;
    }

    public List<ConcourstciView> getConcoursList() {
        //Iterable<Concourstci> concourstci = concourstciRepository.findAll();

        //  concoursTCISansDesDto = new ArrayList<ConcoursTCISansDesDto>();

        return concourstciRepository1.findAllBy();
        //  return concourstciRepository.findAll();}

    }

    public ConcourstciInfo getConcoursById(Integer numero) {
        return concourstciRepository1.findConcourstciInfoById(numero);
    }

   /* public Optional<Centreconcourstci> getCentreByConcoursId(Integer numero) {
      Concourstci concourstci =  concourstciRepository.findById(numero).orElseThrow();

       // return centreconcourstciRepository.findbyIdCtci(concourstci);
return null;
    }

    */
}
