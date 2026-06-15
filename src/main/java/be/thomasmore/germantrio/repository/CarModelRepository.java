package be.thomasmore.germantrio.repository;

import be.thomasmore.germantrio.model.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends CrudRepository<CarModel, Long> {
    List<CarModel> findByBrandId(long brandId);

    Optional<CarModel> findFirstByBrandIdAndIdLessThanOrderByIdDesc(long brandId, long id);

    Optional<CarModel> findFirstByBrandIdAndIdGreaterThanOrderByIdAsc(long brandId, long id);

    @Query("""
            select c from CarModel c
            where c.brand.id = :brandId
              and (:engineType is null or c.engineType = :engineType)
              and (:drivetrain is null or c.drivetrain = :drivetrain)
              and (:transmission is null or c.transmission = :transmission)
              and (:minHorsepower is null or c.horsepower >= :minHorsepower)
              and (:maxBasePrice is null or c.basePriceEur <= :maxBasePrice)
              and (:maxZeroToHundred is null or c.zeroToHundred <= :maxZeroToHundred)
            """)
    List<CarModel> findByBrandIdWithFilters(@Param("brandId") long brandId,
                                             @Param("engineType") String engineType,
                                             @Param("drivetrain") String drivetrain,
                                             @Param("transmission") String transmission,
                                             @Param("minHorsepower") Integer minHorsepower,
                                             @Param("maxBasePrice") BigDecimal maxBasePrice,
                                             @Param("maxZeroToHundred") BigDecimal maxZeroToHundred);

    @Query("""
            select distinct c.engineType from CarModel c
            where c.brand.id = :brandId and c.engineType is not null
            order by c.engineType
            """)
    List<String> findDistinctEngineTypesByBrandId(@Param("brandId") long brandId);

    @Query("""
            select distinct c.transmission from CarModel c
            where c.brand.id = :brandId and c.transmission is not null
            order by c.transmission
            """)
    List<String> findDistinctTransmissionsByBrandId(@Param("brandId") long brandId);
}
