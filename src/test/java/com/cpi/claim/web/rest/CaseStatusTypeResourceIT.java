package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.repository.CaseStatusTypeRepository;
import com.cpi.claim.service.CaseStatusTypeService;
import com.cpi.claim.service.dto.CaseStatusTypeDTO;
import com.cpi.claim.service.mapper.CaseStatusTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CaseStatusTypeCriteria;
import com.cpi.claim.service.CaseStatusTypeQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cpi.claim.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CaseStatusTypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CaseStatusTypeResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_CASE_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CASE_STATUS_NAME = "BBBBBBBBBB";

    @Autowired
    private CaseStatusTypeRepository caseStatusTypeRepository;

    @Autowired
    private CaseStatusTypeMapper caseStatusTypeMapper;

    @Autowired
    private CaseStatusTypeService caseStatusTypeService;

    @Autowired
    private CaseStatusTypeQueryService caseStatusTypeQueryService;

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

    private MockMvc restCaseStatusTypeMockMvc;

    private CaseStatusType caseStatusType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseStatusTypeResource caseStatusTypeResource = new CaseStatusTypeResource(caseStatusTypeService, caseStatusTypeQueryService);
        this.restCaseStatusTypeMockMvc = MockMvcBuilders.standaloneSetup(caseStatusTypeResource)
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
    public static CaseStatusType createEntity(EntityManager em) {
        CaseStatusType caseStatusType = new CaseStatusType()
            .sortNum(DEFAULT_SORT_NUM)
            .caseStatusName(DEFAULT_CASE_STATUS_NAME);
        return caseStatusType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseStatusType createUpdatedEntity(EntityManager em) {
        CaseStatusType caseStatusType = new CaseStatusType()
            .sortNum(UPDATED_SORT_NUM)
            .caseStatusName(UPDATED_CASE_STATUS_NAME);
        return caseStatusType;
    }

    @BeforeEach
    public void initTest() {
        caseStatusType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseStatusType() throws Exception {
        int databaseSizeBeforeCreate = caseStatusTypeRepository.findAll().size();

        // Create the CaseStatusType
        CaseStatusTypeDTO caseStatusTypeDTO = caseStatusTypeMapper.toDto(caseStatusType);
        restCaseStatusTypeMockMvc.perform(post("/api/case-status-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseStatusType in the database
        List<CaseStatusType> caseStatusTypeList = caseStatusTypeRepository.findAll();
        assertThat(caseStatusTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CaseStatusType testCaseStatusType = caseStatusTypeList.get(caseStatusTypeList.size() - 1);
        assertThat(testCaseStatusType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testCaseStatusType.getCaseStatusName()).isEqualTo(DEFAULT_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void createCaseStatusTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseStatusTypeRepository.findAll().size();

        // Create the CaseStatusType with an existing ID
        caseStatusType.setId(1L);
        CaseStatusTypeDTO caseStatusTypeDTO = caseStatusTypeMapper.toDto(caseStatusType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseStatusTypeMockMvc.perform(post("/api/case-status-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseStatusType in the database
        List<CaseStatusType> caseStatusTypeList = caseStatusTypeRepository.findAll();
        assertThat(caseStatusTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCaseStatusNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = caseStatusTypeRepository.findAll().size();
        // set the field null
        caseStatusType.setCaseStatusName(null);

        // Create the CaseStatusType, which fails.
        CaseStatusTypeDTO caseStatusTypeDTO = caseStatusTypeMapper.toDto(caseStatusType);

        restCaseStatusTypeMockMvc.perform(post("/api/case-status-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusTypeDTO)))
            .andExpect(status().isBadRequest());

        List<CaseStatusType> caseStatusTypeList = caseStatusTypeRepository.findAll();
        assertThat(caseStatusTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypes() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList
        restCaseStatusTypeMockMvc.perform(get("/api/case-status-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseStatusType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].caseStatusName").value(hasItem(DEFAULT_CASE_STATUS_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getCaseStatusType() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get the caseStatusType
        restCaseStatusTypeMockMvc.perform(get("/api/case-status-types/{id}", caseStatusType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseStatusType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.caseStatusName").value(DEFAULT_CASE_STATUS_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultCaseStatusTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the caseStatusTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultCaseStatusTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultCaseStatusTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the caseStatusTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultCaseStatusTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where sortNum is not null
        defaultCaseStatusTypeShouldBeFound("sortNum.specified=true");

        // Get all the caseStatusTypeList where sortNum is null
        defaultCaseStatusTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultCaseStatusTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the caseStatusTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultCaseStatusTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultCaseStatusTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the caseStatusTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultCaseStatusTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllCaseStatusTypesByCaseStatusNameIsEqualToSomething() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where caseStatusName equals to DEFAULT_CASE_STATUS_NAME
        defaultCaseStatusTypeShouldBeFound("caseStatusName.equals=" + DEFAULT_CASE_STATUS_NAME);

        // Get all the caseStatusTypeList where caseStatusName equals to UPDATED_CASE_STATUS_NAME
        defaultCaseStatusTypeShouldNotBeFound("caseStatusName.equals=" + UPDATED_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypesByCaseStatusNameIsInShouldWork() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where caseStatusName in DEFAULT_CASE_STATUS_NAME or UPDATED_CASE_STATUS_NAME
        defaultCaseStatusTypeShouldBeFound("caseStatusName.in=" + DEFAULT_CASE_STATUS_NAME + "," + UPDATED_CASE_STATUS_NAME);

        // Get all the caseStatusTypeList where caseStatusName equals to UPDATED_CASE_STATUS_NAME
        defaultCaseStatusTypeShouldNotBeFound("caseStatusName.in=" + UPDATED_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllCaseStatusTypesByCaseStatusNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        // Get all the caseStatusTypeList where caseStatusName is not null
        defaultCaseStatusTypeShouldBeFound("caseStatusName.specified=true");

        // Get all the caseStatusTypeList where caseStatusName is null
        defaultCaseStatusTypeShouldNotBeFound("caseStatusName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCaseStatusTypeShouldBeFound(String filter) throws Exception {
        restCaseStatusTypeMockMvc.perform(get("/api/case-status-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseStatusType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].caseStatusName").value(hasItem(DEFAULT_CASE_STATUS_NAME)));

        // Check, that the count call also returns 1
        restCaseStatusTypeMockMvc.perform(get("/api/case-status-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCaseStatusTypeShouldNotBeFound(String filter) throws Exception {
        restCaseStatusTypeMockMvc.perform(get("/api/case-status-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCaseStatusTypeMockMvc.perform(get("/api/case-status-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCaseStatusType() throws Exception {
        // Get the caseStatusType
        restCaseStatusTypeMockMvc.perform(get("/api/case-status-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseStatusType() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        int databaseSizeBeforeUpdate = caseStatusTypeRepository.findAll().size();

        // Update the caseStatusType
        CaseStatusType updatedCaseStatusType = caseStatusTypeRepository.findById(caseStatusType.getId()).get();
        // Disconnect from session so that the updates on updatedCaseStatusType are not directly saved in db
        em.detach(updatedCaseStatusType);
        updatedCaseStatusType
            .sortNum(UPDATED_SORT_NUM)
            .caseStatusName(UPDATED_CASE_STATUS_NAME);
        CaseStatusTypeDTO caseStatusTypeDTO = caseStatusTypeMapper.toDto(updatedCaseStatusType);

        restCaseStatusTypeMockMvc.perform(put("/api/case-status-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusTypeDTO)))
            .andExpect(status().isOk());

        // Validate the CaseStatusType in the database
        List<CaseStatusType> caseStatusTypeList = caseStatusTypeRepository.findAll();
        assertThat(caseStatusTypeList).hasSize(databaseSizeBeforeUpdate);
        CaseStatusType testCaseStatusType = caseStatusTypeList.get(caseStatusTypeList.size() - 1);
        assertThat(testCaseStatusType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testCaseStatusType.getCaseStatusName()).isEqualTo(UPDATED_CASE_STATUS_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseStatusType() throws Exception {
        int databaseSizeBeforeUpdate = caseStatusTypeRepository.findAll().size();

        // Create the CaseStatusType
        CaseStatusTypeDTO caseStatusTypeDTO = caseStatusTypeMapper.toDto(caseStatusType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseStatusTypeMockMvc.perform(put("/api/case-status-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseStatusType in the database
        List<CaseStatusType> caseStatusTypeList = caseStatusTypeRepository.findAll();
        assertThat(caseStatusTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseStatusType() throws Exception {
        // Initialize the database
        caseStatusTypeRepository.saveAndFlush(caseStatusType);

        int databaseSizeBeforeDelete = caseStatusTypeRepository.findAll().size();

        // Delete the caseStatusType
        restCaseStatusTypeMockMvc.perform(delete("/api/case-status-types/{id}", caseStatusType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CaseStatusType> caseStatusTypeList = caseStatusTypeRepository.findAll();
        assertThat(caseStatusTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseStatusType.class);
        CaseStatusType caseStatusType1 = new CaseStatusType();
        caseStatusType1.setId(1L);
        CaseStatusType caseStatusType2 = new CaseStatusType();
        caseStatusType2.setId(caseStatusType1.getId());
        assertThat(caseStatusType1).isEqualTo(caseStatusType2);
        caseStatusType2.setId(2L);
        assertThat(caseStatusType1).isNotEqualTo(caseStatusType2);
        caseStatusType1.setId(null);
        assertThat(caseStatusType1).isNotEqualTo(caseStatusType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseStatusTypeDTO.class);
        CaseStatusTypeDTO caseStatusTypeDTO1 = new CaseStatusTypeDTO();
        caseStatusTypeDTO1.setId(1L);
        CaseStatusTypeDTO caseStatusTypeDTO2 = new CaseStatusTypeDTO();
        assertThat(caseStatusTypeDTO1).isNotEqualTo(caseStatusTypeDTO2);
        caseStatusTypeDTO2.setId(caseStatusTypeDTO1.getId());
        assertThat(caseStatusTypeDTO1).isEqualTo(caseStatusTypeDTO2);
        caseStatusTypeDTO2.setId(2L);
        assertThat(caseStatusTypeDTO1).isNotEqualTo(caseStatusTypeDTO2);
        caseStatusTypeDTO1.setId(null);
        assertThat(caseStatusTypeDTO1).isNotEqualTo(caseStatusTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseStatusTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseStatusTypeMapper.fromId(null)).isNull();
    }
}
