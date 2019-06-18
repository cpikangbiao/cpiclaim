package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseFeeBill;
import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.repository.CaseFeeBillRepository;
import com.cpi.claim.service.CaseFeeBillService;
import com.cpi.claim.service.dto.CaseFeeBillDTO;
import com.cpi.claim.service.mapper.CaseFeeBillMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseFeeBillCriteria;
import com.cpi.claim.service.CaseFeeBillQueryService;

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
import java.util.List;


import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CaseFeeBillResource REST controller.
 *
 * @see CaseFeeBillResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseFeeBillResourceIntTest {

    private static final Integer DEFAULT_NUMBER_ID = 1;
    private static final Integer UPDATED_NUMBER_ID = 2;

    private static final Boolean DEFAULT_IS_WRITE_OFF = false;
    private static final Boolean UPDATED_IS_WRITE_OFF = true;

    @Autowired
    private CaseFeeBillRepository caseFeeBillRepository;


    @Autowired
    private CaseFeeBillMapper caseFeeBillMapper;
    

    @Autowired
    private CaseFeeBillService caseFeeBillService;

    @Autowired
    private CaseFeeBillQueryService caseFeeBillQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseFeeBillMockMvc;

    private CaseFeeBill caseFeeBill;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseFeeBillResource caseFeeBillResource = new CaseFeeBillResource(caseFeeBillService, caseFeeBillQueryService);
        this.restCaseFeeBillMockMvc = MockMvcBuilders.standaloneSetup(caseFeeBillResource)
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
    public static CaseFeeBill createEntity(EntityManager em) {
        CaseFeeBill caseFeeBill = new CaseFeeBill()
            .numberId(DEFAULT_NUMBER_ID)
            .isWriteOff(DEFAULT_IS_WRITE_OFF);
        return caseFeeBill;
    }

    @Before
    public void initTest() {
        caseFeeBill = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseFeeBill() throws Exception {
        int databaseSizeBeforeCreate = caseFeeBillRepository.findAll().size();

        // Create the CaseFeeBill
        CaseFeeBillDTO caseFeeBillDTO = caseFeeBillMapper.toDto(caseFeeBill);
        restCaseFeeBillMockMvc.perform(post("/api/case-fee-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeBillDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseFeeBill in the database
        List<CaseFeeBill> caseFeeBillList = caseFeeBillRepository.findAll();
        assertThat(caseFeeBillList).hasSize(databaseSizeBeforeCreate + 1);
        CaseFeeBill testCaseFeeBill = caseFeeBillList.get(caseFeeBillList.size() - 1);
        assertThat(testCaseFeeBill.getNumberId()).isEqualTo(DEFAULT_NUMBER_ID);
        assertThat(testCaseFeeBill.isIsWriteOff()).isEqualTo(DEFAULT_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void createCaseFeeBillWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseFeeBillRepository.findAll().size();

        // Create the CaseFeeBill with an existing ID
        caseFeeBill.setId(1L);
        CaseFeeBillDTO caseFeeBillDTO = caseFeeBillMapper.toDto(caseFeeBill);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseFeeBillMockMvc.perform(post("/api/case-fee-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeBillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseFeeBill in the database
        List<CaseFeeBill> caseFeeBillList = caseFeeBillRepository.findAll();
        assertThat(caseFeeBillList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseFeeBills() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList
        restCaseFeeBillMockMvc.perform(get("/api/case-fee-bills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseFeeBill.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].isWriteOff").value(hasItem(DEFAULT_IS_WRITE_OFF.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getCaseFeeBill() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get the caseFeeBill
        restCaseFeeBillMockMvc.perform(get("/api/case-fee-bills/{id}", caseFeeBill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseFeeBill.getId().intValue()))
            .andExpect(jsonPath("$.numberId").value(DEFAULT_NUMBER_ID))
            .andExpect(jsonPath("$.isWriteOff").value(DEFAULT_IS_WRITE_OFF.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByNumberIdIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where numberId equals to DEFAULT_NUMBER_ID
        defaultCaseFeeBillShouldBeFound("numberId.equals=" + DEFAULT_NUMBER_ID);

        // Get all the caseFeeBillList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseFeeBillShouldNotBeFound("numberId.equals=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByNumberIdIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where numberId in DEFAULT_NUMBER_ID or UPDATED_NUMBER_ID
        defaultCaseFeeBillShouldBeFound("numberId.in=" + DEFAULT_NUMBER_ID + "," + UPDATED_NUMBER_ID);

        // Get all the caseFeeBillList where numberId equals to UPDATED_NUMBER_ID
        defaultCaseFeeBillShouldNotBeFound("numberId.in=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByNumberIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where numberId is not null
        defaultCaseFeeBillShouldBeFound("numberId.specified=true");

        // Get all the caseFeeBillList where numberId is null
        defaultCaseFeeBillShouldNotBeFound("numberId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByNumberIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where numberId greater than or equals to DEFAULT_NUMBER_ID
        defaultCaseFeeBillShouldBeFound("numberId.greaterOrEqualThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseFeeBillList where numberId greater than or equals to UPDATED_NUMBER_ID
        defaultCaseFeeBillShouldNotBeFound("numberId.greaterOrEqualThan=" + UPDATED_NUMBER_ID);
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByNumberIdIsLessThanSomething() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where numberId less than or equals to DEFAULT_NUMBER_ID
        defaultCaseFeeBillShouldNotBeFound("numberId.lessThan=" + DEFAULT_NUMBER_ID);

        // Get all the caseFeeBillList where numberId less than or equals to UPDATED_NUMBER_ID
        defaultCaseFeeBillShouldBeFound("numberId.lessThan=" + UPDATED_NUMBER_ID);
    }


    @Test
    @Transactional
    public void getAllCaseFeeBillsByIsWriteOffIsEqualToSomething() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where isWriteOff equals to DEFAULT_IS_WRITE_OFF
        defaultCaseFeeBillShouldBeFound("isWriteOff.equals=" + DEFAULT_IS_WRITE_OFF);

        // Get all the caseFeeBillList where isWriteOff equals to UPDATED_IS_WRITE_OFF
        defaultCaseFeeBillShouldNotBeFound("isWriteOff.equals=" + UPDATED_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByIsWriteOffIsInShouldWork() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where isWriteOff in DEFAULT_IS_WRITE_OFF or UPDATED_IS_WRITE_OFF
        defaultCaseFeeBillShouldBeFound("isWriteOff.in=" + DEFAULT_IS_WRITE_OFF + "," + UPDATED_IS_WRITE_OFF);

        // Get all the caseFeeBillList where isWriteOff equals to UPDATED_IS_WRITE_OFF
        defaultCaseFeeBillShouldNotBeFound("isWriteOff.in=" + UPDATED_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByIsWriteOffIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        // Get all the caseFeeBillList where isWriteOff is not null
        defaultCaseFeeBillShouldBeFound("isWriteOff.specified=true");

        // Get all the caseFeeBillList where isWriteOff is null
        defaultCaseFeeBillShouldNotBeFound("isWriteOff.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseFeeBillsByCaseFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseFee caseFee = CaseFeeResourceIntTest.createEntity(em);
        em.persist(caseFee);
        em.flush();
        caseFeeBill.setCaseFee(caseFee);
        caseFeeBillRepository.saveAndFlush(caseFeeBill);
        Long caseFeeId = caseFee.getId();

        // Get all the caseFeeBillList where caseFee equals to caseFeeId
        defaultCaseFeeBillShouldBeFound("caseFeeId.equals=" + caseFeeId);

        // Get all the caseFeeBillList where caseFee equals to caseFeeId + 1
        defaultCaseFeeBillShouldNotBeFound("caseFeeId.equals=" + (caseFeeId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseFeeBillsByCaseClaimBillIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseClaimBill caseClaimBill = CaseClaimBillResourceIntTest.createEntity(em);
        em.persist(caseClaimBill);
        em.flush();
        caseFeeBill.setCaseClaimBill(caseClaimBill);
        caseFeeBillRepository.saveAndFlush(caseFeeBill);
        Long caseClaimBillId = caseClaimBill.getId();

        // Get all the caseFeeBillList where caseClaimBill equals to caseClaimBillId
        defaultCaseFeeBillShouldBeFound("caseClaimBillId.equals=" + caseClaimBillId);

        // Get all the caseFeeBillList where caseClaimBill equals to caseClaimBillId + 1
        defaultCaseFeeBillShouldNotBeFound("caseClaimBillId.equals=" + (caseClaimBillId + 1));
    }


    @Test
    @Transactional
    public void getAllCaseFeeBillsByWriteOffBillIsEqualToSomething() throws Exception {
        // Initialize the database
        CaseClaimBill writeOffBill = CaseClaimBillResourceIntTest.createEntity(em);
        em.persist(writeOffBill);
        em.flush();
        caseFeeBill.setWriteOffBill(writeOffBill);
        caseFeeBillRepository.saveAndFlush(caseFeeBill);
        Long writeOffBillId = writeOffBill.getId();

        // Get all the caseFeeBillList where writeOffBill equals to writeOffBillId
        defaultCaseFeeBillShouldBeFound("writeOffBillId.equals=" + writeOffBillId);

        // Get all the caseFeeBillList where writeOffBill equals to writeOffBillId + 1
        defaultCaseFeeBillShouldNotBeFound("writeOffBillId.equals=" + (writeOffBillId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseFeeBillShouldBeFound(String filter) throws Exception {
        restCaseFeeBillMockMvc.perform(get("/api/case-fee-bills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseFeeBill.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberId").value(hasItem(DEFAULT_NUMBER_ID)))
            .andExpect(jsonPath("$.[*].isWriteOff").value(hasItem(DEFAULT_IS_WRITE_OFF.booleanValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseFeeBillShouldNotBeFound(String filter) throws Exception {
        restCaseFeeBillMockMvc.perform(get("/api/case-fee-bills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseFeeBill() throws Exception {
        // Get the caseFeeBill
        restCaseFeeBillMockMvc.perform(get("/api/case-fee-bills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseFeeBill() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        int databaseSizeBeforeUpdate = caseFeeBillRepository.findAll().size();

        // Update the caseFeeBill
        CaseFeeBill updatedCaseFeeBill = caseFeeBillRepository.findById(caseFeeBill.getId()).get();
        // Disconnect from session so that the updates on updatedCaseFeeBill are not directly saved in db
        em.detach(updatedCaseFeeBill);
        updatedCaseFeeBill
            .numberId(UPDATED_NUMBER_ID)
            .isWriteOff(UPDATED_IS_WRITE_OFF);
        CaseFeeBillDTO caseFeeBillDTO = caseFeeBillMapper.toDto(updatedCaseFeeBill);

        restCaseFeeBillMockMvc.perform(put("/api/case-fee-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeBillDTO)))
            .andExpect(status().isOk());

        // Validate the CaseFeeBill in the database
        List<CaseFeeBill> caseFeeBillList = caseFeeBillRepository.findAll();
        assertThat(caseFeeBillList).hasSize(databaseSizeBeforeUpdate);
        CaseFeeBill testCaseFeeBill = caseFeeBillList.get(caseFeeBillList.size() - 1);
        assertThat(testCaseFeeBill.getNumberId()).isEqualTo(UPDATED_NUMBER_ID);
        assertThat(testCaseFeeBill.isIsWriteOff()).isEqualTo(UPDATED_IS_WRITE_OFF);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseFeeBill() throws Exception {
        int databaseSizeBeforeUpdate = caseFeeBillRepository.findAll().size();

        // Create the CaseFeeBill
        CaseFeeBillDTO caseFeeBillDTO = caseFeeBillMapper.toDto(caseFeeBill);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCaseFeeBillMockMvc.perform(put("/api/case-fee-bills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseFeeBillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseFeeBill in the database
        List<CaseFeeBill> caseFeeBillList = caseFeeBillRepository.findAll();
        assertThat(caseFeeBillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseFeeBill() throws Exception {
        // Initialize the database
        caseFeeBillRepository.saveAndFlush(caseFeeBill);

        int databaseSizeBeforeDelete = caseFeeBillRepository.findAll().size();

        // Get the caseFeeBill
        restCaseFeeBillMockMvc.perform(delete("/api/case-fee-bills/{id}", caseFeeBill.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseFeeBill> caseFeeBillList = caseFeeBillRepository.findAll();
        assertThat(caseFeeBillList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseFeeBill.class);
        CaseFeeBill caseFeeBill1 = new CaseFeeBill();
        caseFeeBill1.setId(1L);
        CaseFeeBill caseFeeBill2 = new CaseFeeBill();
        caseFeeBill2.setId(caseFeeBill1.getId());
        assertThat(caseFeeBill1).isEqualTo(caseFeeBill2);
        caseFeeBill2.setId(2L);
        assertThat(caseFeeBill1).isNotEqualTo(caseFeeBill2);
        caseFeeBill1.setId(null);
        assertThat(caseFeeBill1).isNotEqualTo(caseFeeBill2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseFeeBillDTO.class);
        CaseFeeBillDTO caseFeeBillDTO1 = new CaseFeeBillDTO();
        caseFeeBillDTO1.setId(1L);
        CaseFeeBillDTO caseFeeBillDTO2 = new CaseFeeBillDTO();
        assertThat(caseFeeBillDTO1).isNotEqualTo(caseFeeBillDTO2);
        caseFeeBillDTO2.setId(caseFeeBillDTO1.getId());
        assertThat(caseFeeBillDTO1).isEqualTo(caseFeeBillDTO2);
        caseFeeBillDTO2.setId(2L);
        assertThat(caseFeeBillDTO1).isNotEqualTo(caseFeeBillDTO2);
        caseFeeBillDTO1.setId(null);
        assertThat(caseFeeBillDTO1).isNotEqualTo(caseFeeBillDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseFeeBillMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseFeeBillMapper.fromId(null)).isNull();
    }
}
