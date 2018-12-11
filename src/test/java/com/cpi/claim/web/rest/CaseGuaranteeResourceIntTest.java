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

import com.cpi.claim.domain.CaseGuarantee;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.GuaranteeType;
import com.cpi.claim.domain.GuaranteeType;
import com.cpi.claim.repository.CaseGuaranteeRepository;
import com.cpi.claim.service.CaseGuaranteeService;
import com.cpi.claim.service.dto.CaseGuaranteeDTO;
import com.cpi.claim.service.mapper.CaseGuaranteeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseGuaranteeCriteria;
import com.cpi.claim.service.CaseGuaranteeQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

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
 * Test class for the CaseGuaranteeResource REST controller.
 *
 * @see CaseGuaranteeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseGuaranteeResourceIntTest {

    private static final Long DEFAULT_VESSEL_ID = 1L;
    private static final Long UPDATED_VESSEL_ID = 2L;

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Instant DEFAULT_ARREST_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ARREST_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ARREST_PORT = 1L;
    private static final Long UPDATED_ARREST_PORT = 2L;

    private static final Long DEFAULT_ARREST_VESSEL_ID = 1L;
    private static final Long UPDATED_ARREST_VESSEL_ID = 2L;

    private static final String DEFAULT_ARREST_VESSEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARREST_VESSEL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILL_OF_LADING = "AAAAAAAAAA";
    private static final String UPDATED_BILL_OF_LADING = "BBBBBBBBBB";

    private static final Instant DEFAULT_BILL_LADING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BILL_LADING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NATURE_OF_CLAIM = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_OF_CLAIM = "BBBBBBBBBB";

    private static final String DEFAULT_GUARANTEE = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE = "BBBBBBBBBB";

    private static final Instant DEFAULT_GUARANTEE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_GUARANTEE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_GUARANTEE_CURRENCY = 1L;
    private static final Long UPDATED_GUARANTEE_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_GUARANTEE_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_GUARANTEE_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_GUARANTEE_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_GUARANTEE_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_GUARANTEE_AMOUNT_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_GUARANTEE_AMOUNT_DOLLAR = new BigDecimal(2);

    private static final String DEFAULT_GUARANTEE_TO = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_TO = "BBBBBBBBBB";

    private static final String DEFAULT_GUARANTEE_TO_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_TO_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_GUARANTEE_NO = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_NO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_GUARANTEE_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_GUARANTEE_FEE = new BigDecimal(2);

    private static final String DEFAULT_GUARANTEE_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_OTHER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CANCEL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CANCEL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CON_GUARANTEE = "AAAAAAAAAA";
    private static final String UPDATED_CON_GUARANTEE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CON_GUARANTEE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CON_GUARANTEE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CON_GUARANTEE_CURRENCY = 1L;
    private static final Long UPDATED_CON_GUARANTEE_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_CON_GUARANTEE_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CON_GUARANTEE_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CON_GUARANTEE_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CON_GUARANTEE_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR = new BigDecimal(2);

    private static final String DEFAULT_CON_GUARANTEE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CON_GUARANTEE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CON_GUARANTEE_TO = "AAAAAAAAAA";
    private static final String UPDATED_CON_GUARANTEE_TO = "BBBBBBBBBB";

    private static final Instant DEFAULT_CON_GUARANTEE_CANCEL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CON_GUARANTEE_CANCEL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_RELEASE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RELEASE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_REGISTER_USER_ID = 1L;
    private static final Long UPDATED_REGISTER_USER_ID = 2L;

    @Autowired
    private CaseGuaranteeRepository caseGuaranteeRepository;


    @Autowired
    private CaseGuaranteeMapper caseGuaranteeMapper;


    @Autowired
    private CaseGuaranteeService caseGuaranteeService;

    @Autowired
    private CaseGuaranteeQueryService caseGuaranteeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseGuaranteeMockMvc;

    private CaseGuarantee caseGuarantee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseGuaranteeResource caseGuaranteeResource = new CaseGuaranteeResource(caseGuaranteeService, caseGuaranteeQueryService);
        this.restCaseGuaranteeMockMvc = MockMvcBuilders.standaloneSetup(caseGuaranteeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseGuarantee createEntity(EntityManager em) {
        CaseGuarantee caseGuarantee = new CaseGuarantee()
            .vesselId(DEFAULT_VESSEL_ID)
            .numberId(DEFAULT_NUMBER_ID)
            .arrestDate(DEFAULT_ARREST_DATE)
            .arrestPort(DEFAULT_ARREST_PORT)
            .arrestVesselId(DEFAULT_ARREST_VESSEL_ID)
            .arrestVesselName(DEFAULT_ARREST_VESSEL_NAME)
            .billOfLading(DEFAULT_BILL_OF_LADING)
            .billLadingDate(DEFAULT_BILL_LADING_DATE)
            .natureOfClaim(DEFAULT_NATURE_OF_CLAIM)
            .guarantee(DEFAULT_GUARANTEE)
            .guaranteeDate(DEFAULT_GUARANTEE_DATE)
            .guaranteeCurrency(DEFAULT_GUARANTEE_CURRENCY)
            .guaranteeRate(DEFAULT_GUARANTEE_RATE)
            .guaranteeAmount(DEFAULT_GUARANTEE_AMOUNT)
            .guaranteeAmountDollar(DEFAULT_GUARANTEE_AMOUNT_DOLLAR)
            .guaranteeTo(DEFAULT_GUARANTEE_TO)
            .guaranteeToAddress(DEFAULT_GUARANTEE_TO_ADDRESS)
            .guaranteeNo(DEFAULT_GUARANTEE_NO)
            .guaranteeFee(DEFAULT_GUARANTEE_FEE)
            .guaranteeOther(DEFAULT_GUARANTEE_OTHER)
            .cancelDate(DEFAULT_CANCEL_DATE)
            .conGuarantee(DEFAULT_CON_GUARANTEE)
            .conGuaranteeDate(DEFAULT_CON_GUARANTEE_DATE)
            .conGuaranteeCurrency(DEFAULT_CON_GUARANTEE_CURRENCY)
            .conGuaranteeRate(DEFAULT_CON_GUARANTEE_RATE)
            .conGuaranteeAmount(DEFAULT_CON_GUARANTEE_AMOUNT)
            .conGuaranteeAmountDollar(DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR)
            .conGuaranteeNo(DEFAULT_CON_GUARANTEE_NO)
            .conGuaranteeTo(DEFAULT_CON_GUARANTEE_TO)
            .conGuaranteeCancelDate(DEFAULT_CON_GUARANTEE_CANCEL_DATE)
            .remark(DEFAULT_REMARK)
            .releaseDate(DEFAULT_RELEASE_DATE)
            .registerUserId(DEFAULT_REGISTER_USER_ID);
        return caseGuarantee;
    }

    @Before
    public void initTest() {
        caseGuarantee = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseGuarantee() throws Exception {
        int databaseSizeBeforeCreate = caseGuaranteeRepository.findAll().size();

        // Create the CaseGuarantee
        CaseGuaranteeDTO caseGuaranteeDTO = caseGuaranteeMapper.toDto(caseGuarantee);
        restCaseGuaranteeMockMvc.perform(post("/api/case-guarantees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseGuaranteeDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseGuarantee in the database
        List<CaseGuarantee> caseGuaranteeList = caseGuaranteeRepository.findAll();
        assertThat(caseGuaranteeList).hasSize(databaseSizeBeforeCreate + 1);
        CaseGuarantee testCaseGuarantee = caseGuaranteeList.get(caseGuaranteeList.size() - 1);
        assertThat(testCaseGuarantee.getVesselId()).isEqualTo(DEFAULT_VESSEL_ID);
        assertThat(testCaseGuarantee.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseGuarantee.getArrestDate()).isEqualTo(DEFAULT_ARREST_DATE);
        assertThat(testCaseGuarantee.getArrestPort()).isEqualTo(DEFAULT_ARREST_PORT);
        assertThat(testCaseGuarantee.getArrestVesselId()).isEqualTo(DEFAULT_ARREST_VESSEL_ID);
        assertThat(testCaseGuarantee.getArrestVesselName()).isEqualTo(DEFAULT_ARREST_VESSEL_NAME);
        assertThat(testCaseGuarantee.getBillOfLading()).isEqualTo(DEFAULT_BILL_OF_LADING);
        assertThat(testCaseGuarantee.getBillLadingDate()).isEqualTo(DEFAULT_BILL_LADING_DATE);
        assertThat(testCaseGuarantee.getNatureOfClaim()).isEqualTo(DEFAULT_NATURE_OF_CLAIM);
        assertThat(testCaseGuarantee.getGuarantee()).isEqualTo(DEFAULT_GUARANTEE);
        assertThat(testCaseGuarantee.getGuaranteeDate()).isEqualTo(DEFAULT_GUARANTEE_DATE);
        assertThat(testCaseGuarantee.getGuaranteeCurrency()).isEqualTo(DEFAULT_GUARANTEE_CURRENCY);
        assertThat(testCaseGuarantee.getGuaranteeRate()).isEqualTo(DEFAULT_GUARANTEE_RATE);
        assertThat(testCaseGuarantee.getGuaranteeAmount()).isEqualTo(DEFAULT_GUARANTEE_AMOUNT);
        assertThat(testCaseGuarantee.getGuaranteeAmountDollar()).isEqualTo(DEFAULT_GUARANTEE_AMOUNT_DOLLAR);
        assertThat(testCaseGuarantee.getGuaranteeTo()).isEqualTo(DEFAULT_GUARANTEE_TO);
        assertThat(testCaseGuarantee.getGuaranteeToAddress()).isEqualTo(DEFAULT_GUARANTEE_TO_ADDRESS);
        assertThat(testCaseGuarantee.getGuaranteeNo()).isEqualTo(DEFAULT_GUARANTEE_NO);
        assertThat(testCaseGuarantee.getGuaranteeFee()).isEqualTo(DEFAULT_GUARANTEE_FEE);
        assertThat(testCaseGuarantee.getGuaranteeOther()).isEqualTo(DEFAULT_GUARANTEE_OTHER);
        assertThat(testCaseGuarantee.getCancelDate()).isEqualTo(DEFAULT_CANCEL_DATE);
        assertThat(testCaseGuarantee.getConGuarantee()).isEqualTo(DEFAULT_CON_GUARANTEE);
        assertThat(testCaseGuarantee.getConGuaranteeDate()).isEqualTo(DEFAULT_CON_GUARANTEE_DATE);
        assertThat(testCaseGuarantee.getConGuaranteeCurrency()).isEqualTo(DEFAULT_CON_GUARANTEE_CURRENCY);
        assertThat(testCaseGuarantee.getConGuaranteeRate()).isEqualTo(DEFAULT_CON_GUARANTEE_RATE);
        assertThat(testCaseGuarantee.getConGuaranteeAmount()).isEqualTo(DEFAULT_CON_GUARANTEE_AMOUNT);
        assertThat(testCaseGuarantee.getConGuaranteeAmountDollar()).isEqualTo(DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR);
        assertThat(testCaseGuarantee.getConGuaranteeNo()).isEqualTo(DEFAULT_CON_GUARANTEE_NO);
        assertThat(testCaseGuarantee.getConGuaranteeTo()).isEqualTo(DEFAULT_CON_GUARANTEE_TO);
        assertThat(testCaseGuarantee.getConGuaranteeCancelDate()).isEqualTo(DEFAULT_CON_GUARANTEE_CANCEL_DATE);
        assertThat(testCaseGuarantee.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testCaseGuarantee.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testCaseGuarantee.getRegisterUserId()).isEqualTo(DEFAULT_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void createCaseGuaranteeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseGuaranteeRepository.findAll().size();

        // Create the CaseGuarantee with an existing ID
        caseGuarantee.setId(1L);
        CaseGuaranteeDTO caseGuaranteeDTO = caseGuaranteeMapper.toDto(caseGuarantee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseGuaranteeMockMvc.perform(post("/api/case-guarantees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseGuaranteeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseGuarantee in the database
        List<CaseGuarantee> caseGuaranteeList = caseGuaranteeRepository.findAll();
        assertThat(caseGuaranteeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseGuarantees() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList
        restCaseGuaranteeMockMvc.perform(get("/api/case-guarantees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseGuarantee.getId().intValue())))
            .andExpect(jsonPath("$.[*].vesselId").value(hasItem(DEFAULT_VESSEL_ID.intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].arrestDate").value(hasItem(DEFAULT_ARREST_DATE.toString())))
            .andExpect(jsonPath("$.[*].arrestPort").value(hasItem(DEFAULT_ARREST_PORT.intValue())))
            .andExpect(jsonPath("$.[*].arrestVesselId").value(hasItem(DEFAULT_ARREST_VESSEL_ID.intValue())))
            .andExpect(jsonPath("$.[*].arrestVesselName").value(hasItem(DEFAULT_ARREST_VESSEL_NAME.toString())))
            .andExpect(jsonPath("$.[*].billOfLading").value(hasItem(DEFAULT_BILL_OF_LADING.toString())))
            .andExpect(jsonPath("$.[*].billLadingDate").value(hasItem(DEFAULT_BILL_LADING_DATE.toString())))
            .andExpect(jsonPath("$.[*].natureOfClaim").value(hasItem(DEFAULT_NATURE_OF_CLAIM.toString())))
            .andExpect(jsonPath("$.[*].guarantee").value(hasItem(DEFAULT_GUARANTEE.toString())))
            .andExpect(jsonPath("$.[*].guaranteeDate").value(hasItem(DEFAULT_GUARANTEE_DATE.toString())))
            .andExpect(jsonPath("$.[*].guaranteeCurrency").value(hasItem(DEFAULT_GUARANTEE_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeRate").value(hasItem(DEFAULT_GUARANTEE_RATE.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeAmount").value(hasItem(DEFAULT_GUARANTEE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeAmountDollar").value(hasItem(DEFAULT_GUARANTEE_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeTo").value(hasItem(DEFAULT_GUARANTEE_TO.toString())))
            .andExpect(jsonPath("$.[*].guaranteeToAddress").value(hasItem(DEFAULT_GUARANTEE_TO_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].guaranteeNo").value(hasItem(DEFAULT_GUARANTEE_NO.toString())))
            .andExpect(jsonPath("$.[*].guaranteeFee").value(hasItem(DEFAULT_GUARANTEE_FEE.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeOther").value(hasItem(DEFAULT_GUARANTEE_OTHER.toString())))
            .andExpect(jsonPath("$.[*].cancelDate").value(hasItem(DEFAULT_CANCEL_DATE.toString())))
            .andExpect(jsonPath("$.[*].conGuarantee").value(hasItem(DEFAULT_CON_GUARANTEE.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeDate").value(hasItem(DEFAULT_CON_GUARANTEE_DATE.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeCurrency").value(hasItem(DEFAULT_CON_GUARANTEE_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeRate").value(hasItem(DEFAULT_CON_GUARANTEE_RATE.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeAmount").value(hasItem(DEFAULT_CON_GUARANTEE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeAmountDollar").value(hasItem(DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeNo").value(hasItem(DEFAULT_CON_GUARANTEE_NO.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeTo").value(hasItem(DEFAULT_CON_GUARANTEE_TO.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeCancelDate").value(hasItem(DEFAULT_CON_GUARANTEE_CANCEL_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].releaseDate").value(hasItem(DEFAULT_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerUserId").value(hasItem(DEFAULT_REGISTER_USER_ID.intValue())));
    }


    @Test
    @Transactional
    public void getCaseGuarantee() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get the caseGuarantee
        restCaseGuaranteeMockMvc.perform(get("/api/case-guarantees/{id}", caseGuarantee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseGuarantee.getId().intValue()))
            .andExpect(jsonPath("$.vesselId").value(DEFAULT_VESSEL_ID.intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.arrestDate").value(DEFAULT_ARREST_DATE.toString()))
            .andExpect(jsonPath("$.arrestPort").value(DEFAULT_ARREST_PORT.intValue()))
            .andExpect(jsonPath("$.arrestVesselId").value(DEFAULT_ARREST_VESSEL_ID.intValue()))
            .andExpect(jsonPath("$.arrestVesselName").value(DEFAULT_ARREST_VESSEL_NAME.toString()))
            .andExpect(jsonPath("$.billOfLading").value(DEFAULT_BILL_OF_LADING.toString()))
            .andExpect(jsonPath("$.billLadingDate").value(DEFAULT_BILL_LADING_DATE.toString()))
            .andExpect(jsonPath("$.natureOfClaim").value(DEFAULT_NATURE_OF_CLAIM.toString()))
            .andExpect(jsonPath("$.guarantee").value(DEFAULT_GUARANTEE.toString()))
            .andExpect(jsonPath("$.guaranteeDate").value(DEFAULT_GUARANTEE_DATE.toString()))
            .andExpect(jsonPath("$.guaranteeCurrency").value(DEFAULT_GUARANTEE_CURRENCY.intValue()))
            .andExpect(jsonPath("$.guaranteeRate").value(DEFAULT_GUARANTEE_RATE.intValue()))
            .andExpect(jsonPath("$.guaranteeAmount").value(DEFAULT_GUARANTEE_AMOUNT.intValue()))
            .andExpect(jsonPath("$.guaranteeAmountDollar").value(DEFAULT_GUARANTEE_AMOUNT_DOLLAR.intValue()))
            .andExpect(jsonPath("$.guaranteeTo").value(DEFAULT_GUARANTEE_TO.toString()))
            .andExpect(jsonPath("$.guaranteeToAddress").value(DEFAULT_GUARANTEE_TO_ADDRESS.toString()))
            .andExpect(jsonPath("$.guaranteeNo").value(DEFAULT_GUARANTEE_NO.toString()))
            .andExpect(jsonPath("$.guaranteeFee").value(DEFAULT_GUARANTEE_FEE.intValue()))
            .andExpect(jsonPath("$.guaranteeOther").value(DEFAULT_GUARANTEE_OTHER.toString()))
            .andExpect(jsonPath("$.cancelDate").value(DEFAULT_CANCEL_DATE.toString()))
            .andExpect(jsonPath("$.conGuarantee").value(DEFAULT_CON_GUARANTEE.toString()))
            .andExpect(jsonPath("$.conGuaranteeDate").value(DEFAULT_CON_GUARANTEE_DATE.toString()))
            .andExpect(jsonPath("$.conGuaranteeCurrency").value(DEFAULT_CON_GUARANTEE_CURRENCY.intValue()))
            .andExpect(jsonPath("$.conGuaranteeRate").value(DEFAULT_CON_GUARANTEE_RATE.intValue()))
            .andExpect(jsonPath("$.conGuaranteeAmount").value(DEFAULT_CON_GUARANTEE_AMOUNT.intValue()))
            .andExpect(jsonPath("$.conGuaranteeAmountDollar").value(DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR.intValue()))
            .andExpect(jsonPath("$.conGuaranteeNo").value(DEFAULT_CON_GUARANTEE_NO.toString()))
            .andExpect(jsonPath("$.conGuaranteeTo").value(DEFAULT_CON_GUARANTEE_TO.toString()))
            .andExpect(jsonPath("$.conGuaranteeCancelDate").value(DEFAULT_CON_GUARANTEE_CANCEL_DATE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.releaseDate").value(DEFAULT_RELEASE_DATE.toString()))
            .andExpect(jsonPath("$.registerUserId").value(DEFAULT_REGISTER_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByVesselIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where vesselId equals to DEFAULT_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("vesselId.equals=" + DEFAULT_VESSEL_ID);

        // Get all the caseGuaranteeList where vesselId equals to UPDATED_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("vesselId.equals=" + UPDATED_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByVesselIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where vesselId in DEFAULT_VESSEL_ID or UPDATED_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("vesselId.in=" + DEFAULT_VESSEL_ID + "," + UPDATED_VESSEL_ID);

        // Get all the caseGuaranteeList where vesselId equals to UPDATED_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("vesselId.in=" + UPDATED_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByVesselIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where vesselId is not null
        defaultCaseGuaranteeShouldBeFound("vesselId.specified=true");

        // Get all the caseGuaranteeList where vesselId is null
        defaultCaseGuaranteeShouldNotBeFound("vesselId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByVesselIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where vesselId greater than or equals to DEFAULT_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("vesselId.greaterOrEqualThan=" + DEFAULT_VESSEL_ID);

        // Get all the caseGuaranteeList where vesselId greater than or equals to UPDATED_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("vesselId.greaterOrEqualThan=" + UPDATED_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByVesselIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where vesselId less than or equals to DEFAULT_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("vesselId.lessThan=" + DEFAULT_VESSEL_ID);

        // Get all the caseGuaranteeList where vesselId less than or equals to UPDATED_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("vesselId.lessThan=" + UPDATED_VESSEL_ID);
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseGuaranteeShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseGuaranteeList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseGuaranteeShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseGuaranteeShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseGuaranteeList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseGuaranteeShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where numberId is not null
        defaultCaseGuaranteeShouldBeFound("numberId.specified=true");

        // Get all the caseGuaranteeList where numberId is null
        defaultCaseGuaranteeShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCaseGuaranteeShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseGuaranteeList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCaseGuaranteeShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCaseGuaranteeShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseGuaranteeList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCaseGuaranteeShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestDate equals to DEFAULT_ARREST_DATE
        defaultCaseGuaranteeShouldBeFound("arrestDate.equals=" + DEFAULT_ARREST_DATE);

        // Get all the caseGuaranteeList where arrestDate equals to UPDATED_ARREST_DATE
        defaultCaseGuaranteeShouldNotBeFound("arrestDate.equals=" + UPDATED_ARREST_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestDate in DEFAULT_ARREST_DATE or UPDATED_ARREST_DATE
        defaultCaseGuaranteeShouldBeFound("arrestDate.in=" + DEFAULT_ARREST_DATE + "," + UPDATED_ARREST_DATE);

        // Get all the caseGuaranteeList where arrestDate equals to UPDATED_ARREST_DATE
        defaultCaseGuaranteeShouldNotBeFound("arrestDate.in=" + UPDATED_ARREST_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestDate is not null
        defaultCaseGuaranteeShouldBeFound("arrestDate.specified=true");

        // Get all the caseGuaranteeList where arrestDate is null
        defaultCaseGuaranteeShouldNotBeFound("arrestDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestPortIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestPort equals to DEFAULT_ARREST_PORT
        defaultCaseGuaranteeShouldBeFound("arrestPort.equals=" + DEFAULT_ARREST_PORT);

        // Get all the caseGuaranteeList where arrestPort equals to UPDATED_ARREST_PORT
        defaultCaseGuaranteeShouldNotBeFound("arrestPort.equals=" + UPDATED_ARREST_PORT);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestPortIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestPort in DEFAULT_ARREST_PORT or UPDATED_ARREST_PORT
        defaultCaseGuaranteeShouldBeFound("arrestPort.in=" + DEFAULT_ARREST_PORT + "," + UPDATED_ARREST_PORT);

        // Get all the caseGuaranteeList where arrestPort equals to UPDATED_ARREST_PORT
        defaultCaseGuaranteeShouldNotBeFound("arrestPort.in=" + UPDATED_ARREST_PORT);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestPortIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestPort is not null
        defaultCaseGuaranteeShouldBeFound("arrestPort.specified=true");

        // Get all the caseGuaranteeList where arrestPort is null
        defaultCaseGuaranteeShouldNotBeFound("arrestPort.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestPortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestPort greater than or equals to DEFAULT_ARREST_PORT
        defaultCaseGuaranteeShouldBeFound("arrestPort.greaterOrEqualThan=" + DEFAULT_ARREST_PORT);

        // Get all the caseGuaranteeList where arrestPort greater than or equals to UPDATED_ARREST_PORT
        defaultCaseGuaranteeShouldNotBeFound("arrestPort.greaterOrEqualThan=" + UPDATED_ARREST_PORT);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestPortIsLessThanSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestPort less than or equals to DEFAULT_ARREST_PORT
        defaultCaseGuaranteeShouldNotBeFound("arrestPort.lessThan=" + DEFAULT_ARREST_PORT);

        // Get all the caseGuaranteeList where arrestPort less than or equals to UPDATED_ARREST_PORT
        defaultCaseGuaranteeShouldBeFound("arrestPort.lessThan=" + UPDATED_ARREST_PORT);
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselId equals to DEFAULT_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("arrestVesselId.equals=" + DEFAULT_ARREST_VESSEL_ID);

        // Get all the caseGuaranteeList where arrestVesselId equals to UPDATED_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselId.equals=" + UPDATED_ARREST_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselId in DEFAULT_ARREST_VESSEL_ID or UPDATED_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("arrestVesselId.in=" + DEFAULT_ARREST_VESSEL_ID + "," + UPDATED_ARREST_VESSEL_ID);

        // Get all the caseGuaranteeList where arrestVesselId equals to UPDATED_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselId.in=" + UPDATED_ARREST_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselId is not null
        defaultCaseGuaranteeShouldBeFound("arrestVesselId.specified=true");

        // Get all the caseGuaranteeList where arrestVesselId is null
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselId greater than or equals to DEFAULT_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("arrestVesselId.greaterOrEqualThan=" + DEFAULT_ARREST_VESSEL_ID);

        // Get all the caseGuaranteeList where arrestVesselId greater than or equals to UPDATED_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselId.greaterOrEqualThan=" + UPDATED_ARREST_VESSEL_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselId less than or equals to DEFAULT_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselId.lessThan=" + DEFAULT_ARREST_VESSEL_ID);

        // Get all the caseGuaranteeList where arrestVesselId less than or equals to UPDATED_ARREST_VESSEL_ID
        defaultCaseGuaranteeShouldBeFound("arrestVesselId.lessThan=" + UPDATED_ARREST_VESSEL_ID);
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselNameIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselName equals to DEFAULT_ARREST_VESSEL_NAME
        defaultCaseGuaranteeShouldBeFound("arrestVesselName.equals=" + DEFAULT_ARREST_VESSEL_NAME);

        // Get all the caseGuaranteeList where arrestVesselName equals to UPDATED_ARREST_VESSEL_NAME
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselName.equals=" + UPDATED_ARREST_VESSEL_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselNameIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselName in DEFAULT_ARREST_VESSEL_NAME or UPDATED_ARREST_VESSEL_NAME
        defaultCaseGuaranteeShouldBeFound("arrestVesselName.in=" + DEFAULT_ARREST_VESSEL_NAME + "," + UPDATED_ARREST_VESSEL_NAME);

        // Get all the caseGuaranteeList where arrestVesselName equals to UPDATED_ARREST_VESSEL_NAME
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselName.in=" + UPDATED_ARREST_VESSEL_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByArrestVesselNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where arrestVesselName is not null
        defaultCaseGuaranteeShouldBeFound("arrestVesselName.specified=true");

        // Get all the caseGuaranteeList where arrestVesselName is null
        defaultCaseGuaranteeShouldNotBeFound("arrestVesselName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByBillOfLadingIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where billOfLading equals to DEFAULT_BILL_OF_LADING
        defaultCaseGuaranteeShouldBeFound("billOfLading.equals=" + DEFAULT_BILL_OF_LADING);

        // Get all the caseGuaranteeList where billOfLading equals to UPDATED_BILL_OF_LADING
        defaultCaseGuaranteeShouldNotBeFound("billOfLading.equals=" + UPDATED_BILL_OF_LADING);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByBillOfLadingIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where billOfLading in DEFAULT_BILL_OF_LADING or UPDATED_BILL_OF_LADING
        defaultCaseGuaranteeShouldBeFound("billOfLading.in=" + DEFAULT_BILL_OF_LADING + "," + UPDATED_BILL_OF_LADING);

        // Get all the caseGuaranteeList where billOfLading equals to UPDATED_BILL_OF_LADING
        defaultCaseGuaranteeShouldNotBeFound("billOfLading.in=" + UPDATED_BILL_OF_LADING);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByBillOfLadingIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where billOfLading is not null
        defaultCaseGuaranteeShouldBeFound("billOfLading.specified=true");

        // Get all the caseGuaranteeList where billOfLading is null
        defaultCaseGuaranteeShouldNotBeFound("billOfLading.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByBillLadingDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where billLadingDate equals to DEFAULT_BILL_LADING_DATE
        defaultCaseGuaranteeShouldBeFound("billLadingDate.equals=" + DEFAULT_BILL_LADING_DATE);

        // Get all the caseGuaranteeList where billLadingDate equals to UPDATED_BILL_LADING_DATE
        defaultCaseGuaranteeShouldNotBeFound("billLadingDate.equals=" + UPDATED_BILL_LADING_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByBillLadingDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where billLadingDate in DEFAULT_BILL_LADING_DATE or UPDATED_BILL_LADING_DATE
        defaultCaseGuaranteeShouldBeFound("billLadingDate.in=" + DEFAULT_BILL_LADING_DATE + "," + UPDATED_BILL_LADING_DATE);

        // Get all the caseGuaranteeList where billLadingDate equals to UPDATED_BILL_LADING_DATE
        defaultCaseGuaranteeShouldNotBeFound("billLadingDate.in=" + UPDATED_BILL_LADING_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByBillLadingDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where billLadingDate is not null
        defaultCaseGuaranteeShouldBeFound("billLadingDate.specified=true");

        // Get all the caseGuaranteeList where billLadingDate is null
        defaultCaseGuaranteeShouldNotBeFound("billLadingDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByNatureOfClaimIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where natureOfClaim equals to DEFAULT_NATURE_OF_CLAIM
        defaultCaseGuaranteeShouldBeFound("natureOfClaim.equals=" + DEFAULT_NATURE_OF_CLAIM);

        // Get all the caseGuaranteeList where natureOfClaim equals to UPDATED_NATURE_OF_CLAIM
        defaultCaseGuaranteeShouldNotBeFound("natureOfClaim.equals=" + UPDATED_NATURE_OF_CLAIM);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByNatureOfClaimIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where natureOfClaim in DEFAULT_NATURE_OF_CLAIM or UPDATED_NATURE_OF_CLAIM
        defaultCaseGuaranteeShouldBeFound("natureOfClaim.in=" + DEFAULT_NATURE_OF_CLAIM + "," + UPDATED_NATURE_OF_CLAIM);

        // Get all the caseGuaranteeList where natureOfClaim equals to UPDATED_NATURE_OF_CLAIM
        defaultCaseGuaranteeShouldNotBeFound("natureOfClaim.in=" + UPDATED_NATURE_OF_CLAIM);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByNatureOfClaimIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where natureOfClaim is not null
        defaultCaseGuaranteeShouldBeFound("natureOfClaim.specified=true");

        // Get all the caseGuaranteeList where natureOfClaim is null
        defaultCaseGuaranteeShouldNotBeFound("natureOfClaim.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guarantee equals to DEFAULT_GUARANTEE
        defaultCaseGuaranteeShouldBeFound("guarantee.equals=" + DEFAULT_GUARANTEE);

        // Get all the caseGuaranteeList where guarantee equals to UPDATED_GUARANTEE
        defaultCaseGuaranteeShouldNotBeFound("guarantee.equals=" + UPDATED_GUARANTEE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guarantee in DEFAULT_GUARANTEE or UPDATED_GUARANTEE
        defaultCaseGuaranteeShouldBeFound("guarantee.in=" + DEFAULT_GUARANTEE + "," + UPDATED_GUARANTEE);

        // Get all the caseGuaranteeList where guarantee equals to UPDATED_GUARANTEE
        defaultCaseGuaranteeShouldNotBeFound("guarantee.in=" + UPDATED_GUARANTEE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guarantee is not null
        defaultCaseGuaranteeShouldBeFound("guarantee.specified=true");

        // Get all the caseGuaranteeList where guarantee is null
        defaultCaseGuaranteeShouldNotBeFound("guarantee.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeDate equals to DEFAULT_GUARANTEE_DATE
        defaultCaseGuaranteeShouldBeFound("guaranteeDate.equals=" + DEFAULT_GUARANTEE_DATE);

        // Get all the caseGuaranteeList where guaranteeDate equals to UPDATED_GUARANTEE_DATE
        defaultCaseGuaranteeShouldNotBeFound("guaranteeDate.equals=" + UPDATED_GUARANTEE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeDate in DEFAULT_GUARANTEE_DATE or UPDATED_GUARANTEE_DATE
        defaultCaseGuaranteeShouldBeFound("guaranteeDate.in=" + DEFAULT_GUARANTEE_DATE + "," + UPDATED_GUARANTEE_DATE);

        // Get all the caseGuaranteeList where guaranteeDate equals to UPDATED_GUARANTEE_DATE
        defaultCaseGuaranteeShouldNotBeFound("guaranteeDate.in=" + UPDATED_GUARANTEE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeDate is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeDate.specified=true");

        // Get all the caseGuaranteeList where guaranteeDate is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeCurrency equals to DEFAULT_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("guaranteeCurrency.equals=" + DEFAULT_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where guaranteeCurrency equals to UPDATED_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("guaranteeCurrency.equals=" + UPDATED_GUARANTEE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeCurrency in DEFAULT_GUARANTEE_CURRENCY or UPDATED_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("guaranteeCurrency.in=" + DEFAULT_GUARANTEE_CURRENCY + "," + UPDATED_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where guaranteeCurrency equals to UPDATED_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("guaranteeCurrency.in=" + UPDATED_GUARANTEE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeCurrency is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeCurrency.specified=true");

        // Get all the caseGuaranteeList where guaranteeCurrency is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeCurrency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeCurrency greater than or equals to DEFAULT_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("guaranteeCurrency.greaterOrEqualThan=" + DEFAULT_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where guaranteeCurrency greater than or equals to UPDATED_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("guaranteeCurrency.greaterOrEqualThan=" + UPDATED_GUARANTEE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeCurrency less than or equals to DEFAULT_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("guaranteeCurrency.lessThan=" + DEFAULT_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where guaranteeCurrency less than or equals to UPDATED_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("guaranteeCurrency.lessThan=" + UPDATED_GUARANTEE_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeRate equals to DEFAULT_GUARANTEE_RATE
        defaultCaseGuaranteeShouldBeFound("guaranteeRate.equals=" + DEFAULT_GUARANTEE_RATE);

        // Get all the caseGuaranteeList where guaranteeRate equals to UPDATED_GUARANTEE_RATE
        defaultCaseGuaranteeShouldNotBeFound("guaranteeRate.equals=" + UPDATED_GUARANTEE_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeRate in DEFAULT_GUARANTEE_RATE or UPDATED_GUARANTEE_RATE
        defaultCaseGuaranteeShouldBeFound("guaranteeRate.in=" + DEFAULT_GUARANTEE_RATE + "," + UPDATED_GUARANTEE_RATE);

        // Get all the caseGuaranteeList where guaranteeRate equals to UPDATED_GUARANTEE_RATE
        defaultCaseGuaranteeShouldNotBeFound("guaranteeRate.in=" + UPDATED_GUARANTEE_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeRate is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeRate.specified=true");

        // Get all the caseGuaranteeList where guaranteeRate is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeAmount equals to DEFAULT_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldBeFound("guaranteeAmount.equals=" + DEFAULT_GUARANTEE_AMOUNT);

        // Get all the caseGuaranteeList where guaranteeAmount equals to UPDATED_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldNotBeFound("guaranteeAmount.equals=" + UPDATED_GUARANTEE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeAmountIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeAmount in DEFAULT_GUARANTEE_AMOUNT or UPDATED_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldBeFound("guaranteeAmount.in=" + DEFAULT_GUARANTEE_AMOUNT + "," + UPDATED_GUARANTEE_AMOUNT);

        // Get all the caseGuaranteeList where guaranteeAmount equals to UPDATED_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldNotBeFound("guaranteeAmount.in=" + UPDATED_GUARANTEE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeAmount is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeAmount.specified=true");

        // Get all the caseGuaranteeList where guaranteeAmount is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeAmountDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeAmountDollar equals to DEFAULT_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldBeFound("guaranteeAmountDollar.equals=" + DEFAULT_GUARANTEE_AMOUNT_DOLLAR);

        // Get all the caseGuaranteeList where guaranteeAmountDollar equals to UPDATED_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldNotBeFound("guaranteeAmountDollar.equals=" + UPDATED_GUARANTEE_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeAmountDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeAmountDollar in DEFAULT_GUARANTEE_AMOUNT_DOLLAR or UPDATED_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldBeFound("guaranteeAmountDollar.in=" + DEFAULT_GUARANTEE_AMOUNT_DOLLAR + "," + UPDATED_GUARANTEE_AMOUNT_DOLLAR);

        // Get all the caseGuaranteeList where guaranteeAmountDollar equals to UPDATED_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldNotBeFound("guaranteeAmountDollar.in=" + UPDATED_GUARANTEE_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeAmountDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeAmountDollar is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeAmountDollar.specified=true");

        // Get all the caseGuaranteeList where guaranteeAmountDollar is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeAmountDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeToIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeTo equals to DEFAULT_GUARANTEE_TO
        defaultCaseGuaranteeShouldBeFound("guaranteeTo.equals=" + DEFAULT_GUARANTEE_TO);

        // Get all the caseGuaranteeList where guaranteeTo equals to UPDATED_GUARANTEE_TO
        defaultCaseGuaranteeShouldNotBeFound("guaranteeTo.equals=" + UPDATED_GUARANTEE_TO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeToIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeTo in DEFAULT_GUARANTEE_TO or UPDATED_GUARANTEE_TO
        defaultCaseGuaranteeShouldBeFound("guaranteeTo.in=" + DEFAULT_GUARANTEE_TO + "," + UPDATED_GUARANTEE_TO);

        // Get all the caseGuaranteeList where guaranteeTo equals to UPDATED_GUARANTEE_TO
        defaultCaseGuaranteeShouldNotBeFound("guaranteeTo.in=" + UPDATED_GUARANTEE_TO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeToIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeTo is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeTo.specified=true");

        // Get all the caseGuaranteeList where guaranteeTo is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeToAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeToAddress equals to DEFAULT_GUARANTEE_TO_ADDRESS
        defaultCaseGuaranteeShouldBeFound("guaranteeToAddress.equals=" + DEFAULT_GUARANTEE_TO_ADDRESS);

        // Get all the caseGuaranteeList where guaranteeToAddress equals to UPDATED_GUARANTEE_TO_ADDRESS
        defaultCaseGuaranteeShouldNotBeFound("guaranteeToAddress.equals=" + UPDATED_GUARANTEE_TO_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeToAddressIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeToAddress in DEFAULT_GUARANTEE_TO_ADDRESS or UPDATED_GUARANTEE_TO_ADDRESS
        defaultCaseGuaranteeShouldBeFound("guaranteeToAddress.in=" + DEFAULT_GUARANTEE_TO_ADDRESS + "," + UPDATED_GUARANTEE_TO_ADDRESS);

        // Get all the caseGuaranteeList where guaranteeToAddress equals to UPDATED_GUARANTEE_TO_ADDRESS
        defaultCaseGuaranteeShouldNotBeFound("guaranteeToAddress.in=" + UPDATED_GUARANTEE_TO_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeToAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeToAddress is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeToAddress.specified=true");

        // Get all the caseGuaranteeList where guaranteeToAddress is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeToAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeNoIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeNo equals to DEFAULT_GUARANTEE_NO
        defaultCaseGuaranteeShouldBeFound("guaranteeNo.equals=" + DEFAULT_GUARANTEE_NO);

        // Get all the caseGuaranteeList where guaranteeNo equals to UPDATED_GUARANTEE_NO
        defaultCaseGuaranteeShouldNotBeFound("guaranteeNo.equals=" + UPDATED_GUARANTEE_NO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeNoIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeNo in DEFAULT_GUARANTEE_NO or UPDATED_GUARANTEE_NO
        defaultCaseGuaranteeShouldBeFound("guaranteeNo.in=" + DEFAULT_GUARANTEE_NO + "," + UPDATED_GUARANTEE_NO);

        // Get all the caseGuaranteeList where guaranteeNo equals to UPDATED_GUARANTEE_NO
        defaultCaseGuaranteeShouldNotBeFound("guaranteeNo.in=" + UPDATED_GUARANTEE_NO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeNo is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeNo.specified=true");

        // Get all the caseGuaranteeList where guaranteeNo is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeFee equals to DEFAULT_GUARANTEE_FEE
        defaultCaseGuaranteeShouldBeFound("guaranteeFee.equals=" + DEFAULT_GUARANTEE_FEE);

        // Get all the caseGuaranteeList where guaranteeFee equals to UPDATED_GUARANTEE_FEE
        defaultCaseGuaranteeShouldNotBeFound("guaranteeFee.equals=" + UPDATED_GUARANTEE_FEE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeFeeIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeFee in DEFAULT_GUARANTEE_FEE or UPDATED_GUARANTEE_FEE
        defaultCaseGuaranteeShouldBeFound("guaranteeFee.in=" + DEFAULT_GUARANTEE_FEE + "," + UPDATED_GUARANTEE_FEE);

        // Get all the caseGuaranteeList where guaranteeFee equals to UPDATED_GUARANTEE_FEE
        defaultCaseGuaranteeShouldNotBeFound("guaranteeFee.in=" + UPDATED_GUARANTEE_FEE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeFee is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeFee.specified=true");

        // Get all the caseGuaranteeList where guaranteeFee is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeOtherIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeOther equals to DEFAULT_GUARANTEE_OTHER
        defaultCaseGuaranteeShouldBeFound("guaranteeOther.equals=" + DEFAULT_GUARANTEE_OTHER);

        // Get all the caseGuaranteeList where guaranteeOther equals to UPDATED_GUARANTEE_OTHER
        defaultCaseGuaranteeShouldNotBeFound("guaranteeOther.equals=" + UPDATED_GUARANTEE_OTHER);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeOtherIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeOther in DEFAULT_GUARANTEE_OTHER or UPDATED_GUARANTEE_OTHER
        defaultCaseGuaranteeShouldBeFound("guaranteeOther.in=" + DEFAULT_GUARANTEE_OTHER + "," + UPDATED_GUARANTEE_OTHER);

        // Get all the caseGuaranteeList where guaranteeOther equals to UPDATED_GUARANTEE_OTHER
        defaultCaseGuaranteeShouldNotBeFound("guaranteeOther.in=" + UPDATED_GUARANTEE_OTHER);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeOtherIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where guaranteeOther is not null
        defaultCaseGuaranteeShouldBeFound("guaranteeOther.specified=true");

        // Get all the caseGuaranteeList where guaranteeOther is null
        defaultCaseGuaranteeShouldNotBeFound("guaranteeOther.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByCancelDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where cancelDate equals to DEFAULT_CANCEL_DATE
        defaultCaseGuaranteeShouldBeFound("cancelDate.equals=" + DEFAULT_CANCEL_DATE);

        // Get all the caseGuaranteeList where cancelDate equals to UPDATED_CANCEL_DATE
        defaultCaseGuaranteeShouldNotBeFound("cancelDate.equals=" + UPDATED_CANCEL_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByCancelDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where cancelDate in DEFAULT_CANCEL_DATE or UPDATED_CANCEL_DATE
        defaultCaseGuaranteeShouldBeFound("cancelDate.in=" + DEFAULT_CANCEL_DATE + "," + UPDATED_CANCEL_DATE);

        // Get all the caseGuaranteeList where cancelDate equals to UPDATED_CANCEL_DATE
        defaultCaseGuaranteeShouldNotBeFound("cancelDate.in=" + UPDATED_CANCEL_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByCancelDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where cancelDate is not null
        defaultCaseGuaranteeShouldBeFound("cancelDate.specified=true");

        // Get all the caseGuaranteeList where cancelDate is null
        defaultCaseGuaranteeShouldNotBeFound("cancelDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuarantee equals to DEFAULT_CON_GUARANTEE
        defaultCaseGuaranteeShouldBeFound("conGuarantee.equals=" + DEFAULT_CON_GUARANTEE);

        // Get all the caseGuaranteeList where conGuarantee equals to UPDATED_CON_GUARANTEE
        defaultCaseGuaranteeShouldNotBeFound("conGuarantee.equals=" + UPDATED_CON_GUARANTEE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuarantee in DEFAULT_CON_GUARANTEE or UPDATED_CON_GUARANTEE
        defaultCaseGuaranteeShouldBeFound("conGuarantee.in=" + DEFAULT_CON_GUARANTEE + "," + UPDATED_CON_GUARANTEE);

        // Get all the caseGuaranteeList where conGuarantee equals to UPDATED_CON_GUARANTEE
        defaultCaseGuaranteeShouldNotBeFound("conGuarantee.in=" + UPDATED_CON_GUARANTEE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuarantee is not null
        defaultCaseGuaranteeShouldBeFound("conGuarantee.specified=true");

        // Get all the caseGuaranteeList where conGuarantee is null
        defaultCaseGuaranteeShouldNotBeFound("conGuarantee.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeDate equals to DEFAULT_CON_GUARANTEE_DATE
        defaultCaseGuaranteeShouldBeFound("conGuaranteeDate.equals=" + DEFAULT_CON_GUARANTEE_DATE);

        // Get all the caseGuaranteeList where conGuaranteeDate equals to UPDATED_CON_GUARANTEE_DATE
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeDate.equals=" + UPDATED_CON_GUARANTEE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeDate in DEFAULT_CON_GUARANTEE_DATE or UPDATED_CON_GUARANTEE_DATE
        defaultCaseGuaranteeShouldBeFound("conGuaranteeDate.in=" + DEFAULT_CON_GUARANTEE_DATE + "," + UPDATED_CON_GUARANTEE_DATE);

        // Get all the caseGuaranteeList where conGuaranteeDate equals to UPDATED_CON_GUARANTEE_DATE
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeDate.in=" + UPDATED_CON_GUARANTEE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeDate is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeDate.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeDate is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCurrency equals to DEFAULT_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCurrency.equals=" + DEFAULT_CON_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where conGuaranteeCurrency equals to UPDATED_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCurrency.equals=" + UPDATED_CON_GUARANTEE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCurrency in DEFAULT_CON_GUARANTEE_CURRENCY or UPDATED_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCurrency.in=" + DEFAULT_CON_GUARANTEE_CURRENCY + "," + UPDATED_CON_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where conGuaranteeCurrency equals to UPDATED_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCurrency.in=" + UPDATED_CON_GUARANTEE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCurrency is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCurrency.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeCurrency is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCurrency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCurrency greater than or equals to DEFAULT_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCurrency.greaterOrEqualThan=" + DEFAULT_CON_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where conGuaranteeCurrency greater than or equals to UPDATED_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCurrency.greaterOrEqualThan=" + UPDATED_CON_GUARANTEE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCurrency less than or equals to DEFAULT_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCurrency.lessThan=" + DEFAULT_CON_GUARANTEE_CURRENCY);

        // Get all the caseGuaranteeList where conGuaranteeCurrency less than or equals to UPDATED_CON_GUARANTEE_CURRENCY
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCurrency.lessThan=" + UPDATED_CON_GUARANTEE_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeRateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeRate equals to DEFAULT_CON_GUARANTEE_RATE
        defaultCaseGuaranteeShouldBeFound("conGuaranteeRate.equals=" + DEFAULT_CON_GUARANTEE_RATE);

        // Get all the caseGuaranteeList where conGuaranteeRate equals to UPDATED_CON_GUARANTEE_RATE
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeRate.equals=" + UPDATED_CON_GUARANTEE_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeRateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeRate in DEFAULT_CON_GUARANTEE_RATE or UPDATED_CON_GUARANTEE_RATE
        defaultCaseGuaranteeShouldBeFound("conGuaranteeRate.in=" + DEFAULT_CON_GUARANTEE_RATE + "," + UPDATED_CON_GUARANTEE_RATE);

        // Get all the caseGuaranteeList where conGuaranteeRate equals to UPDATED_CON_GUARANTEE_RATE
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeRate.in=" + UPDATED_CON_GUARANTEE_RATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeRate is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeRate.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeRate is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeAmount equals to DEFAULT_CON_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldBeFound("conGuaranteeAmount.equals=" + DEFAULT_CON_GUARANTEE_AMOUNT);

        // Get all the caseGuaranteeList where conGuaranteeAmount equals to UPDATED_CON_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeAmount.equals=" + UPDATED_CON_GUARANTEE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeAmountIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeAmount in DEFAULT_CON_GUARANTEE_AMOUNT or UPDATED_CON_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldBeFound("conGuaranteeAmount.in=" + DEFAULT_CON_GUARANTEE_AMOUNT + "," + UPDATED_CON_GUARANTEE_AMOUNT);

        // Get all the caseGuaranteeList where conGuaranteeAmount equals to UPDATED_CON_GUARANTEE_AMOUNT
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeAmount.in=" + UPDATED_CON_GUARANTEE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeAmount is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeAmount.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeAmount is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeAmountDollarIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeAmountDollar equals to DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldBeFound("conGuaranteeAmountDollar.equals=" + DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR);

        // Get all the caseGuaranteeList where conGuaranteeAmountDollar equals to UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeAmountDollar.equals=" + UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeAmountDollarIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeAmountDollar in DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR or UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldBeFound("conGuaranteeAmountDollar.in=" + DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR + "," + UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR);

        // Get all the caseGuaranteeList where conGuaranteeAmountDollar equals to UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeAmountDollar.in=" + UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeAmountDollarIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeAmountDollar is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeAmountDollar.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeAmountDollar is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeAmountDollar.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeNoIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeNo equals to DEFAULT_CON_GUARANTEE_NO
        defaultCaseGuaranteeShouldBeFound("conGuaranteeNo.equals=" + DEFAULT_CON_GUARANTEE_NO);

        // Get all the caseGuaranteeList where conGuaranteeNo equals to UPDATED_CON_GUARANTEE_NO
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeNo.equals=" + UPDATED_CON_GUARANTEE_NO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeNoIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeNo in DEFAULT_CON_GUARANTEE_NO or UPDATED_CON_GUARANTEE_NO
        defaultCaseGuaranteeShouldBeFound("conGuaranteeNo.in=" + DEFAULT_CON_GUARANTEE_NO + "," + UPDATED_CON_GUARANTEE_NO);

        // Get all the caseGuaranteeList where conGuaranteeNo equals to UPDATED_CON_GUARANTEE_NO
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeNo.in=" + UPDATED_CON_GUARANTEE_NO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeNo is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeNo.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeNo is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeToIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeTo equals to DEFAULT_CON_GUARANTEE_TO
        defaultCaseGuaranteeShouldBeFound("conGuaranteeTo.equals=" + DEFAULT_CON_GUARANTEE_TO);

        // Get all the caseGuaranteeList where conGuaranteeTo equals to UPDATED_CON_GUARANTEE_TO
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeTo.equals=" + UPDATED_CON_GUARANTEE_TO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeToIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeTo in DEFAULT_CON_GUARANTEE_TO or UPDATED_CON_GUARANTEE_TO
        defaultCaseGuaranteeShouldBeFound("conGuaranteeTo.in=" + DEFAULT_CON_GUARANTEE_TO + "," + UPDATED_CON_GUARANTEE_TO);

        // Get all the caseGuaranteeList where conGuaranteeTo equals to UPDATED_CON_GUARANTEE_TO
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeTo.in=" + UPDATED_CON_GUARANTEE_TO);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeToIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeTo is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeTo.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeTo is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCancelDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCancelDate equals to DEFAULT_CON_GUARANTEE_CANCEL_DATE
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCancelDate.equals=" + DEFAULT_CON_GUARANTEE_CANCEL_DATE);

        // Get all the caseGuaranteeList where conGuaranteeCancelDate equals to UPDATED_CON_GUARANTEE_CANCEL_DATE
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCancelDate.equals=" + UPDATED_CON_GUARANTEE_CANCEL_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCancelDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCancelDate in DEFAULT_CON_GUARANTEE_CANCEL_DATE or UPDATED_CON_GUARANTEE_CANCEL_DATE
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCancelDate.in=" + DEFAULT_CON_GUARANTEE_CANCEL_DATE + "," + UPDATED_CON_GUARANTEE_CANCEL_DATE);

        // Get all the caseGuaranteeList where conGuaranteeCancelDate equals to UPDATED_CON_GUARANTEE_CANCEL_DATE
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCancelDate.in=" + UPDATED_CON_GUARANTEE_CANCEL_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeCancelDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where conGuaranteeCancelDate is not null
        defaultCaseGuaranteeShouldBeFound("conGuaranteeCancelDate.specified=true");

        // Get all the caseGuaranteeList where conGuaranteeCancelDate is null
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeCancelDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByReleaseDateIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where releaseDate equals to DEFAULT_RELEASE_DATE
        defaultCaseGuaranteeShouldBeFound("releaseDate.equals=" + DEFAULT_RELEASE_DATE);

        // Get all the caseGuaranteeList where releaseDate equals to UPDATED_RELEASE_DATE
        defaultCaseGuaranteeShouldNotBeFound("releaseDate.equals=" + UPDATED_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByReleaseDateIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where releaseDate in DEFAULT_RELEASE_DATE or UPDATED_RELEASE_DATE
        defaultCaseGuaranteeShouldBeFound("releaseDate.in=" + DEFAULT_RELEASE_DATE + "," + UPDATED_RELEASE_DATE);

        // Get all the caseGuaranteeList where releaseDate equals to UPDATED_RELEASE_DATE
        defaultCaseGuaranteeShouldNotBeFound("releaseDate.in=" + UPDATED_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByReleaseDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where releaseDate is not null
        defaultCaseGuaranteeShouldBeFound("releaseDate.specified=true");

        // Get all the caseGuaranteeList where releaseDate is null
        defaultCaseGuaranteeShouldNotBeFound("releaseDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByRegisterUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where registerUserId equals to DEFAULT_REGISTER_USER_ID
        defaultCaseGuaranteeShouldBeFound("registerUserId.equals=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseGuaranteeList where registerUserId equals to UPDATED_REGISTER_USER_ID
        defaultCaseGuaranteeShouldNotBeFound("registerUserId.equals=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByRegisterUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where registerUserId in DEFAULT_REGISTER_USER_ID or UPDATED_REGISTER_USER_ID
        defaultCaseGuaranteeShouldBeFound("registerUserId.in=" + DEFAULT_REGISTER_USER_ID + "," + UPDATED_REGISTER_USER_ID);

        // Get all the caseGuaranteeList where registerUserId equals to UPDATED_REGISTER_USER_ID
        defaultCaseGuaranteeShouldNotBeFound("registerUserId.in=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByRegisterUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where registerUserId is not null
        defaultCaseGuaranteeShouldBeFound("registerUserId.specified=true");

        // Get all the caseGuaranteeList where registerUserId is null
        defaultCaseGuaranteeShouldNotBeFound("registerUserId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByRegisterUserIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where registerUserId greater than or equals to DEFAULT_REGISTER_USER_ID
        defaultCaseGuaranteeShouldBeFound("registerUserId.greaterOrEqualThan=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseGuaranteeList where registerUserId greater than or equals to UPDATED_REGISTER_USER_ID
        defaultCaseGuaranteeShouldNotBeFound("registerUserId.greaterOrEqualThan=" + UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseGuaranteesByRegisterUserIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        // Get all the caseGuaranteeList where registerUserId less than or equals to DEFAULT_REGISTER_USER_ID
        defaultCaseGuaranteeShouldNotBeFound("registerUserId.lessThan=" + DEFAULT_REGISTER_USER_ID);

        // Get all the caseGuaranteeList where registerUserId less than or equals to UPDATED_REGISTER_USER_ID
        defaultCaseGuaranteeShouldBeFound("registerUserId.lessThan=" + UPDATED_REGISTER_USER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIntTest.createEntity(em);
        em.persist(subcase);
        em.flush();
        caseGuarantee.setSubcase(subcase);
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);
        Long subcaseId = subcase.getId();

        // Get all the caseGuaranteeList where subcase equals to subcaseId
        defaultCaseGuaranteeShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the caseGuaranteeList where subcase equals to subcaseId + 1
        defaultCaseGuaranteeShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByGuaranteeTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        GuaranteeType guaranteeType = GuaranteeTypeResourceIntTest.createEntity(em);
        em.persist(guaranteeType);
        em.flush();
        caseGuarantee.setGuaranteeType(guaranteeType);
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);
        Long guaranteeTypeId = guaranteeType.getId();

        // Get all the caseGuaranteeList where guaranteeType equals to guaranteeTypeId
        defaultCaseGuaranteeShouldBeFound("guaranteeTypeId.equals=" + guaranteeTypeId);

        // Get all the caseGuaranteeList where guaranteeType equals to guaranteeTypeId + 1
        defaultCaseGuaranteeShouldNotBeFound("guaranteeTypeId.equals=" + (guaranteeTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseGuaranteesByConGuaranteeTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        GuaranteeType conGuaranteeType = GuaranteeTypeResourceIntTest.createEntity(em);
        em.persist(conGuaranteeType);
        em.flush();
        caseGuarantee.setConGuaranteeType(conGuaranteeType);
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);
        Long conGuaranteeTypeId = conGuaranteeType.getId();

        // Get all the caseGuaranteeList where conGuaranteeType equals to conGuaranteeTypeId
        defaultCaseGuaranteeShouldBeFound("conGuaranteeTypeId.equals=" + conGuaranteeTypeId);

        // Get all the caseGuaranteeList where conGuaranteeType equals to conGuaranteeTypeId + 1
        defaultCaseGuaranteeShouldNotBeFound("conGuaranteeTypeId.equals=" + (conGuaranteeTypeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseGuaranteeShouldBeFound(String filter) throws Exception {
        restCaseGuaranteeMockMvc.perform(get("/api/case-guarantees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseGuarantee.getId().intValue())))
            .andExpect(jsonPath("$.[*].vesselId").value(hasItem(DEFAULT_VESSEL_ID.intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].arrestDate").value(hasItem(DEFAULT_ARREST_DATE.toString())))
            .andExpect(jsonPath("$.[*].arrestPort").value(hasItem(DEFAULT_ARREST_PORT.intValue())))
            .andExpect(jsonPath("$.[*].arrestVesselId").value(hasItem(DEFAULT_ARREST_VESSEL_ID.intValue())))
            .andExpect(jsonPath("$.[*].arrestVesselName").value(hasItem(DEFAULT_ARREST_VESSEL_NAME.toString())))
            .andExpect(jsonPath("$.[*].billOfLading").value(hasItem(DEFAULT_BILL_OF_LADING.toString())))
            .andExpect(jsonPath("$.[*].billLadingDate").value(hasItem(DEFAULT_BILL_LADING_DATE.toString())))
            .andExpect(jsonPath("$.[*].natureOfClaim").value(hasItem(DEFAULT_NATURE_OF_CLAIM.toString())))
            .andExpect(jsonPath("$.[*].guarantee").value(hasItem(DEFAULT_GUARANTEE.toString())))
            .andExpect(jsonPath("$.[*].guaranteeDate").value(hasItem(DEFAULT_GUARANTEE_DATE.toString())))
            .andExpect(jsonPath("$.[*].guaranteeCurrency").value(hasItem(DEFAULT_GUARANTEE_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeRate").value(hasItem(DEFAULT_GUARANTEE_RATE.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeAmount").value(hasItem(DEFAULT_GUARANTEE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeAmountDollar").value(hasItem(DEFAULT_GUARANTEE_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeTo").value(hasItem(DEFAULT_GUARANTEE_TO.toString())))
            .andExpect(jsonPath("$.[*].guaranteeToAddress").value(hasItem(DEFAULT_GUARANTEE_TO_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].guaranteeNo").value(hasItem(DEFAULT_GUARANTEE_NO.toString())))
            .andExpect(jsonPath("$.[*].guaranteeFee").value(hasItem(DEFAULT_GUARANTEE_FEE.intValue())))
            .andExpect(jsonPath("$.[*].guaranteeOther").value(hasItem(DEFAULT_GUARANTEE_OTHER.toString())))
            .andExpect(jsonPath("$.[*].cancelDate").value(hasItem(DEFAULT_CANCEL_DATE.toString())))
            .andExpect(jsonPath("$.[*].conGuarantee").value(hasItem(DEFAULT_CON_GUARANTEE.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeDate").value(hasItem(DEFAULT_CON_GUARANTEE_DATE.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeCurrency").value(hasItem(DEFAULT_CON_GUARANTEE_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeRate").value(hasItem(DEFAULT_CON_GUARANTEE_RATE.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeAmount").value(hasItem(DEFAULT_CON_GUARANTEE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeAmountDollar").value(hasItem(DEFAULT_CON_GUARANTEE_AMOUNT_DOLLAR.intValue())))
            .andExpect(jsonPath("$.[*].conGuaranteeNo").value(hasItem(DEFAULT_CON_GUARANTEE_NO.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeTo").value(hasItem(DEFAULT_CON_GUARANTEE_TO.toString())))
            .andExpect(jsonPath("$.[*].conGuaranteeCancelDate").value(hasItem(DEFAULT_CON_GUARANTEE_CANCEL_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].releaseDate").value(hasItem(DEFAULT_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerUserId").value(hasItem(DEFAULT_REGISTER_USER_ID.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseGuaranteeShouldNotBeFound(String filter) throws Exception {
        restCaseGuaranteeMockMvc.perform(get("/api/case-guarantees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseGuarantee() throws Exception {
        // Get the caseGuarantee
        restCaseGuaranteeMockMvc.perform(get("/api/case-guarantees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseGuarantee() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        int databaseSizeBeforeUpdate = caseGuaranteeRepository.findAll().size();

        // Update the caseGuarantee
        CaseGuarantee updatedCaseGuarantee = caseGuaranteeRepository.findById(caseGuarantee.getId()).get();
        // Disconnect from session so that the updates on updatedCaseGuarantee are not directly saved in db
        em.detach(updatedCaseGuarantee);
        updatedCaseGuarantee
            .vesselId(UPDATED_VESSEL_ID)
            .numberId(UPDATED_NUMBER_ID)
            .arrestDate(UPDATED_ARREST_DATE)
            .arrestPort(UPDATED_ARREST_PORT)
            .arrestVesselId(UPDATED_ARREST_VESSEL_ID)
            .arrestVesselName(UPDATED_ARREST_VESSEL_NAME)
            .billOfLading(UPDATED_BILL_OF_LADING)
            .billLadingDate(UPDATED_BILL_LADING_DATE)
            .natureOfClaim(UPDATED_NATURE_OF_CLAIM)
            .guarantee(UPDATED_GUARANTEE)
            .guaranteeDate(UPDATED_GUARANTEE_DATE)
            .guaranteeCurrency(UPDATED_GUARANTEE_CURRENCY)
            .guaranteeRate(UPDATED_GUARANTEE_RATE)
            .guaranteeAmount(UPDATED_GUARANTEE_AMOUNT)
            .guaranteeAmountDollar(UPDATED_GUARANTEE_AMOUNT_DOLLAR)
            .guaranteeTo(UPDATED_GUARANTEE_TO)
            .guaranteeToAddress(UPDATED_GUARANTEE_TO_ADDRESS)
            .guaranteeNo(UPDATED_GUARANTEE_NO)
            .guaranteeFee(UPDATED_GUARANTEE_FEE)
            .guaranteeOther(UPDATED_GUARANTEE_OTHER)
            .cancelDate(UPDATED_CANCEL_DATE)
            .conGuarantee(UPDATED_CON_GUARANTEE)
            .conGuaranteeDate(UPDATED_CON_GUARANTEE_DATE)
            .conGuaranteeCurrency(UPDATED_CON_GUARANTEE_CURRENCY)
            .conGuaranteeRate(UPDATED_CON_GUARANTEE_RATE)
            .conGuaranteeAmount(UPDATED_CON_GUARANTEE_AMOUNT)
            .conGuaranteeAmountDollar(UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR)
            .conGuaranteeNo(UPDATED_CON_GUARANTEE_NO)
            .conGuaranteeTo(UPDATED_CON_GUARANTEE_TO)
            .conGuaranteeCancelDate(UPDATED_CON_GUARANTEE_CANCEL_DATE)
            .remark(UPDATED_REMARK)
            .releaseDate(UPDATED_RELEASE_DATE)
            .registerUserId(UPDATED_REGISTER_USER_ID);
        CaseGuaranteeDTO caseGuaranteeDTO = caseGuaranteeMapper.toDto(updatedCaseGuarantee);

        restCaseGuaranteeMockMvc.perform(put("/api/case-guarantees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseGuaranteeDTO)))
            .andExpect(status().isOk());

        // Validate the CaseGuarantee in the database
        List<CaseGuarantee> caseGuaranteeList = caseGuaranteeRepository.findAll();
        assertThat(caseGuaranteeList).hasSize(databaseSizeBeforeUpdate);
        CaseGuarantee testCaseGuarantee = caseGuaranteeList.get(caseGuaranteeList.size() - 1);
        assertThat(testCaseGuarantee.getVesselId()).isEqualTo(UPDATED_VESSEL_ID);
        assertThat(testCaseGuarantee.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseGuarantee.getArrestDate()).isEqualTo(UPDATED_ARREST_DATE);
        assertThat(testCaseGuarantee.getArrestPort()).isEqualTo(UPDATED_ARREST_PORT);
        assertThat(testCaseGuarantee.getArrestVesselId()).isEqualTo(UPDATED_ARREST_VESSEL_ID);
        assertThat(testCaseGuarantee.getArrestVesselName()).isEqualTo(UPDATED_ARREST_VESSEL_NAME);
        assertThat(testCaseGuarantee.getBillOfLading()).isEqualTo(UPDATED_BILL_OF_LADING);
        assertThat(testCaseGuarantee.getBillLadingDate()).isEqualTo(UPDATED_BILL_LADING_DATE);
        assertThat(testCaseGuarantee.getNatureOfClaim()).isEqualTo(UPDATED_NATURE_OF_CLAIM);
        assertThat(testCaseGuarantee.getGuarantee()).isEqualTo(UPDATED_GUARANTEE);
        assertThat(testCaseGuarantee.getGuaranteeDate()).isEqualTo(UPDATED_GUARANTEE_DATE);
        assertThat(testCaseGuarantee.getGuaranteeCurrency()).isEqualTo(UPDATED_GUARANTEE_CURRENCY);
        assertThat(testCaseGuarantee.getGuaranteeRate()).isEqualTo(UPDATED_GUARANTEE_RATE);
        assertThat(testCaseGuarantee.getGuaranteeAmount()).isEqualTo(UPDATED_GUARANTEE_AMOUNT);
        assertThat(testCaseGuarantee.getGuaranteeAmountDollar()).isEqualTo(UPDATED_GUARANTEE_AMOUNT_DOLLAR);
        assertThat(testCaseGuarantee.getGuaranteeTo()).isEqualTo(UPDATED_GUARANTEE_TO);
        assertThat(testCaseGuarantee.getGuaranteeToAddress()).isEqualTo(UPDATED_GUARANTEE_TO_ADDRESS);
        assertThat(testCaseGuarantee.getGuaranteeNo()).isEqualTo(UPDATED_GUARANTEE_NO);
        assertThat(testCaseGuarantee.getGuaranteeFee()).isEqualTo(UPDATED_GUARANTEE_FEE);
        assertThat(testCaseGuarantee.getGuaranteeOther()).isEqualTo(UPDATED_GUARANTEE_OTHER);
        assertThat(testCaseGuarantee.getCancelDate()).isEqualTo(UPDATED_CANCEL_DATE);
        assertThat(testCaseGuarantee.getConGuarantee()).isEqualTo(UPDATED_CON_GUARANTEE);
        assertThat(testCaseGuarantee.getConGuaranteeDate()).isEqualTo(UPDATED_CON_GUARANTEE_DATE);
        assertThat(testCaseGuarantee.getConGuaranteeCurrency()).isEqualTo(UPDATED_CON_GUARANTEE_CURRENCY);
        assertThat(testCaseGuarantee.getConGuaranteeRate()).isEqualTo(UPDATED_CON_GUARANTEE_RATE);
        assertThat(testCaseGuarantee.getConGuaranteeAmount()).isEqualTo(UPDATED_CON_GUARANTEE_AMOUNT);
        assertThat(testCaseGuarantee.getConGuaranteeAmountDollar()).isEqualTo(UPDATED_CON_GUARANTEE_AMOUNT_DOLLAR);
        assertThat(testCaseGuarantee.getConGuaranteeNo()).isEqualTo(UPDATED_CON_GUARANTEE_NO);
        assertThat(testCaseGuarantee.getConGuaranteeTo()).isEqualTo(UPDATED_CON_GUARANTEE_TO);
        assertThat(testCaseGuarantee.getConGuaranteeCancelDate()).isEqualTo(UPDATED_CON_GUARANTEE_CANCEL_DATE);
        assertThat(testCaseGuarantee.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCaseGuarantee.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testCaseGuarantee.getRegisterUserId()).isEqualTo(UPDATED_REGISTER_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseGuarantee() throws Exception {
        int databaseSizeBeforeUpdate = caseGuaranteeRepository.findAll().size();

        // Create the CaseGuarantee
        CaseGuaranteeDTO caseGuaranteeDTO = caseGuaranteeMapper.toDto(caseGuarantee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseGuaranteeMockMvc.perform(put("/api/case-guarantees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseGuaranteeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseGuarantee in the database
        List<CaseGuarantee> caseGuaranteeList = caseGuaranteeRepository.findAll();
        assertThat(caseGuaranteeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseGuarantee() throws Exception {
        // Initialize the database
        caseGuaranteeRepository.saveAndFlush(caseGuarantee);

        int databaseSizeBeforeDelete = caseGuaranteeRepository.findAll().size();

        // Get the caseGuarantee
        restCaseGuaranteeMockMvc.perform(delete("/api/case-guarantees/{id}", caseGuarantee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseGuarantee> caseGuaranteeList = caseGuaranteeRepository.findAll();
        assertThat(caseGuaranteeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseGuarantee.class);
        CaseGuarantee caseGuarantee1 = new CaseGuarantee();
        caseGuarantee1.setId(1L);
        CaseGuarantee caseGuarantee2 = new CaseGuarantee();
        caseGuarantee2.setId(caseGuarantee1.getId());
        assertThat(caseGuarantee1).isEqualTo(caseGuarantee2);
        caseGuarantee2.setId(2L);
        assertThat(caseGuarantee1).isNotEqualTo(caseGuarantee2);
        caseGuarantee1.setId(null);
        assertThat(caseGuarantee1).isNotEqualTo(caseGuarantee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseGuaranteeDTO.class);
        CaseGuaranteeDTO caseGuaranteeDTO1 = new CaseGuaranteeDTO();
        caseGuaranteeDTO1.setId(1L);
        CaseGuaranteeDTO caseGuaranteeDTO2 = new CaseGuaranteeDTO();
        assertThat(caseGuaranteeDTO1).isNotEqualTo(caseGuaranteeDTO2);
        caseGuaranteeDTO2.setId(caseGuaranteeDTO1.getId());
        assertThat(caseGuaranteeDTO1).isEqualTo(caseGuaranteeDTO2);
        caseGuaranteeDTO2.setId(2L);
        assertThat(caseGuaranteeDTO1).isNotEqualTo(caseGuaranteeDTO2);
        caseGuaranteeDTO1.setId(null);
        assertThat(caseGuaranteeDTO1).isNotEqualTo(caseGuaranteeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseGuaranteeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseGuaranteeMapper.fromId(null)).isNull();
    }
}
