package com.appli.springjwt.repository;

import com.appli.springjwt.models.Concourstci;
import com.appli.springjwt.view.ConcourstciInfo;
import com.appli.springjwt.view.ConcourstciView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcourstciRepository1 extends JpaRepository<Concourstci, Integer> {
   // @EntityGraph(value = "Concours.sansDes", type = EntityGraph.EntityGraphType.FETCH)

    List<ConcourstciView> findAllBy();
    ConcourstciInfo findConcourstciInfoById(Integer id);


}