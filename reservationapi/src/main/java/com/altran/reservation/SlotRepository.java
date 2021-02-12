package com.altran.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
    List<Slot> findByStartTime(int startTime);
    @Query(value="select * from slot where sid not in (select sid from reservation where cid= :cid)",nativeQuery = true)
    List<Slot> availableSlots(@Param("cid")Integer cid);
}
