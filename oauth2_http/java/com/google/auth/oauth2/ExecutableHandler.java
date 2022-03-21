/*
 * Copyright 2022 Google LLC
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google LLC nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.google.auth.oauth2;

import java.io.IOException;
import java.util.Map;
import javax.annotation.Nullable;

/** Interface for 3rd party executable handling. */
interface ExecutableHandler {

  /** Interface for required fields needed to call 3rd party executables. */
  interface ExecutableOptions {

    /** @return The full command (absolute path) used to retrieve 3rd party tokens. */
    String getExecutableCommand();

    /**
     * @return The set of process-local environment variable mappings to be set for the script to
     *     execute.
     */
    Map<String, String> getEnvironmentMap();

    /** @return The timeout for waiting for the executable to finish, in milliseconds. */
    int getExecutableTimeoutMs();

    /**
     * @return The output file path which points to the 3rd party credentials generated by the
     *     executable.
     */
    @Nullable
    String getOutputFilePath();
  }

  /**
   * Handles executing the 3rd party script and parsing the token from the response.
   *
   * @param options The set executable options for handling the executable.
   * @return The 3rd party token.
   */
  String retrieveTokenFromExecutable(ExecutableOptions options) throws IOException;
}