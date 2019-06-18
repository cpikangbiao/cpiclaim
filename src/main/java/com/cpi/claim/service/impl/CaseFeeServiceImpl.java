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

import com.cpi.claim.service.CaseFeeService;
import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.repository.CaseFeeRepository;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.mapper.CaseFeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseFee}.
 */
@Service
@Transactional
public class CaseFeeServiceImpl implements CaseFeeService {

    private final Logger log = LoggerFactory.getLogger(CaseFeeServiceImpl.class);

    private final CaseFeeRepository caseFeeRepository;

    private final CaseFeeMapper caseFeeMapper;

    public CaseFeeServiceImpl(CaseFeeRepository caseFeeRepository, CaseFeeMapper caseFeeMapper) {
        this.caseFeeRepository = caseFeeRepository;
        this.caseFeeMapper = caseFeeMapper;
    }

    /**
     * Save a caseFee.
     *
     * @param caseFeeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseFeeDTO save(CaseFeeDTO caseFeeDTO) {
        log.debug("Request to save CaseFee : {}", caseFeeDTO);
        CaseFee caseFee = caseFeeMapper.toEntity(caseFeeDTO);
        caseFee = caseFeeRepository.save(caseFee);
        return caseFeeMapper.toDto(caseFee);
    }

    /**
     * Get all the caseFees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseFeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseFees");
        return caseFeeRepository.findAll(pageable)
            .map(caseFeeMapper::toDto);
    }


    /**
     * Get one caseFee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseFeeDTO> findOne(Long id) {
        log.debug("Request to get CaseFee : {}", id);
        return caseFeeRepository.findById(id)
            .map(caseFeeMapper::toDto);
    }

    /**
     * Delete the caseFee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseFee : {}", id);
        caseFeeRepository.deleteById(id);
    }
}
