package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.PaymentTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.PaymentTypeDTO;
import com.cpi.claim.service.dto.PaymentTypeCriteria;
import com.cpi.claim.service.PaymentTypeQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PaymentType.
 */
@RestController
@RequestMapping("/api")
public class PaymentTypeResource {

    private final Logger log = LoggerFactory.getLogger(PaymentTypeResource.class);

    private static final String ENTITY_NAME = "paymentType";

    private final PaymentTypeService paymentTypeService;

    private final PaymentTypeQueryService paymentTypeQueryService;

    public PaymentTypeResource(PaymentTypeService paymentTypeService, PaymentTypeQueryService paymentTypeQueryService) {
        this.paymentTypeService = paymentTypeService;
        this.paymentTypeQueryService = paymentTypeQueryService;
    }

    /**
     * POST  /payment-types : Create a new paymentType.
     *
     * @param paymentTypeDTO the paymentTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paymentTypeDTO, or with status 400 (Bad Request) if the paymentType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/payment-types")
    @Timed
    public ResponseEntity<PaymentTypeDTO> createPaymentType(@Valid @RequestBody PaymentTypeDTO paymentTypeDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentType : {}", paymentTypeDTO);
        if (paymentTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentTypeDTO result = paymentTypeService.save(paymentTypeDTO);
        return ResponseEntity.created(new URI("/api/payment-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /payment-types : Updates an existing paymentType.
     *
     * @param paymentTypeDTO the paymentTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paymentTypeDTO,
     * or with status 400 (Bad Request) if the paymentTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the paymentTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/payment-types")
    @Timed
    public ResponseEntity<PaymentTypeDTO> updatePaymentType(@Valid @RequestBody PaymentTypeDTO paymentTypeDTO) throws URISyntaxException {
        log.debug("REST request to update PaymentType : {}", paymentTypeDTO);
        if (paymentTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentTypeDTO result = paymentTypeService.save(paymentTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paymentTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /payment-types : get all the paymentTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of paymentTypes in body
     */
    @GetMapping("/payment-types")
    @Timed
    public ResponseEntity<List<PaymentTypeDTO>> getAllPaymentTypes(PaymentTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PaymentTypes by criteria: {}", criteria);
        Page<PaymentTypeDTO> page = paymentTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/payment-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /payment-types/:id : get the "id" paymentType.
     *
     * @param id the id of the paymentTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the paymentTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/payment-types/{id}")
    @Timed
    public ResponseEntity<PaymentTypeDTO> getPaymentType(@PathVariable Long id) {
        log.debug("REST request to get PaymentType : {}", id);
        Optional<PaymentTypeDTO> paymentTypeDTO = paymentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentTypeDTO);
    }

    /**
     * DELETE  /payment-types/:id : delete the "id" paymentType.
     *
     * @param id the id of the paymentTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/payment-types/{id}")
    @Timed
    public ResponseEntity<Void> deletePaymentType(@PathVariable Long id) {
        log.debug("REST request to delete PaymentType : {}", id);
        paymentTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
