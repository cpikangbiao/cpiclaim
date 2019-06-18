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
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.domain.Risk;
import com.cpi.claim.repository.VesselSubCaseRepository;
import com.cpi.claim.service.VesselSubCaseService;
import com.cpi.claim.service.dto.VesselSubCaseDTO;
import com.cpi.claim.service.mapper.VesselSubCaseMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.VesselSubCaseCriteria;
import com.cpi.claim.service.VesselSubCaseQueryService;

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
 * Integration tests for the {@Link VesselSubCaseResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class VesselSubCaseResourceIT {

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Long DEFAULT_ASSIGNED_USER_ID = 1L;
    private static final Long UPDATED_ASSIGNED_USER_ID = 2L;

    private static final Instant DEFAULT_INSERT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSERT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SUBCASE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUBCASE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BL_NO = "AAAAAAAAAA";
    private static final String UPDATED_BL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIMANT = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMANT = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIM_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_AMOUNT = "BBBBBBBBBB";

    private static final Long DEFAULT_CURRENCY = 1L;
    private static final Long UPDATED_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_DEDUCTIBLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCTIBLE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CURRENCY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEDUCT_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCT_DOLLAR = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private VesselSubCaseRepository vesselSubCaseRepository;

    @Autowired
    private VesselSubCaseMapper vesselSubCaseMapper;

    @Autowired
    private VesselSubCaseService vesselSubCaseService;

    @Autowired
    private VesselSubCaseQueryService vesselSubCaseQueryService;

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

    private MockMvc restVesselSubCaseMockMvc;

    private VesselSubCase vesselSubCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VesselSubCaseResource vesselSubCaseResource = new VesselSubCaseResource(vesselSubCaseService, vesselSubCaseQueryService);
        this.restVesselSubCaseMockMvc = MockMvcBuilders.standaloneSetup(vesselSubCaseResource)
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
    public static VesselSubCase createEntity(EntityManager em) {
        VesselSubCase vesselSubCase = new VesselSubCase()
            .numberId(DEFAULT_NUMBER_ID)
            .assignedUserId(DEFAULT_ASSIGNED_USER_ID)
            .insertTime(DEFAULT_INSERT_TIME)
            .subcaseCode(DEFAULT_SUBCASE_CODE)
            .blNo(DEFAULT_BL_NO)
            .claimant(DEFAULT_CLAIMANT)
            .claimAmount(DEFAULT_CLAIM_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .deductible(DEFAULT_DEDUCTIBLE)
            .currencyRate(DEFAULT_CURRENCY_RATE)
            .deductDollar(DEFAULT_DEDUCT_DOLLAR)
            .remark(DEFAULT_REMARK);
        return vesselSubCase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VesselSubCase createUpdatedEntity(EntityManager em) {
        VesselSubCase vesselSubCase = new VesselSubCase()
            .numberId(UPDATED_NUMBER_ID)
            .assignedUserId(UPDATED_ASSIGNED_USER_ID)
            .insertTime(UPDATED_INSERT_TIME)
            .subcaseCode(UPDATED_SUBCASE_CODE)
            .blNo(UPDATED_BL_NO)
            .claimant(UPDATED_CLAIMANT)
            .claimAmount(UPDATED_CLAIM_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .deductible(UPDATED_DEDUCTIBLE)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .deductDollar(UPDATED_DEDUCT_DOLLAR)
            .remark(UPDATED_REMARK);
        return vesselSubCase;
    }

    @BeforeEach
    public void initTest() {
        vesselSubCase = createEntity(em);
    }

    @Test
    @Transactional
    public void createVesselSubCase() throws Exception {
        int databaseSizeBeforeCreate = vesselSubCaseRepository.findAll().size();

        // Create the VesselSubCase
        VesselSubCaseDTO vesselSubCaseDTO = vesselSubCaseMapper.toDto(vesselSubCase);
        restVesselSubCaseMockMvc.perform(post("/api/vessel-sub-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselSubCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the VesselSubCase in the database
        List<VesselSubCase> vesselSubCaseList = vesselSubCaseRepository.findAll();
        assertThat(vesselSubCaseList).hasSize(databaseSizeBeforeCreate + 1);
        VesselSubCase testVesselSubCase = vesselSubCaseList.get(vesselSubCaseList.size() - 1);
        assertThat(testVesselSubCase.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testVesselSubCase.getAssignedUserId()).isEqualTo(DEFAULT_ASSIGNED_USER_ID);
        assertThat(testVesselSubCase.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testVesselSubCase.getSubcaseCode()).isEqualTo(DEFAULT_SUBCASE_CODE);
        assertThat(testVesselSubCase.getBlNo()).isEqualTo(DEFAULT_BL_NO);
        assertThat(testVesselSubCase.getClaimant()).isEqualTo(DEFAULT_CLAIMANT);
        assertThat(testVesselSubCase.getClaimAmount()).isEqualTo(DEFAULT_CLAIM_AMOUNT);
        assertThat(testVesselSubCase.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testVesselSubCase.getDeductible()).isEqualTo(DEFAULT_DEDUCTIBLE);
        assertThat(testVesselSubCase.getCurrencyRate()).isEqualTo(DEFAULT_CURRENCY_RATE);
        assertThat(testVesselSubCase.getDeductDollar()).isEqualTo(DEFAULT_DEDUCT_DOLLAR);
        assertThat(testVesselSubCase.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createVesselSubCaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vesselSubCaseRepository.findAll().size();

        // Create the VesselSubCase with an existing ID
        vesselSubCase.setId(1L);
        VesselSubCaseDTO vesselSubCaseDTO = vesselSubCaseMapper.toDto(vesselSubCase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVesselSubCaseMockMvc.perform(post("/api/vessel-sub-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselSubCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VesselSubCase in the database
        List<VesselSubCase> vesselSubCaseList = vesselSubCaseRepository.findAll();
        assertThat(vesselSubCaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVesselSubCases() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList
        restVesselSubCaseMockMvc.perform(get("/api/vessel-sub-cases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vesselSubCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].assignedUserId").value(hasItem(DEFAULT_ASSIGNED_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].subcaseCode").value(hasItem(DEFAULT_SUBCASE_CODE.toString())))
            .andExpect(jsonPath("$.[*].blNo").value(hasItem(DEFAULT_BL_NO.toString())))
            .andExpect(jsonPath("$.[*].claimant").value(hasItem(DEFAULT_CLAIMANT.toString())))
            .andExpect(jsonPath("$.[*].claimAmount").value(hasItem(DEFAULT_CLAIM_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].deductible").value(hasItem(DEFAULT_DEDUCTIBLE.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].deductDollar").value(hasItem(DEFAULT_DEDUCT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getVesselSubCase() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get the vesselSubCase
        restVesselSubCaseMockMvc.perform(get("/api/vessel-sub-cases/{id}", vesselSubCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vesselSubCase.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.assignedUserId").value(DEFAULT_ASSIGNED_USER_ID.intValue()))
            .andExpect(jsonPath("$.insertTime").value(DEFAULT_INSERT_TIME.toString()))
            .andExpect(jsonPath("$.subcaseCode").value(DEFAULT_SUBCASE_CODE.toString()))
            .andExpect(jsonPath("$.blNo").value(DEFAULT_BL_NO.toString()))
            .andExpect(jsonPath("$.claimant").value(DEFAULT_CLAIMANT.toString()))
            .andExpect(jsonPath("$.claimAmount").value(DEFAULT_CLAIM_AMOUNT.toString()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.intValue()))
            .andExpect(jsonPath("$.deductible").value(DEFAULT_DEDUCTIBLE.intValue()))
            .andExpect(jsonPath("$.currencyRate").value(DEFAULT_CURRENCY_RATE.intValue()))
            .andExpect(jsonPath("$.deductDollar").value(DEFAULT_DEDUCT_DOLLAR.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where numberId equals to DEFAULT_NUMBER_ID
        defaultVesselSubCaseShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the vesselSubCaseList where numberId equals to UPDATED_NUMBER_ID
        defaultVesselSubCaseShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultVesselSubCaseShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the vesselSubCaseList where numberId equals to UPDATED_NUMBER_ID
        defaultVesselSubCaseShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where numberId is not null
        defaultVesselSubCaseShouldBeFound("numberId.specified=true");

        // Get all the vesselSubCaseList where numberId is null
        defaultVesselSubCaseShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultVesselSubCaseShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the vesselSubCaseList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultVesselSubCaseShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultVesselSubCaseShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the vesselSubCaseList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultVesselSubCaseShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllVesselSubCasesByAssignedUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where assignedUserId equals to DEFAULT_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldBeFound("assignedUserId.equals=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the vesselSubCaseList where assignedUserId equals to UPDATED_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldNotBeFound("assignedUserId.equals=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByAssignedUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where assignedUserId in DEFAULT_ASSIGNED_USER_ID or UPDATED_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldBeFound("assignedUserId.in=" + DEFAULT_ASSIGNED_USER_ID + "," + UPDATED_ASSIGNED_USER_ID);

        // Get all the vesselSubCaseList where assignedUserId equals to UPDATED_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldNotBeFound("assignedUserId.in=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByAssignedUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where assignedUserId is not null
        defaultVesselSubCaseShouldBeFound("assignedUserId.specified=true");

        // Get all the vesselSubCaseList where assignedUserId is null
        defaultVesselSubCaseShouldNotBeFound("assignedUserId.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByAssignedUserIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where assignedUserId greater than or equals to DEFAULT_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldBeFound("assignedUserId.greaterOrEqualThan=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the vesselSubCaseList where assignedUserId greater than or equals to UPDATED_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldNotBeFound("assignedUserId.greaterOrEqualThan=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByAssignedUserIdIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where assignedUserId less than or equals to DEFAULT_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldNotBeFound("assignedUserId.lessThan=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the vesselSubCaseList where assignedUserId less than or equals to UPDATED_ASSIGNED_USER_ID
        defaultVesselSubCaseShouldBeFound("assignedUserId.lessThan=" + UPDATED_ASSIGNED_USER_ID);
    }


    @Test
    @Transactional
    public void getAllVesselSubCasesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where insertTime equals to DEFAULT_INSERT_TIME
        defaultVesselSubCaseShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the vesselSubCaseList where insertTime equals to UPDATED_INSERT_TIME
        defaultVesselSubCaseShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultVesselSubCaseShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the vesselSubCaseList where insertTime equals to UPDATED_INSERT_TIME
        defaultVesselSubCaseShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where insertTime is not null
        defaultVesselSubCaseShouldBeFound("insertTime.specified=true");

        // Get all the vesselSubCaseList where insertTime is null
        defaultVesselSubCaseShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesBySubcaseCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where subcaseCode equals to DEFAULT_SUBCASE_CODE
        defaultVesselSubCaseShouldBeFound("subcaseCode.equals=" + DEFAULT_SUBCASE_CODE);

        // Get all the vesselSubCaseList where subcaseCode equals to UPDATED_SUBCASE_CODE
        defaultVesselSubCaseShouldNotBeFound("subcaseCode.equals=" + UPDATED_SUBCASE_CODE);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesBySubcaseCodeIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where subcaseCode in DEFAULT_SUBCASE_CODE or UPDATED_SUBCASE_CODE
        defaultVesselSubCaseShouldBeFound("subcaseCode.in=" + DEFAULT_SUBCASE_CODE + "," + UPDATED_SUBCASE_CODE);

        // Get all the vesselSubCaseList where subcaseCode equals to UPDATED_SUBCASE_CODE
        defaultVesselSubCaseShouldNotBeFound("subcaseCode.in=" + UPDATED_SUBCASE_CODE);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesBySubcaseCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where subcaseCode is not null
        defaultVesselSubCaseShouldBeFound("subcaseCode.specified=true");

        // Get all the vesselSubCaseList where subcaseCode is null
        defaultVesselSubCaseShouldNotBeFound("subcaseCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByBlNoIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where blNo equals to DEFAULT_BL_NO
        defaultVesselSubCaseShouldBeFound("blNo.equals=" + DEFAULT_BL_NO);

        // Get all the vesselSubCaseList where blNo equals to UPDATED_BL_NO
        defaultVesselSubCaseShouldNotBeFound("blNo.equals=" + UPDATED_BL_NO);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByBlNoIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where blNo in DEFAULT_BL_NO or UPDATED_BL_NO
        defaultVesselSubCaseShouldBeFound("blNo.in=" + DEFAULT_BL_NO + "," + UPDATED_BL_NO);

        // Get all the vesselSubCaseList where blNo equals to UPDATED_BL_NO
        defaultVesselSubCaseShouldNotBeFound("blNo.in=" + UPDATED_BL_NO);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByBlNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where blNo is not null
        defaultVesselSubCaseShouldBeFound("blNo.specified=true");

        // Get all the vesselSubCaseList where blNo is null
        defaultVesselSubCaseShouldNotBeFound("blNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByClaimantIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where claimant equals to DEFAULT_CLAIMANT
        defaultVesselSubCaseShouldBeFound("claimant.equals=" + DEFAULT_CLAIMANT);

        // Get all the vesselSubCaseList where claimant equals to UPDATED_CLAIMANT
        defaultVesselSubCaseShouldNotBeFound("claimant.equals=" + UPDATED_CLAIMANT);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByClaimantIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where claimant in DEFAULT_CLAIMANT or UPDATED_CLAIMANT
        defaultVesselSubCaseShouldBeFound("claimant.in=" + DEFAULT_CLAIMANT + "," + UPDATED_CLAIMANT);

        // Get all the vesselSubCaseList where claimant equals to UPDATED_CLAIMANT
        defaultVesselSubCaseShouldNotBeFound("claimant.in=" + UPDATED_CLAIMANT);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByClaimantIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where claimant is not null
        defaultVesselSubCaseShouldBeFound("claimant.specified=true");

        // Get all the vesselSubCaseList where claimant is null
        defaultVesselSubCaseShouldNotBeFound("claimant.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByClaimAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where claimAmount equals to DEFAULT_CLAIM_AMOUNT
        defaultVesselSubCaseShouldBeFound("claimAmount.equals=" + DEFAULT_CLAIM_AMOUNT);

        // Get all the vesselSubCaseList where claimAmount equals to UPDATED_CLAIM_AMOUNT
        defaultVesselSubCaseShouldNotBeFound("claimAmount.equals=" + UPDATED_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByClaimAmountIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where claimAmount in DEFAULT_CLAIM_AMOUNT or UPDATED_CLAIM_AMOUNT
        defaultVesselSubCaseShouldBeFound("claimAmount.in=" + DEFAULT_CLAIM_AMOUNT + "," + UPDATED_CLAIM_AMOUNT);

        // Get all the vesselSubCaseList where claimAmount equals to UPDATED_CLAIM_AMOUNT
        defaultVesselSubCaseShouldNotBeFound("claimAmount.in=" + UPDATED_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByClaimAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where claimAmount is not null
        defaultVesselSubCaseShouldBeFound("claimAmount.specified=true");

        // Get all the vesselSubCaseList where claimAmount is null
        defaultVesselSubCaseShouldNotBeFound("claimAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currency equals to DEFAULT_CURRENCY
        defaultVesselSubCaseShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the vesselSubCaseList where currency equals to UPDATED_CURRENCY
        defaultVesselSubCaseShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultVesselSubCaseShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the vesselSubCaseList where currency equals to UPDATED_CURRENCY
        defaultVesselSubCaseShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currency is not null
        defaultVesselSubCaseShouldBeFound("currency.specified=true");

        // Get all the vesselSubCaseList where currency is null
        defaultVesselSubCaseShouldNotBeFound("currency.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currency greater than or equals to DEFAULT_CURRENCY
        defaultVesselSubCaseShouldBeFound("currency.greaterOrEqualThan=" + DEFAULT_CURRENCY);

        // Get all the vesselSubCaseList where currency greater than or equals to UPDATED_CURRENCY
        defaultVesselSubCaseShouldNotBeFound("currency.greaterOrEqualThan=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currency less than or equals to DEFAULT_CURRENCY
        defaultVesselSubCaseShouldNotBeFound("currency.lessThan=" + DEFAULT_CURRENCY);

        // Get all the vesselSubCaseList where currency less than or equals to UPDATED_CURRENCY
        defaultVesselSubCaseShouldBeFound("currency.lessThan=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllVesselSubCasesByDeductibleIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where deductible equals to DEFAULT_DEDUCTIBLE
        defaultVesselSubCaseShouldBeFound("deductible.equals=" + DEFAULT_DEDUCTIBLE);

        // Get all the vesselSubCaseList where deductible equals to UPDATED_DEDUCTIBLE
        defaultVesselSubCaseShouldNotBeFound("deductible.equals=" + UPDATED_DEDUCTIBLE);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByDeductibleIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where deductible in DEFAULT_DEDUCTIBLE or UPDATED_DEDUCTIBLE
        defaultVesselSubCaseShouldBeFound("deductible.in=" + DEFAULT_DEDUCTIBLE + "," + UPDATED_DEDUCTIBLE);

        // Get all the vesselSubCaseList where deductible equals to UPDATED_DEDUCTIBLE
        defaultVesselSubCaseShouldNotBeFound("deductible.in=" + UPDATED_DEDUCTIBLE);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByDeductibleIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where deductible is not null
        defaultVesselSubCaseShouldBeFound("deductible.specified=true");

        // Get all the vesselSubCaseList where deductible is null
        defaultVesselSubCaseShouldNotBeFound("deductible.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyRateIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currencyRate equals to DEFAULT_CURRENCY_RATE
        defaultVesselSubCaseShouldBeFound("currencyRate.equals=" + DEFAULT_CURRENCY_RATE);

        // Get all the vesselSubCaseList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultVesselSubCaseShouldNotBeFound("currencyRate.equals=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyRateIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currencyRate in DEFAULT_CURRENCY_RATE or UPDATED_CURRENCY_RATE
        defaultVesselSubCaseShouldBeFound("currencyRate.in=" + DEFAULT_CURRENCY_RATE + "," + UPDATED_CURRENCY_RATE);

        // Get all the vesselSubCaseList where currencyRate equals to UPDATED_CURRENCY_RATE
        defaultVesselSubCaseShouldNotBeFound("currencyRate.in=" + UPDATED_CURRENCY_RATE);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByCurrencyRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where currencyRate is not null
        defaultVesselSubCaseShouldBeFound("currencyRate.specified=true");

        // Get all the vesselSubCaseList where currencyRate is null
        defaultVesselSubCaseShouldNotBeFound("currencyRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByDeductDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where deductDollar equals to DEFAULT_DEDUCT_DOLLAR
        defaultVesselSubCaseShouldBeFound("deductDollar.equals=" + DEFAULT_DEDUCT_DOLLAR);

        // Get all the vesselSubCaseList where deductDollar equals to UPDATED_DEDUCT_DOLLAR
        defaultVesselSubCaseShouldNotBeFound("deductDollar.equals=" + UPDATED_DEDUCT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByDeductDollarIsInShouldWork() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where deductDollar in DEFAULT_DEDUCT_DOLLAR or UPDATED_DEDUCT_DOLLAR
        defaultVesselSubCaseShouldBeFound("deductDollar.in=" + DEFAULT_DEDUCT_DOLLAR + "," + UPDATED_DEDUCT_DOLLAR);

        // Get all the vesselSubCaseList where deductDollar equals to UPDATED_DEDUCT_DOLLAR
        defaultVesselSubCaseShouldNotBeFound("deductDollar.in=" + UPDATED_DEDUCT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByDeductDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        // Get all the vesselSubCaseList where deductDollar is not null
        defaultVesselSubCaseShouldBeFound("deductDollar.specified=true");

        // Get all the vesselSubCaseList where deductDollar is null
        defaultVesselSubCaseShouldNotBeFound("deductDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllVesselSubCasesByVesselCaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselCase vesselCase = VesselCaseResourceIT.createEntity(em);
        em.persist(vesselCase);
        em.flush();
        vesselSubCase.setVesselCase(vesselCase);
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);
        Long vesselCaseId = vesselCase.getId();

        // Get all the vesselSubCaseList where vesselCase equals to vesselCaseId
        defaultVesselSubCaseShouldBeFound("vesselCaseId.equals=" + vesselCaseId);

        // Get all the vesselSubCaseList where vesselCase equals to vesselCaseId + 1
        defaultVesselSubCaseShouldNotBeFound("vesselCaseId.equals=" + (vesselCaseId + 1));
    }


    @Test
    @Transactional
    public void getAllVesselSubCasesByRiskIsEqualToSomething() throws Exception {
        // Initialize the database
        Risk risk = RiskResourceIT.createEntity(em);
        em.persist(risk);
        em.flush();
        vesselSubCase.setRisk(risk);
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);
        Long riskId = risk.getId();

        // Get all the vesselSubCaseList where risk equals to riskId
        defaultVesselSubCaseShouldBeFound("riskId.equals=" + riskId);

        // Get all the vesselSubCaseList where risk equals to riskId + 1
        defaultVesselSubCaseShouldNotBeFound("riskId.equals=" + (riskId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultVesselSubCaseShouldBeFound(String filter) throws Exception {
        restVesselSubCaseMockMvc.perform(get("/api/vessel-sub-cases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vesselSubCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].assignedUserId").value(hasItem(DEFAULT_ASSIGNED_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].subcaseCode").value(hasItem(DEFAULT_SUBCASE_CODE)))
            .andExpect(jsonPath("$.[*].blNo").value(hasItem(DEFAULT_BL_NO)))
            .andExpect(jsonPath("$.[*].claimant").value(hasItem(DEFAULT_CLAIMANT)))
            .andExpect(jsonPath("$.[*].claimAmount").value(hasItem(DEFAULT_CLAIM_AMOUNT)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].deductible").value(hasItem(DEFAULT_DEDUCTIBLE.intValue())))
            .andExpect(jsonPath("$.[*].currencyRate").value(hasItem(DEFAULT_CURRENCY_RATE.intValue())))
            .andExpect(jsonPath("$.[*].deductDollar").value(hasItem(DEFAULT_DEDUCT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));

        // Check, that the count call also returns 1
        restVesselSubCaseMockMvc.perform(get("/api/vessel-sub-cases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultVesselSubCaseShouldNotBeFound(String filter) throws Exception {
        restVesselSubCaseMockMvc.perform(get("/api/vessel-sub-cases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVesselSubCaseMockMvc.perform(get("/api/vessel-sub-cases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingVesselSubCase() throws Exception {
        // Get the vesselSubCase
        restVesselSubCaseMockMvc.perform(get("/api/vessel-sub-cases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVesselSubCase() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        int databaseSizeBeforeUpdate = vesselSubCaseRepository.findAll().size();

        // Update the vesselSubCase
        VesselSubCase updatedVesselSubCase = vesselSubCaseRepository.findById(vesselSubCase.getId()).get();
        // Disconnect from session so that the updates on updatedVesselSubCase are not directly saved in db
        em.detach(updatedVesselSubCase);
        updatedVesselSubCase
            .numberId(UPDATED_NUMBER_ID)
            .assignedUserId(UPDATED_ASSIGNED_USER_ID)
            .insertTime(UPDATED_INSERT_TIME)
            .subcaseCode(UPDATED_SUBCASE_CODE)
            .blNo(UPDATED_BL_NO)
            .claimant(UPDATED_CLAIMANT)
            .claimAmount(UPDATED_CLAIM_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .deductible(UPDATED_DEDUCTIBLE)
            .currencyRate(UPDATED_CURRENCY_RATE)
            .deductDollar(UPDATED_DEDUCT_DOLLAR)
            .remark(UPDATED_REMARK);
        VesselSubCaseDTO vesselSubCaseDTO = vesselSubCaseMapper.toDto(updatedVesselSubCase);

        restVesselSubCaseMockMvc.perform(put("/api/vessel-sub-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselSubCaseDTO)))
            .andExpect(status().isOk());

        // Validate the VesselSubCase in the database
        List<VesselSubCase> vesselSubCaseList = vesselSubCaseRepository.findAll();
        assertThat(vesselSubCaseList).hasSize(databaseSizeBeforeUpdate);
        VesselSubCase testVesselSubCase = vesselSubCaseList.get(vesselSubCaseList.size() - 1);
        assertThat(testVesselSubCase.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testVesselSubCase.getAssignedUserId()).isEqualTo(UPDATED_ASSIGNED_USER_ID);
        assertThat(testVesselSubCase.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testVesselSubCase.getSubcaseCode()).isEqualTo(UPDATED_SUBCASE_CODE);
        assertThat(testVesselSubCase.getBlNo()).isEqualTo(UPDATED_BL_NO);
        assertThat(testVesselSubCase.getClaimant()).isEqualTo(UPDATED_CLAIMANT);
        assertThat(testVesselSubCase.getClaimAmount()).isEqualTo(UPDATED_CLAIM_AMOUNT);
        assertThat(testVesselSubCase.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testVesselSubCase.getDeductible()).isEqualTo(UPDATED_DEDUCTIBLE);
        assertThat(testVesselSubCase.getCurrencyRate()).isEqualTo(UPDATED_CURRENCY_RATE);
        assertThat(testVesselSubCase.getDeductDollar()).isEqualTo(UPDATED_DEDUCT_DOLLAR);
        assertThat(testVesselSubCase.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingVesselSubCase() throws Exception {
        int databaseSizeBeforeUpdate = vesselSubCaseRepository.findAll().size();

        // Create the VesselSubCase
        VesselSubCaseDTO vesselSubCaseDTO = vesselSubCaseMapper.toDto(vesselSubCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVesselSubCaseMockMvc.perform(put("/api/vessel-sub-cases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vesselSubCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VesselSubCase in the database
        List<VesselSubCase> vesselSubCaseList = vesselSubCaseRepository.findAll();
        assertThat(vesselSubCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVesselSubCase() throws Exception {
        // Initialize the database
        vesselSubCaseRepository.saveAndFlush(vesselSubCase);

        int databaseSizeBeforeDelete = vesselSubCaseRepository.findAll().size();

        // Delete the vesselSubCase
        restVesselSubCaseMockMvc.perform(delete("/api/vessel-sub-cases/{id}", vesselSubCase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<VesselSubCase> vesselSubCaseList = vesselSubCaseRepository.findAll();
        assertThat(vesselSubCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VesselSubCase.class);
        VesselSubCase vesselSubCase1 = new VesselSubCase();
        vesselSubCase1.setId(1L);
        VesselSubCase vesselSubCase2 = new VesselSubCase();
        vesselSubCase2.setId(vesselSubCase1.getId());
        assertThat(vesselSubCase1).isEqualTo(vesselSubCase2);
        vesselSubCase2.setId(2L);
        assertThat(vesselSubCase1).isNotEqualTo(vesselSubCase2);
        vesselSubCase1.setId(null);
        assertThat(vesselSubCase1).isNotEqualTo(vesselSubCase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VesselSubCaseDTO.class);
        VesselSubCaseDTO vesselSubCaseDTO1 = new VesselSubCaseDTO();
        vesselSubCaseDTO1.setId(1L);
        VesselSubCaseDTO vesselSubCaseDTO2 = new VesselSubCaseDTO();
        assertThat(vesselSubCaseDTO1).isNotEqualTo(vesselSubCaseDTO2);
        vesselSubCaseDTO2.setId(vesselSubCaseDTO1.getId());
        assertThat(vesselSubCaseDTO1).isEqualTo(vesselSubCaseDTO2);
        vesselSubCaseDTO2.setId(2L);
        assertThat(vesselSubCaseDTO1).isNotEqualTo(vesselSubCaseDTO2);
        vesselSubCaseDTO1.setId(null);
        assertThat(vesselSubCaseDTO1).isNotEqualTo(vesselSubCaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vesselSubCaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vesselSubCaseMapper.fromId(null)).isNull();
    }
}
