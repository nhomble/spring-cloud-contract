/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.loan;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

// tag::autoconfigure_stubrunner[]
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {
		"com.example:http-server-dsl"},
		repositoryRoot = "stubs://classpath:contractsAtRuntime/",
		stubsMode = StubRunnerProperties.StubsMode.LOCAL,
		generateStubs = true)
public class GoodbyeWorldTests {
	// end::autoconfigure_stubrunner[]

	@StubRunnerPort("http-server-dsl")
	int port;

	@Test
	public void shouldGenerateStubsAtRuntime() {
		// when:
		String response = new RestTemplate().getForObject("http://localhost:" + this.port + "/goodbye", String.class);
		// then:
		assertThat(response)
				.isEqualTo("Goodbye World!");
	}

}
