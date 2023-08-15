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


    public Personne creerConcours(ConcoursDto dto){

        ArrayList<CentreConcoursTCIDto> objCentreConcoursTCIDto = dto.getCentreConcoursTCI();

        ArrayList<MatiereDto> objMatiereDtos = dto.getMatiereConcoursTCI();

        ArrayList<CalendrierConcoursTCIDto> ObjCalendrierConcoursTCIDtos = dto.getCalendrierConcoursTCI();

        ConcoursTCIDto objConcoursTCIDto = dto.getConcoursTCI();

        if (objConcoursTCIDto.getId() == null) {

            Concourstci concours = new Concourstci(objConcoursTCIDto.getSessionCTCI(),objConcoursTCIDto.getDescriptionCTCI());

            concourstciRepository.save(concours);

            // Maintenant que l'enregistrement est effectué, vous pouvez récupérer l'ID du concours
            Integer idConcours = concours.getId();
            System.out.println("======== anatiny id null " + idConcours);

            for (CentreConcoursTCIDto centre : objCentreConcoursTCIDto ){

                Centreconcourstci centreconcours = new Centreconcourstci(centre.getNomCentreCTCI(),centre.getCodePostale(),concours,centre.getIdPersonne());
                if (centre.getId_centreCTCI() == null) {
                    centreconcours.setNomCentreCTCI(centre.getNomCentreCTCI());
                    System.out.println("/////////////////////"+centreconcours.getNomCentreCTCI()+"///////");
                    centreconcours.setCodePostale(centre.getCodePostale());

                    centreconcours.setIdCTCIINT(concours.getId());

                    Integer id_chefDeCentre = Integer.valueOf(String.valueOf(findPersonIdByFullName(centre.getNom(), centre.getPrenoms())));

                    if (id_chefDeCentre != null){
                        System.out.println("Id personne : "+id_chefDeCentre);
                        centreconcours.setIdPersonneID(id_chefDeCentre);
                    }
                    System.out.println("Centre: "+ centre.getNomCentreCTCI());
                    System.out.println("Nom Chef de centre"+centre.getNom());
                    System.out.println("Prenom Chef de centre"+centre.getPrenoms());
                    System.out.println("Id concours TCI "+centre.getIdCTCI());
                    System.out.println("Num tel  Chef de centre"+centre.getTelephone());
                    System.out.println("Id Chef de centre"+centre.getIdPersonne());


                    centreconcourstciRepository.save(centreconcours);

                } else {

                    System.out.println("////////////////////ici");
                    Centreconcourstci centre1 = centreconcourstciRepository.findById(centre.getId_centreCTCI()).orElseThrow();
                    Centreconcourstci centreconcours1 = new Centreconcourstci(centre.getNomCentreCTCI(),centre.getCodePostale(),concours,centre.getIdPersonne());

                    System.out.println("////////////////////////"+centreconcours1+"//////////////");
                    centre1.setNomCentreCTCI(centre.getNom());
                    centre1.setCodePostale(centre.getCodePostale());
                    centre1.setIdCTCI(centre.getIdCTCI());
                    centre1.setIdPersonne(centre1.getIdPersonne());
                    //centre1.getIdPersonne().setNom(centre.getNom());
                    //centre1.getIdPersonne().setPrenoms(centre.getPrenoms());
                    //centre1.getIdPersonne().setTelephone(centre.getTelephone());

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
        else{
            System.out.println("========= eto aminy id tsy null : " + objConcoursTCIDto.getId());
            Concourstci concours = concourstciRepository.findById(objConcoursTCIDto.getId()).orElseThrow();
            concours.setSessionCTCI(objConcoursTCIDto.getSessionCTCI());
            concours.setDescriptionCTCI(objConcoursTCIDto.getDescriptionCTCI());
            concours.setDateDebutConcours(objConcoursTCIDto.getDateDebutConcours());
            concours.setDateFinConcours(objConcoursTCIDto.getDateFinConcours());
            concourstciRepository.save(concours);
            System.out.println("=========== concours enregistrer ilay misy id  : "+concours);
            // Maintenant que l'enregistrement est effectué, vous pouvez récupérer l'ID du concours
            Integer idConcours = concours.getId();
            System.out.println("============id Concour : " + idConcours);

            for (CentreConcoursTCIDto centre : objCentreConcoursTCIDto ){

                Centreconcourstci centreconcours = new Centreconcourstci(centre.getNomCentreCTCI(),centre.getCodePostale(),concours,centre.getIdPersonne());
                if (centre.getId_centreCTCI() == null) {
                    centreconcours.setNomCentreCTCI(centre.getNomCentreCTCI());
                    centreconcours.setCodePostale(centre.getCodePostale());
                    centreconcours.setIdCTCIINT(concours.getId());

                    System.out.println("/////////////////////"+centreconcours.getNomCentreCTCI()+"///////");

                    Integer id_chefDeCentre = Integer.valueOf(String.valueOf(findPersonIdByFullName(centre.getNom(), centre.getPrenoms())));

                    if (id_chefDeCentre != null){
                        System.out.println("Id personne chef de centre : "+id_chefDeCentre);
                        centreconcours.setIdPersonneID(id_chefDeCentre);
                    }
                    System.out.println("Centre: "+ centreconcours.getNomCentreCTCI());
                    System.out.println("Nom Chef de centre"+centre.getNom());
                    System.out.println("Prenom Chef de centre"+centre.getPrenoms());
                    System.out.println("Id concours TCI "+centreconcours.getIdCTCI());
                    System.out.println("Num tel  Chef de centre"+centre.getTelephone());
                    System.out.println("Id Chef de centre"+centreconcours.getIdPersonne());

                    centreconcourstciRepository.save(centreconcours);
                    System.out.println("Eto izy Enregistrer ilay centre " + centreconcourstciRepository.save(centreconcours));

                } else {
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
                    Centreconcourstci centre1 = centreconcourstciRepository.findById(centre.getId_centreCTCI()).orElseThrow();
                    centre1.setNomCentreCTCI(centre.getNomCentreCTCI());
                    centre1.setCodePostale(centre.getCodePostale());
                    centre1.getIdPersonne().setNom(centre.getNom());
                    centre1.getIdPersonne().setPrenoms(centre.getPrenoms());
                    centre1.getIdPersonne().setTelephone(centre.getTelephone());

                    centreconcourstciRepository.save(centre1);
                    System.out.println("NomCentreCTCI : " + centre1.getNomCentreCTCI() );
                    System.out.println("CodePostale : " + centre1.getCodePostale());
                    System.out.println("Nom Chef de centre : " + centre1.getIdPersonne().getNom());
                    System.out.println("Prenom Chef  : " + centre1.getIdPersonne().getPrenoms());
                    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
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

    public Integer findPersonIdByFullName(String firstName, String lastName) {
        Optional<Personne> personOptional = personneRepository.findByNomAndPrenoms(firstName, lastName);
        //personOptional.ifPresent(personne -> System.out.println("ID de la personne : " + personne.getId()));
        return personOptional.map(personne -> personne.getId()).orElse(null);
    }
    public List<ConcourstciView> getConcoursList() {
        return concourstciRepository1.findAllBy();
    }

    public ConcourstciInfo getConcoursById(Integer numero) {
        return concourstciRepository1.findConcourstciInfoById(numero);
    }

}
