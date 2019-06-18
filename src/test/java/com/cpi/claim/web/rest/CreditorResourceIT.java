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
import com.cpi.claim.domain.Creditor;
import com.cpi.claim.repository.CreditorRepository;
import com.cpi.claim.service.CreditorService;
import com.cpi.claim.service.dto.CreditorDTO;
import com.cpi.claim.service.mapper.CreditorMapper;
import com.cpi.claim.web.rest.errors.ExceptionTranslator;
import com.cpi.claim.service.dto.CreditorCriteria;
import com.cpi.claim.service.CreditorQueryService;

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
 * Integration tests for the {@Link CreditorResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CpiclaimApp.class})
public class CreditorResourceIT {

    private static final String DEFAULT_CREDITOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CREDITOR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CREDITOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREDITOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREDITOR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CREDITOR_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_IBAN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IBAN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CORR_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CORR_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CORR_BANK_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CORR_BANK_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CORR_BANK_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_CORR_BANK_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CORR_BANK_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_CORR_BANK_ADDRESS_2 = "BBBBBBBBBB";

    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    private CreditorMapper creditorMapper;

    @Autowired
    private CreditorService creditorService;

    @Autowired
    private CreditorQueryService creditorQueryService;

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

    private MockMvc restCreditorMockMvc;

