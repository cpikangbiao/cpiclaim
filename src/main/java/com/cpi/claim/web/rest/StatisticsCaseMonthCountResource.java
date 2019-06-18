package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.domain.StatisticsCaseMonthCount;
import com.cpi.claim.repository.StatisticsCaseMonthCountRepository;
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
 * REST controller for managing StatisticsCaseMonthCount.
 */
@RestController
@RequestMapping("/api")
public class StatisticsCaseMonthCountResource {

    private final Logger log = LoggerFactory.getLogger(StatisticsCaseMonthCountResource.class);

    private static final String ENTITY_NAME = "statisticsCaseMonthCount";

    private final StatisticsCaseMonthCountRepository statisticsCaseMonthCountRepository;

    public StatisticsCaseMonthCountResource(StatisticsCaseMonthCountRepository statisticsCaseMonthCountRepository) {
        this.statisticsCaseMonthCountRepository = statisticsCaseMonthCountRepository;
    }

    /**
     * POST  /statistics-case-month-counts : Create a new statisticsCaseMonthCount.
     *
     * @param statisticsCaseMonthCount the statisticsCaseMonthCount to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statisticsCaseMonthCount, or with status 400 (Bad Request) if the statisticsCaseMonthCount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/statistics-case-month-counts")
    @Timed
    public ResponseEntity<StatisticsCaseMonthCount> createStatisticsCaseMonthCount(@RequestBody StatisticsCaseMonthCount statisticsCaseMonthCount) throws URISyntaxException {
        log.debug("REST request to save StatisticsCaseMonthCount : {}", statisticsCaseMonthCount);
        if (statisticsCaseMonthCount.getId() != null) {
            throw new BadRequestAlertException("A new statisticsCaseMonthCount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatisticsCaseMonthCount result = statisticsCaseMonthCountRepository.save(statisticsCaseMonthCount);
        return ResponseEntity.created(new URI("/api/statistics-case-month-counts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /statistics-case-month-counts : Updates an existing statisticsCaseMonthCount.
     *
     * @param statisticsCaseMonthCount the statisticsCaseMonthCount to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statisticsCaseMonthCount,
     * or with status 400 (Bad Request) if the statisticsCaseMonthCount is not valid,
     * or with status 500 (Internal Server Error) if the statisticsCaseMonthCount couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/statistics-case-month-counts")
    @Timed
    public ResponseEntity<StatisticsCaseMonthCount> updateStatisticsCaseMonthCount(@RequestBody StatisticsCaseMonthCount statisticsCaseMonthCount) throws URISyntaxException {
        log.debug("REST request to update StatisticsCaseMonthCount : {}", statisticsCaseMonthCount);
        if (statisticsCaseMonthCount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatisticsCaseMonthCount result = statisticsCaseMonthCountRepository.save(statisticsCaseMonthCount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statisticsCaseMonthCount.getId().toString()))
            .body(result);
    }

    /**
     * GET  /statistics-case-month-counts : get all the statisticsCaseMonthCounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of statisticsCaseMonthCounts in body
     */
    @GetMapping("/statistics-case-month-counts")
    @Timed
    public List<StatisticsCaseMonthCount> getAllStatisticsCaseMonthCounts() {
        log.debug("REST request to get all StatisticsCaseMonthCounts");
        return statisticsCaseMonthCountRepository.findAll();
    }

    /**
     * GET  /statistics-case-month-counts/:id : get the "id" statisticsCaseMonthCount.
     *
     * @param id the id of the statisticsCaseMonthCount to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statisticsCaseMonthCount, or with status 404 (Not Found)
     */
    @GetMapping("/statistics-case-month-counts/{id}")
    @Timed
    public ResponseEntity<StatisticsCaseMonthCount> getStatisticsCaseMonthCount(@PathVariable Long id) {
        log.debug("REST request to get StatisticsCaseMonthCount : {}", id);
        Optional<StatisticsCaseMonthCount> statisticsCaseMonthCount = statisticsCaseMonthCountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(statisticsCaseMonthCount);
    }

    /**
     * DELETE  /statistics-case-month-counts/:id : delete the "id" statisticsCaseMonthCount.
     *
     * @param id the id of the statisticsCaseMonthCount to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/statistics-case-month-counts/{id}")
    @Timed
    public ResponseEntity<Void> deleteStatisticsCaseMonthCount(@PathVariable Long id) {
        log.debug("REST request to delete StatisticsCaseMonthCount : {}", id);

        statisticsCaseMonthCountRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
