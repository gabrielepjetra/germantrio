package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.CarModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarModelRepository extends CrudRepository<CarModel, Long> {
    List<CarModel> findByBrandId(long brandId);
}
