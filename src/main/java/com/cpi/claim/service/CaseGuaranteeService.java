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

package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseGuaranteeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseGuarantee.
 */
public interface CaseGuaranteeService {

    /**
     * Save a caseGuarantee.
     *
     * @param caseGuaranteeDTO the entity to save
     * @return the persisted entity
     */
    CaseGuaranteeDTO save(CaseGuaranteeDTO caseGuaranteeDTO);

    /**
     * Get all the caseGuarantees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseGuaranteeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseGuarantee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseGuaranteeDTO> findOne(Long id);

    /**
     * Delete the "id" caseGuarantee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
