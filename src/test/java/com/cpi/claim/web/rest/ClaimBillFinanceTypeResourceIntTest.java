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

import com.cpi.claim.domain.ClaimBillFinanceType;
import com.cpi.claim.repository.ClaimBillFinanceTypeRepository;
import com.cpi.claim.service.ClaimBillFinanceTypeService;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeDTO;
import com.cpi.claim.service.mapper.ClaimBillFinanceTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeCriteria;
import com.cpi.claim.service.ClaimBillFinanceTypeQueryService;

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
 * Test class for the ClaimBillFinanceTypeResource REST controller.
 *
 * @see ClaimBillFinanceTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class ClaimBillFinanceTypeResourceIntTest {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private ClaimBillFinanceTypeRepository claimBillFinanceTypeRepository;


    @Autowired
    private ClaimBillFinanceTypeMapper claimBillFinanceTypeMapper;


    @Autowired
    private ClaimBillFinanceTypeService claimBillFinanceTypeService;

    @Autowired
    private ClaimBillFinanceTypeQueryService claimBillFinanceTypeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClaimBillFinanceTypeMockMvc;

    private ClaimBillFinanceType claimBillFinanceType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClaimBillFinanceTypeResource claimBillFinanceTypeResource = new ClaimBillFinanceTypeResource(claimBillFinanceTypeService, claimBillFinanceTypeQueryService);
        this.restClaimBillFinanceTypeMockMvc = MockMvcBuilders.standaloneSetup(claimBillFinanceTypeResource)
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
    public static ClaimBillFinanceType createEntity(EntityManager em) {
        ClaimBillFinanceType claimBillFinanceType = new ClaimBillFinanceType()
            .sortNum(DEFAULT_SORT_NUM)
            .claimBillFinanceTypeName(DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME);
        return claimBillFinanceType;
    }

    @Before
    public void initTest() {
        claimBillFinanceType = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimBillFinanceType() throws Exception {
        int databaseSizeBeforeCreate = claimBillFinanceTypeRepository.findAll().size();

        // Create the ClaimBillFinanceType
        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO = claimBillFinanceTypeMapper.toDto(claimBillFinanceType);
        restClaimBillFinanceTypeMockMvc.perform(post("/api/claim-bill-finance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillFinanceTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaimBillFinanceType in the database
        List<ClaimBillFinanceType> claimBillFinanceTypeList = claimBillFinanceTypeRepository.findAll();
        assertThat(claimBillFinanceTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimBillFinanceType testClaimBillFinanceType = claimBillFinanceTypeList.get(claimBillFinanceTypeList.size() - 1);
        assertThat(testClaimBillFinanceType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testClaimBillFinanceType.getClaimBillFinanceTypeName()).isEqualTo(DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createClaimBillFinanceTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimBillFinanceTypeRepository.findAll().size();

        // Create the ClaimBillFinanceType with an existing ID
        claimBillFinanceType.setId(1L);
        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO = claimBillFinanceTypeMapper.toDto(claimBillFinanceType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimBillFinanceTypeMockMvc.perform(post("/api/claim-bill-finance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillFinanceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimBillFinanceType in the database
        List<ClaimBillFinanceType> claimBillFinanceTypeList = claimBillFinanceTypeRepository.findAll();
        assertThat(claimBillFinanceTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkClaimBillFinanceTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = claimBillFinanceTypeRepository.findAll().size();
        // set the field null
        claimBillFinanceType.setClaimBillFinanceTypeName(null);

        // Create the ClaimBillFinanceType, which fails.
        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO = claimBillFinanceTypeMapper.toDto(claimBillFinanceType);

        restClaimBillFinanceTypeMockMvc.perform(post("/api/claim-bill-finance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillFinanceTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ClaimBillFinanceType> claimBillFinanceTypeList = claimBillFinanceTypeRepository.findAll();
        assertThat(claimBillFinanceTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypes() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList
        restClaimBillFinanceTypeMockMvc.perform(get("/api/claim-bill-finance-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimBillFinanceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].claimBillFinanceTypeName").value(hasItem(DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME.toString())));
    }


    @Test
    @Transactional
    public void getClaimBillFinanceType() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get the claimBillFinanceType
        restClaimBillFinanceTypeMockMvc.perform(get("/api/claim-bill-finance-types/{id}", claimBillFinanceType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimBillFinanceType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.claimBillFinanceTypeName").value(DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultClaimBillFinanceTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the claimBillFinanceTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultClaimBillFinanceTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultClaimBillFinanceTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the claimBillFinanceTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultClaimBillFinanceTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where sortNum is not null
        defaultClaimBillFinanceTypeShouldBeFound("sortNum.specified=true");

        // Get all the claimBillFinanceTypeList where sortNum is null
        defaultClaimBillFinanceTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultClaimBillFinanceTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the claimBillFinanceTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultClaimBillFinanceTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultClaimBillFinanceTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the claimBillFinanceTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultClaimBillFinanceTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesByClaimBillFinanceTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where claimBillFinanceTypeName equals to DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME
        defaultClaimBillFinanceTypeShouldBeFound("claimBillFinanceTypeName.equals=" + DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME);

        // Get all the claimBillFinanceTypeList where claimBillFinanceTypeName equals to UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME
        defaultClaimBillFinanceTypeShouldNotBeFound("claimBillFinanceTypeName.equals=" + UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesByClaimBillFinanceTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where claimBillFinanceTypeName in DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME or UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME
        defaultClaimBillFinanceTypeShouldBeFound("claimBillFinanceTypeName.in=" + DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME + "," + UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME);

        // Get all the claimBillFinanceTypeList where claimBillFinanceTypeName equals to UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME
        defaultClaimBillFinanceTypeShouldNotBeFound("claimBillFinanceTypeName.in=" + UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllClaimBillFinanceTypesByClaimBillFinanceTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        // Get all the claimBillFinanceTypeList where claimBillFinanceTypeName is not null
        defaultClaimBillFinanceTypeShouldBeFound("claimBillFinanceTypeName.specified=true");

        // Get all the claimBillFinanceTypeList where claimBillFinanceTypeName is null
        defaultClaimBillFinanceTypeShouldNotBeFound("claimBillFinanceTypeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultClaimBillFinanceTypeShouldBeFound(String filter) throws Exception {
        restClaimBillFinanceTypeMockMvc.perform(get("/api/claim-bill-finance-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimBillFinanceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].claimBillFinanceTypeName").value(hasItem(DEFAULT_CLAIM_BILL_FINANCE_TYPE_NAME.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultClaimBillFinanceTypeShouldNotBeFound(String filter) throws Exception {
        restClaimBillFinanceTypeMockMvc.perform(get("/api/claim-bill-finance-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingClaimBillFinanceType() throws Exception {
        // Get the claimBillFinanceType
        restClaimBillFinanceTypeMockMvc.perform(get("/api/claim-bill-finance-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimBillFinanceType() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        int databaseSizeBeforeUpdate = claimBillFinanceTypeRepository.findAll().size();

        // Update the claimBillFinanceType
        ClaimBillFinanceType updatedClaimBillFinanceType = claimBillFinanceTypeRepository.findById(claimBillFinanceType.getId()).get();
        // Disconnect from session so that the updates on updatedClaimBillFinanceType are not directly saved in db
        em.detach(updatedClaimBillFinanceType);
        updatedClaimBillFinanceType
            .sortNum(UPDATED_SORT_NUM)
            .claimBillFinanceTypeName(UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME);
        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO = claimBillFinanceTypeMapper.toDto(updatedClaimBillFinanceType);

        restClaimBillFinanceTypeMockMvc.perform(put("/api/claim-bill-finance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillFinanceTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ClaimBillFinanceType in the database
        List<ClaimBillFinanceType> claimBillFinanceTypeList = claimBillFinanceTypeRepository.findAll();
        assertThat(claimBillFinanceTypeList).hasSize(databaseSizeBeforeUpdate);
        ClaimBillFinanceType testClaimBillFinanceType = claimBillFinanceTypeList.get(claimBillFinanceTypeList.size() - 1);
        assertThat(testClaimBillFinanceType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testClaimBillFinanceType.getClaimBillFinanceTypeName()).isEqualTo(UPDATED_CLAIM_BILL_FINANCE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimBillFinanceType() throws Exception {
        int databaseSizeBeforeUpdate = claimBillFinanceTypeRepository.findAll().size();

        // Create the ClaimBillFinanceType
        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO = claimBillFinanceTypeMapper.toDto(claimBillFinanceType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimBillFinanceTypeMockMvc.perform(put("/api/claim-bill-finance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimBillFinanceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaimBillFinanceType in the database
        List<ClaimBillFinanceType> claimBillFinanceTypeList = claimBillFinanceTypeRepository.findAll();
        assertThat(claimBillFinanceTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClaimBillFinanceType() throws Exception {
        // Initialize the database
        claimBillFinanceTypeRepository.saveAndFlush(claimBillFinanceType);

        int databaseSizeBeforeDelete = claimBillFinanceTypeRepository.findAll().size();

        // Get the claimBillFinanceType
        restClaimBillFinanceTypeMockMvc.perform(delete("/api/claim-bill-finance-types/{id}", claimBillFinanceType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ClaimBillFinanceType> claimBillFinanceTypeList = claimBillFinanceTypeRepository.findAll();
        assertThat(claimBillFinanceTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimBillFinanceType.class);
        ClaimBillFinanceType claimBillFinanceType1 = new ClaimBillFinanceType();
        claimBillFinanceType1.setId(1L);
        ClaimBillFinanceType claimBillFinanceType2 = new ClaimBillFinanceType();
        claimBillFinanceType2.setId(claimBillFinanceType1.getId());
        assertThat(claimBillFinanceType1).isEqualTo(claimBillFinanceType2);
        claimBillFinanceType2.setId(2L);
        assertThat(claimBillFinanceType1).isNotEqualTo(claimBillFinanceType2);
        claimBillFinanceType1.setId(null);
        assertThat(claimBillFinanceType1).isNotEqualTo(claimBillFinanceType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimBillFinanceTypeDTO.class);
        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO1 = new ClaimBillFinanceTypeDTO();
        claimBillFinanceTypeDTO1.setId(1L);
        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO2 = new ClaimBillFinanceTypeDTO();
        assertThat(claimBillFinanceTypeDTO1).isNotEqualTo(claimBillFinanceTypeDTO2);
        claimBillFinanceTypeDTO2.setId(claimBillFinanceTypeDTO1.getId());
        assertThat(claimBillFinanceTypeDTO1).isEqualTo(claimBillFinanceTypeDTO2);
        claimBillFinanceTypeDTO2.setId(2L);
        assertThat(claimBillFinanceTypeDTO1).isNotEqualTo(claimBillFinanceTypeDTO2);
        claimBillFinanceTypeDTO1.setId(null);
        assertThat(claimBillFinanceTypeDTO1).isNotEqualTo(claimBillFinanceTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(claimBillFinanceTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(claimBillFinanceTypeMapper.fromId(null)).isNull();
    }
}
