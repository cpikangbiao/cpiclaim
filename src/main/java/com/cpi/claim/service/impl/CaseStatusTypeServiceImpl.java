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

package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseStatusTypeService;
import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.repository.CaseStatusTypeRepository;
import com.cpi.claim.service.dto.CaseStatusTypeDTO;
import com.cpi.claim.service.mapper.CaseStatusTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseStatusType.
 */
@Service
@Transactional
public class CaseStatusTypeServiceImpl implements CaseStatusTypeService {

    private final Logger log = LoggerFactory.getLogger(CaseStatusTypeServiceImpl.class);

    private final CaseStatusTypeRepository caseStatusTypeRepository;

    private final CaseStatusTypeMapper caseStatusTypeMapper;

    public CaseStatusTypeServiceImpl(CaseStatusTypeRepository caseStatusTypeRepository, CaseStatusTypeMapper caseStatusTypeMapper) {
        this.caseStatusTypeRepository = caseStatusTypeRepository;
        this.caseStatusTypeMapper = caseStatusTypeMapper;
    }

    /**
     * Save a caseStatusType.
     *
     * @param caseStatusTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseStatusTypeDTO save(CaseStatusTypeDTO caseStatusTypeDTO) {
        log.debug("Request to save CaseStatusType : {}", caseStatusTypeDTO);
        CaseStatusType caseStatusType = caseStatusTypeMapper.toEntity(caseStatusTypeDTO);
        caseStatusType = caseStatusTypeRepository.save(caseStatusType);
        return caseStatusTypeMapper.toDto(caseStatusType);
    }

    /**
     * Get all the caseStatusTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseStatusTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseStatusTypes");
        return caseStatusTypeRepository.findAll(pageable)
            .map(caseStatusTypeMapper::toDto);
    }


    /**
     * Get one caseStatusType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseStatusTypeDTO> findOne(Long id) {
        log.debug("Request to get CaseStatusType : {}", id);
        return caseStatusTypeRepository.findById(id)
            .map(caseStatusTypeMapper::toDto);
    }

    /**
     * Delete the caseStatusType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseStatusType : {}", id);
        caseStatusTypeRepository.deleteById(id);
    }
}
