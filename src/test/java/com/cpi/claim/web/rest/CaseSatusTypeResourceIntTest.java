package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;

import com.cpi.claim.config.SecurityBeanOverrideConfiguration;

import com.cpi.claim.domain.CaseSatusType;
import com.cpi.claim.repository.CaseSatusTypeRepository;
import com.cpi.claim.service.CaseSatusTypeService;
import com.cpi.claim.service.dto.CaseSatusTypeDTO;
import com.cpi.claim.service.mapper.CaseSatusTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseSatusTypeCriteria;
import com.cpi.claim.service.CaseSatusTypeQueryService;

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
 * Test class for the CaseSatusTypeResource REST controller.
 *
 * @see CaseSatusTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseSatusTypeResourceIntTest {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_CASE_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CASE_STATUS_NAME = "BBBBBBBBBB";

    @Autowired
    private CaseSatusTypeRepository caseSatusTypeRepository;


    @Autowired
    private CaseSatusTypeMapper caseSatusTypeMapper;
    

    @Autowired
    private CaseSatusTypeService caseSatusTypeService;

    @Autowired
    private CaseSatusTypeQueryService caseSatusTypeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCaseSatusTypeMockMvc;

    private CaseSatusType caseSatusType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseSatusTypeResource caseSatusTypeResource = new CaseSatusTypeResource(caseSatusTypeService, caseSatusTypeQueryService);
        this.restCaseSatusTypeMockMvc = MockMvcBuilders.standaloneSetup(caseSatusTypeResource)
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
    public static CaseSatusType createEntity(EntityManager em) {
        CaseSatusType caseSatusType = new CaseSatusType()
            .sortNum(DEFAULT_SORT_NUM)
            .caseStatusName(DEFAULT_CASE_STATUS_NAME);
        return caseSatusType;
    }

    @Before
    public void initTest() {
        caseSatusType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseSatusType() throws Exception {
        int databaseSizeBeforeCreate = caseSatusTypeRepository.findAll().size();

        // Create the CaseSatusType
        CaseSatusTypeDTO caseSatusTypeDTO = caseSatusTypeMapper.toDto(caseSatusType);
        restCaseSatusTypeMockMvc.perform(post("/api/case-satus-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSatusTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseSatusType in the database
        List<CaseSatusType> caseSatusTypeList = caseSatusTypeRepository.findAll();
        assertThat(caseSatusTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CaseSatusType testCaseSatusType = caseSatusTypeList.get(caseSatusTypeList.size() - 1);
        assertThat(testCaseSatusType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testCaseSatusType.getCaseStatusName()).isEqualTo(DEFAULT_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void createCaseSatusTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseSatusTypeRepository.findAll().size();

        // Create the CaseSatusType with an existing ID
        caseSatusType.setId(1L);
        CaseSatusTypeDTO caseSatusTypeDTO = caseSatusTypeMapper.toDto(caseSatusType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseSatusTypeMockMvc.perform(post("/api/case-satus-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSatusTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseSatusType in the database
        List<CaseSatusType> caseSatusTypeList = caseSatusTypeRepository.findAll();
        assertThat(caseSatusTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCaseStatusNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = caseSatusTypeRepository.findAll().size();
        // set the field null
        caseSatusType.setCaseStatusName(null);

        // Create the CaseSatusType, which fails.
        CaseSatusTypeDTO caseSatusTypeDTO = caseSatusTypeMapper.toDto(caseSatusType);

        restCaseSatusTypeMockMvc.perform(post("/api/case-satus-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSatusTypeDTO)))
            .andExpect(status().isBadRequest());

        List<CaseSatusType> caseSatusTypeList = caseSatusTypeRepository.findAll();
        assertThat(caseSatusTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypes() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList
        restCaseSatusTypeMockMvc.perform(get("/api/case-satus-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseSatusType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].caseStatusName").value(hasItem(DEFAULT_CASE_STATUS_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getCaseSatusType() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get the caseSatusType
        restCaseSatusTypeMockMvc.perform(get("/api/case-satus-types/{id}", caseSatusType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseSatusType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.caseStatusName").value(DEFAULT_CASE_STATUS_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultCaseSatusTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the caseSatusTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultCaseSatusTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultCaseSatusTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the caseSatusTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultCaseSatusTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where sortNum is not null
        defaultCaseSatusTypeShouldBeFound("sortNum.specified=true");

        // Get all the caseSatusTypeList where sortNum is null
        defaultCaseSatusTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultCaseSatusTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the caseSatusTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultCaseSatusTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultCaseSatusTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the caseSatusTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultCaseSatusTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllCaseSatusTypesByCaseStatusNameIsEqualToSomething() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where caseStatusName equals to DEFAULT_CASE_STATUS_NAME
        defaultCaseSatusTypeShouldBeFound("caseStatusName.equals=" + DEFAULT_CASE_STATUS_NAME);

        // Get all the caseSatusTypeList where caseStatusName equals to UPDATED_CASE_STATUS_NAME
        defaultCaseSatusTypeShouldNotBeFound("caseStatusName.equals=" + UPDATED_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypesByCaseStatusNameIsInShouldWork() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where caseStatusName in DEFAULT_CASE_STATUS_NAME or UPDATED_CASE_STATUS_NAME
        defaultCaseSatusTypeShouldBeFound("caseStatusName.in=" + DEFAULT_CASE_STATUS_NAME + "," + UPDATED_CASE_STATUS_NAME);

        // Get all the caseSatusTypeList where caseStatusName equals to UPDATED_CASE_STATUS_NAME
        defaultCaseSatusTypeShouldNotBeFound("caseStatusName.in=" + UPDATED_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseSatusTypesByCaseStatusNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        // Get all the caseSatusTypeList where caseStatusName is not null
        defaultCaseSatusTypeShouldBeFound("caseStatusName.specified=true");

        // Get all the caseSatusTypeList where caseStatusName is null
        defaultCaseSatusTypeShouldNotBeFound("caseStatusName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCaseSatusTypeShouldBeFound(String filter) throws Exception {
        restCaseSatusTypeMockMvc.perform(get("/api/case-satus-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseSatusType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].caseStatusName").value(hasItem(DEFAULT_CASE_STATUS_NAME.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCaseSatusTypeShouldNotBeFound(String filter) throws Exception {
        restCaseSatusTypeMockMvc.perform(get("/api/case-satus-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCaseSatusType() throws Exception {
        // Get the caseSatusType
        restCaseSatusTypeMockMvc.perform(get("/api/case-satus-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseSatusType() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        int databaseSizeBeforeUpdate = caseSatusTypeRepository.findAll().size();

        // Update the caseSatusType
        CaseSatusType updatedCaseSatusType = caseSatusTypeRepository.findById(caseSatusType.getId()).get();
        // Disconnect from session so that the updates on updatedCaseSatusType are not directly saved in db
        em.detach(updatedCaseSatusType);
        updatedCaseSatusType
            .sortNum(UPDATED_SORT_NUM)
            .caseStatusName(UPDATED_CASE_STATUS_NAME);
        CaseSatusTypeDTO caseSatusTypeDTO = caseSatusTypeMapper.toDto(updatedCaseSatusType);

        restCaseSatusTypeMockMvc.perform(put("/api/case-satus-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSatusTypeDTO)))
            .andExpect(status().isOk());

        // Validate the CaseSatusType in the database
        List<CaseSatusType> caseSatusTypeList = caseSatusTypeRepository.findAll();
        assertThat(caseSatusTypeList).hasSize(databaseSizeBeforeUpdate);
        CaseSatusType testCaseSatusType = caseSatusTypeList.get(caseSatusTypeList.size() - 1);
        assertThat(testCaseSatusType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testCaseSatusType.getCaseStatusName()).isEqualTo(UPDATED_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseSatusType() throws Exception {
        int databaseSizeBeforeUpdate = caseSatusTypeRepository.findAll().size();

        // Create the CaseSatusType
        CaseSatusTypeDTO caseSatusTypeDTO = caseSatusTypeMapper.toDto(caseSatusType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCaseSatusTypeMockMvc.perform(put("/api/case-satus-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseSatusTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseSatusType in the database
        List<CaseSatusType> caseSatusTypeList = caseSatusTypeRepository.findAll();
        assertThat(caseSatusTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseSatusType() throws Exception {
        // Initialize the database
        caseSatusTypeRepository.saveAndFlush(caseSatusType);

        int databaseSizeBeforeDelete = caseSatusTypeRepository.findAll().size();

        // Get the caseSatusType
        restCaseSatusTypeMockMvc.perform(delete("/api/case-satus-types/{id}", caseSatusType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseSatusType> caseSatusTypeList = caseSatusTypeRepository.findAll();
        assertThat(caseSatusTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseSatusType.class);
        CaseSatusType caseSatusType1 = new CaseSatusType();
        caseSatusType1.setId(1L);
        CaseSatusType caseSatusType2 = new CaseSatusType();
        caseSatusType2.setId(caseSatusType1.getId());
        assertThat(caseSatusType1).isEqualTo(caseSatusType2);
        caseSatusType2.setId(2L);
        assertThat(caseSatusType1).isNotEqualTo(caseSatusType2);
        caseSatusType1.setId(null);
        assertThat(caseSatusType1).isNotEqualTo(caseSatusType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseSatusTypeDTO.class);
        CaseSatusTypeDTO caseSatusTypeDTO1 = new CaseSatusTypeDTO();
        caseSatusTypeDTO1.setId(1L);
        CaseSatusTypeDTO caseSatusTypeDTO2 = new CaseSatusTypeDTO();
        assertThat(caseSatusTypeDTO1).isNotEqualTo(caseSatusTypeDTO2);
        caseSatusTypeDTO2.setId(caseSatusTypeDTO1.getId());
        assertThat(caseSatusTypeDTO1).isEqualTo(caseSatusTypeDTO2);
        caseSatusTypeDTO2.setId(2L);
        assertThat(caseSatusTypeDTO1).isNotEqualTo(caseSatusTypeDTO2);
        caseSatusTypeDTO1.setId(null);
        assertThat(caseSatusTypeDTO1).isNotEqualTo(caseSatusTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseSatusTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseSatusTypeMapper.fromId(null)).isNull();
    }
}
