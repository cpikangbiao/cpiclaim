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
import com.cpi.claim.domain.CpiInsuranceType;
import com.cpi.claim.repository.CpiInsuranceTypeRepository;
import com.cpi.claim.service.CpiInsuranceTypeService;
import com.cpi.claim.service.dto.CpiInsuranceTypeDTO;
import com.cpi.claim.service.mapper.CpiInsuranceTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CpiInsuranceTypeCriteria;
import com.cpi.claim.service.CpiInsuranceTypeQueryService;

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
 * Integration tests for the {@Link CpiInsuranceTypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CpiInsuranceTypeResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_CPI_INSURANCE_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CPI_INSURANCE_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private CpiInsuranceTypeRepository cpiInsuranceTypeRepository;

    @Autowired
    private CpiInsuranceTypeMapper cpiInsuranceTypeMapper;

    @Autowired
    private CpiInsuranceTypeService cpiInsuranceTypeService;

    @Autowired
    private CpiInsuranceTypeQueryService cpiInsuranceTypeQueryService;

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

    private MockMvc restCpiInsuranceTypeMockMvc;

    private CpiInsuranceType cpiInsuranceType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CpiInsuranceTypeResource cpiInsuranceTypeResource = new CpiInsuranceTypeResource(cpiInsuranceTypeService, cpiInsuranceTypeQueryService);
        this.restCpiInsuranceTypeMockMvc = MockMvcBuilders.standaloneSetup(cpiInsuranceTypeResource)
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
    public static CpiInsuranceType createEntity(EntityManager em) {
        CpiInsuranceType cpiInsuranceType = new CpiInsuranceType()
            .sortNum(DEFAULT_SORT_NUM)
            .cpiInsuranceTypeName(DEFAULT_CPI_INSURANCE_TYPE_NAME);
        return cpiInsuranceType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CpiInsuranceType createUpdatedEntity(EntityManager em) {
        CpiInsuranceType cpiInsuranceType = new CpiInsuranceType()
            .sortNum(UPDATED_SORT_NUM)
            .cpiInsuranceTypeName(UPDATED_CPI_INSURANCE_TYPE_NAME);
        return cpiInsuranceType;
    }

    @BeforeEach
    public void initTest() {
        cpiInsuranceType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCpiInsuranceType() throws Exception {
        int databaseSizeBeforeCreate = cpiInsuranceTypeRepository.findAll().size();

        // Create the CpiInsuranceType
        CpiInsuranceTypeDTO cpiInsuranceTypeDTO = cpiInsuranceTypeMapper.toDto(cpiInsuranceType);
        restCpiInsuranceTypeMockMvc.perform(post("/api/cpi-insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpiInsuranceTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the CpiInsuranceType in the database
        List<CpiInsuranceType> cpiInsuranceTypeList = cpiInsuranceTypeRepository.findAll();
        assertThat(cpiInsuranceTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CpiInsuranceType testCpiInsuranceType = cpiInsuranceTypeList.get(cpiInsuranceTypeList.size() - 1);
        assertThat(testCpiInsuranceType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testCpiInsuranceType.getCpiInsuranceTypeName()).isEqualTo(DEFAULT_CPI_INSURANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createCpiInsuranceTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cpiInsuranceTypeRepository.findAll().size();

        // Create the CpiInsuranceType with an existing ID
        cpiInsuranceType.setId(1L);
        CpiInsuranceTypeDTO cpiInsuranceTypeDTO = cpiInsuranceTypeMapper.toDto(cpiInsuranceType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCpiInsuranceTypeMockMvc.perform(post("/api/cpi-insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpiInsuranceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CpiInsuranceType in the database
        List<CpiInsuranceType> cpiInsuranceTypeList = cpiInsuranceTypeRepository.findAll();
        assertThat(cpiInsuranceTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCpiInsuranceTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpiInsuranceTypeRepository.findAll().size();
        // set the field null
        cpiInsuranceType.setCpiInsuranceTypeName(null);

        // Create the CpiInsuranceType, which fails.
        CpiInsuranceTypeDTO cpiInsuranceTypeDTO = cpiInsuranceTypeMapper.toDto(cpiInsuranceType);

        restCpiInsuranceTypeMockMvc.perform(post("/api/cpi-insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpiInsuranceTypeDTO)))
            .andExpect(status().isBadRequest());

        List<CpiInsuranceType> cpiInsuranceTypeList = cpiInsuranceTypeRepository.findAll();
        assertThat(cpiInsuranceTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypes() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList
        restCpiInsuranceTypeMockMvc.perform(get("/api/cpi-insurance-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cpiInsuranceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].cpiInsuranceTypeName").value(hasItem(DEFAULT_CPI_INSURANCE_TYPE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCpiInsuranceType() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get the cpiInsuranceType
        restCpiInsuranceTypeMockMvc.perform(get("/api/cpi-insurance-types/{id}", cpiInsuranceType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cpiInsuranceType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.cpiInsuranceTypeName").value(DEFAULT_CPI_INSURANCE_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultCpiInsuranceTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the cpiInsuranceTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultCpiInsuranceTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultCpiInsuranceTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the cpiInsuranceTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultCpiInsuranceTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where sortNum is not null
        defaultCpiInsuranceTypeShouldBeFound("sortNum.specified=true");

        // Get all the cpiInsuranceTypeList where sortNum is null
        defaultCpiInsuranceTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultCpiInsuranceTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the cpiInsuranceTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultCpiInsuranceTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultCpiInsuranceTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the cpiInsuranceTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultCpiInsuranceTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllCpiInsuranceTypesByCpiInsuranceTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where cpiInsuranceTypeName equals to DEFAULT_CPI_INSURANCE_TYPE_NAME
        defaultCpiInsuranceTypeShouldBeFound("cpiInsuranceTypeName.equals=" + DEFAULT_CPI_INSURANCE_TYPE_NAME);

        // Get all the cpiInsuranceTypeList where cpiInsuranceTypeName equals to UPDATED_CPI_INSURANCE_TYPE_NAME
        defaultCpiInsuranceTypeShouldNotBeFound("cpiInsuranceTypeName.equals=" + UPDATED_CPI_INSURANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypesByCpiInsuranceTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where cpiInsuranceTypeName in DEFAULT_CPI_INSURANCE_TYPE_NAME or UPDATED_CPI_INSURANCE_TYPE_NAME
        defaultCpiInsuranceTypeShouldBeFound("cpiInsuranceTypeName.in=" + DEFAULT_CPI_INSURANCE_TYPE_NAME + "," + UPDATED_CPI_INSURANCE_TYPE_NAME);

        // Get all the cpiInsuranceTypeList where cpiInsuranceTypeName equals to UPDATED_CPI_INSURANCE_TYPE_NAME
        defaultCpiInsuranceTypeShouldNotBeFound("cpiInsuranceTypeName.in=" + UPDATED_CPI_INSURANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllCpiInsuranceTypesByCpiInsuranceTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        // Get all the cpiInsuranceTypeList where cpiInsuranceTypeName is not null
        defaultCpiInsuranceTypeShouldBeFound("cpiInsuranceTypeName.specified=true");

        // Get all the cpiInsuranceTypeList where cpiInsuranceTypeName is null
        defaultCpiInsuranceTypeShouldNotBeFound("cpiInsuranceTypeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCpiInsuranceTypeShouldBeFound(String filter) throws Exception {
        restCpiInsuranceTypeMockMvc.perform(get("/api/cpi-insurance-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cpiInsuranceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].cpiInsuranceTypeName").value(hasItem(DEFAULT_CPI_INSURANCE_TYPE_NAME)));

        // Check, that the count call also returns 1
        restCpiInsuranceTypeMockMvc.perform(get("/api/cpi-insurance-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCpiInsuranceTypeShouldNotBeFound(String filter) throws Exception {
        restCpiInsuranceTypeMockMvc.perform(get("/api/cpi-insurance-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCpiInsuranceTypeMockMvc.perform(get("/api/cpi-insurance-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCpiInsuranceType() throws Exception {
        // Get the cpiInsuranceType
        restCpiInsuranceTypeMockMvc.perform(get("/api/cpi-insurance-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCpiInsuranceType() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        int databaseSizeBeforeUpdate = cpiInsuranceTypeRepository.findAll().size();

        // Update the cpiInsuranceType
        CpiInsuranceType updatedCpiInsuranceType = cpiInsuranceTypeRepository.findById(cpiInsuranceType.getId()).get();
        // Disconnect from session so that the updates on updatedCpiInsuranceType are not directly saved in db
        em.detach(updatedCpiInsuranceType);
        updatedCpiInsuranceType
            .sortNum(UPDATED_SORT_NUM)
            .cpiInsuranceTypeName(UPDATED_CPI_INSURANCE_TYPE_NAME);
        CpiInsuranceTypeDTO cpiInsuranceTypeDTO = cpiInsuranceTypeMapper.toDto(updatedCpiInsuranceType);

        restCpiInsuranceTypeMockMvc.perform(put("/api/cpi-insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpiInsuranceTypeDTO)))
            .andExpect(status().isOk());

        // Validate the CpiInsuranceType in the database
        List<CpiInsuranceType> cpiInsuranceTypeList = cpiInsuranceTypeRepository.findAll();
        assertThat(cpiInsuranceTypeList).hasSize(databaseSizeBeforeUpdate);
        CpiInsuranceType testCpiInsuranceType = cpiInsuranceTypeList.get(cpiInsuranceTypeList.size() - 1);
        assertThat(testCpiInsuranceType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testCpiInsuranceType.getCpiInsuranceTypeName()).isEqualTo(UPDATED_CPI_INSURANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCpiInsuranceType() throws Exception {
        int databaseSizeBeforeUpdate = cpiInsuranceTypeRepository.findAll().size();

        // Create the CpiInsuranceType
        CpiInsuranceTypeDTO cpiInsuranceTypeDTO = cpiInsuranceTypeMapper.toDto(cpiInsuranceType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCpiInsuranceTypeMockMvc.perform(put("/api/cpi-insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpiInsuranceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CpiInsuranceType in the database
        List<CpiInsuranceType> cpiInsuranceTypeList = cpiInsuranceTypeRepository.findAll();
        assertThat(cpiInsuranceTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCpiInsuranceType() throws Exception {
        // Initialize the database
        cpiInsuranceTypeRepository.saveAndFlush(cpiInsuranceType);

        int databaseSizeBeforeDelete = cpiInsuranceTypeRepository.findAll().size();

        // Delete the cpiInsuranceType
        restCpiInsuranceTypeMockMvc.perform(delete("/api/cpi-insurance-types/{id}", cpiInsuranceType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CpiInsuranceType> cpiInsuranceTypeList = cpiInsuranceTypeRepository.findAll();
        assertThat(cpiInsuranceTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CpiInsuranceType.class);
        CpiInsuranceType cpiInsuranceType1 = new CpiInsuranceType();
        cpiInsuranceType1.setId(1L);
        CpiInsuranceType cpiInsuranceType2 = new CpiInsuranceType();
        cpiInsuranceType2.setId(cpiInsuranceType1.getId());
        assertThat(cpiInsuranceType1).isEqualTo(cpiInsuranceType2);
        cpiInsuranceType2.setId(2L);
        assertThat(cpiInsuranceType1).isNotEqualTo(cpiInsuranceType2);
        cpiInsuranceType1.setId(null);
        assertThat(cpiInsuranceType1).isNotEqualTo(cpiInsuranceType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CpiInsuranceTypeDTO.class);
        CpiInsuranceTypeDTO cpiInsuranceTypeDTO1 = new CpiInsuranceTypeDTO();
        cpiInsuranceTypeDTO1.setId(1L);
        CpiInsuranceTypeDTO cpiInsuranceTypeDTO2 = new CpiInsuranceTypeDTO();
        assertThat(cpiInsuranceTypeDTO1).isNotEqualTo(cpiInsuranceTypeDTO2);
        cpiInsuranceTypeDTO2.setId(cpiInsuranceTypeDTO1.getId());
        assertThat(cpiInsuranceTypeDTO1).isEqualTo(cpiInsuranceTypeDTO2);
        cpiInsuranceTypeDTO2.setId(2L);
        assertThat(cpiInsuranceTypeDTO1).isNotEqualTo(cpiInsuranceTypeDTO2);
        cpiInsuranceTypeDTO1.setId(null);
        assertThat(cpiInsuranceTypeDTO1).isNotEqualTo(cpiInsuranceTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cpiInsuranceTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cpiInsuranceTypeMapper.fromId(null)).isNull();
    }
}
