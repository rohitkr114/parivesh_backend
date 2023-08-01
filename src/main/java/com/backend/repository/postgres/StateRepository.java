package com.backend.repository.postgres;

import com.backend.dto.UserStateDetailsDto;
import com.backend.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Query("SELECT s from State s where s.is_active='true' and s.is_deleted='false' order by name")
    List<State> findAllStates();

    @Query("SELECT s from State s where s.is_active=cast(?1 as boolean) and s.is_deleted=cast(?2 as boolean) order by name")
    List<State> findAllStatesByStatus(String active, String delete);

    @Query("SELECT s from State s where s.is_active='true' and s.is_deleted='false' and s.id=?1 order by name")
    State getStateById(Integer id);

    @Query("SELECT s from State s where s.is_active='true' and s.is_deleted='false' and s.code=?1 order by name")
    Optional<State> getStateByCode(Integer code);

    @Query("SELECT s from State s where s.is_active='true' and s.is_deleted='false' and s.code=?1 order by name")
    State getByStateCode(Integer code);

    @Query(value = "SELECT s.name from master.state s where s.is_active='true' and s.is_deleted='false' and s.code=?1 order by s.name", nativeQuery = true)
    String getNameByStateCode(Integer code);

    @Query(value = " select * from master.get_user_state_details(:entityid); ", nativeQuery = true)
    List<UserStateDetailsDto> getUserStateDetails(Integer entityid);

//	@Query(value = "Select s.code from state s where s.name =?1", nativeQuery = true)
//	public Integer getStateAbbrByName (String stateName);

    @Query(value = "select distinct s.* from master.state s left join ua.office_state os on s.id = os.state_id left join ua.office_entity oe on oe.entityid = os.office_id  where oe.office_type= 6 order by s.name;", nativeQuery = true)
    List<State> findCrzStates();

}
