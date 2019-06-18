package com.cpi.claim.web.rest;

import com.cpi.claim.CpiclaimApp;
import com.cpi.claim.config.SecurityBeanOverrideConfiguration;
import com.cpi.claim.domain.GuaranteeType;
import com.cpi.claim.repository.GuaranteeTypeRepository;
import com.cpi.claim.service.GuaranteeTypeService;
import com.cpi.claim.service.dto.GuaranteeTypeDTO;
import com.cpi.claim.service.mapper.GuaranteeTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.GuaranteeTypeCriteria;
import com.cpi.claim.service.GuaranteeTypeQueryService;

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
 * Integration tests for the {@Link GuaranteeTypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class GuaranteeTypeResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_GUARANTEE_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GUARANTEE_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private GuaranteeTypeRepository guaranteeTypeRepository;

    @Autowired
    private GuaranteeTypeMapper guaranteeTypeMapper;

    @Autowired
    private GuaranteeTypeService guaranteeTypeService;

    @Autowired
    private GuaranteeTypeQueryService guaranteeTypeQueryService;

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

    private MockMvc restGuaranteeTypeMockMvc;

    private GuaranteeType guaranteeType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GuaranteeTypeResource guaranteeTypeResource = new GuaranteeTypeResource(guaranteeTypeService, guaranteeTypeQueryService);
        this.restGuaranteeTypeMockMvc = MockMvcBuilders.standaloneSetup(guaranteeTypeResource)
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
    public static GuaranteeType createEntity(EntityManager em) {
        GuaranteeType guaranteeType = new GuaranteeType()
            .sortNum(DEFAULT_SORT_NUM)
            .guaranteeTypeName(DEFAULT_GUARANTEE_TYPE_NAME);
        return guaranteeType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GuaranteeType createUpdatedEntity(EntityManager em) {
        GuaranteeType guaranteeType = new GuaranteeType()
            .sortNum(UPDATED_SORT_NUM)
            .guaranteeTypeName(UPDATED_GUARANTEE_TYPE_NAME);
        return guaranteeType;
    }

    @BeforeEach
    public void initTest() {
        guaranteeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createGuaranteeType() throws Exception {
        int databaseSizeBeforeCreate = guaranteeTypeRepository.findAll().size();

        // Create the GuaranteeType
        GuaranteeTypeDTO guaranteeTypeDTO = guaranteeTypeMapper.toDto(guaranteeType);
        restGuaranteeTypeMockMvc.perform(post("/api/guarantee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guaranteeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the GuaranteeType in the database
        List<GuaranteeType> guaranteeTypeList = guaranteeTypeRepository.findAll();
        assertThat(guaranteeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        GuaranteeType testGuaranteeType = guaranteeTypeList.get(guaranteeTypeList.size() - 1);
        assertThat(testGuaranteeType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testGuaranteeType.getGuaranteeTypeName()).isEqualTo(DEFAULT_GUARANTEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createGuaranteeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = guaranteeTypeRepository.findAll().size();

        // Create the GuaranteeType with an existing ID
        guaranteeType.setId(1L);
        GuaranteeTypeDTO guaranteeTypeDTO = guaranteeTypeMapper.toDto(guaranteeType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGuaranteeTypeMockMvc.perform(post("/api/guarantee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guaranteeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GuaranteeType in the database
        List<GuaranteeType> guaranteeTypeList = guaranteeTypeRepository.findAll();
        assertThat(guaranteeTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGuaranteeTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = guaranteeTypeRepository.findAll().size();
        // set the field null
        guaranteeType.setGuaranteeTypeName(null);

        // Create the GuaranteeType, which fails.
        GuaranteeTypeDTO guaranteeTypeDTO = guaranteeTypeMapper.toDto(guaranteeType);

        restGuaranteeTypeMockMvc.perform(post("/api/guarantee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guaranteeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<GuaranteeType> guaranteeTypeList = guaranteeTypeRepository.findAll();
        assertThat(guaranteeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypes() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList
        restGuaranteeTypeMockMvc.perform(get("/api/guarantee-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(guaranteeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].guaranteeTypeName").value(hasItem(DEFAULT_GUARANTEE_TYPE_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getGuaranteeType() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get the guaranteeType
        restGuaranteeTypeMockMvc.perform(get("/api/guarantee-types/{id}", guaranteeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(guaranteeType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.guaranteeTypeName").value(DEFAULT_GUARANTEE_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultGuaranteeTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the guaranteeTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultGuaranteeTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultGuaranteeTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the guaranteeTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultGuaranteeTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where sortNum is not null
        defaultGuaranteeTypeShouldBeFound("sortNum.specified=true");

        // Get all the guaranteeTypeList where sortNum is null
        defaultGuaranteeTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultGuaranteeTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the guaranteeTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultGuaranteeTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultGuaranteeTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the guaranteeTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultGuaranteeTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllGuaranteeTypesByGuaranteeTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where guaranteeTypeName equals to DEFAULT_GUARANTEE_TYPE_NAME
        defaultGuaranteeTypeShouldBeFound("guaranteeTypeName.equals=" + DEFAULT_GUARANTEE_TYPE_NAME);

        // Get all the guaranteeTypeList where guaranteeTypeName equals to UPDATED_GUARANTEE_TYPE_NAME
        defaultGuaranteeTypeShouldNotBeFound("guaranteeTypeName.equals=" + UPDATED_GUARANTEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypesByGuaranteeTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where guaranteeTypeName in DEFAULT_GUARANTEE_TYPE_NAME or UPDATED_GUARANTEE_TYPE_NAME
        defaultGuaranteeTypeShouldBeFound("guaranteeTypeName.in=" + DEFAULT_GUARANTEE_TYPE_NAME + "," + UPDATED_GUARANTEE_TYPE_NAME);

        // Get all the guaranteeTypeList where guaranteeTypeName equals to UPDATED_GUARANTEE_TYPE_NAME
        defaultGuaranteeTypeShouldNotBeFound("guaranteeTypeName.in=" + UPDATED_GUARANTEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllGuaranteeTypesByGuaranteeTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        // Get all the guaranteeTypeList where guaranteeTypeName is not null
        defaultGuaranteeTypeShouldBeFound("guaranteeTypeName.specified=true");

        // Get all the guaranteeTypeList where guaranteeTypeName is null
        defaultGuaranteeTypeShouldNotBeFound("guaranteeTypeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultGuaranteeTypeShouldBeFound(String filter) throws Exception {
        restGuaranteeTypeMockMvc.perform(get("/api/guarantee-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(guaranteeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].guaranteeTypeName").value(hasItem(DEFAULT_GUARANTEE_TYPE_NAME)));

        // Check, that the count call also returns 1
        restGuaranteeTypeMockMvc.perform(get("/api/guarantee-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultGuaranteeTypeShouldNotBeFound(String filter) throws Exception {
        restGuaranteeTypeMockMvc.perform(get("/api/guarantee-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restGuaranteeTypeMockMvc.perform(get("/api/guarantee-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingGuaranteeType() throws Exception {
        // Get the guaranteeType
        restGuaranteeTypeMockMvc.perform(get("/api/guarantee-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGuaranteeType() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        int databaseSizeBeforeUpdate = guaranteeTypeRepository.findAll().size();

        // Update the guaranteeType
        GuaranteeType updatedGuaranteeType = guaranteeTypeRepository.findById(guaranteeType.getId()).get();
        // Disconnect from session so that the updates on updatedGuaranteeType are not directly saved in db
        em.detach(updatedGuaranteeType);
        updatedGuaranteeType
            .sortNum(UPDATED_SORT_NUM)
            .guaranteeTypeName(UPDATED_GUARANTEE_TYPE_NAME);
        GuaranteeTypeDTO guaranteeTypeDTO = guaranteeTypeMapper.toDto(updatedGuaranteeType);

        restGuaranteeTypeMockMvc.perform(put("/api/guarantee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guaranteeTypeDTO)))
            .andExpect(status().isOk());

        // Validate the GuaranteeType in the database
        List<GuaranteeType> guaranteeTypeList = guaranteeTypeRepository.findAll();
        assertThat(guaranteeTypeList).hasSize(databaseSizeBeforeUpdate);
        GuaranteeType testGuaranteeType = guaranteeTypeList.get(guaranteeTypeList.size() - 1);
        assertThat(testGuaranteeType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testGuaranteeType.getGuaranteeTypeName()).isEqualTo(UPDATED_GUARANTEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingGuaranteeType() throws Exception {
        int databaseSizeBeforeUpdate = guaranteeTypeRepository.findAll().size();

        // Create the GuaranteeType
        GuaranteeTypeDTO guaranteeTypeDTO = guaranteeTypeMapper.toDto(guaranteeType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGuaranteeTypeMockMvc.perform(put("/api/guarantee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guaranteeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GuaranteeType in the database
        List<GuaranteeType> guaranteeTypeList = guaranteeTypeRepository.findAll();
        assertThat(guaranteeTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGuaranteeType() throws Exception {
        // Initialize the database
        guaranteeTypeRepository.saveAndFlush(guaranteeType);

        int databaseSizeBeforeDelete = guaranteeTypeRepository.findAll().size();

        // Delete the guaranteeType
        restGuaranteeTypeMockMvc.perform(delete("/api/guarantee-types/{id}", guaranteeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<GuaranteeType> guaranteeTypeList = guaranteeTypeRepository.findAll();
        assertThat(guaranteeTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GuaranteeType.class);
        GuaranteeType guaranteeType1 = new GuaranteeType();
        guaranteeType1.setId(1L);
        GuaranteeType guaranteeType2 = new GuaranteeType();
        guaranteeType2.setId(guaranteeType1.getId());
        assertThat(guaranteeType1).isEqualTo(guaranteeType2);
        guaranteeType2.setId(2L);
        assertThat(guaranteeType1).isNotEqualTo(guaranteeType2);
        guaranteeType1.setId(null);
        assertThat(guaranteeType1).isNotEqualTo(guaranteeType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GuaranteeTypeDTO.class);
        GuaranteeTypeDTO guaranteeTypeDTO1 = new GuaranteeTypeDTO();
        guaranteeTypeDTO1.setId(1L);
        GuaranteeTypeDTO guaranteeTypeDTO2 = new GuaranteeTypeDTO();
        assertThat(guaranteeTypeDTO1).isNotEqualTo(guaranteeTypeDTO2);
        guaranteeTypeDTO2.setId(guaranteeTypeDTO1.getId());
        assertThat(guaranteeTypeDTO1).isEqualTo(guaranteeTypeDTO2);
        guaranteeTypeDTO2.setId(2L);
        assertThat(guaranteeTypeDTO1).isNotEqualTo(guaranteeTypeDTO2);
        guaranteeTypeDTO1.setId(null);
        assertThat(guaranteeTypeDTO1).isNotEqualTo(guaranteeTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(guaranteeTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(guaranteeTypeMapper.fromId(null)).isNull();
    }
}
