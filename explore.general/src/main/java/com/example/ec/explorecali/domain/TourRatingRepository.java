package com.example.ec.explorecali.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    /**
     * @param tourId
     * @return
     */
    List<TourRating> findByPkTourId(Integer tourId);

    /**
     * @param tourId
     * @param customerId
     * @return
     */
    TourRating findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
}
