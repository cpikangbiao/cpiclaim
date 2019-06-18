package com.cpi.claim.web.rest;

import com.cpi.claim.domain.StatisticsCaseMonthCount;
import com.cpi.claim.repository.StatisticsCaseMonthCountRepository;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cpi.claim.domain.StatisticsCaseMonthCount}.
 */
@RestController
@RequestMapping("/api")
public class StatisticsCaseMonthCountResource {

    private final Logger log = LoggerFactory.getLogger(StatisticsCaseMonthCountResource.class);

    private static final String ENTITY_NAME = "cpiclaimStatisticsCaseMonthCount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatisticsCaseMonthCountRepository statisticsCaseMonthCountRepository;

    public StatisticsCaseMonthCountResource(StatisticsCaseMonthCountRepository statisticsCaseMonthCountRepository) {
        this.statisticsCaseMonthCountRepository = statisticsCaseMonthCountRepository;
    }

    /**
     * {@code POST  /statistics-case-month-counts} : Create a new statisticsCaseMonthCount.
     *
     * @param statisticsCaseMonthCount the statisticsCaseMonthCount to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statisticsCaseMonthCount, or with status {@code 400 (Bad Request)} if the statisticsCaseMonthCount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statistics-case-month-counts")
    public ResponseEntity<StatisticsCaseMonthCount> createStatisticsCaseMonthCount(@RequestBody StatisticsCaseMonthCount statisticsCaseMonthCount) throws URISyntaxException {
        log.debug("REST request to save StatisticsCaseMonthCount : {}", statisticsCaseMonthCount);
        if (statisticsCaseMonthCount.getId() != null) {
            throw new BadRequestAlertException("A new statisticsCaseMonthCount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatisticsCaseMonthCount result = statisticsCaseMonthCountRepository.save(statisticsCaseMonthCount);
        return ResponseEntity.created(new URI("/api/statistics-case-month-counts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statistics-case-month-counts} : Updates an existing statisticsCaseMonthCount.
     *
     * @param statisticsCaseMonthCount the statisticsCaseMonthCount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statisticsCaseMonthCount,
     * or with status {@code 400 (Bad Request)} if the statisticsCaseMonthCount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statisticsCaseMonthCount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statistics-case-month-counts")
    public ResponseEntity<StatisticsCaseMonthCount> updateStatisticsCaseMonthCount(@RequestBody StatisticsCaseMonthCount statisticsCaseMonthCount) throws URISyntaxException {
        log.debug("REST request to update StatisticsCaseMonthCount : {}", statisticsCaseMonthCount);
        if (statisticsCaseMonthCount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatisticsCaseMonthCount result = statisticsCaseMonthCountRepository.save(statisticsCaseMonthCount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, statisticsCaseMonthCount.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /statistics-case-month-counts} : get all the statisticsCaseMonthCounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statisticsCaseMonthCounts in body.
     */
    @GetMapping("/statistics-case-month-counts")
    public List<StatisticsCaseMonthCount> getAllStatisticsCaseMonthCounts() {
        log.debug("REST request to get all StatisticsCaseMonthCounts");
        return statisticsCaseMonthCountRepository.findAll();
    }

    /**
     * {@code GET  /statistics-case-month-counts/:id} : get the "id" statisticsCaseMonthCount.
     *
     * @param id the id of the statisticsCaseMonthCount to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statisticsCaseMonthCount, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statistics-case-month-counts/{id}")
    public ResponseEntity<StatisticsCaseMonthCount> getStatisticsCaseMonthCount(@PathVariable Long id) {
        log.debug("REST request to get StatisticsCaseMonthCount : {}", id);
        Optional<StatisticsCaseMonthCount> statisticsCaseMonthCount = statisticsCaseMonthCountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(statisticsCaseMonthCount);
    }

    /**
     * {@code DELETE  /statistics-case-month-counts/:id} : delete the "id" statisticsCaseMonthCount.
     *
     * @param id the id of the statisticsCaseMonthCount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statistics-case-month-counts/{id}")
    public ResponseEntity<Void> deleteStatisticsCaseMonthCount(@PathVariable Long id) {
        log.debug("REST request to delete StatisticsCaseMonthCount : {}", id);
        statisticsCaseMonthCountRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
