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
            System.out.println("CREATION D UN NOUVEAU CONCOUR / CENTRE");
            Concourstci concours = new Concourstci(objConcoursTCIDto.getSessionCTCI(),objConcoursTCIDto.getDescriptionCTCI());
            concourstciRepository.save(concours);
            System.out.println("CREATION D UN NOUVEAU CONCOUR OK :)");



            for (CentreConcoursTCIDto centre : objCentreConcoursTCIDto ){
                System.out.println("CREATION 1");
                Centreconcourstci centreconcours = new Centreconcourstci(
                        centre.getNomCentreCTCI(),
                        centre.getCodePostale(),
                        concours,
                        centre.getIdPersonne());

                System.out.println("CREATION 2");
                Personne adjoint = personneRepository.findByNomAndPrenoms(centre.getNomAdjoint(), centre.getPrenomAdjoint()).orElse(null);

                System.out.println("CREATION 3");
                Personne adjointNew = new Personne();
                if (adjoint == null){
                    System.out.println("NOUVEAU ADJOINT");
                    adjointNew.setNom(centre.getNomAdjoint());
                    adjointNew.setPrenoms(centre.getPrenomAdjoint());
                    personneRepository.save(adjointNew);
                    System.out.println(" AJOUT ADJOINT OK id : " + adjointNew.getId());
                }else {
                    System.out.println(" ADJOINT EXIST DEJA id : " + adjoint.getId());
                }

                Personne adjointExist = personneRepository.findByNomAndPrenoms(centre.getNomAdjoint(), centre.getPrenomAdjoint()).orElse(null);

                if (centre.getId_centreCTCI() == null) {
                    System.out.println("CREATION NEW CENTRE ");
                    centreconcours.setNomCentreCTCI(centre.getNomCentreCTCI());
                    centreconcours.setCodePostale(centre.getCodePostale());
                    centreconcours.setIdCTCIINT(concours.getId());
                    centreconcours.setIdPersonneAdjoit(adjointExist);

                    Integer id_chefDeCentre = Integer.valueOf(String.valueOf(findPersonIdByFullName(centre.getNom(), centre.getPrenoms())));

                    if (id_chefDeCentre != null){
                        System.out.println("Id personne : "+id_chefDeCentre);
                        centreconcours.setIdPersonneID(id_chefDeCentre);
                    }
                 centreconcourstciRepository.save(centreconcours);
                    System.out.println("CREATION D UN NOUVEAU CONCOUR / CENTRE / ADJOINT  OK :)");

                } else {
                    System.out.println("MODIFICATION CENTRE id :  " + centre.getId_centreCTCI());
                    Centreconcourstci centre1 = centreconcourstciRepository.findById(centre.getId_centreCTCI()).orElseThrow();
                    Centreconcourstci centreconcours1 = new Centreconcourstci(centre.getNomCentreCTCI(),centre.getCodePostale(),concours,centre.getIdPersonne());

                    centre1.setNomCentreCTCI(centre.getNom());
                    centre1.setCodePostale(centre.getCodePostale());
                    centre1.setIdCTCI(centre.getIdCTCI());
                    centre1.setIdPersonne(centre1.getIdPersonne());
                    centre1.setIdPersonneAdjoit(adjointExist);

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
            System.out.println("MODIFICATION CONCOUR id : " + objConcoursTCIDto.getId() + " Session : " + objConcoursTCIDto.getSessionCTCI());

            Concourstci concours = concourstciRepository.findById(objConcoursTCIDto.getId()).orElseThrow();
            concours.setSessionCTCI(objConcoursTCIDto.getSessionCTCI());
            concours.setDescriptionCTCI(objConcoursTCIDto.getDescriptionCTCI());
            concours.setDateDebutConcours(objConcoursTCIDto.getDateDebutConcours());
            concours.setDateFinConcours(objConcoursTCIDto.getDateFinConcours());
            concourstciRepository.save(concours);

            for (CentreConcoursTCIDto centre : objCentreConcoursTCIDto ){

                Centreconcourstci centreconcours = new Centreconcourstci(centre.getNomCentreCTCI(),centre.getCodePostale(),concours,centre.getIdPersonne());
                if (centre.getId_centreCTCI() == null) {
                    System.out.println("CREATION DU NOUVEAU CENTRE ");

                    Personne adjoint = personneRepository.findByNomAndPrenoms(centre.getNomAdjoint(), centre.getPrenomAdjoint()).orElse(null);
                    Personne adjointNew = new Personne();
                    if (adjoint == null){
                        System.out.println("NOUVEAU ADJOINT");
                        adjointNew.setNom(centre.getNomAdjoint());
                        adjointNew.setPrenoms(centre.getPrenomAdjoint());
                        personneRepository.save(adjointNew);
                        System.out.println(" AJOUT ADJOINT OK id : " + adjointNew.getId());
                    }else {
                        System.out.println(" ADJOINT EXIST DEJA id : " + adjoint.getId());
                    }

                    Personne adjointExist = personneRepository.findByNomAndPrenoms(centre.getNomAdjoint(), centre.getPrenomAdjoint()).orElse(null);

                    centreconcours.setNomCentreCTCI(centre.getNomCentreCTCI());
                    centreconcours.setCodePostale(centre.getCodePostale());
                    centreconcours.setIdCTCIINT(concours.getId());
                    centreconcours.setIdPersonneAdjoit(adjointExist);
                    Integer id_chefDeCentre = Integer.valueOf(String.valueOf(findPersonIdByFullName(centre.getNom(), centre.getPrenoms())));

                    if (id_chefDeCentre != null){
                        centreconcours.setIdPersonneID(id_chefDeCentre);
                    }

                    centreconcourstciRepository.save(centreconcours);
                    System.out.println("CREATION NOUVEAU CONCOUR ET CENTRE ET ADJOINT OK");

                }
                else {
                    System.out.println("CENTRE EXIST MAIS MODIFIER ");
                    Centreconcourstci centreCTCIExist = centreconcourstciRepository.findById(centre.getId_centreCTCI()).orElseThrow();

                    Personne adjointModifier = personneRepository.findById(centreCTCIExist.getIdPersonneAdjoit().getId()).orElseThrow();

                    adjointModifier.setNom(centre.getNomAdjoint());
                    adjointModifier.setPrenoms(centre.getPrenomAdjoint());
                    personneRepository.save(adjointModifier);

                    Centreconcourstci centre1 = centreconcourstciRepository.findById(centre.getId_centreCTCI()).orElseThrow();
                    centre1.setNomCentreCTCI(centre.getNomCentreCTCI());
                    centre1.setCodePostale(centre.getCodePostale());
                    centre1.getIdPersonne().setNom(centre.getNom());
                    centre1.getIdPersonne().setPrenoms(centre.getPrenoms());
                    centre1.getIdPersonne().setTelephone(centre.getTelephone());
                    centre1.setIdPersonneAdjoit(adjointModifier);

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
        return personOptional.map(personne -> personne.getId()).orElse(null);
    }
    public List<ConcourstciView> getConcoursList() {
        return concourstciRepository1.findAllBy();
    }

    public ConcourstciInfo getConcoursById(Integer numero) {
        return concourstciRepository1.findConcourstciInfoById(numero);
    }

}
