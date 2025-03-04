/*
 * Copyright (c) Microsoft Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.playwright;

import java.nio.file.Path;
import java.util.*;

/**
 * Whenever a network route is set up with {@link Page#route Page.route()} or {@link BrowserContext#route
 * BrowserContext.route()}, the {@code Route} object allows to handle the route.
 *
 * <p> Learn more about <a href="https://playwright.dev/java/docs/network">networking</a>.
 */
public interface Route {
  class ResumeOptions {
    /**
     * If set changes the request HTTP headers. Header values will be converted to a string.
     */
    public Map<String, String> headers;
    /**
     * If set changes the request method (e.g. GET or POST)
     */
    public String method;
    /**
     * If set changes the post data of request
     */
    public Object postData;
    /**
     * If set changes the request URL. New URL must have same protocol as original one.
     */
    public String url;

    /**
     * If set changes the request HTTP headers. Header values will be converted to a string.
     */
    public ResumeOptions setHeaders(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }
    /**
     * If set changes the request method (e.g. GET or POST)
     */
    public ResumeOptions setMethod(String method) {
      this.method = method;
      return this;
    }
    /**
     * If set changes the post data of request
     */
    public ResumeOptions setPostData(String postData) {
      this.postData = postData;
      return this;
    }
    /**
     * If set changes the post data of request
     */
    public ResumeOptions setPostData(byte[] postData) {
      this.postData = postData;
      return this;
    }
    /**
     * If set changes the request URL. New URL must have same protocol as original one.
     */
    public ResumeOptions setUrl(String url) {
      this.url = url;
      return this;
    }
  }
  class FulfillOptions {
    /**
     * Optional response body as text.
     */
    public String body;
    /**
     * Optional response body as raw bytes.
     */
    public byte[] bodyBytes;
    /**
     * If set, equals to setting {@code Content-Type} response header.
     */
    public String contentType;
    /**
     * Response headers. Header values will be converted to a string.
     */
    public Map<String, String> headers;
    /**
     * File path to respond with. The content type will be inferred from file extension. If {@code path} is a relative path, then it
     * is resolved relative to the current working directory.
     */
    public Path path;
    /**
     * {@code APIResponse} to fulfill route's request with. Individual fields of the response (such as headers) can be overridden
     * using fulfill options.
     */
    public APIResponse response;
    /**
     * Response status code, defaults to {@code 200}.
     */
    public Integer status;

    /**
     * Optional response body as text.
     */
    public FulfillOptions setBody(String body) {
      this.body = body;
      return this;
    }
    /**
     * Optional response body as raw bytes.
     */
    public FulfillOptions setBodyBytes(byte[] bodyBytes) {
      this.bodyBytes = bodyBytes;
      return this;
    }
    /**
     * If set, equals to setting {@code Content-Type} response header.
     */
    public FulfillOptions setContentType(String contentType) {
      this.contentType = contentType;
      return this;
    }
    /**
     * Response headers. Header values will be converted to a string.
     */
    public FulfillOptions setHeaders(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }
    /**
     * File path to respond with. The content type will be inferred from file extension. If {@code path} is a relative path, then it
     * is resolved relative to the current working directory.
     */
    public FulfillOptions setPath(Path path) {
      this.path = path;
      return this;
    }
    /**
     * {@code APIResponse} to fulfill route's request with. Individual fields of the response (such as headers) can be overridden
     * using fulfill options.
     */
    public FulfillOptions setResponse(APIResponse response) {
      this.response = response;
      return this;
    }
    /**
     * Response status code, defaults to {@code 200}.
     */
    public FulfillOptions setStatus(int status) {
      this.status = status;
      return this;
    }
  }
  /**
   * Aborts the route's request.
   */
  default void abort() {
    abort(null);
  }
  /**
   * Aborts the route's request.
   *
   * @param errorCode Optional error code. Defaults to {@code failed}, could be one of the following:
   * <ul>
   * <li> {@code "aborted"} - An operation was aborted (due to user action)</li>
   * <li> {@code "accessdenied"} - Permission to access a resource, other than the network, was denied</li>
   * <li> {@code "addressunreachable"} - The IP address is unreachable. This usually means that there is no route to the specified host
   * or network.</li>
   * <li> {@code "blockedbyclient"} - The client chose to block the request.</li>
   * <li> {@code "blockedbyresponse"} - The request failed because the response was delivered along with requirements which are not met
   * ('X-Frame-Options' and 'Content-Security-Policy' ancestor checks, for instance).</li>
   * <li> {@code "connectionaborted"} - A connection timed out as a result of not receiving an ACK for data sent.</li>
   * <li> {@code "connectionclosed"} - A connection was closed (corresponding to a TCP FIN).</li>
   * <li> {@code "connectionfailed"} - A connection attempt failed.</li>
   * <li> {@code "connectionrefused"} - A connection attempt was refused.</li>
   * <li> {@code "connectionreset"} - A connection was reset (corresponding to a TCP RST).</li>
   * <li> {@code "internetdisconnected"} - The Internet connection has been lost.</li>
   * <li> {@code "namenotresolved"} - The host name could not be resolved.</li>
   * <li> {@code "timedout"} - An operation timed out.</li>
   * <li> {@code "failed"} - A generic failure occurred.</li>
   * </ul>
   */
  void abort(String errorCode);
  /**
   * Continues route's request with optional overrides.
   * <pre>{@code
   * page.route("**\/*", route -> {
   *   // Override headers
   *   Map<String, String> headers = new HashMap<>(route.request().headers());
   *   headers.put("foo", "bar"); // set "foo" header
   *   headers.remove("origin"); // remove "origin" header
   *   route.resume(new Route.ResumeOptions().setHeaders(headers));
   * });
   * }</pre>
   */
  default void resume() {
    resume(null);
  }
  /**
   * Continues route's request with optional overrides.
   * <pre>{@code
   * page.route("**\/*", route -> {
   *   // Override headers
   *   Map<String, String> headers = new HashMap<>(route.request().headers());
   *   headers.put("foo", "bar"); // set "foo" header
   *   headers.remove("origin"); // remove "origin" header
   *   route.resume(new Route.ResumeOptions().setHeaders(headers));
   * });
   * }</pre>
   */
  void resume(ResumeOptions options);
  /**
   * Fulfills route's request with given response.
   *
   * <p> An example of fulfilling all requests with 404 responses:
   * <pre>{@code
   * page.route("**\/*", route -> {
   *   route.fulfill(new Route.FulfillOptions()
   *     .setStatus(404)
   *     .setContentType("text/plain")
   *     .setBody("Not Found!"));
   * });
   * }</pre>
   *
   * <p> An example of serving static file:
   * <pre>{@code
   * page.route("**\/xhr_endpoint", route -> route.fulfill(
   *   new Route.FulfillOptions().setPath(Paths.get("mock_data.json"))));
   * }</pre>
   */
  default void fulfill() {
    fulfill(null);
  }
  /**
   * Fulfills route's request with given response.
   *
   * <p> An example of fulfilling all requests with 404 responses:
   * <pre>{@code
   * page.route("**\/*", route -> {
   *   route.fulfill(new Route.FulfillOptions()
   *     .setStatus(404)
   *     .setContentType("text/plain")
   *     .setBody("Not Found!"));
   * });
   * }</pre>
   *
   * <p> An example of serving static file:
   * <pre>{@code
   * page.route("**\/xhr_endpoint", route -> route.fulfill(
   *   new Route.FulfillOptions().setPath(Paths.get("mock_data.json"))));
   * }</pre>
   */
  void fulfill(FulfillOptions options);
  /**
   * A request to be routed.
   */
  Request request();
}

