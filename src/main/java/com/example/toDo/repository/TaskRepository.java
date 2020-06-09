package com.example.toDo.repository;

import com.example.toDo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.project.id = :id")
    List<Task> getTasksByProjectId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from Task t where t.id = :id")
    void deleteTask(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Task t set t.name = :name where t.id = :id")
    void updateName(@Param("name") String name, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Task t set t.descr = :descr where t.id = :id")
    void updateDescr(@Param("descr") String descr, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Task t set t.date = :date where t.id = :id")
    void updateDate(@Param("date") LocalDateTime date, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Task t set t.type = case t.type when 'today' then 'future'" +
            " else 'today' end where t.id = :id")
    void updateType(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Task t set t.state = case t.state when true then false" +
            " else true end where t.id = :id")
    void updateState(@Param("id") Long id);

}
