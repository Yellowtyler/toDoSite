package com.example.toDo.repository;

import com.example.toDo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Transactional
    @Modifying
    @Query("update Project p set p.state = case p.state when true then false" +
            " else true end where p.id = :id")
    void updateStateProject(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from Project p where p.id = :id")
    void deleteProject(@Param("id") Long id);

    @Query("select p from Project p where p.state = true and p.user.id = :id")
    List<Project> getUserProjectsById(@Param("id") Long id);

    @Query("select p from Project p where p.user.id = :id")
    List<Project> getUserHiddenProjectsById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.name = :name where p.id = :id")
    void updateName(@Param("name") String name, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.descr = :descr where p.id = :id")
    void updateDescr(@Param("descr") String descr, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.date = :date where p.id = :id")
    void updateDate(@Param("date") LocalDateTime date, @Param("id") Long id);
}
