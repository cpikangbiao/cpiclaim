/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.CaseEstimate;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.repository.CaseEstimateRepository;
import com.cpi.claim.service.CaseEstimateService;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.mapper.CaseEstimateMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseEstimateCriteria;
import com.cpi.claim.service.CaseEstimateQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CaseEstimateResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseEstimateResourceIT {

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Long DEFAULT_REGISTER_USER_ID = 1L;
    private static final Long UPDATED_REGISTER_USER_ID = 2L;

    private static final Instant DEFAULT_ESTIMATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ESTIMATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_ESTIMATE_ENTITY_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ESTIMATE_ENTITY_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ESTIMATE_COST_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ESTIMATE_COST_FEE = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private CaseEstimateRepository caseEstimateRepository;

    @Autowired
    private CaseEstimateMapper caseEstimateMapper;

    @Autowired
    private CaseEstimateService caseEstimateService;

    @Autowired
    private CaseEstimateQueryService caseEstimateQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCaseEstimateMockMvc;

    private CaseEstimate caseEstimate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseEstimateResource caseEstimateResource = new CaseEstimateResource(caseEstimateService, caseEstimateQueryService);
        this.restCaseEstimateMockMvc = MockMvcBuilders.standaloneSetup(caseEstimateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseEstimate createEntity(EntityManager em) {
        CaseEstimate caseEstimate = new CaseEstimate()
            .numberId(DEFAULT_NUMBER_ID)
            .registerUserId(DEFAULT_REGISTER_USER_ID)
            .estimateDate(DEFAULT_ESTIMATE_DATE)
            .estimateEntityFee(DEFAULT_ESTIMATE_ENTITY_FEE)
            .estimateCostFee(DEFAULT_ESTIMATE_COST_FEE)
            .remark(DEFAULT_REMARK);
        return caseEstimate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseEstimate createUpdatedEntity(EntityManager em) {
        CaseEstimate caseEstimate = new CaseEstimate()
            .numberId(UPDATED_NUMBER_ID)
            .registerUserId(UPDATED_REGISTER_USER_ID)
            .estimateDate(UPDATED_ESTIMATE_DATE)
            .estimateEntityFee(UPDATED_ESTIMATE_ENTITY_FEE)
            .estimateCostFee(UPDATED_ESTIMATE_COST_FEE)
            .remark(UPDATED_REMARK);
        return caseEstimate;
    }

    @BeforeEach
    public void initTest() {
        caseEstimate = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseEstimate() throws Exception {
        int databaseSizeBeforeCreate = caseEstimateRepository.findAll().size();

        // Create the CaseEstimate
        CaseEstimateDTO caseEstimateDTO = caseEstimateMapper.toDto(caseEstimate);
        restCaseEstimateMockMvc.perform(post("/api/case-estimates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseEstimateDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseEstimate in the database
        List<CaseEstimate> caseEstimateList = caseEstimateRepository.findAll();
        assertThat(caseEstimateList).hasSize(databaseSizeBeforeCreate + 1);
        CaseEstimate testCaseEstimate = caseEstimateList.get(caseEstimateList.size() - 1);
        assertThat(testCaseEstimate.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseEstimate.getRegisterUserId()).isEqualTo(DEFAULT_REGISTER_USER_ID);
        assertThat(testCaseEstimate.getEstimateDate()).isEqualTo(DEFAULT_ESTIMATE_DATE);
        assertThat(testCaseEstimate.getEstimateEntityFee()).isEqualTo(DEFAULT_ESTIMATE_ENTITY_FEE);
        assertThat(testCaseEstimate.getEstimateCostFee()).isEqualTo(DEFAULT_ESTIMATE_COST_FEE);
        assertThat(testCaseEstimate.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createCaseEstimateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseEstimateRepository.findAll().size();

        // Create the CaseEstimate with an existing ID
        caseEstimate.setId(1L);
        CaseEstimateDTO caseEstimateDTO = caseEstimateMapper.toDto(caseEstimate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseEstimateMockMvc.perform(post("/api/case-estimates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseEstimateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseEstimate in the database
        List<CaseEstimate> caseEstimateList = caseEstimateRepository.findAll();
        assertThat(caseEstimateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaseEstimates() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList
        restCaseEstimateMockMvc.perform(get("/api/case-estimates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseEstimate.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].registerUserId").value(hasItem(DEFAULT_REGISTER_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].estimateDate").value(hasItem(DEFAULT_ESTIMATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].estimateEntityFee").value(hasItem(DEFAULT_ESTIMATE_ENTITY_FEE.intValue())))
            .andExpect(jsonPath("$.[*].estimateCostFee").value(hasItem(DEFAULT_ESTIMATE_COST_FEE.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getCaseEstimate() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get the caseEstimate
        restCaseEstimateMockMvc.perform(get("/api/case-estimates/{id}", caseEstimate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseEstimate.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.registerUserId").value(DEFAULT_REGISTER_USER_ID.intValue()))
            .andExpect(jsonPath("$.estimateDate").value(DEFAULT_ESTIMATE_DATE.toString()))
            .andExpect(jsonPath("$.estimateEntityFee").value(DEFAULT_ESTIMATE_ENTITY_FEE.intValue()))
            .andExpect(jsonPath("$.estimateCostFee").value(DEFAULT_ESTIMATE_COST_FEE.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseEstimateShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseEstimateList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseEstimateShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseEstimateShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseEstimateList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseEstimateShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where numberId is not null
        defaultCaseEstimateShouldBeFound("numberId.specified=true");

        // Get all the caseEstimateList where numberId is null
        defaultCaseEstimateShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCaseEstimateShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseEstimateList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCaseEstimateShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCaseEstimateShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseEstimateList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCaseEstimateShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseEstimatesByRegisterUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where registerUserId equals to DEFAULT_REGISTER_USER_ID
        defaultCaseEstimateShouldBeFound("registerUserId.equals=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseEstimateList where registerUserId equals to UPDATED_REGISTER_USER_ID
        defaultCaseEstimateShouldNotBeFound("registerUserId.equals=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByRegisterUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where registerUserId in DEFAULT_REGISTER_USER_ID or UPDATED_REGISTER_USER_ID
        defaultCaseEstimateShouldBeFound("registerUserId.in=" + DEFAULT_REGISTER_USER_ID + "," + UPDATED_REGISTER_USER_ID);

        // Get all the caseEstimateList where registerUserId equals to UPDATED_REGISTER_USER_ID
        defaultCaseEstimateShouldNotBeFound("registerUserId.in=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByRegisterUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where registerUserId is not null
        defaultCaseEstimateShouldBeFound("registerUserId.specified=true");

        // Get all the caseEstimateList where registerUserId is null
        defaultCaseEstimateShouldNotBeFound("registerUserId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByRegisterUserIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where registerUserId greater than or equals to DEFAULT_REGISTER_USER_ID
        defaultCaseEstimateShouldBeFound("registerUserId.greaterOrEqualThan=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseEstimateList where registerUserId greater than or equals to UPDATED_REGISTER_USER_ID
        defaultCaseEstimateShouldNotBeFound("registerUserId.greaterOrEqualThan=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByRegisterUserIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where registerUserId less than or equals to DEFAULT_REGISTER_USER_ID
        defaultCaseEstimateShouldNotBeFound("registerUserId.lessThan=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseEstimateList where registerUserId less than or equals to UPDATED_REGISTER_USER_ID
        defaultCaseEstimateShouldBeFound("registerUserId.lessThan=" + UPDATED_REGISTER_USER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateDate equals to DEFAULT_ESTIMATE_DATE
        defaultCaseEstimateShouldBeFound("estimateDate.equals=" + DEFAULT_ESTIMATE_DATE);

        // Get all the caseEstimateList where estimateDate equals to UPDATED_ESTIMATE_DATE
        defaultCaseEstimateShouldNotBeFound("estimateDate.equals=" + UPDATED_ESTIMATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateDate in DEFAULT_ESTIMATE_DATE or UPDATED_ESTIMATE_DATE
        defaultCaseEstimateShouldBeFound("estimateDate.in=" + DEFAULT_ESTIMATE_DATE + "," + UPDATED_ESTIMATE_DATE);

        // Get all the caseEstimateList where estimateDate equals to UPDATED_ESTIMATE_DATE
        defaultCaseEstimateShouldNotBeFound("estimateDate.in=" + UPDATED_ESTIMATE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateDate is not null
        defaultCaseEstimateShouldBeFound("estimateDate.specified=true");

        // Get all the caseEstimateList where estimateDate is null
        defaultCaseEstimateShouldNotBeFound("estimateDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateEntityFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateEntityFee equals to DEFAULT_ESTIMATE_ENTITY_FEE
        defaultCaseEstimateShouldBeFound("estimateEntityFee.equals=" + DEFAULT_ESTIMATE_ENTITY_FEE);

        // Get all the caseEstimateList where estimateEntityFee equals to UPDATED_ESTIMATE_ENTITY_FEE
        defaultCaseEstimateShouldNotBeFound("estimateEntityFee.equals=" + UPDATED_ESTIMATE_ENTITY_FEE);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateEntityFeeIsInShouldWork() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateEntityFee in DEFAULT_ESTIMATE_ENTITY_FEE or UPDATED_ESTIMATE_ENTITY_FEE
        defaultCaseEstimateShouldBeFound("estimateEntityFee.in=" + DEFAULT_ESTIMATE_ENTITY_FEE + "," + UPDATED_ESTIMATE_ENTITY_FEE);

        // Get all the caseEstimateList where estimateEntityFee equals to UPDATED_ESTIMATE_ENTITY_FEE
        defaultCaseEstimateShouldNotBeFound("estimateEntityFee.in=" + UPDATED_ESTIMATE_ENTITY_FEE);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateEntityFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateEntityFee is not null
        defaultCaseEstimateShouldBeFound("estimateEntityFee.specified=true");

        // Get all the caseEstimateList where estimateEntityFee is null
        defaultCaseEstimateShouldNotBeFound("estimateEntityFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateCostFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateCostFee equals to DEFAULT_ESTIMATE_COST_FEE
        defaultCaseEstimateShouldBeFound("estimateCostFee.equals=" + DEFAULT_ESTIMATE_COST_FEE);

        // Get all the caseEstimateList where estimateCostFee equals to UPDATED_ESTIMATE_COST_FEE
        defaultCaseEstimateShouldNotBeFound("estimateCostFee.equals=" + UPDATED_ESTIMATE_COST_FEE);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateCostFeeIsInShouldWork() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateCostFee in DEFAULT_ESTIMATE_COST_FEE or UPDATED_ESTIMATE_COST_FEE
        defaultCaseEstimateShouldBeFound("estimateCostFee.in=" + DEFAULT_ESTIMATE_COST_FEE + "," + UPDATED_ESTIMATE_COST_FEE);

        // Get all the caseEstimateList where estimateCostFee equals to UPDATED_ESTIMATE_COST_FEE
        defaultCaseEstimateShouldNotBeFound("estimateCostFee.in=" + UPDATED_ESTIMATE_COST_FEE);
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesByEstimateCostFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        // Get all the caseEstimateList where estimateCostFee is not null
        defaultCaseEstimateShouldBeFound("estimateCostFee.specified=true");

        // Get all the caseEstimateList where estimateCostFee is null
        defaultCaseEstimateShouldNotBeFound("estimateCostFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseEstimatesBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIT.createEntity(em);
        em.persist(subcase);
        em.flush();
        caseEstimate.setSubcase(subcase);
        caseEstimateRepository.saveAndFlush(caseEstimate);
        Long subcaseId = subcase.getId();

        // Get all the caseEstimateList where subcase equals to subcaseId
        defaultCaseEstimateShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the caseEstimateList where subcase equals to subcaseId + 1
        defaultCaseEstimateShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCaseEstimateShouldBeFound(String filter) throws Exception {
        restCaseEstimateMockMvc.perform(get("/api/case-estimates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseEstimate.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].registerUserId").value(hasItem(DEFAULT_REGISTER_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].estimateDate").value(hasItem(DEFAULT_ESTIMATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].estimateEntityFee").value(hasItem(DEFAULT_ESTIMATE_ENTITY_FEE.intValue())))
            .andExpect(jsonPath("$.[*].estimateCostFee").value(hasItem(DEFAULT_ESTIMATE_COST_FEE.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));

        // Check, that the count call also returns 1
        restCaseEstimateMockMvc.perform(get("/api/case-estimates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCaseEstimateShouldNotBeFound(String filter) throws Exception {
        restCaseEstimateMockMvc.perform(get("/api/case-estimates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCaseEstimateMockMvc.perform(get("/api/case-estimates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCaseEstimate() throws Exception {
        // Get the caseEstimate
        restCaseEstimateMockMvc.perform(get("/api/case-estimates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseEstimate() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        int databaseSizeBeforeUpdate = caseEstimateRepository.findAll().size();

        // Update the caseEstimate
        CaseEstimate updatedCaseEstimate = caseEstimateRepository.findById(caseEstimate.getId()).get();
        // Disconnect from session so that the updates on updatedCaseEstimate are not directly saved in db
        em.detach(updatedCaseEstimate);
        updatedCaseEstimate
            .numberId(UPDATED_NUMBER_ID)
            .registerUserId(UPDATED_REGISTER_USER_ID)
            .estimateDate(UPDATED_ESTIMATE_DATE)
            .estimateEntityFee(UPDATED_ESTIMATE_ENTITY_FEE)
            .estimateCostFee(UPDATED_ESTIMATE_COST_FEE)
            .remark(UPDATED_REMARK);
        CaseEstimateDTO caseEstimateDTO = caseEstimateMapper.toDto(updatedCaseEstimate);

        restCaseEstimateMockMvc.perform(put("/api/case-estimates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseEstimateDTO)))
            .andExpect(status().isOk());

        // Validate the CaseEstimate in the database
        List<CaseEstimate> caseEstimateList = caseEstimateRepository.findAll();
        assertThat(caseEstimateList).hasSize(databaseSizeBeforeUpdate);
        CaseEstimate testCaseEstimate = caseEstimateList.get(caseEstimateList.size() - 1);
        assertThat(testCaseEstimate.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseEstimate.getRegisterUserId()).isEqualTo(UPDATED_REGISTER_USER_ID);
        assertThat(testCaseEstimate.getEstimateDate()).isEqualTo(UPDATED_ESTIMATE_DATE);
        assertThat(testCaseEstimate.getEstimateEntityFee()).isEqualTo(UPDATED_ESTIMATE_ENTITY_FEE);
        assertThat(testCaseEstimate.getEstimateCostFee()).isEqualTo(UPDATED_ESTIMATE_COST_FEE);
        assertThat(testCaseEstimate.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseEstimate() throws Exception {
        int databaseSizeBeforeUpdate = caseEstimateRepository.findAll().size();

        // Create the CaseEstimate
        CaseEstimateDTO caseEstimateDTO = caseEstimateMapper.toDto(caseEstimate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseEstimateMockMvc.perform(put("/api/case-estimates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseEstimateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseEstimate in the database
        List<CaseEstimate> caseEstimateList = caseEstimateRepository.findAll();
        assertThat(caseEstimateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseEstimate() throws Exception {
        // Initialize the database
        caseEstimateRepository.saveAndFlush(caseEstimate);

        int databaseSizeBeforeDelete = caseEstimateRepository.findAll().size();

        // Delete the caseEstimate
        restCaseEstimateMockMvc.perform(delete("/api/case-estimates/{id}", caseEstimate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CaseEstimate> caseEstimateList = caseEstimateRepository.findAll();
        assertThat(caseEstimateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseEstimate.class);
        CaseEstimate caseEstimate1 = new CaseEstimate();
        caseEstimate1.setId(1L);
        CaseEstimate caseEstimate2 = new CaseEstimate();
        caseEstimate2.setId(caseEstimate1.getId());
        assertThat(caseEstimate1).isEqualTo(caseEstimate2);
        caseEstimate2.setId(2L);
        assertThat(caseEstimate1).isNotEqualTo(caseEstimate2);
        caseEstimate1.setId(null);
        assertThat(caseEstimate1).isNotEqualTo(caseEstimate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseEstimateDTO.class);
        CaseEstimateDTO caseEstimateDTO1 = new CaseEstimateDTO();
        caseEstimateDTO1.setId(1L);
        CaseEstimateDTO caseEstimateDTO2 = new CaseEstimateDTO();
        assertThat(caseEstimateDTO1).isNotEqualTo(caseEstimateDTO2);
        caseEstimateDTO2.setId(caseEstimateDTO1.getId());
        assertThat(caseEstimateDTO1).isEqualTo(caseEstimateDTO2);
        caseEstimateDTO2.setId(2L);
        assertThat(caseEstimateDTO1).isNotEqualTo(caseEstimateDTO2);
        caseEstimateDTO1.setId(null);
        assertThat(caseEstimateDTO1).isNotEqualTo(caseEstimateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseEstimateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseEstimateMapper.fromId(null)).isNull();
    }
}
