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

package com.cpi.claim.security.oauth2;

import org.springframework.security.jwt.crypto.sign.SignatureVerifier;

/**
 * Abstracts how to create a {@link SignatureVerifier} to verify JWT tokens with a public key.
 * Implementations will have to contact the OAuth2 authorization server to fetch the public key
 * and use it to build a {@link SignatureVerifier} in a server specific way.
 *
 * @see UaaSignatureVerifierClient
 */
public interface OAuth2SignatureVerifierClient {
    /**
     * Returns the {@link SignatureVerifier} used to verify JWT tokens.
     * Fetches the public key from the Authorization server to create
     * this verifier.
     *
     * @return the new verifier used to verify JWT signatures.
     * Will be null if we cannot contact the token endpoint.
     * @throws Exception if we could not create a {@link SignatureVerifier} or contact the token endpoint.
     */
    SignatureVerifier getSignatureVerifier() throws Exception;
}
