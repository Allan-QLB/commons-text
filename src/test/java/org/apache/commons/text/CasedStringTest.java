/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.text;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasedStringTest {

    private static String expectedValue(CasedString.StringCase stringCase) {
        switch (stringCase) {
            case Camel:
                return "HelloWorld";
            case Kebab:
                return "Hello-World";
            case Phrase:
                return "Hello World";
            case Snake:
                return "Hello_World";
            case Dot:
                return "Hello.World";
            default:
                throw new RuntimeException("Unsupported StringCase: " + stringCase);
        }
    }

    @ParameterizedTest
    @MethodSource("conversionProvider")
    public void testConversions(CasedString underTest) {
        for (CasedString.StringCase stringCase : CasedString.StringCase.values()) {
            assertEquals(expectedValue(stringCase), underTest.toCase(stringCase), () -> "failed converting to " + stringCase);
        }
    }

    private static Stream<Arguments> conversionProvider() {
        List<Arguments> lst = new ArrayList<>();
        for (CasedString.StringCase stringCase : CasedString.StringCase.values()) {
            lst.add(Arguments.of(new CasedString(stringCase, expectedValue(stringCase))));
        }
        return lst.stream();
    }
}
