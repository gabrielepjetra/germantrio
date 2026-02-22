package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.CarModel;
import org.springframework.data.repository.CrudRepository;

public interface CarModelRepository extends CrudRepository<CarModel, Long> {
}
