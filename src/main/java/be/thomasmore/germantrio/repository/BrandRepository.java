package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {
}