    private Creditor creditor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CreditorResource creditorResource = new CreditorResource(creditorService, creditorQueryService);
        this.restCreditorMockMvc = MockMvcBuilders.standaloneSetup(creditorResource)
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
    public static Creditor createEntity(EntityManager em) {
        Creditor creditor = new Creditor()
            .creditorCode(DEFAULT_CREDITOR_CODE)
            .creditorName(DEFAULT_CREDITOR_NAME)
            .creditorAddress(DEFAULT_CREDITOR_ADDRESS)
            .portName(DEFAULT_PORT_NAME)
            .swiftCode(DEFAULT_SWIFT_CODE)
            .ibanCode(DEFAULT_IBAN_CODE)
            .bankName(DEFAULT_BANK_NAME)
            .bankAddress(DEFAULT_BANK_ADDRESS)
            .accountNo(DEFAULT_ACCOUNT_NO)
            .corrBankName(DEFAULT_CORR_BANK_NAME)
            .corrBankAddress(DEFAULT_CORR_BANK_ADDRESS)
            .corrBankName2(DEFAULT_CORR_BANK_NAME_2)
            .corrBankAddress2(DEFAULT_CORR_BANK_ADDRESS_2);
        return creditor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditor createUpdatedEntity(EntityManager em) {
        Creditor creditor = new Creditor()
            .creditorCode(UPDATED_CREDITOR_CODE)
            .creditorName(UPDATED_CREDITOR_NAME)
            .creditorAddress(UPDATED_CREDITOR_ADDRESS)
            .portName(UPDATED_PORT_NAME)
            .swiftCode(UPDATED_SWIFT_CODE)
            .ibanCode(UPDATED_IBAN_CODE)
            .bankName(UPDATED_BANK_NAME)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .accountNo(UPDATED_ACCOUNT_NO)
            .corrBankName(UPDATED_CORR_BANK_NAME)
            .corrBankAddress(UPDATED_CORR_BANK_ADDRESS)
            .corrBankName2(UPDATED_CORR_BANK_NAME_2)
            .corrBankAddress2(UPDATED_CORR_BANK_ADDRESS_2);
        return creditor;
    }

    @BeforeEach
    public void initTest() {
        creditor = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditor() throws Exception {
        int databaseSizeBeforeCreate = creditorRepository.findAll().size();

        // Create the Creditor
        CreditorDTO creditorDTO = creditorMapper.toDto(creditor);
        restCreditorMockMvc.perform(post("/api/creditors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditorDTO)))
            .andExpect(status().isCreated());

        // Validate the Creditor in the database
        List<Creditor> creditorList = creditorRepository.findAll();
        assertThat(creditorList).hasSize(databaseSizeBeforeCreate + 1);
        Creditor testCreditor = creditorList.get(creditorList.size() - 1);
        assertThat(testCreditor.getCreditorCode()).isEqualTo(DEFAULT_CREDITOR_CODE);
        assertThat(testCreditor.getCreditorName()).isEqualTo(DEFAULT_CREDITOR_NAME);
        assertThat(testCreditor.getCreditorAddress()).isEqualTo(DEFAULT_CREDITOR_ADDRESS);
        assertThat(testCreditor.getPortName()).isEqualTo(DEFAULT_PORT_NAME);
        assertThat(testCreditor.getSwiftCode()).isEqualTo(DEFAULT_SWIFT_CODE);
        assertThat(testCreditor.getIbanCode()).isEqualTo(DEFAULT_IBAN_CODE);
        assertThat(testCreditor.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testCreditor.getBankAddress()).isEqualTo(DEFAULT_BANK_ADDRESS);
        assertThat(testCreditor.getAccountNo()).isEqualTo(DEFAULT_ACCOUNT_NO);
        assertThat(testCreditor.getCorrBankName()).isEqualTo(DEFAULT_CORR_BANK_NAME);
        assertThat(testCreditor.getCorrBankAddress()).isEqualTo(DEFAULT_CORR_BANK_ADDRESS);
        assertThat(testCreditor.getCorrBankName2()).isEqualTo(DEFAULT_CORR_BANK_NAME_2);
        assertThat(testCreditor.getCorrBankAddress2()).isEqualTo(DEFAULT_CORR_BANK_ADDRESS_2);
    }

    @Test
    @Transactional
    public void createCreditorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditorRepository.findAll().size();

        // Create the Creditor with an existing ID
        creditor.setId(1L);
        CreditorDTO creditorDTO = creditorMapper.toDto(creditor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditorMockMvc.perform(post("/api/creditors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Creditor in the database
        List<Creditor> creditorList = creditorRepository.findAll();
        assertThat(creditorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCreditors() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList
        restCreditorMockMvc.perform(get("/api/creditors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditor.getId().intValue())))
            .andExpect(jsonPath("$.[*].creditorCode").value(hasItem(DEFAULT_CREDITOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].creditorName").value(hasItem(DEFAULT_CREDITOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].creditorAddress").value(hasItem(DEFAULT_CREDITOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].portName").value(hasItem(DEFAULT_PORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].swiftCode").value(hasItem(DEFAULT_SWIFT_CODE.toString())))
            .andExpect(jsonPath("$.[*].ibanCode").value(hasItem(DEFAULT_IBAN_CODE.toString())))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankAddress").value(hasItem(DEFAULT_BANK_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO.toString())))
            .andExpect(jsonPath("$.[*].corrBankName").value(hasItem(DEFAULT_CORR_BANK_NAME.toString())))
            .andExpect(jsonPath("$.[*].corrBankAddress").value(hasItem(DEFAULT_CORR_BANK_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].corrBankName2").value(hasItem(DEFAULT_CORR_BANK_NAME_2.toString())))
            .andExpect(jsonPath("$.[*].corrBankAddress2").value(hasItem(DEFAULT_CORR_BANK_ADDRESS_2.toString())));
    }

    @Test
    @Transactional
    public void getCreditor() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get the creditor
        restCreditorMockMvc.perform(get("/api/creditors/{id}", creditor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(creditor.getId().intValue()))
            .andExpect(jsonPath("$.creditorCode").value(DEFAULT_CREDITOR_CODE.toString()))
            .andExpect(jsonPath("$.creditorName").value(DEFAULT_CREDITOR_NAME.toString()))
            .andExpect(jsonPath("$.creditorAddress").value(DEFAULT_CREDITOR_ADDRESS.toString()))
            .andExpect(jsonPath("$.portName").value(DEFAULT_PORT_NAME.toString()))
            .andExpect(jsonPath("$.swiftCode").value(DEFAULT_SWIFT_CODE.toString()))
            .andExpect(jsonPath("$.ibanCode").value(DEFAULT_IBAN_CODE.toString()))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME.toString()))
            .andExpect(jsonPath("$.bankAddress").value(DEFAULT_BANK_ADDRESS.toString()))
            .andExpect(jsonPath("$.accountNo").value(DEFAULT_ACCOUNT_NO.toString()))
            .andExpect(jsonPath("$.corrBankName").value(DEFAULT_CORR_BANK_NAME.toString()))
            .andExpect(jsonPath("$.corrBankAddress").value(DEFAULT_CORR_BANK_ADDRESS.toString()))
            .andExpect(jsonPath("$.corrBankName2").value(DEFAULT_CORR_BANK_NAME_2.toString()))
            .andExpect(jsonPath("$.corrBankAddress2").value(DEFAULT_CORR_BANK_ADDRESS_2.toString()));
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorCode equals to DEFAULT_CREDITOR_CODE
        defaultCreditorShouldBeFound("creditorCode.equals=" + DEFAULT_CREDITOR_CODE);

        // Get all the creditorList where creditorCode equals to UPDATED_CREDITOR_CODE
        defaultCreditorShouldNotBeFound("creditorCode.equals=" + UPDATED_CREDITOR_CODE);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorCodeIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorCode in DEFAULT_CREDITOR_CODE or UPDATED_CREDITOR_CODE
        defaultCreditorShouldBeFound("creditorCode.in=" + DEFAULT_CREDITOR_CODE + "," + UPDATED_CREDITOR_CODE);

        // Get all the creditorList where creditorCode equals to UPDATED_CREDITOR_CODE
        defaultCreditorShouldNotBeFound("creditorCode.in=" + UPDATED_CREDITOR_CODE);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorCode is not null
        defaultCreditorShouldBeFound("creditorCode.specified=true");

        // Get all the creditorList where creditorCode is null
        defaultCreditorShouldNotBeFound("creditorCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorNameIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorName equals to DEFAULT_CREDITOR_NAME
        defaultCreditorShouldBeFound("creditorName.equals=" + DEFAULT_CREDITOR_NAME);

        // Get all the creditorList where creditorName equals to UPDATED_CREDITOR_NAME
        defaultCreditorShouldNotBeFound("creditorName.equals=" + UPDATED_CREDITOR_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorNameIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorName in DEFAULT_CREDITOR_NAME or UPDATED_CREDITOR_NAME
        defaultCreditorShouldBeFound("creditorName.in=" + DEFAULT_CREDITOR_NAME + "," + UPDATED_CREDITOR_NAME);

        // Get all the creditorList where creditorName equals to UPDATED_CREDITOR_NAME
        defaultCreditorShouldNotBeFound("creditorName.in=" + UPDATED_CREDITOR_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorName is not null
        defaultCreditorShouldBeFound("creditorName.specified=true");

        // Get all the creditorList where creditorName is null
        defaultCreditorShouldNotBeFound("creditorName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorAddress equals to DEFAULT_CREDITOR_ADDRESS
        defaultCreditorShouldBeFound("creditorAddress.equals=" + DEFAULT_CREDITOR_ADDRESS);

        // Get all the creditorList where creditorAddress equals to UPDATED_CREDITOR_ADDRESS
        defaultCreditorShouldNotBeFound("creditorAddress.equals=" + UPDATED_CREDITOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorAddressIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorAddress in DEFAULT_CREDITOR_ADDRESS or UPDATED_CREDITOR_ADDRESS
        defaultCreditorShouldBeFound("creditorAddress.in=" + DEFAULT_CREDITOR_ADDRESS + "," + UPDATED_CREDITOR_ADDRESS);

        // Get all the creditorList where creditorAddress equals to UPDATED_CREDITOR_ADDRESS
        defaultCreditorShouldNotBeFound("creditorAddress.in=" + UPDATED_CREDITOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCreditorAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where creditorAddress is not null
        defaultCreditorShouldBeFound("creditorAddress.specified=true");

        // Get all the creditorList where creditorAddress is null
        defaultCreditorShouldNotBeFound("creditorAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByPortNameIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where portName equals to DEFAULT_PORT_NAME
        defaultCreditorShouldBeFound("portName.equals=" + DEFAULT_PORT_NAME);

        // Get all the creditorList where portName equals to UPDATED_PORT_NAME
        defaultCreditorShouldNotBeFound("portName.equals=" + UPDATED_PORT_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByPortNameIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where portName in DEFAULT_PORT_NAME or UPDATED_PORT_NAME
        defaultCreditorShouldBeFound("portName.in=" + DEFAULT_PORT_NAME + "," + UPDATED_PORT_NAME);

        // Get all the creditorList where portName equals to UPDATED_PORT_NAME
        defaultCreditorShouldNotBeFound("portName.in=" + UPDATED_PORT_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByPortNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where portName is not null
        defaultCreditorShouldBeFound("portName.specified=true");

        // Get all the creditorList where portName is null
        defaultCreditorShouldNotBeFound("portName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsBySwiftCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where swiftCode equals to DEFAULT_SWIFT_CODE
        defaultCreditorShouldBeFound("swiftCode.equals=" + DEFAULT_SWIFT_CODE);

        // Get all the creditorList where swiftCode equals to UPDATED_SWIFT_CODE
        defaultCreditorShouldNotBeFound("swiftCode.equals=" + UPDATED_SWIFT_CODE);
    }

    @Test
    @Transactional
    public void getAllCreditorsBySwiftCodeIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where swiftCode in DEFAULT_SWIFT_CODE or UPDATED_SWIFT_CODE
        defaultCreditorShouldBeFound("swiftCode.in=" + DEFAULT_SWIFT_CODE + "," + UPDATED_SWIFT_CODE);

        // Get all the creditorList where swiftCode equals to UPDATED_SWIFT_CODE
        defaultCreditorShouldNotBeFound("swiftCode.in=" + UPDATED_SWIFT_CODE);
    }

    @Test
    @Transactional
    public void getAllCreditorsBySwiftCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where swiftCode is not null
        defaultCreditorShouldBeFound("swiftCode.specified=true");

        // Get all the creditorList where swiftCode is null
        defaultCreditorShouldNotBeFound("swiftCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByIbanCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where ibanCode equals to DEFAULT_IBAN_CODE
        defaultCreditorShouldBeFound("ibanCode.equals=" + DEFAULT_IBAN_CODE);

        // Get all the creditorList where ibanCode equals to UPDATED_IBAN_CODE
        defaultCreditorShouldNotBeFound("ibanCode.equals=" + UPDATED_IBAN_CODE);
    }

    @Test
    @Transactional
    public void getAllCreditorsByIbanCodeIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where ibanCode in DEFAULT_IBAN_CODE or UPDATED_IBAN_CODE
        defaultCreditorShouldBeFound("ibanCode.in=" + DEFAULT_IBAN_CODE + "," + UPDATED_IBAN_CODE);

        // Get all the creditorList where ibanCode equals to UPDATED_IBAN_CODE
        defaultCreditorShouldNotBeFound("ibanCode.in=" + UPDATED_IBAN_CODE);
    }

    @Test
    @Transactional
    public void getAllCreditorsByIbanCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where ibanCode is not null
        defaultCreditorShouldBeFound("ibanCode.specified=true");

        // Get all the creditorList where ibanCode is null
        defaultCreditorShouldNotBeFound("ibanCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByBankNameIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where bankName equals to DEFAULT_BANK_NAME
        defaultCreditorShouldBeFound("bankName.equals=" + DEFAULT_BANK_NAME);

        // Get all the creditorList where bankName equals to UPDATED_BANK_NAME
        defaultCreditorShouldNotBeFound("bankName.equals=" + UPDATED_BANK_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByBankNameIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where bankName in DEFAULT_BANK_NAME or UPDATED_BANK_NAME
        defaultCreditorShouldBeFound("bankName.in=" + DEFAULT_BANK_NAME + "," + UPDATED_BANK_NAME);

        // Get all the creditorList where bankName equals to UPDATED_BANK_NAME
        defaultCreditorShouldNotBeFound("bankName.in=" + UPDATED_BANK_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByBankNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where bankName is not null
        defaultCreditorShouldBeFound("bankName.specified=true");

        // Get all the creditorList where bankName is null
        defaultCreditorShouldNotBeFound("bankName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByBankAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where bankAddress equals to DEFAULT_BANK_ADDRESS
        defaultCreditorShouldBeFound("bankAddress.equals=" + DEFAULT_BANK_ADDRESS);

        // Get all the creditorList where bankAddress equals to UPDATED_BANK_ADDRESS
        defaultCreditorShouldNotBeFound("bankAddress.equals=" + UPDATED_BANK_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCreditorsByBankAddressIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where bankAddress in DEFAULT_BANK_ADDRESS or UPDATED_BANK_ADDRESS
        defaultCreditorShouldBeFound("bankAddress.in=" + DEFAULT_BANK_ADDRESS + "," + UPDATED_BANK_ADDRESS);

        // Get all the creditorList where bankAddress equals to UPDATED_BANK_ADDRESS
        defaultCreditorShouldNotBeFound("bankAddress.in=" + UPDATED_BANK_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCreditorsByBankAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where bankAddress is not null
        defaultCreditorShouldBeFound("bankAddress.specified=true");

        // Get all the creditorList where bankAddress is null
        defaultCreditorShouldNotBeFound("bankAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByAccountNoIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where accountNo equals to DEFAULT_ACCOUNT_NO
        defaultCreditorShouldBeFound("accountNo.equals=" + DEFAULT_ACCOUNT_NO);

        // Get all the creditorList where accountNo equals to UPDATED_ACCOUNT_NO
        defaultCreditorShouldNotBeFound("accountNo.equals=" + UPDATED_ACCOUNT_NO);
    }

    @Test
    @Transactional
    public void getAllCreditorsByAccountNoIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where accountNo in DEFAULT_ACCOUNT_NO or UPDATED_ACCOUNT_NO
        defaultCreditorShouldBeFound("accountNo.in=" + DEFAULT_ACCOUNT_NO + "," + UPDATED_ACCOUNT_NO);

        // Get all the creditorList where accountNo equals to UPDATED_ACCOUNT_NO
        defaultCreditorShouldNotBeFound("accountNo.in=" + UPDATED_ACCOUNT_NO);
    }

    @Test
    @Transactional
    public void getAllCreditorsByAccountNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where accountNo is not null
        defaultCreditorShouldBeFound("accountNo.specified=true");

        // Get all the creditorList where accountNo is null
        defaultCreditorShouldNotBeFound("accountNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankNameIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankName equals to DEFAULT_CORR_BANK_NAME
        defaultCreditorShouldBeFound("corrBankName.equals=" + DEFAULT_CORR_BANK_NAME);

        // Get all the creditorList where corrBankName equals to UPDATED_CORR_BANK_NAME
        defaultCreditorShouldNotBeFound("corrBankName.equals=" + UPDATED_CORR_BANK_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankNameIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankName in DEFAULT_CORR_BANK_NAME or UPDATED_CORR_BANK_NAME
        defaultCreditorShouldBeFound("corrBankName.in=" + DEFAULT_CORR_BANK_NAME + "," + UPDATED_CORR_BANK_NAME);

        // Get all the creditorList where corrBankName equals to UPDATED_CORR_BANK_NAME
        defaultCreditorShouldNotBeFound("corrBankName.in=" + UPDATED_CORR_BANK_NAME);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankName is not null
        defaultCreditorShouldBeFound("corrBankName.specified=true");

        // Get all the creditorList where corrBankName is null
        defaultCreditorShouldNotBeFound("corrBankName.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankAddress equals to DEFAULT_CORR_BANK_ADDRESS
        defaultCreditorShouldBeFound("corrBankAddress.equals=" + DEFAULT_CORR_BANK_ADDRESS);

        // Get all the creditorList where corrBankAddress equals to UPDATED_CORR_BANK_ADDRESS
        defaultCreditorShouldNotBeFound("corrBankAddress.equals=" + UPDATED_CORR_BANK_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankAddressIsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankAddress in DEFAULT_CORR_BANK_ADDRESS or UPDATED_CORR_BANK_ADDRESS
        defaultCreditorShouldBeFound("corrBankAddress.in=" + DEFAULT_CORR_BANK_ADDRESS + "," + UPDATED_CORR_BANK_ADDRESS);

        // Get all the creditorList where corrBankAddress equals to UPDATED_CORR_BANK_ADDRESS
        defaultCreditorShouldNotBeFound("corrBankAddress.in=" + UPDATED_CORR_BANK_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankAddress is not null
        defaultCreditorShouldBeFound("corrBankAddress.specified=true");

        // Get all the creditorList where corrBankAddress is null
        defaultCreditorShouldNotBeFound("corrBankAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankName2IsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankName2 equals to DEFAULT_CORR_BANK_NAME_2
        defaultCreditorShouldBeFound("corrBankName2.equals=" + DEFAULT_CORR_BANK_NAME_2);

        // Get all the creditorList where corrBankName2 equals to UPDATED_CORR_BANK_NAME_2
        defaultCreditorShouldNotBeFound("corrBankName2.equals=" + UPDATED_CORR_BANK_NAME_2);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankName2IsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankName2 in DEFAULT_CORR_BANK_NAME_2 or UPDATED_CORR_BANK_NAME_2
        defaultCreditorShouldBeFound("corrBankName2.in=" + DEFAULT_CORR_BANK_NAME_2 + "," + UPDATED_CORR_BANK_NAME_2);

        // Get all the creditorList where corrBankName2 equals to UPDATED_CORR_BANK_NAME_2
        defaultCreditorShouldNotBeFound("corrBankName2.in=" + UPDATED_CORR_BANK_NAME_2);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankName2IsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankName2 is not null
        defaultCreditorShouldBeFound("corrBankName2.specified=true");

        // Get all the creditorList where corrBankName2 is null
        defaultCreditorShouldNotBeFound("corrBankName2.specified=false");
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankAddress2IsEqualToSomething() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankAddress2 equals to DEFAULT_CORR_BANK_ADDRESS_2
        defaultCreditorShouldBeFound("corrBankAddress2.equals=" + DEFAULT_CORR_BANK_ADDRESS_2);

        // Get all the creditorList where corrBankAddress2 equals to UPDATED_CORR_BANK_ADDRESS_2
        defaultCreditorShouldNotBeFound("corrBankAddress2.equals=" + UPDATED_CORR_BANK_ADDRESS_2);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankAddress2IsInShouldWork() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankAddress2 in DEFAULT_CORR_BANK_ADDRESS_2 or UPDATED_CORR_BANK_ADDRESS_2
        defaultCreditorShouldBeFound("corrBankAddress2.in=" + DEFAULT_CORR_BANK_ADDRESS_2 + "," + UPDATED_CORR_BANK_ADDRESS_2);

        // Get all the creditorList where corrBankAddress2 equals to UPDATED_CORR_BANK_ADDRESS_2
        defaultCreditorShouldNotBeFound("corrBankAddress2.in=" + UPDATED_CORR_BANK_ADDRESS_2);
    }

    @Test
    @Transactional
    public void getAllCreditorsByCorrBankAddress2IsNullOrNotNull() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        // Get all the creditorList where corrBankAddress2 is not null
        defaultCreditorShouldBeFound("corrBankAddress2.specified=true");

        // Get all the creditorList where corrBankAddress2 is null
        defaultCreditorShouldNotBeFound("corrBankAddress2.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCreditorShouldBeFound(String filter) throws Exception {
        restCreditorMockMvc.perform(get("/api/creditors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditor.getId().intValue())))
            .andExpect(jsonPath("$.[*].creditorCode").value(hasItem(DEFAULT_CREDITOR_CODE)))
            .andExpect(jsonPath("$.[*].creditorName").value(hasItem(DEFAULT_CREDITOR_NAME)))
            .andExpect(jsonPath("$.[*].creditorAddress").value(hasItem(DEFAULT_CREDITOR_ADDRESS)))
            .andExpect(jsonPath("$.[*].portName").value(hasItem(DEFAULT_PORT_NAME)))
            .andExpect(jsonPath("$.[*].swiftCode").value(hasItem(DEFAULT_SWIFT_CODE)))
            .andExpect(jsonPath("$.[*].ibanCode").value(hasItem(DEFAULT_IBAN_CODE)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].bankAddress").value(hasItem(DEFAULT_BANK_ADDRESS)))
            .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].corrBankName").value(hasItem(DEFAULT_CORR_BANK_NAME)))
            .andExpect(jsonPath("$.[*].corrBankAddress").value(hasItem(DEFAULT_CORR_BANK_ADDRESS)))
            .andExpect(jsonPath("$.[*].corrBankName2").value(hasItem(DEFAULT_CORR_BANK_NAME_2)))
            .andExpect(jsonPath("$.[*].corrBankAddress2").value(hasItem(DEFAULT_CORR_BANK_ADDRESS_2)));

        // Check, that the count call also returns 1
        restCreditorMockMvc.perform(get("/api/creditors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCreditorShouldNotBeFound(String filter) throws Exception {
        restCreditorMockMvc.perform(get("/api/creditors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCreditorMockMvc.perform(get("/api/creditors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCreditor() throws Exception {
        // Get the creditor
        restCreditorMockMvc.perform(get("/api/creditors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditor() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        int databaseSizeBeforeUpdate = creditorRepository.findAll().size();

        // Update the creditor
        Creditor updatedCreditor = creditorRepository.findById(creditor.getId()).get();
        // Disconnect from session so that the updates on updatedCreditor are not directly saved in db
        em.detach(updatedCreditor);
        updatedCreditor
            .creditorCode(UPDATED_CREDITOR_CODE)
            .creditorName(UPDATED_CREDITOR_NAME)
            .creditorAddress(UPDATED_CREDITOR_ADDRESS)
            .portName(UPDATED_PORT_NAME)
            .swiftCode(UPDATED_SWIFT_CODE)
            .ibanCode(UPDATED_IBAN_CODE)
            .bankName(UPDATED_BANK_NAME)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .accountNo(UPDATED_ACCOUNT_NO)
            .corrBankName(UPDATED_CORR_BANK_NAME)
            .corrBankAddress(UPDATED_CORR_BANK_ADDRESS)
            .corrBankName2(UPDATED_CORR_BANK_NAME_2)
            .corrBankAddress2(UPDATED_CORR_BANK_ADDRESS_2);
        CreditorDTO creditorDTO = creditorMapper.toDto(updatedCreditor);

        restCreditorMockMvc.perform(put("/api/creditors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditorDTO)))
            .andExpect(status().isOk());

        // Validate the Creditor in the database
        List<Creditor> creditorList = creditorRepository.findAll();
        assertThat(creditorList).hasSize(databaseSizeBeforeUpdate);
        Creditor testCreditor = creditorList.get(creditorList.size() - 1);
        assertThat(testCreditor.getCreditorCode()).isEqualTo(UPDATED_CREDITOR_CODE);
        assertThat(testCreditor.getCreditorName()).isEqualTo(UPDATED_CREDITOR_NAME);
        assertThat(testCreditor.getCreditorAddress()).isEqualTo(UPDATED_CREDITOR_ADDRESS);
        assertThat(testCreditor.getPortName()).isEqualTo(UPDATED_PORT_NAME);
        assertThat(testCreditor.getSwiftCode()).isEqualTo(UPDATED_SWIFT_CODE);
        assertThat(testCreditor.getIbanCode()).isEqualTo(UPDATED_IBAN_CODE);
        assertThat(testCreditor.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testCreditor.getBankAddress()).isEqualTo(UPDATED_BANK_ADDRESS);
        assertThat(testCreditor.getAccountNo()).isEqualTo(UPDATED_ACCOUNT_NO);
        assertThat(testCreditor.getCorrBankName()).isEqualTo(UPDATED_CORR_BANK_NAME);
        assertThat(testCreditor.getCorrBankAddress()).isEqualTo(UPDATED_CORR_BANK_ADDRESS);
        assertThat(testCreditor.getCorrBankName2()).isEqualTo(UPDATED_CORR_BANK_NAME_2);
        assertThat(testCreditor.getCorrBankAddress2()).isEqualTo(UPDATED_CORR_BANK_ADDRESS_2);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditor() throws Exception {
        int databaseSizeBeforeUpdate = creditorRepository.findAll().size();

        // Create the Creditor
        CreditorDTO creditorDTO = creditorMapper.toDto(creditor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditorMockMvc.perform(put("/api/creditors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Creditor in the database
        List<Creditor> creditorList = creditorRepository.findAll();
        assertThat(creditorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCreditor() throws Exception {
        // Initialize the database
        creditorRepository.saveAndFlush(creditor);

        int databaseSizeBeforeDelete = creditorRepository.findAll().size();

        // Delete the creditor
        restCreditorMockMvc.perform(delete("/api/creditors/{id}", creditor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Creditor> creditorList = creditorRepository.findAll();
        assertThat(creditorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Creditor.class);
        Creditor creditor1 = new Creditor();
        creditor1.setId(1L);
        Creditor creditor2 = new Creditor();
        creditor2.setId(creditor1.getId());
        assertThat(creditor1).isEqualTo(creditor2);
        creditor2.setId(2L);
        assertThat(creditor1).isNotEqualTo(creditor2);
        creditor1.setId(null);
        assertThat(creditor1).isNotEqualTo(creditor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditorDTO.class);
        CreditorDTO creditorDTO1 = new CreditorDTO();
        creditorDTO1.setId(1L);
        CreditorDTO creditorDTO2 = new CreditorDTO();
        assertThat(creditorDTO1).isNotEqualTo(creditorDTO2);
        creditorDTO2.setId(creditorDTO1.getId());
        assertThat(creditorDTO1).isEqualTo(creditorDTO2);
        creditorDTO2.setId(2L);
        assertThat(creditorDTO1).isNotEqualTo(creditorDTO2);
        creditorDTO1.setId(null);
        assertThat(creditorDTO1).isNotEqualTo(creditorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(creditorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(creditorMapper.fromId(null)).isNull();
    }
}
