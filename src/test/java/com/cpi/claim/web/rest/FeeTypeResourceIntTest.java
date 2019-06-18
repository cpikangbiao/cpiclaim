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
import com.cpi.claim.domain.FeeType;
import com.cpi.claim.repository.FeeTypeRepository;
import com.cpi.claim.service.FeeTypeQueryService;
import com.cpi.claim.service.FeeTypeService;
import com.cpi.claim.service.dto.FeeTypeDTO;
import com.cpi.claim.service.mapper.FeeTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import org.junit.Before;
import org.junit.jupiter.api.Test;
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
 * Test class for the FeeTypeResource REST controller.
 *
 * @see FeeTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class FeeTypeResourceIntTest {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_FEE_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FEE_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private FeeTypeRepository feeTypeRepository;


    @Autowired
    private FeeTypeMapper feeTypeMapper;


    @Autowired
    private FeeTypeService feeTypeService;

    @Autowired
    private FeeTypeQueryService feeTypeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFeeTypeMockMvc;

    private FeeType feeType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeeTypeResource feeTypeResource = new FeeTypeResource(feeTypeService, feeTypeQueryService);
        this.restFeeTypeMockMvc = MockMvcBuilders.standaloneSetup(feeTypeResource)
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
    public static FeeType createEntity(EntityManager em) {
        FeeType feeType = new FeeType()
            .sortNum(DEFAULT_SORT_NUM)
            .feeTypeName(DEFAULT_FEE_TYPE_NAME);
        return feeType;
    }

    @Before
    public void initTest() {
        feeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeeType() throws Exception {
        int databaseSizeBeforeCreate = feeTypeRepository.findAll().size();

        // Create the FeeType
        FeeTypeDTO feeTypeDTO = feeTypeMapper.toDto(feeType);
        restFeeTypeMockMvc.perform(post("/api/fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the FeeType in the database
        List<FeeType> feeTypeList = feeTypeRepository.findAll();
        assertThat(feeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        FeeType testFeeType = feeTypeList.get(feeTypeList.size() - 1);
        assertThat(testFeeType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testFeeType.getFeeTypeName()).isEqualTo(DEFAULT_FEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createFeeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feeTypeRepository.findAll().size();

        // Create the FeeType with an existing ID
        feeType.setId(1L);
        FeeTypeDTO feeTypeDTO = feeTypeMapper.toDto(feeType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeeTypeMockMvc.perform(post("/api/fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeeType in the database
        List<FeeType> feeTypeList = feeTypeRepository.findAll();
        assertThat(feeTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFeeTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = feeTypeRepository.findAll().size();
        // set the field null
        feeType.setFeeTypeName(null);

        // Create the FeeType, which fails.
        FeeTypeDTO feeTypeDTO = feeTypeMapper.toDto(feeType);

        restFeeTypeMockMvc.perform(post("/api/fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<FeeType> feeTypeList = feeTypeRepository.findAll();
        assertThat(feeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFeeTypes() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList
        restFeeTypeMockMvc.perform(get("/api/fee-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].feeTypeName").value(hasItem(DEFAULT_FEE_TYPE_NAME.toString())));
    }


    @Test
    @Transactional
    public void getFeeType() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get the feeType
        restFeeTypeMockMvc.perform(get("/api/fee-types/{id}", feeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(feeType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.feeTypeName").value(DEFAULT_FEE_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllFeeTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultFeeTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the feeTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultFeeTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllFeeTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultFeeTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the feeTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultFeeTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllFeeTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where sortNum is not null
        defaultFeeTypeShouldBeFound("sortNum.specified=true");

        // Get all the feeTypeList where sortNum is null
        defaultFeeTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllFeeTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultFeeTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the feeTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultFeeTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllFeeTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultFeeTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the feeTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultFeeTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllFeeTypesByFeeTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where feeTypeName equals to DEFAULT_FEE_TYPE_NAME
        defaultFeeTypeShouldBeFound("feeTypeName.equals=" + DEFAULT_FEE_TYPE_NAME);

        // Get all the feeTypeList where feeTypeName equals to UPDATED_FEE_TYPE_NAME
        defaultFeeTypeShouldNotBeFound("feeTypeName.equals=" + UPDATED_FEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllFeeTypesByFeeTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where feeTypeName in DEFAULT_FEE_TYPE_NAME or UPDATED_FEE_TYPE_NAME
        defaultFeeTypeShouldBeFound("feeTypeName.in=" + DEFAULT_FEE_TYPE_NAME + "," + UPDATED_FEE_TYPE_NAME);

        // Get all the feeTypeList where feeTypeName equals to UPDATED_FEE_TYPE_NAME
        defaultFeeTypeShouldNotBeFound("feeTypeName.in=" + UPDATED_FEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllFeeTypesByFeeTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        // Get all the feeTypeList where feeTypeName is not null
        defaultFeeTypeShouldBeFound("feeTypeName.specified=true");

        // Get all the feeTypeList where feeTypeName is null
        defaultFeeTypeShouldNotBeFound("feeTypeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFeeTypeShouldBeFound(String filter) throws Exception {
        restFeeTypeMockMvc.perform(get("/api/fee-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].feeTypeName").value(hasItem(DEFAULT_FEE_TYPE_NAME.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFeeTypeShouldNotBeFound(String filter) throws Exception {
        restFeeTypeMockMvc.perform(get("/api/fee-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingFeeType() throws Exception {
        // Get the feeType
        restFeeTypeMockMvc.perform(get("/api/fee-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeeType() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        int databaseSizeBeforeUpdate = feeTypeRepository.findAll().size();

        // Update the feeType
        FeeType updatedFeeType = feeTypeRepository.findById(feeType.getId()).get();
        // Disconnect from session so that the updates on updatedFeeType are not directly saved in db
        em.detach(updatedFeeType);
        updatedFeeType
            .sortNum(UPDATED_SORT_NUM)
            .feeTypeName(UPDATED_FEE_TYPE_NAME);
        FeeTypeDTO feeTypeDTO = feeTypeMapper.toDto(updatedFeeType);

        restFeeTypeMockMvc.perform(put("/api/fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeTypeDTO)))
            .andExpect(status().isOk());

        // Validate the FeeType in the database
        List<FeeType> feeTypeList = feeTypeRepository.findAll();
        assertThat(feeTypeList).hasSize(databaseSizeBeforeUpdate);
        FeeType testFeeType = feeTypeList.get(feeTypeList.size() - 1);
        assertThat(testFeeType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testFeeType.getFeeTypeName()).isEqualTo(UPDATED_FEE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFeeType() throws Exception {
        int databaseSizeBeforeUpdate = feeTypeRepository.findAll().size();

        // Create the FeeType
        FeeTypeDTO feeTypeDTO = feeTypeMapper.toDto(feeType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeeTypeMockMvc.perform(put("/api/fee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeeType in the database
        List<FeeType> feeTypeList = feeTypeRepository.findAll();
        assertThat(feeTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFeeType() throws Exception {
        // Initialize the database
        feeTypeRepository.saveAndFlush(feeType);

        int databaseSizeBeforeDelete = feeTypeRepository.findAll().size();

        // Get the feeType
        restFeeTypeMockMvc.perform(delete("/api/fee-types/{id}", feeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FeeType> feeTypeList = feeTypeRepository.findAll();
        assertThat(feeTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeeType.class);
        FeeType feeType1 = new FeeType();
        feeType1.setId(1L);
        FeeType feeType2 = new FeeType();
        feeType2.setId(feeType1.getId());
        assertThat(feeType1).isEqualTo(feeType2);
        feeType2.setId(2L);
        assertThat(feeType1).isNotEqualTo(feeType2);
        feeType1.setId(null);
        assertThat(feeType1).isNotEqualTo(feeType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeeTypeDTO.class);
        FeeTypeDTO feeTypeDTO1 = new FeeTypeDTO();
        feeTypeDTO1.setId(1L);
        FeeTypeDTO feeTypeDTO2 = new FeeTypeDTO();
        assertThat(feeTypeDTO1).isNotEqualTo(feeTypeDTO2);
        feeTypeDTO2.setId(feeTypeDTO1.getId());
        assertThat(feeTypeDTO1).isEqualTo(feeTypeDTO2);
        feeTypeDTO2.setId(2L);
        assertThat(feeTypeDTO1).isNotEqualTo(feeTypeDTO2);
        feeTypeDTO1.setId(null);
        assertThat(feeTypeDTO1).isNotEqualTo(feeTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(feeTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(feeTypeMapper.fromId(null)).isNull();
    }
}
