package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.domain.StatisticsCaseYearCount;
import com.cpi.claim.repository.StatisticsCaseYearCountRepository;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StatisticsCaseYearCount.
 */
@RestController
@RequestMapping("/api")
public class StatisticsCaseYearCountResource {

    private final Logger log = LoggerFactory.getLogger(StatisticsCaseYearCountResource.class);

    private static final String ENTITY_NAME = "statisticsCaseYearCount";

    private final StatisticsCaseYearCountRepository statisticsCaseYearCountRepository;

    public StatisticsCaseYearCountResource(StatisticsCaseYearCountRepository statisticsCaseYearCountRepository) {
        this.statisticsCaseYearCountRepository = statisticsCaseYearCountRepository;
    }

    /**
     * POST  /statistics-case-year-counts : Create a new statisticsCaseYearCount.
     *
     * @param statisticsCaseYearCount the statisticsCaseYearCount to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statisticsCaseYearCount, or with status 400 (Bad Request) if the statisticsCaseYearCount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/statistics-case-year-counts")
    @Timed
    public ResponseEntity<StatisticsCaseYearCount> createStatisticsCaseYearCount(@RequestBody StatisticsCaseYearCount statisticsCaseYearCount) throws URISyntaxException {
        log.debug("REST request to save StatisticsCaseYearCount : {}", statisticsCaseYearCount);
        if (statisticsCaseYearCount.getId() != null) {
            throw new BadRequestAlertException("A new statisticsCaseYearCount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatisticsCaseYearCount result = statisticsCaseYearCountRepository.save(statisticsCaseYearCount);
        return ResponseEntity.created(new URI("/api/statistics-case-year-counts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /statistics-case-year-counts : Updates an existing statisticsCaseYearCount.
     *
     * @param statisticsCaseYearCount the statisticsCaseYearCount to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statisticsCaseYearCount,
     * or with status 400 (Bad Request) if the statisticsCaseYearCount is not valid,
     * or with status 500 (Internal Server Error) if the statisticsCaseYearCount couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/statistics-case-year-counts")
    @Timed
    public ResponseEntity<StatisticsCaseYearCount> updateStatisticsCaseYearCount(@RequestBody StatisticsCaseYearCount statisticsCaseYearCount) throws URISyntaxException {
        log.debug("REST request to update StatisticsCaseYearCount : {}", statisticsCaseYearCount);
        if (statisticsCaseYearCount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatisticsCaseYearCount result = statisticsCaseYearCountRepository.save(statisticsCaseYearCount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statisticsCaseYearCount.getId().toString()))
            .body(result);
    }

    /**
     * GET  /statistics-case-year-counts : get all the statisticsCaseYearCounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of statisticsCaseYearCounts in body
     */
    @GetMapping("/statistics-case-year-counts")
    @Timed
    public List<StatisticsCaseYearCount> getAllStatisticsCaseYearCounts() {
        log.debug("REST request to get all StatisticsCaseYearCounts");
        return statisticsCaseYearCountRepository.findAll();
    }

    /**
     * GET  /statistics-case-year-counts/:id : get the "id" statisticsCaseYearCount.
     *
     * @param id the id of the statisticsCaseYearCount to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statisticsCaseYearCount, or with status 404 (Not Found)
     */
    @GetMapping("/statistics-case-year-counts/{id}")
    @Timed
    public ResponseEntity<StatisticsCaseYearCount> getStatisticsCaseYearCount(@PathVariable Long id) {
        log.debug("REST request to get StatisticsCaseYearCount : {}", id);
        Optional<StatisticsCaseYearCount> statisticsCaseYearCount = statisticsCaseYearCountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(statisticsCaseYearCount);
    }

    /**
     * DELETE  /statistics-case-year-counts/:id : delete the "id" statisticsCaseYearCount.
     *
     * @param id the id of the statisticsCaseYearCount to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/statistics-case-year-counts/{id}")
    @Timed
    public ResponseEntity<Void> deleteStatisticsCaseYearCount(@PathVariable Long id) {
        log.debug("REST request to delete StatisticsCaseYearCount : {}", id);

        statisticsCaseYearCountRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
