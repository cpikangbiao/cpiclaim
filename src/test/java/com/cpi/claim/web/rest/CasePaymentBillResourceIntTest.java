package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CasePaymentBill;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.repository.CasePaymentBillRepository;
import com.cpi.claim.service.CasePaymentBillService;
import com.cpi.claim.service.dto.CasePaymentBillDTO;
import com.cpi.claim.service.mapper.CasePaymentBillMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CasePaymentBillCriteria;
import com.cpi.claim.service.CasePaymentBillQueryService;

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

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CasePaymentBillResource REST controller.
 *
 * @see CasePaymentBillResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CasePaymentBillResourceIntTest {

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Long DEFAULT_CURRENCY = 1L;
    private static final Long UPDATED_CURRENCY = 2L;

    private static final BigDecimal DEFAULT_AMOUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUT = new BigDecimal(2);

    private static final Boolean DEFAULT_IS_WRITE_OFF = false;
    private static final Boolean UPDATED_IS_WRITE_OFF = true;

    @Autowired
    private CasePaymentBillRepository casePaymentBillRepository;


    @Autowired
    private CasePaymentBillMapper casePaymentBillMapper;
    

    @Autowired
    private CasePaymentBillService casePaymentBillService;

    @Autowired
    private CasePaymentBillQueryService casePaymentBillQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCasePaymentBillMockMvc;

    private CasePaymentBill casePaymentBill;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CasePaymentBillResource casePaymentBillResource = new CasePaymentBillResource(casePaymentBillService, casePaymentBillQueryService);
        this.restCasePaymentBillMockMvc = MockMvcBuilders.standaloneSetup(casePaymentBillResource)
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
    public static CasePaymentBill createEntity(EntityManager em) {
        CasePaymentBill casePaymentBill = new CasePaymentBill()
            .numberId(DEFAULT_NUMBER_ID)
            .currency(DEFAULT_CURRENCY)
            .amout(DEFAULT_AMOUT)
            .isWriteOff(DEFAULT_IS_WRITE_OFF);
        return casePaymentBill;
    }

    @Before
    public void initTest() {
        casePaymentBill = createEntity(em);
    }

    @Test
    @Transactional
    public void createCasePaymentBill() throws Exception {
        int databaseSizeBeforeCreate = casePaymentBillRepository.findAll().size();

        // Create the CasePaymentBill
        CasePaymentBillDTO casePaymentBillDTO = casePaymentBillMapper.toDto(casePaymentBill);
        restCasePaymentBillMockMvc.perform(post("/api/case-payment-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentBillDTO)))
            .andExpect(status().isCreated());

        // Validate the CasePaymentBill in the database
        List<CasePaymentBill> casePaymentBillList = casePaymentBillRepository.findAll();
        assertThat(casePaymentBillList).hasSize(databaseSizeBeforeCreate + 1);
        CasePaymentBill testCasePaymentBill = casePaymentBillList.get(casePaymentBillList.size() - 1);
        assertThat(testCasePaymentBill.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCasePaymentBill.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testCasePaymentBill.getAmout()).isEqualTo(DEFAULT_AMOUT);
        assertThat(testCasePaymentBill.isIsWriteOff()).isEqualTo(DEFAULT_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void createCasePaymentBillWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = casePaymentBillRepository.findAll().size();

        // Create the CasePaymentBill with an existing ID
        casePaymentBill.setId(1L);
        CasePaymentBillDTO casePaymentBillDTO = casePaymentBillMapper.toDto(casePaymentBill);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCasePaymentBillMockMvc.perform(post("/api/case-payment-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentBillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CasePaymentBill in the database
        List<CasePaymentBill> casePaymentBillList = casePaymentBillRepository.findAll();
        assertThat(casePaymentBillList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBills() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList
        restCasePaymentBillMockMvc.perform(get("/api/case-payment-bills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casePaymentBill.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].amout").value(hasItem(DEFAULT_AMOUT.intValue())))
            .andExpect(jsonPath("$.[*].isWriteOff").value(hasItem(DEFAULT_IS_WRITE_OFF.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getCasePaymentBill() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get the casePaymentBill
        restCasePaymentBillMockMvc.perform(get("/api/case-payment-bills/{id}", casePaymentBill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(casePaymentBill.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.intValue()))
            .andExpect(jsonPath("$.amout").value(DEFAULT_AMOUT.intValue()))
            .andExpect(jsonPath("$.isWriteOff").value(DEFAULT_IS_WRITE_OFF.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where numberId equals to DEFAULT_NUMBER_ID
        defaultCasePaymentBillShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the casePaymentBillList where numberId equals to UPDATED_NUMBER_ID
        defaultCasePaymentBillShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCasePaymentBillShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the casePaymentBillList where numberId equals to UPDATED_NUMBER_ID
        defaultCasePaymentBillShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where numberId is not null
        defaultCasePaymentBillShouldBeFound("numberId.specified=true");

        // Get all the casePaymentBillList where numberId is null
        defaultCasePaymentBillShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCasePaymentBillShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the casePaymentBillList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCasePaymentBillShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCasePaymentBillShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the casePaymentBillList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCasePaymentBillShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCasePaymentBillsByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where currency equals to DEFAULT_CURRENCY
        defaultCasePaymentBillShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the casePaymentBillList where currency equals to UPDATED_CURRENCY
        defaultCasePaymentBillShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultCasePaymentBillShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the casePaymentBillList where currency equals to UPDATED_CURRENCY
        defaultCasePaymentBillShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where currency is not null
        defaultCasePaymentBillShouldBeFound("currency.specified=true");

        // Get all the casePaymentBillList where currency is null
        defaultCasePaymentBillShouldNotBeFound("currency.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByCurrencyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where currency greater than or equals to DEFAULT_CURRENCY
        defaultCasePaymentBillShouldBeFound("currency.greaterOrEqualThan=" + DEFAULT_CURRENCY);

        // Get all the casePaymentBillList where currency greater than or equals to UPDATED_CURRENCY
        defaultCasePaymentBillShouldNotBeFound("currency.greaterOrEqualThan=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByCurrencyIsLessThanSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where currency less than or equals to DEFAULT_CURRENCY
        defaultCasePaymentBillShouldNotBeFound("currency.lessThan=" + DEFAULT_CURRENCY);

        // Get all the casePaymentBillList where currency less than or equals to UPDATED_CURRENCY
        defaultCasePaymentBillShouldBeFound("currency.lessThan=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllCasePaymentBillsByAmoutIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where amout equals to DEFAULT_AMOUT
        defaultCasePaymentBillShouldBeFound("amout.equals=" + DEFAULT_AMOUT);

        // Get all the casePaymentBillList where amout equals to UPDATED_AMOUT
        defaultCasePaymentBillShouldNotBeFound("amout.equals=" + UPDATED_AMOUT);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByAmoutIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where amout in DEFAULT_AMOUT or UPDATED_AMOUT
        defaultCasePaymentBillShouldBeFound("amout.in=" + DEFAULT_AMOUT + "," + UPDATED_AMOUT);

        // Get all the casePaymentBillList where amout equals to UPDATED_AMOUT
        defaultCasePaymentBillShouldNotBeFound("amout.in=" + UPDATED_AMOUT);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByAmoutIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where amout is not null
        defaultCasePaymentBillShouldBeFound("amout.specified=true");

        // Get all the casePaymentBillList where amout is null
        defaultCasePaymentBillShouldNotBeFound("amout.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByIsWriteOffIsEqualToSomething() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where isWriteOff equals to DEFAULT_IS_WRITE_OFF
        defaultCasePaymentBillShouldBeFound("isWriteOff.equals=" + DEFAULT_IS_WRITE_OFF);

        // Get all the casePaymentBillList where isWriteOff equals to UPDATED_IS_WRITE_OFF
        defaultCasePaymentBillShouldNotBeFound("isWriteOff.equals=" + UPDATED_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByIsWriteOffIsInShouldWork() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where isWriteOff in DEFAULT_IS_WRITE_OFF or UPDATED_IS_WRITE_OFF
        defaultCasePaymentBillShouldBeFound("isWriteOff.in=" + DEFAULT_IS_WRITE_OFF + "," + UPDATED_IS_WRITE_OFF);

        // Get all the casePaymentBillList where isWriteOff equals to UPDATED_IS_WRITE_OFF
        defaultCasePaymentBillShouldNotBeFound("isWriteOff.in=" + UPDATED_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsByIsWriteOffIsNullOrNotNull() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        // Get all the casePaymentBillList where isWriteOff is not null
        defaultCasePaymentBillShouldBeFound("isWriteOff.specified=true");

        // Get all the casePaymentBillList where isWriteOff is null
        defaultCasePaymentBillShouldNotBeFound("isWriteOff.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasePaymentBillsBySubcaseIsEqualToSomething() throws Exception {
        // Initialize the database
        VesselSubCase subcase = VesselSubCaseResourceIntTest.createEntity(em);
        em.persist(subcase);
        em.flush();
        casePaymentBill.setSubcase(subcase);
        casePaymentBillRepository.saveAndFlush(casePaymentBill);
        Long subcaseId = subcase.getId();

        // Get all the casePaymentBillList where subcase equals to subcaseId
        defaultCasePaymentBillShouldBeFound("subcaseId.equals=" + subcaseId);

        // Get all the casePaymentBillList where subcase equals to subcaseId + 1
        defaultCasePaymentBillShouldNotBeFound("subcaseId.equals=" + (subcaseId + 1));
    }


    @Test
    @Transactional
    public void getAllCasePaymentBillsByCaseClaimBillIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseClaimBill caseClaimBill = CaseClaimBillResourceIntTest.createEntity(em);
        em.persist(caseClaimBill);
        em.flush();
        casePaymentBill.setCaseClaimBill(caseClaimBill);
        casePaymentBillRepository.saveAndFlush(casePaymentBill);
        Long caseClaimBillId = caseClaimBill.getId();

        // Get all the casePaymentBillList where caseClaimBill equals to caseClaimBillId
        defaultCasePaymentBillShouldBeFound("caseClaimBillId.equals=" + caseClaimBillId);

        // Get all the casePaymentBillList where caseClaimBill equals to caseClaimBillId + 1
        defaultCasePaymentBillShouldNotBeFound("caseClaimBillId.equals=" + (caseClaimBillId + 1));
    }


    @Test
    @Transactional
    public void getAllCasePaymentBillsByWriteOffBillIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseClaimBill writeOffBill = CaseClaimBillResourceIntTest.createEntity(em);
        em.persist(writeOffBill);
        em.flush();
        casePaymentBill.setWriteOffBill(writeOffBill);
        casePaymentBillRepository.saveAndFlush(casePaymentBill);
        Long writeOffBillId = writeOffBill.getId();

        // Get all the casePaymentBillList where writeOffBill equals to writeOffBillId
        defaultCasePaymentBillShouldBeFound("writeOffBillId.equals=" + writeOffBillId);

        // Get all the casePaymentBillList where writeOffBill equals to writeOffBillId + 1
        defaultCasePaymentBillShouldNotBeFound("writeOffBillId.equals=" + (writeOffBillId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCasePaymentBillShouldBeFound(String filter) throws Exception {
        restCasePaymentBillMockMvc.perform(get("/api/case-payment-bills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casePaymentBill.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.intValue())))
            .andExpect(jsonPath("$.[*].amout").value(hasItem(DEFAULT_AMOUT.intValue())))
            .andExpect(jsonPath("$.[*].isWriteOff").value(hasItem(DEFAULT_IS_WRITE_OFF.booleanValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCasePaymentBillShouldNotBeFound(String filter) throws Exception {
        restCasePaymentBillMockMvc.perform(get("/api/case-payment-bills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCasePaymentBill() throws Exception {
        // Get the casePaymentBill
        restCasePaymentBillMockMvc.perform(get("/api/case-payment-bills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCasePaymentBill() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        int databaseSizeBeforeUpdate = casePaymentBillRepository.findAll().size();

        // Update the casePaymentBill
        CasePaymentBill updatedCasePaymentBill = casePaymentBillRepository.findById(casePaymentBill.getId()).get();
        // Disconnect from session so that the updates on updatedCasePaymentBill are not directly saved in db
        em.detach(updatedCasePaymentBill);
        updatedCasePaymentBill
            .numberId(UPDATED_NUMBER_ID)
            .currency(UPDATED_CURRENCY)
            .amout(UPDATED_AMOUT)
            .isWriteOff(UPDATED_IS_WRITE_OFF);
        CasePaymentBillDTO casePaymentBillDTO = casePaymentBillMapper.toDto(updatedCasePaymentBill);

        restCasePaymentBillMockMvc.perform(put("/api/case-payment-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentBillDTO)))
            .andExpect(status().isOk());

        // Validate the CasePaymentBill in the database
        List<CasePaymentBill> casePaymentBillList = casePaymentBillRepository.findAll();
        assertThat(casePaymentBillList).hasSize(databaseSizeBeforeUpdate);
        CasePaymentBill testCasePaymentBill = casePaymentBillList.get(casePaymentBillList.size() - 1);
        assertThat(testCasePaymentBill.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCasePaymentBill.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCasePaymentBill.getAmout()).isEqualTo(UPDATED_AMOUT);
        assertThat(testCasePaymentBill.isIsWriteOff()).isEqualTo(UPDATED_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void updateNonExistingCasePaymentBill() throws Exception {
        int databaseSizeBeforeUpdate = casePaymentBillRepository.findAll().size();

        // Create the CasePaymentBill
        CasePaymentBillDTO casePaymentBillDTO = casePaymentBillMapper.toDto(casePaymentBill);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCasePaymentBillMockMvc.perform(put("/api/case-payment-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(casePaymentBillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CasePaymentBill in the database
        List<CasePaymentBill> casePaymentBillList = casePaymentBillRepository.findAll();
        assertThat(casePaymentBillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCasePaymentBill() throws Exception {
        // Initialize the database
        casePaymentBillRepository.saveAndFlush(casePaymentBill);

        int databaseSizeBeforeDelete = casePaymentBillRepository.findAll().size();

        // Get the casePaymentBill
        restCasePaymentBillMockMvc.perform(delete("/api/case-payment-bills/{id}", casePaymentBill.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CasePaymentBill> casePaymentBillList = casePaymentBillRepository.findAll();
        assertThat(casePaymentBillList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasePaymentBill.class);
        CasePaymentBill casePaymentBill1 = new CasePaymentBill();
        casePaymentBill1.setId(1L);
        CasePaymentBill casePaymentBill2 = new CasePaymentBill();
        casePaymentBill2.setId(casePaymentBill1.getId());
        assertThat(casePaymentBill1).isEqualTo(casePaymentBill2);
        casePaymentBill2.setId(2L);
        assertThat(casePaymentBill1).isNotEqualTo(casePaymentBill2);
        casePaymentBill1.setId(null);
        assertThat(casePaymentBill1).isNotEqualTo(casePaymentBill2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasePaymentBillDTO.class);
        CasePaymentBillDTO casePaymentBillDTO1 = new CasePaymentBillDTO();
        casePaymentBillDTO1.setId(1L);
        CasePaymentBillDTO casePaymentBillDTO2 = new CasePaymentBillDTO();
        assertThat(casePaymentBillDTO1).isNotEqualTo(casePaymentBillDTO2);
        casePaymentBillDTO2.setId(casePaymentBillDTO1.getId());
        assertThat(casePaymentBillDTO1).isEqualTo(casePaymentBillDTO2);
        casePaymentBillDTO2.setId(2L);
        assertThat(casePaymentBillDTO1).isNotEqualTo(casePaymentBillDTO2);
        casePaymentBillDTO1.setId(null);
        assertThat(casePaymentBillDTO1).isNotEqualTo(casePaymentBillDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(casePaymentBillMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(casePaymentBillMapper.fromId(null)).isNull();
    }
}
