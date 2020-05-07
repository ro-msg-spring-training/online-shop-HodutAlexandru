package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.models.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    public Location findByCity(String name);

}
