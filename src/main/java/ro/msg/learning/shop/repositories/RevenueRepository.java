package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.models.entities.Revenue;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    public List<Revenue> findAllByDate(LocalDate date);

}
