package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.RecoveryType;
import com.cpi.claim.repository.RecoveryTypeRepository;
import com.cpi.claim.service.RecoveryTypeService;
import com.cpi.claim.service.dto.RecoveryTypeDTO;
import com.cpi.claim.service.mapper.RecoveryTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.RecoveryTypeCriteria;
import com.cpi.claim.service.RecoveryTypeQueryService;

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
 * Integration tests for the {@Link RecoveryTypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class RecoveryTypeResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_RECOVERY_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECOVERY_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private RecoveryTypeRepository recoveryTypeRepository;

    @Autowired
    private RecoveryTypeMapper recoveryTypeMapper;

    @Autowired
    private RecoveryTypeService recoveryTypeService;

    @Autowired
    private RecoveryTypeQueryService recoveryTypeQueryService;

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

    private MockMvc restRecoveryTypeMockMvc;

    private RecoveryType recoveryType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecoveryTypeResource recoveryTypeResource = new RecoveryTypeResource(recoveryTypeService, recoveryTypeQueryService);
        this.restRecoveryTypeMockMvc = MockMvcBuilders.standaloneSetup(recoveryTypeResource)
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
    public static RecoveryType createEntity(EntityManager em) {
        RecoveryType recoveryType = new RecoveryType()
            .sortNum(DEFAULT_SORT_NUM)
            .recoveryTypeName(DEFAULT_RECOVERY_TYPE_NAME);
        return recoveryType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecoveryType createUpdatedEntity(EntityManager em) {
        RecoveryType recoveryType = new RecoveryType()
            .sortNum(UPDATED_SORT_NUM)
            .recoveryTypeName(UPDATED_RECOVERY_TYPE_NAME);
        return recoveryType;
    }

    @BeforeEach
    public void initTest() {
        recoveryType = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecoveryType() throws Exception {
        int databaseSizeBeforeCreate = recoveryTypeRepository.findAll().size();

        // Create the RecoveryType
        RecoveryTypeDTO recoveryTypeDTO = recoveryTypeMapper.toDto(recoveryType);
        restRecoveryTypeMockMvc.perform(post("/api/recovery-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recoveryTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the RecoveryType in the database
        List<RecoveryType> recoveryTypeList = recoveryTypeRepository.findAll();
        assertThat(recoveryTypeList).hasSize(databaseSizeBeforeCreate + 1);
        RecoveryType testRecoveryType = recoveryTypeList.get(recoveryTypeList.size() - 1);
        assertThat(testRecoveryType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testRecoveryType.getRecoveryTypeName()).isEqualTo(DEFAULT_RECOVERY_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createRecoveryTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recoveryTypeRepository.findAll().size();

        // Create the RecoveryType with an existing ID
        recoveryType.setId(1L);
        RecoveryTypeDTO recoveryTypeDTO = recoveryTypeMapper.toDto(recoveryType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecoveryTypeMockMvc.perform(post("/api/recovery-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recoveryTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecoveryType in the database
        List<RecoveryType> recoveryTypeList = recoveryTypeRepository.findAll();
        assertThat(recoveryTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRecoveryTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = recoveryTypeRepository.findAll().size();
        // set the field null
        recoveryType.setRecoveryTypeName(null);

        // Create the RecoveryType, which fails.
        RecoveryTypeDTO recoveryTypeDTO = recoveryTypeMapper.toDto(recoveryType);

        restRecoveryTypeMockMvc.perform(post("/api/recovery-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recoveryTypeDTO)))
            .andExpect(status().isBadRequest());

        List<RecoveryType> recoveryTypeList = recoveryTypeRepository.findAll();
        assertThat(recoveryTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRecoveryTypes() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList
        restRecoveryTypeMockMvc.perform(get("/api/recovery-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recoveryType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].recoveryTypeName").value(hasItem(DEFAULT_RECOVERY_TYPE_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getRecoveryType() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get the recoveryType
        restRecoveryTypeMockMvc.perform(get("/api/recovery-types/{id}", recoveryType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recoveryType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.recoveryTypeName").value(DEFAULT_RECOVERY_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllRecoveryTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultRecoveryTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the recoveryTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultRecoveryTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRecoveryTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultRecoveryTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the recoveryTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultRecoveryTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRecoveryTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where sortNum is not null
        defaultRecoveryTypeShouldBeFound("sortNum.specified=true");

        // Get all the recoveryTypeList where sortNum is null
        defaultRecoveryTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllRecoveryTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultRecoveryTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the recoveryTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultRecoveryTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllRecoveryTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultRecoveryTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the recoveryTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultRecoveryTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllRecoveryTypesByRecoveryTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where recoveryTypeName equals to DEFAULT_RECOVERY_TYPE_NAME
        defaultRecoveryTypeShouldBeFound("recoveryTypeName.equals=" + DEFAULT_RECOVERY_TYPE_NAME);

        // Get all the recoveryTypeList where recoveryTypeName equals to UPDATED_RECOVERY_TYPE_NAME
        defaultRecoveryTypeShouldNotBeFound("recoveryTypeName.equals=" + UPDATED_RECOVERY_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllRecoveryTypesByRecoveryTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where recoveryTypeName in DEFAULT_RECOVERY_TYPE_NAME or UPDATED_RECOVERY_TYPE_NAME
        defaultRecoveryTypeShouldBeFound("recoveryTypeName.in=" + DEFAULT_RECOVERY_TYPE_NAME + "," + UPDATED_RECOVERY_TYPE_NAME);

        // Get all the recoveryTypeList where recoveryTypeName equals to UPDATED_RECOVERY_TYPE_NAME
        defaultRecoveryTypeShouldNotBeFound("recoveryTypeName.in=" + UPDATED_RECOVERY_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllRecoveryTypesByRecoveryTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        // Get all the recoveryTypeList where recoveryTypeName is not null
        defaultRecoveryTypeShouldBeFound("recoveryTypeName.specified=true");

        // Get all the recoveryTypeList where recoveryTypeName is null
        defaultRecoveryTypeShouldNotBeFound("recoveryTypeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRecoveryTypeShouldBeFound(String filter) throws Exception {
        restRecoveryTypeMockMvc.perform(get("/api/recovery-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recoveryType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].recoveryTypeName").value(hasItem(DEFAULT_RECOVERY_TYPE_NAME)));

        // Check, that the count call also returns 1
        restRecoveryTypeMockMvc.perform(get("/api/recovery-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRecoveryTypeShouldNotBeFound(String filter) throws Exception {
        restRecoveryTypeMockMvc.perform(get("/api/recovery-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRecoveryTypeMockMvc.perform(get("/api/recovery-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRecoveryType() throws Exception {
        // Get the recoveryType
        restRecoveryTypeMockMvc.perform(get("/api/recovery-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecoveryType() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        int databaseSizeBeforeUpdate = recoveryTypeRepository.findAll().size();

        // Update the recoveryType
        RecoveryType updatedRecoveryType = recoveryTypeRepository.findById(recoveryType.getId()).get();
        // Disconnect from session so that the updates on updatedRecoveryType are not directly saved in db
        em.detach(updatedRecoveryType);
        updatedRecoveryType
            .sortNum(UPDATED_SORT_NUM)
            .recoveryTypeName(UPDATED_RECOVERY_TYPE_NAME);
        RecoveryTypeDTO recoveryTypeDTO = recoveryTypeMapper.toDto(updatedRecoveryType);

        restRecoveryTypeMockMvc.perform(put("/api/recovery-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recoveryTypeDTO)))
            .andExpect(status().isOk());

        // Validate the RecoveryType in the database
        List<RecoveryType> recoveryTypeList = recoveryTypeRepository.findAll();
        assertThat(recoveryTypeList).hasSize(databaseSizeBeforeUpdate);
        RecoveryType testRecoveryType = recoveryTypeList.get(recoveryTypeList.size() - 1);
        assertThat(testRecoveryType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testRecoveryType.getRecoveryTypeName()).isEqualTo(UPDATED_RECOVERY_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingRecoveryType() throws Exception {
        int databaseSizeBeforeUpdate = recoveryTypeRepository.findAll().size();

        // Create the RecoveryType
        RecoveryTypeDTO recoveryTypeDTO = recoveryTypeMapper.toDto(recoveryType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecoveryTypeMockMvc.perform(put("/api/recovery-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recoveryTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecoveryType in the database
        List<RecoveryType> recoveryTypeList = recoveryTypeRepository.findAll();
        assertThat(recoveryTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecoveryType() throws Exception {
        // Initialize the database
        recoveryTypeRepository.saveAndFlush(recoveryType);

        int databaseSizeBeforeDelete = recoveryTypeRepository.findAll().size();

        // Delete the recoveryType
        restRecoveryTypeMockMvc.perform(delete("/api/recovery-types/{id}", recoveryType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<RecoveryType> recoveryTypeList = recoveryTypeRepository.findAll();
        assertThat(recoveryTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecoveryType.class);
        RecoveryType recoveryType1 = new RecoveryType();
        recoveryType1.setId(1L);
        RecoveryType recoveryType2 = new RecoveryType();
        recoveryType2.setId(recoveryType1.getId());
        assertThat(recoveryType1).isEqualTo(recoveryType2);
        recoveryType2.setId(2L);
        assertThat(recoveryType1).isNotEqualTo(recoveryType2);
        recoveryType1.setId(null);
        assertThat(recoveryType1).isNotEqualTo(recoveryType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecoveryTypeDTO.class);
        RecoveryTypeDTO recoveryTypeDTO1 = new RecoveryTypeDTO();
        recoveryTypeDTO1.setId(1L);
        RecoveryTypeDTO recoveryTypeDTO2 = new RecoveryTypeDTO();
        assertThat(recoveryTypeDTO1).isNotEqualTo(recoveryTypeDTO2);
        recoveryTypeDTO2.setId(recoveryTypeDTO1.getId());
        assertThat(recoveryTypeDTO1).isEqualTo(recoveryTypeDTO2);
        recoveryTypeDTO2.setId(2L);
        assertThat(recoveryTypeDTO1).isNotEqualTo(recoveryTypeDTO2);
        recoveryTypeDTO1.setId(null);
        assertThat(recoveryTypeDTO1).isNotEqualTo(recoveryTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(recoveryTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(recoveryTypeMapper.fromId(null)).isNull();
    }
}
