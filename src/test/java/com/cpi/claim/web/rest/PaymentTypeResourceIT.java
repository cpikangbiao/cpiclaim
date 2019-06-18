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
import com.cpi.claim.domain.PaymentType;
import com.cpi.claim.repository.PaymentTypeRepository;
import com.cpi.claim.service.PaymentTypeService;
import com.cpi.claim.service.dto.PaymentTypeDTO;
import com.cpi.claim.service.mapper.PaymentTypeMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.PaymentTypeCriteria;
import com.cpi.claim.service.PaymentTypeQueryService;

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
 * Integration tests for the {@Link PaymentTypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class PaymentTypeResourceIT {

    private static final Integer DEFAULT_SORT_NUM = 1;
    private static final Integer UPDATED_SORT_NUM = 2;

    private static final String DEFAULT_PAYMENT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Autowired
    private PaymentTypeQueryService paymentTypeQueryService;

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

    private MockMvc restPaymentTypeMockMvc;

    private PaymentType paymentType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentTypeResource paymentTypeResource = new PaymentTypeResource(paymentTypeService, paymentTypeQueryService);
        this.restPaymentTypeMockMvc = MockMvcBuilders.standaloneSetup(paymentTypeResource)
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
    public static PaymentType createEntity(EntityManager em) {
        PaymentType paymentType = new PaymentType()
            .sortNum(DEFAULT_SORT_NUM)
            .paymentTypeName(DEFAULT_PAYMENT_TYPE_NAME);
        return paymentType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentType createUpdatedEntity(EntityManager em) {
        PaymentType paymentType = new PaymentType()
            .sortNum(UPDATED_SORT_NUM)
            .paymentTypeName(UPDATED_PAYMENT_TYPE_NAME);
        return paymentType;
    }

    @BeforeEach
    public void initTest() {
        paymentType = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentType() throws Exception {
        int databaseSizeBeforeCreate = paymentTypeRepository.findAll().size();

        // Create the PaymentType
        PaymentTypeDTO paymentTypeDTO = paymentTypeMapper.toDto(paymentType);
        restPaymentTypeMockMvc.perform(post("/api/payment-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the PaymentType in the database
        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();
        assertThat(paymentTypeList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentType testPaymentType = paymentTypeList.get(paymentTypeList.size() - 1);
        assertThat(testPaymentType.getSortNum()).isEqualTo(DEFAULT_SORT_NUM);
        assertThat(testPaymentType.getPaymentTypeName()).isEqualTo(DEFAULT_PAYMENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createPaymentTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentTypeRepository.findAll().size();

        // Create the PaymentType with an existing ID
        paymentType.setId(1L);
        PaymentTypeDTO paymentTypeDTO = paymentTypeMapper.toDto(paymentType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentTypeMockMvc.perform(post("/api/payment-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentType in the database
        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();
        assertThat(paymentTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPaymentTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTypeRepository.findAll().size();
        // set the field null
        paymentType.setPaymentTypeName(null);

        // Create the PaymentType, which fails.
        PaymentTypeDTO paymentTypeDTO = paymentTypeMapper.toDto(paymentType);

        restPaymentTypeMockMvc.perform(post("/api/payment-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentTypeDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();
        assertThat(paymentTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaymentTypes() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList
        restPaymentTypeMockMvc.perform(get("/api/payment-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].paymentTypeName").value(hasItem(DEFAULT_PAYMENT_TYPE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getPaymentType() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get the paymentType
        restPaymentTypeMockMvc.perform(get("/api/payment-types/{id}", paymentType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paymentType.getId().intValue()))
            .andExpect(jsonPath("$.sortNum").value(DEFAULT_SORT_NUM))
            .andExpect(jsonPath("$.paymentTypeName").value(DEFAULT_PAYMENT_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllPaymentTypesBySortNumIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where sortNum equals to DEFAULT_SORT_NUM
        defaultPaymentTypeShouldBeFound("sortNum.equals=" + DEFAULT_SORT_NUM);

        // Get all the paymentTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultPaymentTypeShouldNotBeFound("sortNum.equals=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllPaymentTypesBySortNumIsInShouldWork() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where sortNum in DEFAULT_SORT_NUM or UPDATED_SORT_NUM
        defaultPaymentTypeShouldBeFound("sortNum.in=" + DEFAULT_SORT_NUM + "," + UPDATED_SORT_NUM);

        // Get all the paymentTypeList where sortNum equals to UPDATED_SORT_NUM
        defaultPaymentTypeShouldNotBeFound("sortNum.in=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllPaymentTypesBySortNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where sortNum is not null
        defaultPaymentTypeShouldBeFound("sortNum.specified=true");

        // Get all the paymentTypeList where sortNum is null
        defaultPaymentTypeShouldNotBeFound("sortNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentTypesBySortNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where sortNum greater than or equals to DEFAULT_SORT_NUM
        defaultPaymentTypeShouldBeFound("sortNum.greaterOrEqualThan=" + DEFAULT_SORT_NUM);

        // Get all the paymentTypeList where sortNum greater than or equals to UPDATED_SORT_NUM
        defaultPaymentTypeShouldNotBeFound("sortNum.greaterOrEqualThan=" + UPDATED_SORT_NUM);
    }

    @Test
    @Transactional
    public void getAllPaymentTypesBySortNumIsLessThanSomething() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where sortNum less than or equals to DEFAULT_SORT_NUM
        defaultPaymentTypeShouldNotBeFound("sortNum.lessThan=" + DEFAULT_SORT_NUM);

        // Get all the paymentTypeList where sortNum less than or equals to UPDATED_SORT_NUM
        defaultPaymentTypeShouldBeFound("sortNum.lessThan=" + UPDATED_SORT_NUM);
    }


    @Test
    @Transactional
    public void getAllPaymentTypesByPaymentTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where paymentTypeName equals to DEFAULT_PAYMENT_TYPE_NAME
        defaultPaymentTypeShouldBeFound("paymentTypeName.equals=" + DEFAULT_PAYMENT_TYPE_NAME);

        // Get all the paymentTypeList where paymentTypeName equals to UPDATED_PAYMENT_TYPE_NAME
        defaultPaymentTypeShouldNotBeFound("paymentTypeName.equals=" + UPDATED_PAYMENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllPaymentTypesByPaymentTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where paymentTypeName in DEFAULT_PAYMENT_TYPE_NAME or UPDATED_PAYMENT_TYPE_NAME
        defaultPaymentTypeShouldBeFound("paymentTypeName.in=" + DEFAULT_PAYMENT_TYPE_NAME + "," + UPDATED_PAYMENT_TYPE_NAME);

        // Get all the paymentTypeList where paymentTypeName equals to UPDATED_PAYMENT_TYPE_NAME
        defaultPaymentTypeShouldNotBeFound("paymentTypeName.in=" + UPDATED_PAYMENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllPaymentTypesByPaymentTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        // Get all the paymentTypeList where paymentTypeName is not null
        defaultPaymentTypeShouldBeFound("paymentTypeName.specified=true");

        // Get all the paymentTypeList where paymentTypeName is null
        defaultPaymentTypeShouldNotBeFound("paymentTypeName.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPaymentTypeShouldBeFound(String filter) throws Exception {
        restPaymentTypeMockMvc.perform(get("/api/payment-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortNum").value(hasItem(DEFAULT_SORT_NUM)))
            .andExpect(jsonPath("$.[*].paymentTypeName").value(hasItem(DEFAULT_PAYMENT_TYPE_NAME)));

        // Check, that the count call also returns 1
        restPaymentTypeMockMvc.perform(get("/api/payment-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPaymentTypeShouldNotBeFound(String filter) throws Exception {
        restPaymentTypeMockMvc.perform(get("/api/payment-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPaymentTypeMockMvc.perform(get("/api/payment-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPaymentType() throws Exception {
        // Get the paymentType
        restPaymentTypeMockMvc.perform(get("/api/payment-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentType() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        int databaseSizeBeforeUpdate = paymentTypeRepository.findAll().size();

        // Update the paymentType
        PaymentType updatedPaymentType = paymentTypeRepository.findById(paymentType.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentType are not directly saved in db
        em.detach(updatedPaymentType);
        updatedPaymentType
            .sortNum(UPDATED_SORT_NUM)
            .paymentTypeName(UPDATED_PAYMENT_TYPE_NAME);
        PaymentTypeDTO paymentTypeDTO = paymentTypeMapper.toDto(updatedPaymentType);

        restPaymentTypeMockMvc.perform(put("/api/payment-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentTypeDTO)))
            .andExpect(status().isOk());

        // Validate the PaymentType in the database
        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();
        assertThat(paymentTypeList).hasSize(databaseSizeBeforeUpdate);
        PaymentType testPaymentType = paymentTypeList.get(paymentTypeList.size() - 1);
        assertThat(testPaymentType.getSortNum()).isEqualTo(UPDATED_SORT_NUM);
        assertThat(testPaymentType.getPaymentTypeName()).isEqualTo(UPDATED_PAYMENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentType() throws Exception {
        int databaseSizeBeforeUpdate = paymentTypeRepository.findAll().size();

        // Create the PaymentType
        PaymentTypeDTO paymentTypeDTO = paymentTypeMapper.toDto(paymentType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentTypeMockMvc.perform(put("/api/payment-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentType in the database
        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();
        assertThat(paymentTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentType() throws Exception {
        // Initialize the database
        paymentTypeRepository.saveAndFlush(paymentType);

        int databaseSizeBeforeDelete = paymentTypeRepository.findAll().size();

        // Delete the paymentType
        restPaymentTypeMockMvc.perform(delete("/api/payment-types/{id}", paymentType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<PaymentType> paymentTypeList = paymentTypeRepository.findAll();
        assertThat(paymentTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentType.class);
        PaymentType paymentType1 = new PaymentType();
        paymentType1.setId(1L);
        PaymentType paymentType2 = new PaymentType();
        paymentType2.setId(paymentType1.getId());
        assertThat(paymentType1).isEqualTo(paymentType2);
        paymentType2.setId(2L);
        assertThat(paymentType1).isNotEqualTo(paymentType2);
        paymentType1.setId(null);
        assertThat(paymentType1).isNotEqualTo(paymentType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentTypeDTO.class);
        PaymentTypeDTO paymentTypeDTO1 = new PaymentTypeDTO();
        paymentTypeDTO1.setId(1L);
        PaymentTypeDTO paymentTypeDTO2 = new PaymentTypeDTO();
        assertThat(paymentTypeDTO1).isNotEqualTo(paymentTypeDTO2);
        paymentTypeDTO2.setId(paymentTypeDTO1.getId());
        assertThat(paymentTypeDTO1).isEqualTo(paymentTypeDTO2);
        paymentTypeDTO2.setId(2L);
        assertThat(paymentTypeDTO1).isNotEqualTo(paymentTypeDTO2);
        paymentTypeDTO1.setId(null);
        assertThat(paymentTypeDTO1).isNotEqualTo(paymentTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(paymentTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(paymentTypeMapper.fromId(null)).isNull();
    }
}
